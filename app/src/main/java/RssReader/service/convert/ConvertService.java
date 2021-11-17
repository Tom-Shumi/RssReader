package RssReader.service.convert;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.factory.ConvertServiceFactory;

import java.util.List;

public interface ConvertService {

    List<Article> convertArticle(List<Article> articleList);

    static List<Article> convert(List<Article> articleList, Argument argument) {
        List<ConvertService> convertServiceList = ConvertServiceFactory.create(argument);

        for (ConvertService convertService: convertServiceList) {
            articleList = convertService.convertArticle(articleList);
        }

        return articleList;
    }
}
