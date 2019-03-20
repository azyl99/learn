package a_localcache;

import java.util.List;

/**
 * @author guya on 2019/3/18
 */
public interface ItemRepository<T> {

    List<T> selectAll();
}
