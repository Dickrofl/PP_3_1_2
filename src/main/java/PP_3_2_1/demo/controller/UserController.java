package PP_3_2_1.demo.controller;

import PP_3_2_1.demo.model.User;
import PP_3_2_1.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsersPage(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("newuser", new User());
        return "newUser";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}