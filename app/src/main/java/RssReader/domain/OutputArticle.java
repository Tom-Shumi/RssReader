package RssReader.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputArticle {

    private Article article;

    private String output;
}
