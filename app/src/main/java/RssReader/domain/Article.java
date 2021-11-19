package RssReader.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 取り込んだ1コンテンツ
 */
@Data
@Builder
public class Article {

    private String title;

    private String body;
}
