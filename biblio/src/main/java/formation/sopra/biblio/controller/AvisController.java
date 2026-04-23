package formation.sopra.biblio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formation.sopra.biblio.dto.avis.AvisRequest;
import formation.sopra.biblio.dto.avis.AvisResponse;
import formation.sopra.biblio.model.Avis;
import formation.sopra.biblio.repository.IDAOAvis;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    private final IDAOAvis daoAvis;

    AvisController(IDAOAvis daoAvis) {
        this.daoAvis = daoAvis;
    }

    @GetMapping
    public List<AvisResponse> getAll() {
        return daoAvis.findAll()
                .stream()
                .map(AvisResponse::convert)
                .toList();
        ;
    }

    @GetMapping("/{id}")
    public AvisResponse getAvisById(@RequestParam int id) {
        return AvisResponse.convert(daoAvis.findById(id).orElse(null));
    }

    @PostMapping
    public AvisResponse insert(@RequestBody AvisRequest avisRequest) {
        Avis a = new Avis();

        a.setNote(avisRequest.getNote());
        a.setCommentaire(avisRequest.getCommentaire());
        a.setDate(avisRequest.getDate());
        a.setLivre(avisRequest.getLivre());

        return AvisResponse.convert(daoAvis.save(a));
    }

    @PutMapping("/{id}")
    public AvisResponse update(@PathVariable Integer id, @RequestBody AvisRequest avisRequest) {
        Avis a = daoAvis.findById(id).orElse(null);

        if (a == null) {
            throw new AvisNotFoundException(id);
        }

        a.setNote(avisRequest.getNote());
        a.setCommentaire(avisRequest.getCommentaire());
        a.setDate(avisRequest.getDate());
        a.setLivre(avisRequest.getLivre());

        return AvisResponse.convert(daoAvis.save(a));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        daoAvis.deleteById(id);
    }

}
