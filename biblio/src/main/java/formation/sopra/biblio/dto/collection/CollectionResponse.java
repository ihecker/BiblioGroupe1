package formation.sopra.biblio.dto.collection;

import java.util.Collection;

public class CollectionResponse {
    private String nom;

    public CollectionResponse() {
    }

    public CollectionResponse(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static CollectionResponse convert(Collection r){
        CollectionResponse response = new CollectionResponse();
        r.setNom(r.getNom());
        return response;
    }
}
