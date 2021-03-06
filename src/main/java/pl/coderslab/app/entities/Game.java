package pl.coderslab.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.app.repositories.CandidateRepository;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private Long id;


    @Future
    private LocalDate gameDate;


    private LocalTime startTime;


    private int gameTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pitch_id")
    private Pitch pitch;

    private int maxPlayer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_players")
    private List<User> players = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    private boolean available;

    @Column(columnDefinition = "double default 0.0")
    private double pricePerPlayer;

    @NotEmpty
    private String description;

    public void checkAvailable(boolean available) {
        if(maxPlayer == players.size()){
            this.available = false;
        } else {
            this.available = available;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameDate=" + gameDate +
                ", startTime=" + startTime +
                ", gameTime=" + gameTime +
                ", pitch=" + pitch +
                ", maxPlayer=" + maxPlayer +
                ", players=" + players +
                ", creator=" + creator +
                ", available=" + available +
                ", pricePerPlayer=" + pricePerPlayer +
                ", description='" + description + '\'' +
                '}';
    }


}
