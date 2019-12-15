package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.entities.Pitch;
import pl.coderslab.app.repositories.PitchRepository;

public class PitchConverter implements Converter<String, Pitch> {

    @Autowired
    PitchRepository pitchRepository;

    @Override
    public Pitch convert(String s) {
        return pitchRepository.getOne(Long.parseLong(s));
    }
}
