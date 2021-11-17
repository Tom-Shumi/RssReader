package RssReader.constant;

import static RssReader.constant.ErrorMessage.UNDEFINED_CONVERT_ARGUMENT;

public enum ConvertTypeEnum {
    CUT,
    CONVERT;

    public static ConvertTypeEnum of(String convertType) {
        try {
            return valueOf(convertType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(UNDEFINED_CONVERT_ARGUMENT);
        }
    }
}
