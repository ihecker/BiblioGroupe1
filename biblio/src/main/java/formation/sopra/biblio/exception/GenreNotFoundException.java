package formation.sopra.biblio.exception;

public class GenreNotFoundException extends RuntimeException {
  
    public GenreNotFoundException(int id) {
        super("Genre not found with id: " + id);
    }

}
