package pl.coderslab.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime joinedDateTime;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public String getUserName(){
        return user.getUserName();
    }

    public Long getGameId(){
        return game.getId();
    }
}
