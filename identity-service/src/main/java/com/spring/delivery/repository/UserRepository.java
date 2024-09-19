package com.spring.delivery.repository;

import java.security.Permissions;
import java.util.List;
import java.util.Optional;

import com.spring.delivery.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.delivery.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	@Transactional
	void deleteByVerifiedFalse();

	@Transactional
	@Modifying
	@Query("UPDATE User user SET user.verified = :verifyStatus WHERE user.email = :email")
	void updateVerifyStatusByEmail(@Param("email") String email, @Param("verifyStatus") boolean isVerify);

	@Transactional
	@Modifying
	@Query("UPDATE User user SET user.password = :passwordEncoded WHERE user.email = :email")
	void updatePasswordByEmail(@Param("email") String email, @Param("passwordEncoded") String passwordEncoded);

	@Transactional
	@Modifying
	@Query("UPDATE User user SET user.password = :passwordEncoded WHERE user.phoneNumber = :phoneNumber")
	void updatePasswordByPhoneNumber(@Param("phoneNumber") String email, @Param("passwordEncoded") String passwordEncoded);

	Optional<User> findByPhoneNumber(String email);

	boolean existsByEmailAndVerifiedIsTrue(String email);

	boolean existsByPhoneNumber(String phoneNumber);


}
