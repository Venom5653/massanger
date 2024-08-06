package ru.mihajlov.SpringSecurity.services;

import ru.mihajlov.SpringSecurity.dto.PersonDto;
import ru.mihajlov.SpringSecurity.models.Person;

import java.util.List;

public interface AdminService {
    void addPerson(Person person);

    void updateUser(int id, Person person);

    void removeUser(int id);

    Person getUserById(int id);

    List<Person> getAllUsers();

    Person convertDtoToUSer(PersonDto personDto);
}
