package com.blinkchat.chat.user.repository;

import com.blinkchat.chat.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
     User findUserBySessionToken(String sessionToken);

     boolean existsByAnonymousName(String anonymousName);
}
