package ru.mihajlov.SpringSecurity.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.services.AdminServiceImpl;

@Component
public class PersonValidator implements Validator {

    private final AdminServiceImpl personService;

    public PersonValidator(AdminServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.findByName(person.getName()) != null) {
            errors.rejectValue("name", "", "Логин занят");
        }

        if (!person.getPassword().equals(person.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "", "Пароль не совпадает");
        }
    }
}
