package formation.sopra.biblio.dto.avis;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import formation.sopra.biblio.model.Avis;

public class AvisResponse {

    private Integer id;
    private Integer note;
    private String commentaire;
    private LocalDate date;
    private Integer livreId;
    private String livreTitre;

    public AvisResponse(Integer id, Integer note, String commentaire, LocalDate date, Integer livreId,
            String livreTitre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.date = date;
        this.livreId = livreId;
        this.livreTitre = livreTitre;
    }

    public AvisResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLivreTitre() {
        return livreTitre;
    }

    public void setLivreTitre(String livreTitre) {
        this.livreTitre = livreTitre;
    }
    
    public static AvisResponse convert(Avis avis) {
        
        AvisResponse response = new AvisResponse();
        
        BeanUtils.copyProperties(avis, response);

        response.setLivreId(avis.getLivre().getId());
        response.setLivreTitre(avis.getLivre().getTitre().toString());
        return response;

    }
    
}
