package formation.sopra.biblio.repository;
import formation.sopra.biblio.model.Collection;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IDAOCollection extends JpaRepository<Collection, Integer>{
    
}
