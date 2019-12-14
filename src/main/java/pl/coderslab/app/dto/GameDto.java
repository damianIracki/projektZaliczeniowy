package pl.coderslab.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.app.entities.Pitch;

import java.sql.Time;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private LocalDate gameDate;

    private Time startTime;

    private int gameTime;

    private Pitch pitch;

    private int maxPlayers;

    private boolean available;

    private double pricePerPlayer;

    private String description;



}
