package ua.in.usv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByLogin(String login);
}
