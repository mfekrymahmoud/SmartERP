package com.smart.erp.security.repository;

import com.smart.erp.security.model.UserAudit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserAuditRepository extends JpaRepository<UserAudit, Long> {

    @Query(value = "SELECT * FROM FND_USER_AUDIT_V WHERE USER_ID = :userId AND LOGOUT_DATE_TIME IS NULL ORDER BY LOGIN_DATE_TIME DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    Optional<UserAudit> findLastActiveSession(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE FND_USER_AUDIT SET LOGOUT_DATE_TIME = :logoutTime WHERE AUDIT_ID = :auditId", nativeQuery = true)
    void updateLogoutTime(@Param("auditId") Long auditId, @Param("logoutTime") LocalDateTime logoutTime);
}