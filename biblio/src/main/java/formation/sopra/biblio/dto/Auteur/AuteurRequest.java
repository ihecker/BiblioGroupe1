package formation.sopra.biblio.dto.Auteur;

import formation.sopra.biblio.model.Auteur;

public class AuteurRequest {

    private String nom;
    private String prenom;
    private String nationalite;

  
    
    public AuteurRequest() {
    }
    
    public AuteurRequest(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
      
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

    public static AuteurRequest convert(Auteur auteur) {
        return new AuteurRequest(auteur.getNom(), auteur.getPrenom(), auteur.getNationalite());
    }

    
    
    
}
