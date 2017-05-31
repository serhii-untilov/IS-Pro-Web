package ua.in.usv.repository.root;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.root.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByLogin(String login);
}
