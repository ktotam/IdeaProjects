package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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


    private String name;
    private Integer age;

    @Column(nullable = true)
    private int likes;

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

}

