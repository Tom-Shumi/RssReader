package RssReader.service.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.ConvertService;

import java.util.List;

import static RssReader.constant.Constants.CUT_BODY_LENGTH;
import static RssReader.constant.Constants.CUT_TITLE_LENGTH;

public class CutConvertService implements ConvertService {

    @Override
    public List<Article> convert(List<Article> articleList) {
        articleList.forEach(this::cut);
        return articleList;
    }

    private void cut(Article article) {
        article.setTitle(article.getTitle().substring(0, CUT_TITLE_LENGTH));
        article.setBody(article.getBody().substring(0, CUT_BODY_LENGTH));
    }
}
