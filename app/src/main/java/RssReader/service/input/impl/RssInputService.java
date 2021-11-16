package RssReader.service.input.impl;

import RssReader.service.input.InputService;
import RssReader.domain.Article;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class RssInputService implements InputService {

    @Override
    public List<Article> inputArticle(String input) throws IOException, FeedException {

        List<Article> articleList;

        try (XmlReader reader = new XmlReader(new URL(input))) {
            articleList = fetchRssFeed(reader);
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
                .title(syndEntry.getTitle())
                .body(syndEntry.getDescription().getValue())
                .build();
    }
}
