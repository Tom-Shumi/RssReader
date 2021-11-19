package RssReader.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 出力処理用の情報を保持するクラス
 */
@Data
@Builder
public class OutputArticle {

    private List<Article> articleList;

    private String output;
}
