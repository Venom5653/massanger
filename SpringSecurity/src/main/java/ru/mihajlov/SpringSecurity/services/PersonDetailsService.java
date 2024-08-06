package ru.mihajlov.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.repositories.PeopleRepository;
import ru.mihajlov.SpringSecurity.security.PersonDetails;




@Service
public class PersonDetailsService implements UserDetailsService {

    private PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = peopleRepository.findByName(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PersonDetails(person);
    }
}
