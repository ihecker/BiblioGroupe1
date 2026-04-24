package formation.sopra.biblio.dto.collection;
import formation.sopra.biblio.model.Collection;

public class CollectionResponse {
    private Integer id;
    private String nom;

    public CollectionResponse() {
    }

    public CollectionResponse(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public static CollectionResponse convert(Collection collection){
        CollectionResponse response = new CollectionResponse();
        response.setId(collection.getId());
        response.setNom(collection.getNom());
        return response;
    }
}
