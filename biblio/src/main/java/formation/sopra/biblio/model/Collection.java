import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @OneToMany(mappedBy="collection")
    private List<Livre> livres;

    public Collection() {
    }

    public Collection(Integer id, String nom, List<Livre> livres) {
        this.id = id;
        this.nom = nom;
        this.livres = livres;
    }

    public Collection(String nom, List<Livre> livres) {
        this.nom = nom;
        this.livres = livres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Collection [id=" + id + ", nom=" + nom + ", livres=" + livres + "]";
    }
    
}