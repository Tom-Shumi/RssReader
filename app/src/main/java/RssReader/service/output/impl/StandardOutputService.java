package RssReader.service.output.impl;

import RssReader.constant.FileContentEnum;
import RssReader.domain.Article;
import RssReader.domain.OutputArticle;
import RssReader.service.output.OutputService;

import static RssReader.constant.Constants.OUTPUT_FORMAT;

/**
 * 出力処理用（標準出力）のサービス
 */
public class StandardOutputService implements OutputService {

    /**
     * ${inheritDoc}
     */
    @Override
    public void outputArticle(OutputArticle outputArticle) {
        for (Article article : outputArticle.getArticleList()) {
            outputStandard(article);
        }
    }

    private void outputStandard(Article article) {
        System.out.println(String.format(OUTPUT_FORMAT, FileContentEnum.TITLE.toLowerCaseString(), article.getTitle()));
        System.out.println(String.format(OUTPUT_FORMAT, FileContentEnum.BODY.toLowerCaseString(), article.getBody()));
        System.out.println("");
    }
}
