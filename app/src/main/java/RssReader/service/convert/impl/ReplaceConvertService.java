package RssReader.service.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.ConvertService;

import java.util.List;

import static RssReader.constant.Constants.*;

/**
 * 変換処理用（文字列置換）のサービス
 */
public class ReplaceConvertService implements ConvertService {

    /**
     * ${inheritDoc}
     */
    @Override
    public List<Article> convertArticle(List<Article> articleList) {
        articleList.forEach(this::replace);
        return articleList;
    }

    private void replace(Article article) {
        article.setBody(article.getBody().replaceAll(REPLACE_FROM_WORD, REPLACE_TO_WORD));
    }
}
