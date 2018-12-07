package a_监听者模式;

import java.util.EventListener;

/**
 * A contract between a SunEvent source and
 *   listener classes
 */
public abstract class SunListener implements EventListener {
    /** Called whenever the sun changes position
     *   in a SunEvent source object
     */
    public abstract void sunMoved(SunEvent e);
}