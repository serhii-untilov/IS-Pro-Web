package ua.in.usv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.in.usv.entity.Person;
import ua.in.usv.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }
}
