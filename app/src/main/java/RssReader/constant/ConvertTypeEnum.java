package RssReader.constant;

import static RssReader.constant.ErrorMessage.UNDEFINED_CONVERT_ARGUMENT;

/**
 * 変換処理の種類を表すEnum
 */
public enum ConvertTypeEnum {
    CUT,
    CONVERT;

    /**
     * 文字列をConvertTypeEnumに変換する
     * @param convertType 変換する文字列
     * @return 変換されたConvertTypeEnum
     */
    public static ConvertTypeEnum of(String convertType) {
        try {
            return valueOf(convertType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(UNDEFINED_CONVERT_ARGUMENT);
        }
    }
}
