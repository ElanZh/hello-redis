package hello.elan.redis.biz.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 张一然
 * @date 2020/5/12 15:39
 */
@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer> {
}
