package ru.mihajlov.SpringSecurity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.repositories.PeopleRepository;

@Service
public class RegistrationServices {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServices.class);

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServices(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        try {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            peopleRepository.save(person);
        } catch (Exception e) {
            logger.error("Error registering user {}: {}", person.getName(), e.getMessage());
        }
    }
}