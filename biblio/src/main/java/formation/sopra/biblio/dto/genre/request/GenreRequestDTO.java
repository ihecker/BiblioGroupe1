package formation.sopra.biblio.dto.genre.request;

public class GenreRequestDTO {
	private String libelle;
	
	
	public GenreRequestDTO() {}
	
	public GenreRequestDTO(String libelle) {
		this.libelle = libelle;
	}


	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

}
