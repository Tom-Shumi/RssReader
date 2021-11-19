package RssReader.constant;

import lombok.Getter;

/**
 * コマンドライン引数を表すEnum
 */
public enum ArgumentTypeEnum {
    INPUT("-i"),
    CONVERT_TYPE("-c"),
    OUTPUT("-o");

    @Getter
    private final String value;

    ArgumentTypeEnum(String value) {
        this.value = value;
    }
}
