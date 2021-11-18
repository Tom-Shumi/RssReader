package RssReader.input.impl;

import RssReader.domain.Article;
import RssReader.service.input.impl.FileInputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileInputServiceTest {

    FileInputService fileInputService = new FileInputService();
    Method createArticleList;

    @BeforeEach
    void before() throws NoSuchMethodException {
        // 事前準備 & 実行
        createArticleList = FileInputService.class.getDeclaredMethod("createArticleList", List.class);

        // 検証
        createArticleList.setAccessible(true);
    }

    @Test
    void fileEmpty() throws InvocationTargetException, IllegalAccessException {
        // 事前準備 & 実行
        List<Article> actual = (List<Article>) createArticleList.invoke(fileInputService, Collections.emptyList());

        // 検証
        assertEquals(actual.size(), 0);
    }

    @Test
    void fileOneContent() throws InvocationTargetException, IllegalAccessException {
        // 事前準備 & 実行
        List<Article> actual = (List<Article>) createArticleList.invoke(fileInputService, oneContentList());

        // 検証
        assertEquals(actual.size(), 1);
        assertEquals(actual.get(0).getTitle(), "タイトル");
        assertEquals(actual.get(0).getBody(), "内容");
    }

    @Test
    void fileTwoContent() throws InvocationTargetException, IllegalAccessException {
        // 事前準備 & 実行
        List<Article> actual = (List<Article>) createArticleList.invoke(fileInputService, twoContentList());

        // 検証
        assertEquals(actual.size(), 2);
        assertEquals(actual.get(0).getTitle(), "タイトル1");
        assertEquals(actual.get(0).getBody(), "内容1");
        assertEquals(actual.get(1).getTitle(), "タ:イ:ト:ル:2");
        assertEquals(actual.get(1).getBody(), "内容2");
    }

    @Test
    void exception() throws InvocationTargetException, IllegalAccessException {
        // 事前準備 & 実行
        Throwable exception = assertThrows(
                InvocationTargetException.class,
                () -> createArticleList.invoke(fileInputService, exceptionContentList())
        );

        // 検証
        assertEquals(exception.getCause().getMessage(), "ファイル内の形式が不正です。");
    }

    private List<String> oneContentList() {
        return List.of("title: タイトル", "body: 内容", "");
    }

    private List<String> twoContentList() {
        return List.of("title: タイトル1", "body: 内容1", "",
                "title: タ:イ:ト:ル:2", "body: 内容2", "");
    }

    private List<String> exceptionContentList() {
        return List.of("title: タイトル1", "body: 内容1",
                "title: タ:イ:ト:ル:2", "body: 内容2", "");
    }
}
