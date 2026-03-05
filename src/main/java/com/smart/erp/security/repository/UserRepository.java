package com.smart.erp.security.repository;

import com.smart.erp.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM FND_USERS_V WHERE USER_NAME = :userName", nativeQuery = true)
    Optional<User> findByUserName(@Param("userName") String userName);

    @Query(value = "SELECT * FROM FND_USERS_V WHERE IS_ACTIVE = 'Y'", nativeQuery = true)
    List<User> findAllActive();

    @Query(value = "SELECT * FROM FND_USERS_V WHERE USER_ID = :userId", nativeQuery = true)
    Optional<User> findByIdView(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM FND_USERS_V", nativeQuery = true)
    List<User> findAllView();

    boolean existsByUserName(String userName);
}