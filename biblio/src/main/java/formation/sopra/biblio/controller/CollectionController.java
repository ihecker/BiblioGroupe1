package formation.sopra.biblio.controller;

import java.util.List;
import formation.sopra.biblio.model.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import formation.sopra.biblio.dto.collection.CollectionRequest;
import formation.sopra.biblio.dto.collection.CollectionResponse;
import formation.sopra.biblio.exception.CollectionNotFoundException;
import formation.sopra.biblio.repository.IDAOCollection;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

private final IDAOCollection daoCollection;

CollectionController(IDAOCollection daoCollection){
    this.daoCollection = daoCollection;
}

// GET ALL
@GetMapping
public List<CollectionResponse> getAll(){
    return daoCollection.findAll().stream().map(CollectionResponse::convert).toList();
}

// GET BY ID
@GetMapping("/{id}")
public CollectionResponse getCollectionById(@RequestParam int id){
    return CollectionResponse.convert(daoCollection.findById(id).orElse(null));
}

// CREATE
@PostMapping
public CollectionResponse create(@RequestBody CollectionRequest collectionRequest){
    Collection collection = new Collection();
    collection.setNom(collectionRequest.getNom());

    return CollectionResponse.convert(daoCollection.save(collection));
    }

// UPDATE
@PutMapping("/{id}")
    public CollectionResponse update(@PathVariable Integer id, @RequestBody CollectionRequest collectionRequest){

        Collection collection = daoCollection.findById(id).orElse(null);

        if(collection == null){
            throw new CollectionNotFoundException(id);
        }

        collection.setNom(collectionRequest.getNom());

        return CollectionResponse.convert(daoCollection.save(collection));
    }
// DELETE
@DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        daoCollection.deleteById(id);
    }
}