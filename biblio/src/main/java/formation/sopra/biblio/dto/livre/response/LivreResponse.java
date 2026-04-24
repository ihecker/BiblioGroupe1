package formation.sopra.biblio.dto.livre.response;

import formation.sopra.biblio.model.Livre;

public class LivreResponse {

    private Integer id;
    private String titre;
    private String resume;
    private Integer annee;

    private Integer idAuteur;

    private Integer idEditeur;

    private Integer idCollection;

    private Integer idGenre;

    public LivreResponse() {
    }

    public LivreResponse(Integer id, String titre, String resume, Integer annee, Integer idAuteur,
            Integer idEditeur, Integer idCollection, Integer idGenre) {
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.idAuteur = idAuteur;
        this.idEditeur = idEditeur;
        this.idCollection = idCollection;
        this.idGenre = idGenre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
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

    public static LivreResponse convert(Livre l) {
        LivreResponse response = new LivreResponse();
        response.setId(l.getId());
        response.setTitre(l.getTitre());
        response.setResume(l.getResume());
        response.setAnnee(l.getAnnee());
        response.setIdAuteur(l.getAuteur() != null ? l.getAuteur().getId() : null);
        response.setIdEditeur(l.getEditeur() != null ? l.getEditeur().getId() : null);
        response.setIdCollection(l.getCollection() != null ? l.getCollection().getId() : null);
        response.setIdGenre(l.getGenre() != null ? l.getGenre().getId() : null);
        return response;

    }

}
