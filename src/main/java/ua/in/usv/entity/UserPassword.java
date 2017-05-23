package ua.in.usv.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "PU")
public class UserPassword {

    @Id
    @Column(name = "PU_RCD")
    private long id;

    @Column(name = "PU_PwdDate")
    private Date date;

    @Column(name = "PUBlk")
    byte[] passwordBlob;
}
