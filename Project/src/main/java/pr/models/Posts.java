package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String post;
    private String headline;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getUserId() {
        return user.getId();
    }

}
