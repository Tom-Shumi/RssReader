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

    private static final int ARTICLE_ROW_NUMBER = 3;
    private static final int BODY_ROW_INDEX = 1;
    private static final int BLANK_ROW_INDEX = 2;
    private static final int ITEM_NAME_COLUMN_INDEX = 0;
    private static final int CONTENT_COLUMN_INDEX = 1;
    private static final int SPLIT_LIMIT = 2;

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

        // 3行(title,body,空行)ずつ取り込みするため。[i = i + ARTICLE_ROW_NUMBER]
        for (int i = 0; i < fileTextList.size(); i = i + ARTICLE_ROW_NUMBER) {
            articleList.add(Article.builder()
                    .title(getContent(fileTextList.get(i), FileContentEnum.TITLE))
                    .body(getContent(fileTextList.get(i + BODY_ROW_INDEX), FileContentEnum.BODY))
                    .build());

            if (checkBlankLine(fileTextList, i)) {
                throw new InputArticleException(INVALID_INPUT_FORMAT);
            }
        }
        return articleList;
    }

    private String getContent(String fileText, FileContentEnum fileContent) {
        String[] contentArray = fileText.split(FILE_CONTENT_PREFIX_DELIMITER, SPLIT_LIMIT); // コンテンツ中の「:」までsplitしないため

        if (contentArray.length == 1) {
            // splitできなかった場合
            throw new InputArticleException(INVALID_INPUT_DELIMITER);
        } else if (!fileContent.toLowerCaseString().equals(contentArray[ITEM_NAME_COLUMN_INDEX])) {
            throw new InputArticleException(INVALID_INPUT_ITEM_NAME);
        }

        return contentArray[CONTENT_COLUMN_INDEX].trim();
    }

    private boolean checkBlankLine(List<String> fileTextList, int i) {
        // 最終行に空行が無い可能性があるため。[fileTextList.size() > i + BLANK_ROW_INDEX]
        return fileTextList.size() > i + BLANK_ROW_INDEX && StringUtils.isNotEmpty(fileTextList.get(i + BLANK_ROW_INDEX));
    }
}
