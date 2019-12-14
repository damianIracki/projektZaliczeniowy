package pl.coderslab.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty
    private Date startDate;

    @NotEmpty
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "pitch_id")
    private Pitch pitch;

    @NotEmpty
    private int maxPlayer;

    @OneToMany
    @JoinColumn(name = "player_id")
    private List<User> players = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    private boolean available;

    private double pricePerPlayer;

    private String description;

    public void setAvailable(boolean available) {
        if(maxPlayer == players.size()){
            this.available = false;
        } else {
            this.available = available;
        }
    }
}
