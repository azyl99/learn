package a_localcache;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author guya on 2019/3/18
 */
@Slf4j
public class GwInPartnerCache extends AbstractDbLocalCache<GwInPartner> {

    @Override
    void autoRefreshCache() throws Exception {
        Map<String, GwInPartner> gwNewInPartnerLocalcache = getFromRepository();
        log.info("autoRefresh gwInPartner, old:{} new:{}", dumpCache(cache), dumpCache(gwNewInPartnerLocalcache));
        cache = gwNewInPartnerLocalcache;
    }
}
