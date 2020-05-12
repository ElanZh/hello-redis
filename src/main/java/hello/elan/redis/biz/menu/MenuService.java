package hello.elan.redis.biz.menu;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 张一然
 * @date 2020/5/12 15:44
 */
@Service
public class MenuService {
    private final MenuRepo menuRepo;

    public MenuService(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    @Cacheable(cacheNames = "menu", key = "#id")
    public Menu get(int id) {
        return menuRepo.findById(id).get();
    }
}
