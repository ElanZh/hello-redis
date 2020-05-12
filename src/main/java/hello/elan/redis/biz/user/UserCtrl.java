package hello.elan.redis.biz.user;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("user")
public class UserCtrl {
    private final UserRepo userRepo;
    private final UserService userService;

    public UserCtrl(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping("add")
    public User add(@RequestBody User req) {
        req.setCreateAt(LocalDateTime.now());
        return userRepo.saveAndFlush(req);
    }

    @GetMapping("get")
    public User get(Integer id) {
        return userService.find(id);
    }

    @PostMapping("mod")
    public User mod(@RequestBody User req) {
        return userService.mod(req);
    }
}
