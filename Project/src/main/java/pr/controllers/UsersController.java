package pr.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.forms.PostForm;
import pr.forms.SearchForm;
import pr.forms.UploadForm;
import pr.forms.UserForm;
import pr.services.AuthenticationService;
import pr.services.AvatarService;
import pr.services.PostsService;
import pr.services.UsersService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;


@Controller
public class UsersController {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/users")
    public String getUsersPage(ModelMap model) {
        List<UserDto> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "UsersPage";
    }

   /* @GetMapping("/user/{user-id}")
    public String getPage(ModelMap model, @PathVariable("user-id") Long userId) {
        List<PostsDto> posts = postsService.getPosts(userId);
        model.addAttribute("posts", posts);
        return "PostsPage";
    }       */

    @PostMapping("/user")
    public String addUser(UserForm user) {
        usersService.addUser(user);
        return "SignIn";
    }

    @GetMapping("/search")
    public String search(ModelMap model, SearchForm text) {
        List<PostsDto> posts = postsService.searchPosts(text.getText());
        model.addAttribute("posts", posts);
        return "SearchPage";
    }

    @PostMapping("/newPost")
    public String newPost(Authentication authentication, PostForm post) {
        postsService.newPost(post.getText(),
                authenticationService.getUserIdByAuthentication(authentication),
                authenticationService.getNameByAuthentication(authentication));
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication, ModelMap posts, ModelMap user, ModelMap avatar){
        if (authentication == null) {
            return "redirect:/signIn";
        }
        else {
            posts.addAttribute("posts", authenticationService.getUserPostsByAuthentication(authentication));
            user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
            avatar.addAttribute("avatar",authenticationService.getAvatarByAutentication(authentication));
            return "PostsPage";
        }
    }

    @PostMapping("/upload")
    public String avatarUpload(@ModelAttribute UploadForm form, Authentication authentication) {
        StringJoiner sj = new StringJoiner("");

        MultipartFile file = form.getFile();

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get(storagePath + authenticationService.getLoginByAuthentication(authentication) + "_avatar.jpg");
            File oldAvatar = new File("C:/avatars/" + authenticationService.getLoginByAuthentication(authentication) + "_avatar.jpg");
            oldAvatar.delete();
            avatarService.deleteAvatar(authenticationService.getUserIdByAuthentication(authentication));

            Files.write(path, bytes);

            sj.add(file.getOriginalFilename());

            avatarService.saveAvatar(authenticationService.getLoginByAuthentication(authentication) + "_avatar.jpg",
                    path.toString(),
                    authenticationService.getUserIdByAuthentication(authentication));
        } catch (IOException e) {
                e.printStackTrace();
        }

        return "redirect:/user";
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
        return "redirect:/user";
    }
    
}