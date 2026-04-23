package formation.sopra.biblio.dto.editeur;

import formation.sopra.biblio.dto.livre.response.LivreResponse;
import formation.sopra.biblio.model.Editeur;

import java.util.List;

public class EditeurWithLivresResponse {
    private Integer id;
    private String nom;
    private String pays;
    private List<LivreResponse> livres;

    public EditeurWithLivresResponse(Integer id, String nom, String pays,  List<LivreResponse> livres) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.livres = livres;
    }
    public EditeurWithLivresResponse() {}

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

    public List<LivreResponse> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreResponse> livres) {
        this.livres = livres;
    }

    public static EditeurWithLivresResponse convert(Editeur editeur) {
        EditeurWithLivresResponse response = new EditeurWithLivresResponse();
        response.setNom(editeur.getNom());
        response.setPays(editeur.getPays());
        response.setId(editeur.getId());
        response.setLivres(editeur.getLivres()
                .stream()
                .map(LivreResponse::convert)
                .toList()
        );
        return response;
    }
}
