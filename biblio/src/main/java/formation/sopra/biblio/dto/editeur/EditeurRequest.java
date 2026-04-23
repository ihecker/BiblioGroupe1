package formation.sopra.biblio.dto.editeur;

import formation.sopra.biblio.model.Editeur;
import jakarta.validation.constraints.NotBlank;

public class EditeurRequest {
    @NotBlank
    private String nom;
    @NotBlank
    private String pays;

    public EditeurRequest() {
    }
    public EditeurRequest(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
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

    public static Editeur convert(EditeurRequest editeurRequest) {
        Editeur editeur = new Editeur();
        editeur.setNom(editeurRequest.getNom());
        editeur.setPays(editeurRequest.getPays());
        return editeur;
    }
}
