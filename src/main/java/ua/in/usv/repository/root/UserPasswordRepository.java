package ua.in.usv.repository.root;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.root.UserPassword;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
}
