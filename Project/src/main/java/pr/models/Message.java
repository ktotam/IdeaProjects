package pr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "from_id")
    private User user;

    private Long toId;

    private String text;

    private LocalDateTime date;

    public String getStringDate() {
        return date.toString().replace("T", " ");
    }

    public Long getFromId() {
        return user.getId();
    }

    public String getAvatarUrl() {
        return user.getAvatarUrl();
    }

}
