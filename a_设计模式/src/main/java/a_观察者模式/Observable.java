package a_观察者模式;

/**
 * @author guya
 * @date 2018/8/20
 */
public interface Observable {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();

}
