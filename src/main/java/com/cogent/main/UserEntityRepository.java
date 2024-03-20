package com.cogent.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByJwtToken(String jwtToken);
}
