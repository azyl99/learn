package a_localcache;

import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author guya on 2019/3/18
 */
public abstract class AbstractDbLocalCache<T extends CachedItem> implements InitializingBean {

    protected Map<String, T> cache;

    protected ItemRepository<T> repository;

    protected Map<String, T> getFromRepository() {
        Map<String, T> cache = new HashMap<>();
        List<T> list = repository.selectAll();
        for (T item : list) {
            cache.put(item.getCacheKey(), item);
        }
        return cache;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = getFromRepository();
    }

    abstract void autoRefreshCache() throws Exception;

    public String dumpCache() {
        return dumpCache(cache);
    }

    protected static <T> String dumpCache(Map<String, T> cache) {
        StringBuilder sb = new StringBuilder();
        sb.append("{config:");
        if (Objects.nonNull(cache) && cache.size() > 0) {
            for (Map.Entry<String, T> config : cache.entrySet()) {
                sb.append("[{");
                sb.append(config.getKey());
                sb.append(": ");
                sb.append(config.getValue().toString());
                sb.append("}]");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
