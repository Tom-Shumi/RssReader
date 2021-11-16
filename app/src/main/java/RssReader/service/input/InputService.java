package RssReader.service.input;

import RssReader.domain.Article;

import java.util.List;

public interface InputService {

    List<Article> inputArticle(String input);
}
