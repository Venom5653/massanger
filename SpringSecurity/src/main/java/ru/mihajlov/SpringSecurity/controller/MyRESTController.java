package ru.mihajlov.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mihajlov.SpringSecurity.dto.PersonDto;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.repositories.PeopleRepository;
import ru.mihajlov.SpringSecurity.services.AdminService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private final PeopleRepository peopleRepository;
    private final AdminService adminService;


    @Autowired
    public MyRESTController(PeopleRepository peopleRepository, AdminService adminService) {
        this.peopleRepository = peopleRepository;
        this.adminService = adminService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<Person>> getUser(){
        List<Person> list =  adminService.getAllUsers();
        return (list != null && !list.isEmpty())
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/users")
    public ResponseEntity<Person> createUser(@RequestBody PersonDto personDto) {
        Person person = adminService.convertDtoToUSer(personDto);
        adminService.addPerson(new Person());

        return ResponseEntity.ok().body(person);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Person> updateResource(@PathVariable int id, @RequestBody Person person) {
        if (!peopleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        person.setId(id);
        Person updatedPerson = peopleRepository.save(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Person> delete(@PathVariable int id){
        adminService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
