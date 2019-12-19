package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entities.Candidate;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.User;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Override
    <S extends Candidate> S save(S s);

    List<Candidate> findAllByGameOrderByJoinedDateTimeAsc(Game game);

    List<Candidate> findAllByUser(User user);

    Candidate findFirstByUserAndGame(User user, Game game);

}
