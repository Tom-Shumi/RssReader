package RssReader.convert.impl;

import RssReader.domain.Article;
import RssReader.service.convert.impl.CutConvertService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CutConvertServiceTest {

    private final CutConvertService convertService = new CutConvertService();

    @Test
    void inputTitle9LettersAndBody29Letters() {
        // 事前準備 & 実行
        List<Article> actualList = preparationAndExecution("123456789", "12345678901234567890123456789");

        // 検証
        Article actual = actualList.get(0);
        assertEquals(actual.getTitle(), "123456789");
        assertEquals(actual.getBody(), "12345678901234567890123456789");
    }

    @Test
    void inputTitle10LettersAndBody30Letters() {
        // 事前準備 & 実行
        List<Article> actualList = preparationAndExecution("1234567890", "123456789012345678901234567890");

        // 検証
        Article actual = actualList.get(0);
        assertEquals(actual.getTitle(), "1234567890");
        assertEquals(actual.getBody(), "123456789012345678901234567890");
    }

    @Test
    void inputTitle11LettersAndBody31Letters() {
        // 事前準備 & 実行
        List<Article> actualList = preparationAndExecution("12345678901", "1234567890123456789012345678901");

        // 検証
        Article actual = actualList.get(0);
        assertEquals(actual.getTitle(), "1234567890");
        assertEquals(actual.getBody(), "123456789012345678901234567890");
    }

    @Test
    void inputMultipleEntries() {
        // 事前準備
        List<Article> inputList = List.of(Article.builder().title("12345678901").body("1234567890123456789012345678901").build(),
                Article.builder().title("あいうえおかきくけこさ").body("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほま").build());
        // 実行
        List<Article> actualList =convertService.convertArticle(inputList);

        // 検証
        assertEquals(actualList.get(0).getTitle(), "1234567890");
        assertEquals(actualList.get(0).getBody(), "123456789012345678901234567890");
        assertEquals(actualList.get(1).getTitle(), "あいうえおかきくけこ");
        assertEquals(actualList.get(1).getBody(), "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほ");
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
