package formation.sopra.biblio.dto.editeur;

import formation.sopra.biblio.model.Editeur;

public class EditeurResponse {
    private Integer id;
    private String nom;
    private String pays;

    public EditeurResponse(Integer id, String nom, String pays) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
    }
    public EditeurResponse() {}

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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public static EditeurResponse convert(Editeur editeur) {
        EditeurResponse response = new EditeurResponse();
        response.setNom(editeur.getNom());
        response.setPays(editeur.getPays());
        response.setId(editeur.getId());
        return response;
    }
}
