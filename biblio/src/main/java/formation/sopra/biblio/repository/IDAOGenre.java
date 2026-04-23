package formation.sopra.biblio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.sopra.biblio.model.Genre;
import formation.sopra.biblio.model.Livre;

public interface IDAOGenre extends JpaRepository<Genre,Integer>{
	
	@Query ("SELECT g FROM Genre g LEFT JOIN FETCH g.livre where g.id=:id")
	public List<Livre>GenreWithLivre(@Param("id") Integer id);
	

}
