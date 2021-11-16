package RssReader.util;

import RssReader.constant.ArgumentTypeEnum;
import RssReader.constant.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class ConvertArgumentUtils {

    public static Map<ArgumentTypeEnum, String> convertArgument(String[] args) {

        if (checkArgs(args)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);
        }

        return createArgMap(args);
    }

    private static boolean checkArgs(String[] args) {
        return args == null || args.length == 0 || args.length % 2 != 0;
    }

    private static Map<ArgumentTypeEnum, String> createArgMap(String[] args) {
        Map<ArgumentTypeEnum, String> argMap = new HashMap<ArgumentTypeEnum, String>();

        for (int i = 0; i < args.length; i = i + 2) {

            if (args[i].equals(ArgumentTypeEnum.INPUT.getValue())) {
                argMap.put(ArgumentTypeEnum.INPUT, args[i + 1]);

            } else if (args[i].equals(ArgumentTypeEnum.CONVERT_TYPE.getValue())) {
                argMap.put(ArgumentTypeEnum.CONVERT_TYPE, args[i + 1]);

            } else if (args[i].equals(ArgumentTypeEnum.OUTPUT.getValue())) {
                argMap.put(ArgumentTypeEnum.OUTPUT, args[i + 1]);

            } else {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ARGUMENT);

            }
        }

        return argMap;
    }

}
