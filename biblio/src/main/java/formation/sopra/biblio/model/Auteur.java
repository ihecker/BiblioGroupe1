package formation.sopra.biblio.model;

import java.util.List;

import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
@Table(name = "auteur")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auteur_id")
    private int id;


    private String nom;
    private String prenom;
    private String nationalite;


    // Mapping avec la classe Livre (1 auteur peut avoir plusieurs livres)
    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    @Version
    private int version;

    

    public Auteur() {
    }


    public Auteur(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }


    public Auteur(int id, String nom, String prenom, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNationalite() {
        return nationalite;
    }


    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }


    public List<Livre> getLivres() {
        return livres;
    }


    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }


    public int getVersion() {
        return version;
    }


    public void setVersion(int version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + ", livres="
                + livres + "]";
    }


    
    



    
}
