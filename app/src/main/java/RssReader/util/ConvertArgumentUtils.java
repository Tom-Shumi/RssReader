package RssReader.util;

import RssReader.constant.ArgumentTypeEnum;
import RssReader.constant.ConvertTypeEnum;
import RssReader.constant.ErrorMessage;
import RssReader.domain.Argument;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static RssReader.constant.Constants.CONVERT_TYPE_DELIMITER;

public class ConvertArgumentUtils {

    public static Argument convertArgument(String[] args) {

        if (checkArgs(args)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }

        Argument argument = createArgument(args);

        requiredCheck(argument);

        return argument;
    }

    private static boolean checkArgs(String[] args) {
        return args == null || args.length == 0 || args.length % 2 != 0;
    }

    private static Argument createArgument(String[] args) {
        Argument argument = new Argument();

        for (int i = 0; i < args.length; i = i + 2) {

            if (args[i].equals(ArgumentTypeEnum.INPUT.getValue())) {
                argument.setInput(args[i + 1]);

            } else if (args[i].equals(ArgumentTypeEnum.CONVERT_TYPE.getValue())) {
                argument.setConvertTypeList(toConvertTypeList(args[i + 1]));

            } else if (args[i].equals(ArgumentTypeEnum.OUTPUT.getValue())) {
                argument.setOutput(args[i + 1]);

            } else {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);

            }
        }
        return argument;
    }

    private static List<ConvertTypeEnum> toConvertTypeList(String convertTypeArray) {
        if (StringUtils.isEmpty(convertTypeArray)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }
        List<ConvertTypeEnum> convertTypeList = new ArrayList<>();
        for (String convertType: convertTypeArray.split(CONVERT_TYPE_DELIMITER)) {
            convertTypeList.add(toConvertTypeEnum(convertType));
        }

        return convertTypeList;
    }

    private static ConvertTypeEnum toConvertTypeEnum(String convertType) {
        if (StringUtils.isEmpty(convertType)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }
        return ConvertTypeEnum.valueOf(convertType.toUpperCase());
    }

    private static void requiredCheck(Argument argument) {
        if (Objects.isNull(argument.getInput())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }
    }
}
