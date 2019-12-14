package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entities.Pitch;

import java.util.List;

public interface PitchRepository extends JpaRepository<Pitch, Long> {

    @Override
    <S extends Pitch> S save(S s);

    @Override
    List<Pitch> findAll();

    Pitch findFirstByName(String pitchName);

}
