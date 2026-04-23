package formation.sopra.biblio.dto.collection;

public class CollectionRequest {
    private String nom;

    public CollectionRequest() {
    }

    public CollectionRequest(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
