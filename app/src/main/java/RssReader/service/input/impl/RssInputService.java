package RssReader.service.input.impl;

import RssReader.exception.InputArticleException;
import RssReader.service.input.InputService;
import RssReader.domain.Article;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static RssReader.constant.ErrorMessage.INVALID_INPUT_URL;

public class RssInputService implements InputService {

    @Override
    public List<Article> inputArticle(String input) {

        List<Article> articleList;

        try (XmlReader reader = new XmlReader(new URL(input))) {
            articleList = fetchRssFeed(reader);
        } catch (Exception e) {
            throw new InputArticleException(INVALID_INPUT_URL);
        }

        return articleList;
    }

    private List<Article> fetchRssFeed(XmlReader reader) throws FeedException {
        SyndFeed feeder = new SyndFeedInput().build(reader);
        List<SyndEntry> entryList = feeder.getEntries();

        return entryList.stream().map(this::toArticle).collect(Collectors.toList());
    }

    private Article toArticle(SyndEntry syndEntry) {
        return Article.builder()
                .title(toEmpty(syndEntry.getTitle()))
                .body(toEmpty(syndEntry.getDescription().getValue()))
                .build();
    }

    private String toEmpty(String s) {
        return Objects.isNull(s) ? "" : s;
    }
}
