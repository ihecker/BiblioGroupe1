package formation.sopra.biblio.repository;

import formation.sopra.biblio.model.Utilisateur;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
    @Query("select u from Utilisateur u where u.login = :login")
    public Optional<Utilisateur> findByUsernameOptional(@Param("login") String login);
}
