package formation.sopra.biblio.dto.genre.response;

import org.springframework.beans.BeanUtils;


import formation.sopra.biblio.model.Genre;


public class GenreResponseDTO {
	private Integer id;
	private String libelle;
	
	public GenreResponseDTO() {}
	
	public GenreResponseDTO(Integer id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}
	public String getLibelle() {
		return libelle;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	public static GenreResponseDTO convert (Genre genre) {
		GenreResponseDTO response = new GenreResponseDTO();
		BeanUtils.copyProperties(genre, response);
		return response;
	}
	

}
