package formation.sopra.biblio.dto.avis;

import java.time.LocalDate;

public class AvisRequest {

    private Integer note;
    private String commentaire;
    private LocalDate date;
    private Integer livreId;
    
    public AvisRequest(Integer note, String commentaire, LocalDate date, Integer livreId) {
        this.note = note;
        this.commentaire = commentaire;
        this.date = date;
        this.livreId = livreId;
    }

    public AvisRequest() {
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getLivreId() {
        return livreId;
    }

    public void setLivreId(Integer livreId) {
        this.livreId = livreId;
    }

    
}
