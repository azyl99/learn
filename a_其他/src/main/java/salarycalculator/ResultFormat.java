package salarycalculator;

/**
 * @author guya
 * @date 2018/8/17
 */
public class ResultFormat {
    private static final String RESULT_COL_HEAD_FORMAT = "%8s";
    private static final String RESULT_ITEM_FORMAT = "%9.2f(%5.2f%%)";
    private static final String RESULT_ROW_HEAD_FORMAT = "%16s%16s\n";
    public static final String SPLIT = "------------------------------------------\n";
    public static final String RESULT_SHORT_CONTENT_FORMAT = RESULT_COL_HEAD_FORMAT + RESULT_ITEM_FORMAT;
    public static final String RESULT_LONG_CONTENT_FORMAT = RESULT_COL_HEAD_FORMAT + RESULT_ITEM_FORMAT + RESULT_ITEM_FORMAT;
    public static final String RESULT_TITLE_FORMAT = RESULT_COL_HEAD_FORMAT + RESULT_ROW_HEAD_FORMAT;
}
