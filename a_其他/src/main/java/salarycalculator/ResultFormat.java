package salarycalculator;

/**
 * @author guya
 * @date 2018/8/17
 */
public class ResultFormat {
    private static final String COL_HEAD_FORMAT = "%8s";
    private static final String ITEM_FORMAT = "%9.2f(%5.2f%%)";
    private static final String ROW_HEAD_FORMAT = "%16s%16s\n";
    public static final String SPLIT = "------------------------------------------\n";
    public static final String SHORT_CONTENT_FORMAT = COL_HEAD_FORMAT + ITEM_FORMAT;
    public static final String LONG_CONTENT_FORMAT = COL_HEAD_FORMAT + ITEM_FORMAT + ITEM_FORMAT;
    public static final String TITLE_FORMAT = COL_HEAD_FORMAT + ROW_HEAD_FORMAT;
}
