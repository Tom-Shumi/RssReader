package RssReader.service.input;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.factory.InputServiceFactory;
import com.rometools.rome.io.FeedException;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.List;

public interface InputService {

    List<Article> inputArticle(String input) throws IOException, FeedException;

    static List<Article> input(Argument argument) throws IOException, FeedException {
        return InputServiceFactory.create(argument).inputArticle(argument.getInput());
    }
}
