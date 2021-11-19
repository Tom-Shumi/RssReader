package RssReader.constant;

/**
 * 取り込みファイル内の項目名を表すEnum
 */
public enum FileContentEnum {
    TITLE,
    BODY;

    /**
     * FileContentEnumを小文字の文字列に変換する。
     * @return 小文字のFileContentEnum文字列
     */
    public String toLowerCaseString() {
        return toString().toLowerCase();
    }
}
