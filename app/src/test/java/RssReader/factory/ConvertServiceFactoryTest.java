package RssReader.factory;

import RssReader.constant.ConvertTypeEnum;
import RssReader.domain.Argument;
import RssReader.service.convert.ConvertService;
import RssReader.service.convert.impl.CutConvertService;
import RssReader.service.convert.impl.ReplaceConvertService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConvertServiceFactoryTest {


    @Test
    void inputCut() {
        // 事前準備 & 実行
        List<ConvertService> actualList = ConvertServiceFactory.create(argument(ConvertTypeEnum.CUT));

        // 検証
        assertTrue(actualList.get(0) instanceof CutConvertService);
    }

    @Test
    void inputConvert() {
        // 事前準備 & 実行
        List<ConvertService> actualList = ConvertServiceFactory.create(argument(ConvertTypeEnum.CONVERT));

        // 検証
        assertTrue(actualList.get(0) instanceof ReplaceConvertService);
    }

    @Test
    void inputBoth() {
        // 事前準備 & 実行
        List<ConvertService> actualList = ConvertServiceFactory.create(argument(ConvertTypeEnum.CONVERT, ConvertTypeEnum.CUT));

        // 検証
        assertTrue(actualList.get(0) instanceof ReplaceConvertService);
        assertTrue(actualList.get(1) instanceof CutConvertService);
    }

    private Argument argument(ConvertTypeEnum... convertTypeArray) {
        Argument argument = new Argument();
        argument.setConvertTypeList(new ArrayList<>(Arrays.asList(convertTypeArray)));
        return argument;
    }
}
