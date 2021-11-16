package RssReader.service.convert;

import RssReader.domain.Article;

import java.util.List;

public interface ConvertService {

    List<Article> convert(List<Article> articleList);
}
