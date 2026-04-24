package formation.sopra.biblio.exception;

public class CollectionNotFoundException extends RuntimeException {
    
    public CollectionNotFoundException(Integer id){
        super("La collection " + id + " n'existe pas");
    }
}
