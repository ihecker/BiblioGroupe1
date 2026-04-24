package formation.sopra.biblio.dto.Auteur;

import formation.sopra.biblio.model.Auteur;

public class AuteurResponse {
    
    private int id;
    private String nom;
    private String prenom;
    private String nationalite;

    public AuteurResponse() {
    }

    public AuteurResponse(int id, String nom, String prenom, String nationalite) {
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

    public static AuteurResponse convert(Auteur auteur) {
        return new AuteurResponse(auteur.getId(), auteur.getNom(), auteur.getPrenom(), auteur.getNationalite()); 
    }



}
