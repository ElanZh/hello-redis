package hello.elan.redis.biz.user;

import org.springframework.web.bind.annotation.*;

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
        return userRepo.saveAndFlush(req);
    }

    @GetMapping("get")
    public User get(Integer id) {
        return userService.find(id);
    }
}
