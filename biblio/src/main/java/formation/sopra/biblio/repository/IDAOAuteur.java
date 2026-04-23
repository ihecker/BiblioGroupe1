package formation.sopra.biblio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.sopra.biblio.model.Auteur;

public interface IDAOAuteur extends JpaRepository<Auteur, Integer> {

}
