package ua.in.usv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// Needs to Remember Me Authentification
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

    @Column(nullable = false, length = 64)
    private String username;

    @Id
    @Column(nullable = false, length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    String token;

    @Column(nullable = false)
    private Date last_used;
}
