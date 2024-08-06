package ru.mihajlov.SpringSecurity.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.services.RegistrationServices;
import ru.mihajlov.SpringSecurity.util.PersonValidator;

@Controller
@RequestMapping("/Auth")
public class AuthController {

    private final RegistrationServices registrationServices;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrationServices registrationServices, PersonValidator personValidator) {
        this.registrationServices = registrationServices;
        this.personValidator = personValidator;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person){
        return "Auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person")@Valid Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "/Auth/registration";
        registrationServices.register(person);
        return "redirect:/login";
    }

}

