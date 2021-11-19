package RssReader.service.output.impl;

import RssReader.constant.FileContentEnum;
import RssReader.domain.Article;
import RssReader.domain.OutputArticle;
import RssReader.exception.OutputArticleException;
import RssReader.service.output.OutputService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static RssReader.constant.Constants.OUTPUT_FORMAT;
import static RssReader.constant.ErrorMessage.INVALID_OUTPUT_FILE;

/**
 * 出力処理用（ファイル）のサービス
 */
public class FileOutputService implements OutputService {

    /**
     * ${inheritDoc}
     */
    @Override
    public void outputArticle(OutputArticle outputArticle) {
        try (BufferedWriter out = Files.newBufferedWriter(Paths.get(outputArticle.getOutput()), StandardCharsets.UTF_8)) {
            for (Article article : outputArticle.getArticleList()) {
                outputFile(out, article);
            }
        } catch (IOException e) {
            throw new OutputArticleException(INVALID_OUTPUT_FILE);
        }
    }

    private void outputFile(BufferedWriter out, Article article) throws IOException {
        writeAndNewLine(out, String.format(OUTPUT_FORMAT, FileContentEnum.TITLE.toLowerCaseString(), article.getTitle()));
        writeAndNewLine(out, String.format(OUTPUT_FORMAT, FileContentEnum.BODY.toLowerCaseString(), article.getBody()));
        out.newLine();
    }

    private void writeAndNewLine(BufferedWriter out, String content) throws IOException {
        out.write(content);
        out.newLine();
    }
}
