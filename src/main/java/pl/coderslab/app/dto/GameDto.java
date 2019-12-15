package pl.coderslab.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.app.entities.Pitch;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private String gameDate;

    private String startTime;

    private int gameTime;

    private String pitch;

    private int maxPlayers;

    private boolean available;

    private double pricePerPlayer;

    private String description;

    @Override
    public String toString() {
        return "GameDto{" +
                "gameDate='" + gameDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", gameTime=" + gameTime +
                ", pitch='" + pitch + '\'' +
                ", maxPlayers=" + maxPlayers +
                ", available=" + available +
                ", pricePerPlayer=" + pricePerPlayer +
                ", description='" + description + '\'' +
                '}';
    }
}
