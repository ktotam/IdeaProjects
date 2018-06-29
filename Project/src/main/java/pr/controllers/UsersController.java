package pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.forms.*;
import pr.models.Avatar;
import pr.models.User;
import pr.services.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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
    private MessageService messageService;

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/allusers")
    public String getUsersPage(ModelMap users, ModelMap avatars) {
        List<UserDto> allUsers = usersService.getAllUsers();
        users.addAttribute("users", allUsers);

        return "UsersPage";
    }

    @GetMapping("/users/{user-id}")
    public String getPage(Authentication authentication, ModelMap posts, ModelMap user, ModelMap avatar, @PathVariable("user-id") Long userId) {

        if(userId == authenticationService.getUserIdByAuthentication(authentication)) {
            return "redirect:/user";
        }

        List<PostsDto> tempPosts = postsService.getPostsByUserId(userId);
        posts.addAttribute("posts", tempPosts);

        Avatar tempAvatar = avatarService.getAvatarByUserId(userId);
        avatar.addAttribute("avatar", tempAvatar);

        User tempUser = usersService.getOneById(userId);
        user.addAttribute("user", tempUser);
        
        return "PostsPage";
    }

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
        usersService.addNewPost(authenticationService.getUserIdByAuthentication(authentication));
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication, ModelMap posts, ModelMap user, ModelMap avatar, ModelMap popularPosts, ModelMap popularUsers){
        if (authentication == null) {
            return "redirect:/signIn";
        }
        else {
            posts.addAttribute("posts", authenticationService.getUserPostsByAuthentication(authentication));
            popularPosts.addAttribute("popularPosts", postsService.getPopularPosts());
            popularUsers.addAttribute("popularUsers", usersService.getPopularUsers());
            user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
            avatar.addAttribute("avatar",authenticationService.getAvatarByAuthentication(authentication));
            return "UserPage";
        }
    }

    @PostMapping("/upload")
    public String avatarUpload(@ModelAttribute UploadForm form, Authentication authentication) {

        MultipartFile file = form.getFile();
        String type = file.getContentType();

        try {
            byte[] bytes = file.getBytes();

            Path path = Paths.get("C:\\avatars\\" + authenticationService.getLoginByAuthentication(authentication) + "_avatar.png");

            File oldAvatar = new File(storagePath + authenticationService.getLoginByAuthentication(authentication) + "_avatar.png");
            oldAvatar.delete();
            avatarService.deleteAvatar(authenticationService.getUserIdByAuthentication(authentication));

            Files.write(path, bytes);

            String login = authenticationService.getLoginByAuthentication(authentication);

            avatarService.saveAvatar(login + "_avatar.png",
                    storagePath + login + "_avatar.png",
                    type,
                    "http://localhost:8080/files/" + login + "_avatar.png",
                    authenticationService.getUserIdByAuthentication(authentication));

            usersService.addAvatarUrl("http://localhost:8080/files/" + login + "_avatar.png",
                    authenticationService.getUserIdByAuthentication(authentication));
        } catch (IOException e) {
                e.printStackTrace();
        }

        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editProfile() {
        return "ProfileEdit";
    }

    @PostMapping("/edit")
    public String profileEdit(UserForm user, Authentication authentication) {
        usersService.updateUser(user, authenticationService.getUserIdByAuthentication(authentication));
        messageService.updateMessages(user.getName(), authenticationService.getUserIdByAuthentication(authentication));
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

    @PostMapping("/like/{post-id}&{user-id}")
    public String likePost(@PathVariable("post-id") Long postId, @PathVariable("user-id") Long userId) {
        usersService.likeUserPost(userId);
        postsService.likePost(postId);
        return "redirect:/users/{user-id}";
    }

    @GetMapping("/msg/")
    public String messagesPage(Authentication authentication, ModelMap inboxMessages, ModelMap sentMessages, ModelMap user) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        else {
            inboxMessages.addAttribute("inboxMessages", authenticationService.getInboxMessagesByAuthentication(authentication));
            sentMessages.addAttribute("sentMessages", authenticationService.getSentMessagesByAuthentication(authentication));
            return "MessagesPage";
        }
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) throws Exception {
        avatarService.writeAvatarToResponse(fileName, response);
    }

    @PostMapping("/msg")
    public String sendMessage(Authentication authentication, MessageForm messageForm) {
        messageService.newMessage(messageForm, authenticationService.getUserIdByAuthentication(authentication), authenticationService.getNameByAuthentication(authentication));
        return "redirect:/msg/";
    }

    @PostMapping("/msgto/{user-id}")
    public String sendMessageTo(@PathVariable("user-id") Long userId, Authentication authentication, MessageForm messageForm) {
        messageForm.setToId(userId);
        messageService.newMessage(messageForm, authenticationService.getUserIdByAuthentication(authentication), authenticationService.getNameByAuthentication(authentication));
        return "redirect:/users/{user-id}";
    }
}