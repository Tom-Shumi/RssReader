package RssReader.constant;

public enum FileContentEnum {
    TITLE,
    BODY;

    public String toLowerCaseString() {
        return toString().toLowerCase();
    }
}
