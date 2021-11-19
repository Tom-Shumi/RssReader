package RssReader.service.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.ConvertService;

import java.util.List;

import static RssReader.constant.Constants.CUT_BODY_LENGTH;
import static RssReader.constant.Constants.CUT_TITLE_LENGTH;

/**
 * 変換処理用（文字列カット）のサービス
 */
public class CutConvertService implements ConvertService {

    /**
     * ${inheritDoc}
     */
    @Override
    public List<Article> convertArticle(List<Article> articleList) {
        articleList.forEach(this::cut);
        return articleList;
    }

    private void cut(Article article) {
        article.setTitle(article.getTitle().substring(0, minLength(CUT_TITLE_LENGTH, article.getTitle())));
        article.setBody(article.getBody().substring(0, minLength(CUT_BODY_LENGTH, article.getBody())));
    }

    private int minLength(int cutLength, String content) {
        return Math.min(cutLength, content.length());
    }
}
