package formation.sopra.biblio.exception;

public class AvisNotFoundException extends RuntimeException {

    public AvisNotFoundException(Integer id) {
        super("L'avis numéro " + id + " est introuvable");
    }
}
