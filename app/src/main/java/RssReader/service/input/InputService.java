package RssReader.service.input;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.factory.InputServiceFactory;

import java.util.List;

public interface InputService {

    List<Article> inputArticle(String input);

    static List<Article> input(Argument argument) {
        return InputServiceFactory.create(argument).inputArticle(argument.getInput());
    }
}
