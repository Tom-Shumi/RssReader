package RssReader.util;

import RssReader.constant.ConvertTypeEnum;
import RssReader.domain.Argument;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void invalidArgsCount() {
        // 事前準備 & 実行
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConvertArgumentUtils.convertArgument(new String[]{"-i", "input", "-c"})
        );

        // 検証
        assertEquals(exception.getMessage(), "コマンドライン引数が不正です。");
    }

    @Test
    void invalidArgType() {
        // 事前準備 & 実行
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConvertArgumentUtils.convertArgument(new String[]{"-e", "input"})
        );

        // 検証
        assertEquals(exception.getMessage(), "未定義のコマンドライン引数が設定されています。");
    }

    @Test
    void notDefineInput() {
        // 事前準備 & 実行
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConvertArgumentUtils.convertArgument(new String[]{"-c", "cut"})
        );

        // 検証
        assertEquals(exception.getMessage(), "取り込み対象を指定してください。");
    }
}
