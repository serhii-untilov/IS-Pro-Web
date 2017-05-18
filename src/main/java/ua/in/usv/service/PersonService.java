package ua.in.usv.service;

import ua.in.usv.entity.Person;

public interface PersonService {
    Person findByName(String name);
    Person findById(Long id);
    void addPerson(Person person);
    void updatePerson(Person person);
}
