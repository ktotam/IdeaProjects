package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_posts")

public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private int likes;

    private LocalDateTime date;
    private String post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "posts_likes", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> like_users;

    @ManyToMany
    @JoinTable(name = "reposts", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> repostedUsers;

    public Long getUserId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getAvatarUrl() {
        return user.getAvatarUrl();
    }

    public ArrayList<Long> getUsersLikes() {
        ArrayList<Long> arrayList = new ArrayList<>();

        for (User user : like_users) {
            arrayList.add(user.getId());
        }

        return arrayList;
    }
}
