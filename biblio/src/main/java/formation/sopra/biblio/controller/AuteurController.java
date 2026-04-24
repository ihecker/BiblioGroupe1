package formation.sopra.biblio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import formation.sopra.biblio.dto.Auteur.AuteurRequest;
import formation.sopra.biblio.dto.Auteur.AuteurResponse;
import formation.sopra.biblio.model.Auteur;
import formation.sopra.biblio.repository.IDAOAuteur;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/auteur")
public class AuteurController {

    private final IDAOAuteur daoAuteur;
   

    public AuteurController(IDAOAuteur daoAuteur) {
        this.daoAuteur = daoAuteur;
    }

    @GetMapping
    public List<AuteurResponse> getAllAuteur() {
        return daoAuteur.findAll()
                .stream()
                .map(AuteurResponse::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public AuteurResponse getAuteurById(@PathVariable int id) {
        return AuteurResponse.convert(daoAuteur.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auteur non trouvé avec l'id: " + id)));
    }

    @PutMapping("/{id}")
    public AuteurResponse modifierAuteur(@PathVariable int id, @Valid @RequestBody AuteurRequest request) {
        

        //find auteur
        Auteur a = daoAuteur.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auteur non trouvé avec l'id: " + id));

        //maj auteur 
        a.setNom(request.getNom());
        a.setPrenom(request.getPrenom());
        a.setNationalite(request.getNationalite());

        //save auteur
        Auteur tosave = daoAuteur.save(a);

        return AuteurResponse.convert(tosave);
    }

    @PostMapping
    public AuteurResponse ajouterAuteur(@Valid @RequestBody AuteurRequest request) {
        return AuteurResponse
                .convert(daoAuteur
                            .save(AuteurRequest.convert(request)));
    }

    @DeleteMapping("/{id}")
    public void deleteAuteur(@PathVariable int id) {
        daoAuteur.deleteById(id);
    }
    
    
    

}
