package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service_user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthDate;
    private String name;
    private Integer age;

    @Column(nullable = true)
    private int followersCount;

    @Column(name = "posts_count")
    private int postsCount;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(unique = true)
    private String login;

    @Column(name = "hash_password")
    private String hashPassword;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @ManyToMany
    @JoinTable(name = "follow_users", joinColumns = @JoinColumn(name = "from_id"), inverseJoinColumns = @JoinColumn(name = "to_id"))
    private List<User> follows;

    @ManyToMany
    @JoinTable(name = "chat_list", joinColumns = @JoinColumn(name = "user1"), inverseJoinColumns = @JoinColumn(name = "user2"))
    private List<Chat> chatList;

    @ManyToMany
    @JoinTable(name = "follow_users", joinColumns = @JoinColumn(name = "to_id"), inverseJoinColumns = @JoinColumn(name = "from_id"))
    private List<User> followers;

    @ManyToMany
    @JoinTable(name = "posts_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Posts> like_posts;

    @ManyToMany
    @JoinTable(name = "reposts", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Posts> reposts;

}

