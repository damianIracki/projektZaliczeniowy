package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository  extends JpaRepository<Game, Long> {

    @Override
    <S extends Game> S save(S s);

    @Override
    List<Game> findAll();

    List<Game> findFirst5ByOrderByGameDateAsc();

    Game findFirstById(Long id);

    List<Game> findFirst5ByGameDateAfterOrderByGameDateAsc(LocalDate gameDate);

    List<Game> findByCreatorOrderByGameDateAscStartTimeAsc(User user);

    @Override
    void delete(Game game);

    List<Game> findFirst5ByPlayersNotContainsAndGameDateAfterOrderByGameDateAsc(User user, LocalDate gameDate);

    List<Game> findByPlayersNotContainsAndGameDateAfterOrderByGameDateAscStartTimeAsc(User user, LocalDate gameDate);

    List<Game> findByPlayersContainsAndGameDateAfterOrderByGameDateAscGameTimeAsc(User user, LocalDate gameDate);

}
