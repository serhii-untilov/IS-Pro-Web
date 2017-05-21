package ua.in.usv.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import static ua.in.usv.entity.UserRole.USER;

@Entity
@Getter
@Setter
@Table(name = "\"USER\"")
public class CustomUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Rcd", nullable = false, updatable = false)
    private long id;

//    @Enumerated(EnumType.STRING)
//    private UserRole role = USER;

    @Column(name = "User_Nm")
    private String login;

    @Column(name = "User_Pwd", nullable = false)
    private String passwordHash;

    @Column(name = "User_FIO")
    private String name;

    @Column(name = "User_Phn")
    private String phone;

    public CustomUser() {}

    public UserRole getRole() {
        return USER;
    }
}
