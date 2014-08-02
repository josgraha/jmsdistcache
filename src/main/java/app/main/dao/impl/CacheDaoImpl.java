package app.main.dao.impl;

import app.main.model.Employee;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import net.sf.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheDaoImpl {
    public static final String KEY_EMPLOYEE_CACHE = "employees";

    @Autowired
    private EhCacheCacheManager cacheManager;

    public Cache getEmployeeCache() {
        return cacheManager.getCacheManager().getCache(KEY_EMPLOYEE_CACHE);
    }

    public Employee getEmployee(int id) {
       Element elem = getEmployeeCache().get(Integer.valueOf(id));
       if (elem == null || elem.getObjectValue() == null) {
           return null;
       }
       Employee ret = (Employee)elem.getObjectValue();
       return ret;
    }
}
