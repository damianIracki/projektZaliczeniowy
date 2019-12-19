package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Override
    List<User> findAll();

    User findFirstByUserName(String userName);

    User findFirstById(Long id);
}
