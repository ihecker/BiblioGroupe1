package formation.sopra.biblio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.biblio.model.Genre;

public interface IDAOGenre extends JpaRepository<Genre,Integer>{
	
	@Query ("SELECT g FROM Genre g LEFT JOIN FETCH g.livres where g.id=:id")
	public Optional<Genre> GenreWithLivre(@Param("id") Integer id);
	

}
