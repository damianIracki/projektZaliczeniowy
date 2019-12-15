package pl.coderslab.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PitchDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    private String type;
}
