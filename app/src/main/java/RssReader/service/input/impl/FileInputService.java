package RssReader.service.input.impl;

import RssReader.constant.FileContentEnum;
import RssReader.service.input.InputService;
import RssReader.domain.Article;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static RssReader.constant.Constants.FILE_CONTENT_PREFIX_DELIMITER;

public class FileInputService implements InputService {

    @Override
    public List<Article> inputArticle(String input) throws IOException {

        List<String> fileTextList = Files.readAllLines(Paths.get(input));

        return createArticleList(fileTextList);
    }

    private List<Article> createArticleList(List<String> fileTextList) {
        List<Article> articleList = new ArrayList<>();

        for (int i = 0; i < fileTextList.size(); i = i + 3) {
            articleList.add(Article.builder()
                    .title(getContent(fileTextList.get(i), FileContentEnum.TITLE))
                    .body(getContent(fileTextList.get(i + 1), FileContentEnum.BODY))
                    .build());

            if (checkBlankLine(fileTextList, i)) {
                throw new IllegalArgumentException();
            }
        }
        return articleList;
    }

    private String getContent(String fileText, FileContentEnum fileContent) {
        String[] contentArray = fileText.split(FILE_CONTENT_PREFIX_DELIMITER, 2); // コンテンツ中の「:」までsplitしないため

        if (contentArray.length == 0) {
            throw new IllegalArgumentException();
        } else if (!fileContent.toLowerCaseString().equals(contentArray[0])) {
            throw new IllegalArgumentException();
        }

        return contentArray[1].trim();
    }

    private boolean checkBlankLine(List<String> fileTextList, int i) {
        // 最終行に空行が無い可能性があるため。[fileTextList.size() > i + 2]
        return fileTextList.size() > i + 2 && StringUtils.isNotEmpty(fileTextList.get(i + 2));
    }
}
