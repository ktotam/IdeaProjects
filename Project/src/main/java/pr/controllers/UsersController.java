package pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.forms.*;
import pr.models.User;
import pr.services.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/allusers")
    public String getUsersPage(Authentication authentication, ModelMap users) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        List<UserDto> allUsers = usersService.getAllUsers();
        users.addAttribute("users", allUsers);

        return "AllUsersPage";
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication,
                           ModelMap posts, ModelMap user, ModelMap popularPosts, ModelMap popularUsers, ModelMap usersPostsLikes){
        if (authentication == null) {
            return "redirect:/signIn";
        }
        else {
            usersPostsLikes.addAttribute("likedPosts", postsService.getUsersPostsLikes(authenticationService.getUserIdByAuthentication(authentication)));
            posts.addAttribute("posts", authenticationService.getUserPostsByAuthentication(authentication));
            popularPosts.addAttribute("popularPosts", postsService.getPopularPosts());
            popularUsers.addAttribute("popularUsers", usersService.getPopularUsers());
            user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
            return "MainUserPage";
        }
    }

    @GetMapping("/users/{user-id}")
    public String getPage(Authentication authentication, @PathVariable("user-id") Long userId, HttpServletRequest request,
                          ModelMap isFollowed, ModelMap posts, ModelMap user, ModelMap selfId, ModelMap usersPostsLikes) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        if(userId.equals(authenticationService.getUserIdByAuthentication(authentication))) {
            return "redirect:/user";
        }

        selfId.addAttribute("selfId", authenticationService.getUserIdByAuthentication(authentication));

        List<Long> likes = postsService.getUsersPostsLikes(authenticationService.getUserIdByAuthentication(authentication));
        usersPostsLikes.addAttribute("likedPosts", likes);

        List<PostsDto> tempPosts = postsService.getPostsByUserId(userId);
        posts.addAttribute("posts", tempPosts);

        User tempUser = usersService.getOneById(userId);
//        tempUser.setAvatarUrl(request.getProtocol().substring(0, request.getProtocol().indexOf("/")) + "://" +
  //              request.getServerName() + ":" + request.getServerPort() + "/" + tempUser.getAvatarUrl());
        user.addAttribute("user", tempUser);

        Boolean follow = usersService.checkFollow((authenticationService.getUserIdByAuthentication(authentication)), userId);
        isFollowed.addAttribute("isFollowed", follow);
        
        return "UsersPage";
    }

    @PostMapping("/user")
    public String addUser(UserForm user) {
        usersService.addUser(user);
        return "SignIn";
    }

    @GetMapping("/search")
    public String search(ModelMap user, ModelMap posts, ModelMap usersPostsLikes, ModelMap popularPosts, ModelMap popularUsers, ModelMap searchText,
                         SearchForm text, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        usersPostsLikes.addAttribute("likedPosts", postsService.getUsersPostsLikes(authenticationService.getUserIdByAuthentication(authentication)));
        searchText.addAttribute("text", text.getText());
        posts.addAttribute("posts", postsService.searchPosts(text.getText()));
        popularPosts.addAttribute("popularPosts", postsService.getPopularPosts());
        popularUsers.addAttribute("popularUsers", usersService.getPopularUsers());
        return "SearchPage";
    }

    @GetMapping("/feed")
    public String feedPage(ModelMap user, ModelMap posts, ModelMap usersPostsLikes, ModelMap popularPosts, ModelMap popularUsers,
                           Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        usersPostsLikes.addAttribute("likedPosts", postsService.getUsersPostsLikes(authenticationService.getUserIdByAuthentication(authentication)));
        posts.addAttribute("posts", postsService.getFeedPosts(authenticationService.getUserIdByAuthentication(authentication)));
        popularPosts.addAttribute("popularPosts", postsService.getPopularPosts());
        popularUsers.addAttribute("popularUsers", usersService.getPopularUsers());
        return "FeedPage";
    }

    @PostMapping("/newPost")
    public String newPost(Authentication authentication, PostForm post) {
        postsService.newPost(post.getText(),
                authenticationService.getUserIdByAuthentication(authentication));
        usersService.addNewPost(authenticationService.getUserIdByAuthentication(authentication));
        return "redirect:/user";
    }

    @GetMapping("/edit")
    public String editProfile(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        return "ProfileEdit";
    }

    @PostMapping("/edit")
    public String profileEdit(UserForm user, Authentication authentication) {
        usersService.updateUser(user, authenticationService.getUserIdByAuthentication(authentication));
        return "redirect:/user";
    }


    @PostMapping("/like/{post-id}")
    public void likePost(Authentication authentication, @PathVariable("post-id") Long postId) {
        postsService.likePost(authenticationService.getUserIdByAuthentication(authentication), postId);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) throws Exception {
        avatarService.writeAvatarToResponse(fileName, response);
    }

    @PostMapping("/upload")
    public String avatarUpload(@ModelAttribute UploadForm form, Authentication authentication, HttpServletRequest request) {

        String serverUrl = request.getProtocol().substring(0, request.getProtocol().indexOf("/")) + "://" +
                request.getServerName() + ":" + request.getServerPort() + "/";

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
                    type, "files/" + login + "_avatar.png",
                    authenticationService.getUserIdByAuthentication(authentication));

            usersService.addAvatarUrl("files/" + login + "_avatar.png",
                    authenticationService.getUserIdByAuthentication(authentication));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/user";
    }

    @PostMapping("/msg+{to-id}+{from-id}")
    @ResponseBody
    public String getMessages(@PathVariable("to-id") Long toId, @PathVariable("from-id") Long fromId) {
        return messageService.getMessages(toId, fromId);
    }

    @GetMapping("/follow/{user-id}")
    public void followUser(Authentication authentication, @PathVariable("user-id") Long toId) {
        usersService.followUser(authenticationService.getUserIdByAuthentication(authentication), toId);
    }

    @GetMapping("/unfollow/{user-id}")
    public void unfollowUser(Authentication authentication, @PathVariable("user-id") Long toId) {
        usersService.unfollowUser(authenticationService.getUserIdByAuthentication(authentication), toId);
    }

    @PostMapping("/repost/{post-id}")
    public void repost(Authentication authentication, @PathVariable("post-id") Long postId) {
        postsService.repost(authenticationService.getUserIdByAuthentication(authentication), postId);
    }

    @GetMapping("/chat")
    public String chat(Authentication authentication, ModelMap chatList, ModelMap user) {
        if (authentication == null) {
            return "redirect:/signIn";
        }
        user.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        chatList.addAttribute("chatListUsers", authenticationService.getChatListByAuthentication(authentication));
        return "Chat";
    }

    @PostMapping("/chat/{user-id}")
    public String createChat(Authentication authentication, @PathVariable("user-id") Long user2) {
        usersService.createChat(authenticationService.getUserIdByAuthentication(authentication), user2);
        return "redirect:/chat";
    }
}