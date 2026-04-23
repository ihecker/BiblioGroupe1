package formation.sopra.biblio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import formation.sopra.biblio.dto.livre.request.LivreRequest;
import formation.sopra.biblio.dto.livre.response.LivreResponse;
import formation.sopra.biblio.model.Livre;
import formation.sopra.biblio.repository.IDAOAuteur;
import formation.sopra.biblio.repository.IDAOEditeur;
import formation.sopra.biblio.repository.IDAOLivre;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final IDAOLivre daoLivre;
    private final IDAOAuteur daoAuteur;
    private final IDAOEditeur daoEditeur;
    private final IDAOCollection daoCollection;
    private final IDAOGenre daoGenre;

    public LivreController(IDAOLivre daoLivre, IDAOAuteur daoAuteur, IDAOEditeur daoEditeur,
            IDAOCollection daoCollection,
            IDAOGenre daoGenre) {
        this.daoLivre = daoLivre;
        this.daoAuteur = daoAuteur;
        this.daoEditeur = daoEditeur;
        this.daoCollection = daoCollection;
        this.daoGenre = daoGenre;

    }

    @GetMapping
    public List<LivreResponse> getAll() {
        return daoLivre.findAll()
                .stream()
                .map(LivreResponse::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public LivreResponse getLivreById(@PathVariable int id) {
        return LivreResponse.convert(daoLivre.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livre introuvable")));
    }

    @PostMapping
    public LivreResponse insert(@Valid @RequestBody LivreRequest livreRequest) {
        Livre l = new Livre();

        l.setTitre(livreRequest.getTitre());
        l.setResume(livreRequest.getResume());
        l.setAnnee(livreRequest.getAnnee());
        l.setAuteur(daoAuteur.findById(livreRequest.getIdAuteur())
                .orElseThrow(() -> new EntityNotFoundException("Auteur introuvable")));
        l.setEditeur(daoEditeur.findById(livreRequest.getIdEditeur())
                .orElseThrow(() -> new EntityNotFoundException("Editeur introuvable")));
        l.setCollection(daoCollection.findById(livreRequest.getIdCollection())
                .orElseThrow(() -> new EntityNotFoundException("Collection introuvable")));
        l.setGenre(daoGenre.findById(livreRequest.getIdGenre())
                .orElseThrow(() -> new EntityNotFoundException("Genre introuvable")));

        return LivreResponse.convert(daoLivre.save(l));
    }

    @PutMapping("/{id}")
    public LivreResponse update(@PathVariable int id, @Valid @RequestBody LivreRequest livreRequest) {
        Livre l = daoLivre.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livre introuvable"));

        l.setTitre(livreRequest.getTitre());
        l.setResume(livreRequest.getResume());
        l.setAnnee(livreRequest.getAnnee());
        l.setAuteur(daoAuteur.findById(livreRequest.getIdAuteur())
                .orElseThrow(() -> new EntityNotFoundException("Auteur introuvable")));
        l.setEditeur(daoEditeur.findById(livreRequest.getIdEditeur())
                .orElseThrow(() -> new EntityNotFoundException("Editeur introuvable")));
        l.setCollection(daoCollection.findById(livreRequest.getIdCollection())
                .orElseThrow(() -> new EntityNotFoundException("Collection introuvable")));
        l.setGenre(daoGenre.findById(livreRequest.getIdGenre())
                .orElseThrow(() -> new EntityNotFoundException("Genre introuvable")));

        return LivreResponse.convert(daoLivre.save(l));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Livre l = daoLivre.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livre introuvable"));

        daoLivre.delete(l);
        return ResponseEntity.noContent().build();
    }

}
