package ua.in.usv.repository.firm;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.usv.entity.firm.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);

    Person findById(Long id);
}
