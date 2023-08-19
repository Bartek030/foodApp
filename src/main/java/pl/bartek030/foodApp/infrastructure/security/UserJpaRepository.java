package pl.bartek030.foodApp.infrastructure.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<AppUserEntity, Long> {

    Optional<AppUserEntity> findByUserName(String userName);
}
