package formation.sopra.biblio.dto.genre.response;

import org.springframework.beans.BeanUtils;

import formation.sopra.biblio.model.Genre;
import formation.sopra.biblio.model.Livre;

public class GenreWithLivreResponseDTO {
	
	private Integer id;
	private String libelle;
	private Livre livre;
	
	public GenreWithLivreResponseDTO() {}
	
	
	public GenreWithLivreResponseDTO(Integer id, String libelle, Livre livre) {
		this.id = id;
		this.libelle = libelle;
		this.livre = livre;
	}
	public Integer getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	
	public static GenreWithLivreResponseDTO convert(Genre genre) {
		GenreWithLivreResponseDTO response = new GenreWithLivreResponseDTO();
		BeanUtils.copyProperties(genre, response);
        response.setLivres(genre.getLivres()
                .stream()
                .map(LivreResponse::convert)
                .toList()
        );
        return response;
    }
	
	

}
