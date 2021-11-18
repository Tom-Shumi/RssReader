package RssReader.factory;

import RssReader.constant.ConvertTypeEnum;
import RssReader.domain.Argument;
import RssReader.service.convert.ConvertService;
import RssReader.service.convert.impl.CutConvertService;
import RssReader.service.convert.impl.ReplaceConvertService;
import RssReader.service.input.InputService;
import RssReader.service.input.impl.FileInputService;
import RssReader.service.input.impl.RssInputService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputServiceFactoryTest {


    @Test
    void inputFile() {
        // 事前準備 & 実行
        InputService actual = InputServiceFactory.create(argument("test"));

        // 検証
        assertTrue(actual instanceof FileInputService);
    }

    @Test
    void inputRssHttp() {
        // 事前準備 & 実行
        InputService actual = InputServiceFactory.create(argument("http://test"));

        // 検証
        assertTrue(actual instanceof RssInputService);
    }

    @Test
    void inputRssHttps() {
        // 事前準備 & 実行
        InputService actual = InputServiceFactory.create(argument("https://test"));

        // 検証
        assertTrue(actual instanceof RssInputService);
    }

    private Argument argument(String input) {
        Argument argument = new Argument();
        argument.setInput(input);
        return argument;
    }
}
