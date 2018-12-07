package a_监听者模式;

import java.util.Date;
import java.util.EventObject;

/**
 * An event that represents the Sun rising or setting
 */
public class SunEvent extends EventObject {
    private boolean risen;
    private Date date;

    public SunEvent(Object source, boolean risen, Date date) {
        super(source);
        this.risen = risen;
        this.date = date;
    }

    /** return a String representation of the date */
    public String getDate() {
        // return only a String representation
        //   so the user cannot modify the real date
        return date.toString();
    }

    /** return whether the sun rose or set */
    public boolean isRisen() {
        return risen;
    }
}