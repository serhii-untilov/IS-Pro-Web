package ua.in.usv.entity.firm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CrtFrm1")
public class Company {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CrtFrm_Rcd", nullable = false, updatable = false)
    private long id;

    @Column(name = "CrtFrm_Nm", nullable = false)
    private String name;

    @Column(name = "CrtFrm_LnNm", nullable = false)
    private String fullName;
}
