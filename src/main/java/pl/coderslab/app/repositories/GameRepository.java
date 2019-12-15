package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entities.Game;
import pl.coderslab.app.entities.User;

import java.util.List;

public interface GameRepository  extends JpaRepository<Game, Long> {

    @Override
    <S extends Game> S save(S s);

    @Override
    List<Game> findAll();

    List<Game> findFirst5ByOrderByGameDateAsc();

    Game findFirstById(Long id);

    List<Game> findFirst5ByPlayersNotContainsOrderByGameDateAsc(User user);

}
