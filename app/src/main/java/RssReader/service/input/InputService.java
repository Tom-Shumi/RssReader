package RssReader.service.input;

import RssReader.domain.Article;
import com.rometools.rome.io.FeedException;

import java.io.IOException;
import java.util.List;

public interface InputService {

    List<Article> inputArticle(String input) throws IOException, FeedException;
}
