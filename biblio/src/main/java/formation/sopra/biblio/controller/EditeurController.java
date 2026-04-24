package formation.sopra.biblio.controller;

import formation.sopra.biblio.dto.editeur.EditeurRequest;
import formation.sopra.biblio.dto.editeur.EditeurResponse;
import formation.sopra.biblio.dto.editeur.EditeurWithLivresResponse;
import formation.sopra.biblio.exception.EditeurNotFoundException;
import formation.sopra.biblio.model.Editeur;
import formation.sopra.biblio.repository.IDAOEditeur;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editeur")
public class EditeurController {
    private final IDAOEditeur daoEditeur;
    public EditeurController(IDAOEditeur daoEditeur) {
        this.daoEditeur = daoEditeur;
    }

    @GetMapping
    public List<EditeurResponse> findAll() {
        return daoEditeur
                .findAll()
                .stream()
                .map(EditeurResponse::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public EditeurResponse findById(@PathVariable int id) {
        return daoEditeur
                .findById(id)
                .map(EditeurResponse::convert)
                .orElseThrow(
                        ()->new EditeurNotFoundException("Editeur with id:"+id+"does not exist")
                );
    }

    @GetMapping("/livres/{id}")
    public EditeurWithLivresResponse findByIdWithLivres(@PathVariable int id) {
        return daoEditeur
                .findByIdWithLivres(id)
                .map(EditeurWithLivresResponse::convert)
                .orElseThrow(
                        ()->new EditeurNotFoundException("Editeur with id:"+id+"does not exist")
                );
    }

    @PostMapping()
    public EditeurResponse save(@Valid @RequestBody EditeurRequest editeurRequest) {
        return EditeurResponse
                .convert(daoEditeur
                        .save(EditeurRequest.convert(editeurRequest))
        );
    }

    @PutMapping("/{id}")
    public EditeurResponse put(@PathVariable int id, @Valid @RequestBody EditeurRequest editeurRequest) {
        Editeur editeur = daoEditeur.findById(id)
                .orElseThrow( ()->new EditeurNotFoundException("Editeur with id:"+id+"does not exist"));
        editeur.setNom(editeurRequest.getNom());
        editeur.setPays(editeurRequest.getPays());
        return EditeurResponse.convert(daoEditeur.save(editeur));
    }

    @PatchMapping("/{id}")
    public EditeurResponse patch(@PathVariable int id, @RequestBody EditeurRequest editeurRequest) {
        Editeur editeur = daoEditeur.findById(id)
                .orElseThrow( () -> new EditeurNotFoundException("Editeur with id:"+id+"does not exist") );
        if (editeurRequest.getNom() != null && !editeurRequest.getNom().isBlank()) {
            editeur.setNom(editeurRequest.getNom());
        }
        if (editeurRequest.getPays() != null && !editeurRequest.getPays().isBlank()) {
            editeur.setPays(editeurRequest.getPays());
        }
        return EditeurResponse.convert(daoEditeur.save(editeur));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        /*
            Recuperer tous les livres de l'editeur à supprimer, et mettre à null l'editeur,
         */
        daoEditeur.deleteById(id);
    }

    


}
