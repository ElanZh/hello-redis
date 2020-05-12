package hello.elan.redis.biz.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    private final static String USER_CACHE_KEY = "UserService#get";
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Cacheable(cacheNames = USER_CACHE_KEY, key = "#id")
    public User find(Integer id) {
        return userRepo.findById(id).get();
    }

    @CacheEvict(cacheNames = USER_CACHE_KEY, key = "#req.id")
    public User mod(User req) {
        return userRepo.saveAndFlush(req);
    }
}
