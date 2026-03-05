package com.smart.erp.security.repository;

import com.smart.erp.security.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {

    @Query(value = "SELECT * FROM FND_FUNCTIONS_V WHERE FUNCTION_ID = :functionId", nativeQuery = true)
    Optional<Function> findByIdView(@Param("functionId") Long functionId);

    @Query(value = "SELECT * FROM FND_FUNCTIONS_V", nativeQuery = true)
    List<Function> findAllView();

    @Query(value = "SELECT * FROM FND_FUNCTIONS_V WHERE FUNCTION_CODE = :functionCode", nativeQuery = true)
    Optional<Function> findByFunctionCode(@Param("functionCode") String functionCode);
}