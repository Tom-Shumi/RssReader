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

/**
 * コマンドライン引数変換クラス
 */
public class ConvertArgumentUtils {

    private static final int OPTION_PROCESSING_UNIT_COUNT = 2;
    private static final int OPTION_CONTENT_INDEX = 1;

    /**
     * コマンドライン引数を変換する
     * @param args コマンドライン引数
     * @return 変換後のコマンドライン引数
     */
    public static Argument convertArgument(String[] args) {

        if (checkArgs(args)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }

        Argument argument = createArgument(args);

        requiredCheck(argument);

        return argument;
    }

    private static boolean checkArgs(String[] args) {
        return args == null || args.length == 0 || args.length % OPTION_PROCESSING_UNIT_COUNT != 0;
    }

    private static Argument createArgument(String[] args) {
        Argument argument = new Argument();

        // 2個(オプション名,オプション値)ずつ取り込みするため。[i = i + OPTION_PROCESSING_UNIT_COUNT]
        for (int i = 0; i < args.length; i = i + OPTION_PROCESSING_UNIT_COUNT) {

            if (args[i].equals(ArgumentTypeEnum.INPUT.getValue())) {
                argument.setInput(args[i + OPTION_CONTENT_INDEX]);

            } else if (args[i].equals(ArgumentTypeEnum.CONVERT_TYPE.getValue())) {
                argument.setConvertTypeList(toConvertTypeList(args[i + OPTION_CONTENT_INDEX]));

            } else if (args[i].equals(ArgumentTypeEnum.OUTPUT.getValue())) {
                argument.setOutput(args[i + OPTION_CONTENT_INDEX]);

            } else {
                throw new IllegalArgumentException(ErrorMessage.UNDEFINED_ARGUMENT);

            }
        }
        return argument;
    }

    private static List<ConvertTypeEnum> toConvertTypeList(String convertTypeArray) {
        List<ConvertTypeEnum> convertTypeList = new ArrayList<>();
        for (String convertType: convertTypeArray.split(CONVERT_TYPE_DELIMITER)) {
            convertTypeList.add(toConvertTypeEnum(convertType));
        }

        return convertTypeList;
    }

    private static ConvertTypeEnum toConvertTypeEnum(String convertType) {
        return ConvertTypeEnum.of(convertType.toUpperCase());
    }

    private static void requiredCheck(Argument argument) {
        if (Objects.isNull(argument.getInput())) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT_ARGUMENT);
        }
    }
}
