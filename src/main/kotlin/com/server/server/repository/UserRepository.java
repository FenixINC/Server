package com.server.server.repository;

import com.server.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.username = :username")
    User findUserByName(@Param("username") String username);

    @Query("SELECT user.userSalt FROM User user WHERE user.userSalt = :userSalt")
    String findUserSalt(@Param("userSalt") String userSalt);

    @Query("SELECT user.passwordHash FROM User user WHERE user.passwordHash = :passwordHash")
    String findUserPasswordHash(@Param("passwordHash") String passwordHash);

    @Query("SELECT user FROM User user WHERE user.id = :id")
    User getUserById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.passwordHash = :passwordHash WHERE user.id = :id")
    void updateUserPasswordHash(
            @Param("id") long id,
            @Param("passwordHash") String passwordHash
    );

//    @Query(
//            value = "SELECT * FROM User u WHERE u.username = :username AND u.passwordHash = :passwordHash",
//            countQuery = "SELECT count(*) FROM user",
//            nativeQuery = true)
//    User findUser(@Param("username") String username,
//                  @Param("passwordHash") String passwordHash);
}
