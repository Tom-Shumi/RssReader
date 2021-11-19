package RssReader.service.input.impl;

import RssReader.constant.FileContentEnum;
import RssReader.exception.InputArticleException;
import RssReader.service.input.InputService;
import RssReader.domain.Article;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static RssReader.constant.Constants.FILE_CONTENT_PREFIX_DELIMITER;
import static RssReader.constant.ErrorMessage.*;

/**
 * 取り込み処理用（ファイル）のサービス
 */
public class FileInputService implements InputService {

    /**
     * ${inheritDoc}
     */
    @Override
    public List<Article> inputArticle(String input) {

        try {

            List<String> fileTextList = Files.readAllLines(Paths.get(input));
            return createArticleList(fileTextList);

        } catch (IOException e) {
            throw new InputArticleException(INVALID_INPUT_ARGUMENT);
        }
    }

    private List<Article> createArticleList(List<String> fileTextList) {
        List<Article> articleList = new ArrayList<>();

        // 3行(title,body,空行)ずつ取り込みするため。[i = i + 3]
        for (int i = 0; i < fileTextList.size(); i = i + 3) {
            articleList.add(Article.builder()
                    .title(getContent(fileTextList.get(i), FileContentEnum.TITLE))
                    .body(getContent(fileTextList.get(i + 1), FileContentEnum.BODY))
                    .build());

            if (checkBlankLine(fileTextList, i)) {
                throw new InputArticleException(INVALID_INPUT_FORMAT);
            }
        }
        return articleList;
    }

    private String getContent(String fileText, FileContentEnum fileContent) {
        String[] contentArray = fileText.split(FILE_CONTENT_PREFIX_DELIMITER, 2); // コンテンツ中の「:」までsplitしないため

        if (contentArray.length == 1) {
            throw new InputArticleException(INVALID_INPUT_DELIMITER);
        } else if (!fileContent.toLowerCaseString().equals(contentArray[0])) {
            throw new InputArticleException(INVALID_INPUT_ITEM_NAME);
        }

        return contentArray[1].trim();
    }

    private boolean checkBlankLine(List<String> fileTextList, int i) {
        // 最終行に空行が無い可能性があるため。[fileTextList.size() > i + 2]
        return fileTextList.size() > i + 2 && StringUtils.isNotEmpty(fileTextList.get(i + 2));
    }
}
