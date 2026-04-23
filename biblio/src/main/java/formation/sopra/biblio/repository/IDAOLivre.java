package formation.sopra.biblio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.biblio.model.Livre;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {

}
