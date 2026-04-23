package formation.sopra.biblio.controller;

import formation.sopra.biblio.dto.editeur.EditeurRequest;
import formation.sopra.biblio.dto.editeur.EditeurResponse;
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

    @PostMapping()
    public EditeurResponse save(@Valid @RequestBody EditeurRequest editeurRequest) {
        return EditeurResponse
                .convert(daoEditeur
                        .save(EditeurRequest.convert(editeurRequest))
        );
    }

    


}
