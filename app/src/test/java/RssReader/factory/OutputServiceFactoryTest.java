package RssReader.factory;

import RssReader.domain.Argument;
import RssReader.service.input.InputService;
import RssReader.service.input.impl.FileInputService;
import RssReader.service.input.impl.RssInputService;
import RssReader.service.output.OutputService;
import RssReader.service.output.impl.FileOutputService;
import RssReader.service.output.impl.StandardOutputService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputServiceFactoryTest {

    @Test
    void inputFile() {
        // 事前準備 & 実行
        OutputService actual = OutputServiceFactory.create(argument("test"));

        // 検証
        assertTrue(actual instanceof FileOutputService);
    }

    @Test
    void inputEmpty() {
        // 事前準備 & 実行
        OutputService actual = OutputServiceFactory.create(argument(""));

        // 検証
        assertTrue(actual instanceof StandardOutputService);
    }

    @Test
    void inputNull() {
        // 事前準備 & 実行
        OutputService actual = OutputServiceFactory.create(argument(null));

        // 検証
        assertTrue(actual instanceof StandardOutputService);
    }

    private Argument argument(String output) {
        Argument argument = new Argument();
        argument.setOutput(output);
        return argument;
    }
}
