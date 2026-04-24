package formation.sopra.biblio.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="genre")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genre_id")
	private Integer id;
	
	@Column(name="libelle")
	private String libelle;
	
	@OneToMany(mappedBy="genre")
	private List<Livre> livres;
	
	
	public Genre() {}


	public Genre(Integer id, String libelle, List<Livre> livres) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

	public List<Livre> getLivres() {
		return livres;
	}


	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}


	@Override
	public String toString() {
		return "Genre [id=" + id + ", libelle=" + libelle + "]";
	}

	
	
	

}
