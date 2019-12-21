package pl.coderslab.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.app.entities.Pitch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    @NotEmpty
    private String gameDate;

    @NotEmpty
    private String startTime;

    @NotNull
    private int gameTime;

    @NotEmpty
    private String pitch;

    @NotNull
    private int maxPlayers;

    private boolean available;


    private double pricePerPlayer;

    @NotEmpty
    private String description;

}
