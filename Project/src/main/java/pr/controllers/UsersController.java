package pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.forms.UserForm;
import pr.services.PageService;
import pr.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PageService pageService;

    @GetMapping("/users")
    public String getUsersPage(ModelMap model) {
        List<UserDto> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "UsersPage";
    }

    @GetMapping("/page/{user-id}")
    public String getPage(ModelMap model, @PathVariable("user-id") Long userId) {
        List<PostsDto> posts = pageService.getPosts(userId);
        model.addAttribute("posts", posts);
        return "PostsPage";
    }
  

    @PostMapping("/page")
    public String addUser(UserForm user) {
        usersService.addUser(user);
        return "redirect:/page";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "SignUp";
    }

    @GetMapping("/signIn")
    public String signInPage() {
        return "SignIn";
    }

    @GetMapping("/")
    public String defaultPage() {
        return "SignIn";
    }
    
}