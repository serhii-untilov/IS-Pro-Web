package ua.in.usv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);

    Person findById(Long id);
}
