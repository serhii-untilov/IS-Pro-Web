package ua.in.usv.entity;

import lombok.Getter;
import lombok.Setter;
import ua.in.usv.stay.Block;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "PU")
public class UserPassword {

    @Column(name = "PU_Rcd", nullable = false, updatable = false)
    private long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private CustomUser customUser;

    @Temporal(TemporalType.DATE)
    @Column(name = "PU_PwdDate")
    private Date date;

    @Column(name = "PUBlk")
    byte[] passwordBlob;
}
