package formation.sopra.biblio.dto.livre.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LivreRequest {
    @NotNull
    private String titre;

    @NotNull
    private String resume;

    @Positive
    private int annee;

    @Min(value = 1, message = "Un ID ne peut être ni nul ni négatif")
    private Integer idAuteur;

    @Min(value = 1, message = "Un ID ne peut être ni nul ni négatif")
    private Integer idEditeur;

    @Min(value = 1, message = "Un ID ne peut être ni nul ni négatif")
    private Integer idCollection;

    @Min(value = 1, message = "Un ID ne peut être ni nul ni négatif")
    private Integer idGenre;

    public LivreRequest() {
    }

    public LivreRequest(String titre, String resume, int annee, Integer idAuteur, Integer idEditeur,
            Integer idCollection,
            Integer idGenre) {
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.idAuteur = idAuteur;
        this.idEditeur = idEditeur;
        this.idCollection = idCollection;
        this.idGenre = idGenre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Integer getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Integer idAuteur) {
        this.idAuteur = idAuteur;
    }

    public Integer getIdEditeur() {
        return idEditeur;
    }

    public void setIdEditeur(Integer idEditeur) {
        this.idEditeur = idEditeur;
    }

    public Integer getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Integer idCollection) {
        this.idCollection = idCollection;
    }

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

}
