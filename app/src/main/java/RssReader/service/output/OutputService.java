package RssReader.service.output;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.domain.OutputArticle;
import RssReader.factory.OutputServiceFactory;

import java.io.IOException;
import java.util.List;

public interface OutputService {

    void outputArticle(OutputArticle outputArticle) throws IOException;

    static void output(List<Article> articleList, Argument argument) throws IOException {
        OutputServiceFactory.create(argument)
                .outputArticle(OutputArticle.builder().articleList(articleList).output(argument.getOutput()).build());
    }
}
