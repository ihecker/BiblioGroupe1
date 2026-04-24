package formation.sopra.biblio.dto.genre.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import formation.sopra.biblio.dto.livre.response.LivreResponse;
import formation.sopra.biblio.model.Genre;

public class GenreWithLivreResponseDTO {
	
	private Integer id;
	private String libelle;
	private List<LivreResponse> livres;
	
	
	public GenreWithLivreResponseDTO() {}
	
	
	public GenreWithLivreResponseDTO(Integer id, String libelle, List<LivreResponse> livres) {
		this.id = id;
		this.libelle = libelle;
		this.livres = livres;
	}
	public Integer getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	public List<LivreResponse> getLivres() {
		return livres;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setLivres(List<LivreResponse> livres) {
		this.livres = livres;
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
