package formation.sopra.biblio.repository;

import formation.sopra.biblio.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {

    @Query("SELECT e FROM Editeur e LEFT JOIN FETCH e.livres WHERE e.id = :id")
    Optional<Editeur> findByIdWithLivres(@Param("id") Integer id);
}
