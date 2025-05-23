package ru.mihajlov.SpringSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mihajlov.SpringSecurity.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Person findByName (String username);
}
