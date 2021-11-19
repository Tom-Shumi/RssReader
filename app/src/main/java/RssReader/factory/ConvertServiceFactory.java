package RssReader.factory;

import RssReader.constant.ConvertTypeEnum;
import RssReader.domain.Argument;
import RssReader.service.convert.ConvertService;
import RssReader.service.convert.impl.CutConvertService;
import RssReader.service.convert.impl.ReplaceConvertService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConvertServiceFactory {

    public static List<ConvertService> create(Argument argument) {

        if (CollectionUtils.isEmpty(argument.getConvertTypeList())) {
            return Collections.emptyList();
        }

        List<ConvertService> convertServiceList = new ArrayList<>();

        for (ConvertTypeEnum convertType: argument.getConvertTypeList()) {
            convertServiceList.add(createConvertService(convertType));
        }

        return convertServiceList;
    }

    private static ConvertService createConvertService(ConvertTypeEnum convertType) {
        if (ConvertTypeEnum.CUT == convertType) {
            return new CutConvertService();
        } else {
            return new ReplaceConvertService();
        }
    }
}
