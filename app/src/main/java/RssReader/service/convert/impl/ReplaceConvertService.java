package RssReader.service.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.ConvertService;

import java.util.List;

import static RssReader.constant.Constants.*;

public class ReplaceConvertService implements ConvertService {

    @Override
    public List<Article> convert(List<Article> articleList) {
        articleList.forEach(this::replace);
        return articleList;
    }

    private void replace(Article article) {
        article.setBody(article.getBody().replaceAll(REPLACE_FROM_WORD, REPLACE_TO_WORD));
    }
}
