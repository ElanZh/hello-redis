package hello.elan.redis.biz.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Cacheable(value = "UserService#get", key = "#id")
    public User find(Integer id) {
        return userRepo.findById(id).get();
    }
}
