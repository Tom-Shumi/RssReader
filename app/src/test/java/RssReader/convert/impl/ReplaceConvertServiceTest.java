package RssReader.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.impl.ReplaceConvertService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceConvertServiceTest {

    private final ReplaceConvertService convertService = new ReplaceConvertService();

    @Test
    void inputUZABASE() {
        // 事前準備 & 実行
        List<Article> actualList = preparationAndExecution("ユーザベース", "ユーザベース");

        // 検証
        Article actual = actualList.get(0);
        assertEquals(actual.getTitle(), "ユーザベース");
        assertEquals(actual.getBody(), "UZABASE");
    }

    @Test
    void inputNotUZABASE() {
        // 事前準備 & 実行
        List<Article> actualList = preparationAndExecution("タイトル", "UZA_BASE");

        // 検証
        Article actual = actualList.get(0);
        assertEquals(actual.getTitle(), "タイトル");
        assertEquals(actual.getBody(), "UZA_BASE");
    }

    @Test
    void inputMultipleEntries() {
        // 事前準備
        List<Article> inputList = List.of(Article.builder().title("テストユーザベーステスト").body("テストユーザベーステスト1").build(),
                Article.builder().title("タイトル").body("テストユーザベーステスト2").build());
        // 実行
        List<Article> actualList =convertService.convertArticle(inputList);

        // 検証
        assertEquals(actualList.get(0).getTitle(), "テストユーザベーステスト");
        assertEquals(actualList.get(0).getBody(), "テストUZABASEテスト1");
        assertEquals(actualList.get(1).getTitle(), "タイトル");
        assertEquals(actualList.get(1).getBody(), "テストUZABASEテスト2");
    }

    private List<Article> preparationAndExecution(String title, String body) {
        // 事前準備
        List<Article> input = createArticleList(title, body);
        // 実行
        convertService.convertArticle(input);
        return input;
    }

    private List<Article> createArticleList(String title, String body) {
        return List.of(Article.builder().title(title).body(body).build());
    }
}
