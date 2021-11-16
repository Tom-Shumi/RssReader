package RssReader.constant;

import lombok.Getter;

public enum ArgumentTypeEnum {
    INPUT("-i"),
    CONVERT_TYPE("-c"),
    OUTPUT("-o");

    @Getter
    private String value;

    private ArgumentTypeEnum(String value) {
        this.value = value;
    }
}
