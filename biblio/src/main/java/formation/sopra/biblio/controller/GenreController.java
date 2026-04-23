package formation.sopra.biblio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import formation.sopra.biblio.dto.genre.request.GenreRequestDTO;
import formation.sopra.biblio.dto.genre.response.GenreResponseDTO;
import formation.sopra.biblio.dto.genre.response.GenreWithLivreResponseDTO;
import formation.sopra.biblio.model.Genre;
import formation.sopra.biblio.repository.IDAOGenre;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
	
	private final IDAOGenre daoGenre;

    GenreController(IDAOGenre daoGenre) {
        this.daoGenre = daoGenre;
    }
	
	@GetMapping
    public List<GenreResponseDTO> getAll() {
        return daoGenre.findAll()
                .stream()
                .map(GenreResponseDTO::convert)
                .toList();
        
    }

    @GetMapping("/{id}")
    public GenreResponseDTO getGenreById(@RequestParam int id) {
        return GenreResponseDTO.convert(daoGenre.findById(id).orElse(null));
    }
    
    @GetMapping
    public GenreWithLivreResponseDTO findByIdWithLivres(@PathVariable int id) {
        return daoGenre
                .GenreWithLivre(id)
                .map(GenreWithLivreResponseDTO::convert)
                .orElseThrow(
                        ()->new GenreNotFoundException("Le genre avec l'id :"+id+"n'existe pas")
                );
    }


    @PostMapping
    public GenreResponseDTO insert(@RequestBody GenreRequestDTO genreRequest) {
        Genre g = new Genre();

        g.setLibelle(genreRequest.getLibelle());
        

        return GenreResponseDTO.convert(daoGenre.save(g));
    }

    @PutMapping("/{id}")
    public GenreResponseDTO update(@PathVariable Integer id, @RequestBody GenreRequestDTO genreRequest) {
        Genre g = daoGenre.findById(id).orElse(null);

        if (g == null) {
            throw new GenreNotFoundException(id);
        }
        g.setLibelle(genreRequest.getLibelle());
        

        return GenreResponseDTO.convert(daoGenre.save(g));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        daoGenre.deleteById(id);
    }

}
