package RssReader.util;

import RssReader.constant.ConvertTypeEnum;
import RssReader.domain.Argument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertArgumentUtilsTest {

    @Test
    void input() {
        // 事前準備 & 実行
        Argument argument = ConvertArgumentUtils.convertArgument(new String[]{"-i", "input"});

        // 検証
        assertEquals(argument.getInput(), "input");
    }

    @Test
    void convertOne() {
        // 事前準備 & 実行
        Argument argument = ConvertArgumentUtils.convertArgument(new String[]{"-i", "test", "-c", "cut"});

        // 検証
        assertEquals(argument.getConvertTypeList().get(0), ConvertTypeEnum.CUT);
    }

    @Test
    void convertTwo() {
        // 事前準備 & 実行
        Argument argument = ConvertArgumentUtils.convertArgument(new String[]{"-i", "test", "-c", "cut,convert"});

        // 検証
        assertEquals(argument.getConvertTypeList().get(0), ConvertTypeEnum.CUT);
        assertEquals(argument.getConvertTypeList().get(1), ConvertTypeEnum.CONVERT);
    }

    @Test
    void output() {
        // 事前準備 & 実行
        Argument argument = ConvertArgumentUtils.convertArgument(new String[]{"-i", "input", "-o", "output"});

        // 検証
        assertEquals(argument.getOutput(), "output");
    }

    @Test
    void all() {
        // 事前準備 & 実行
        Argument argument = ConvertArgumentUtils.convertArgument(new String[]{"-i", "input", "-c", "cut,convert", "-o", "output"});

        // 検証
        assertEquals(argument.getInput(), "input");
        assertEquals(argument.getConvertTypeList().get(0), ConvertTypeEnum.CUT);
        assertEquals(argument.getConvertTypeList().get(1), ConvertTypeEnum.CONVERT);
        assertEquals(argument.getOutput(), "output");
    }
}
