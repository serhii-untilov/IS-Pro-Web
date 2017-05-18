package ua.in.usv.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "KpuC1")
public class Person {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Kpu_Rcd", nullable = false, updatable = false)
    private long id;

    @Column(name = "Kpu_Fio", nullable = false)
    private String name;
}
