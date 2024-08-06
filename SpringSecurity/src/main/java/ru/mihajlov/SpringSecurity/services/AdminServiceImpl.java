package ru.mihajlov.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mihajlov.SpringSecurity.dto.PersonDto;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.repositories.PeopleRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminServiceImpl(PeopleRepository peopleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.peopleRepository = peopleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Person findByName(String name) {
        return peopleRepository.findByName(name);
    }

    @Override
    public void addPerson(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }

    @Override
    public Person getUserById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAllUsers() {
        return peopleRepository.findAll();
    }

    @Override
    public Person convertDtoToUSer(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setAge(personDto.getAge());

        return person;
    }

    @Override
    public void updateUser(int id, Person person) {
        if (person.getPassword() != null) {
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        } else {
            person.setPassword(peopleRepository.getById(id).getPassword());
        }
        peopleRepository.save(person);
    }

    @Override
    public void removeUser(int id) {
        peopleRepository.deleteById(id);
    }
}
