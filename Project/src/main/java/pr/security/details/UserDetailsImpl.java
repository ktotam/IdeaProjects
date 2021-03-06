package pr.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pr.models.User;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String login;
    private String hashPassword;
    private Long id;
    private Integer age;
    private String name;
    private int followers;
    private String avatarUrl;


    public UserDetailsImpl (User user) {
        this.login = user.getLogin();
        this.hashPassword = user.getHashPassword();
        this.id = user.getId();
        this.age = user.getAge();
        this.name = user.getName();
        this.avatarUrl = user.getAvatarUrl();
        this.followers = user.getFollowersCount();

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.hashPassword;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getName() {
        return this.name;
    }
}
