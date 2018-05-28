package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pr.forms.UserForm;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session.Cookie;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/users")
    public String getUsersPage(ModelMap model) {
        List<String> names = service.getAllNames();
        model.addAttribute("names", names);
        return "UsersPage";
    }

    @PostMapping("/users")
    public String addUser(UserForm user) {
        service.addUser(user);
        Cookie cookie = new Cookie();
        return "redirect:/users";
    }

    @GetMapping("/")
    public String loginPage(UserForm user) {
        return "LoginPage";
    }

    

    
}