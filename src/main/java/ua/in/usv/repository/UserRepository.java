package ua.in.usv.repository;

import ua.in.usv.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByLogin(String login);
}
