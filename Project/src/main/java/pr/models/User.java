package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    private int followers;

    
    private Long avatarId;

    @Column(unique = true)
    private String login;

    @Column(name = "hash_password")
    private String hashPassword;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts;

}

