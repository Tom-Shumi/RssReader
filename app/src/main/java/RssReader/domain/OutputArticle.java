package RssReader.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OutputArticle {

    private List<Article> articleList;

    private String output;
}
