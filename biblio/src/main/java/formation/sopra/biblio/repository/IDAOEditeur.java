package formation.sopra.biblio.repository;

import formation.sopra.biblio.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {
}
