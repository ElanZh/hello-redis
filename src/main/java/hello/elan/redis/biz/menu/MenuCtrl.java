package hello.elan.redis.biz.menu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张一然
 * @date 2020/5/12 15:38
 */
@RestController
@RequestMapping("menu")
public class MenuCtrl {
    private final MenuRepo menuRepo;
    private final MenuService menuService;
    public MenuCtrl(MenuRepo menuRepo, MenuService menuService) {
        this.menuRepo = menuRepo;
        this.menuService = menuService;
    }

    @GetMapping("initMenu")
    public void initMenu() {
        Menu root = Menu.builder().name("root").parent(null).build();
        root = menuRepo.saveAndFlush(root);

        Menu first1 = Menu.builder().name("1").parent(root).build();
        first1 = menuRepo.saveAndFlush(first1);
        Menu first2 = Menu.builder().name("2").parent(root).build();
        first2 = menuRepo.saveAndFlush(first2);

        Menu sec1 = Menu.builder().name("1-1").parent(first1).build();
        sec1 = menuRepo.saveAndFlush(sec1);
        Menu sec2 = Menu.builder().name("1-2").parent(first1).build();
        sec2 = menuRepo.saveAndFlush(sec2);
    }

    @GetMapping("get")
    public Menu get(Integer id) {
        return menuService.get(id);
    }
}
