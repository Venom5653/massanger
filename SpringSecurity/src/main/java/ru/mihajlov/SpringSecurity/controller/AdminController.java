package ru.mihajlov.SpringSecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mihajlov.SpringSecurity.models.Person;
import ru.mihajlov.SpringSecurity.services.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER  = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/add")
    public String addUser(){
        return "admin/add";
    }

    @GetMapping("/show")
    public String showUsersPage(Model model) {
        List<Person> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/show";
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        adminService.removeUser(id);
        return "redirect:/admin/show";
    }

    @PostMapping("/add")
    public String addUser(Person person) {
        if (person.getPassword().equals(person.getConfirmPassword())) {
            adminService.addPerson(person);
        } else {
            return "redirect:/admin/admin";
        }
        return "redirect:/admin/show";
    }

//    @PostMapping("/new_password")
//    public String chandepassword(@AuthenticationPrincipal UserDetails userDetails, String newPassword){
//        LOGGER.info("");
//    }
}
