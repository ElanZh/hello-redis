package hello.elan.redis.biz.menu;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    private Menu parent;
//
//    private Set<Menu> children = new HashSet<>();
}
