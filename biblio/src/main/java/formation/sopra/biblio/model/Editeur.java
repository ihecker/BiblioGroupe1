package formation.sopra.biblio.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="editeur_id")
    private Integer id;

    @Column(nullable=false)
    private String nom;

    @Column(nullable=false)
    private String pays;

    @OneToMany(mappedBy = "editeur")
    private List<Livre> livres;

    public Editeur() {}
    public Editeur(Integer id, String nom, String pays) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
    }
    public Editeur(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Editeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
