package ua.in.usv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
}
