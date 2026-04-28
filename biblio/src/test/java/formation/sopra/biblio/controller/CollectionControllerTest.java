package formation.sopra.biblio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import formation.sopra.biblio.config.SecurityConfig;
import formation.sopra.biblio.model.Collection;
import formation.sopra.biblio.repository.IDAOCollection;
import formation.sopra.biblio.repository.IDAOUtilisateur;

@WebMvcTest(controllers = CollectionController.class)
@Import(SecurityConfig.class)
public class CollectionControllerTest {
    private static final int ID = 1;
    private static final String NOM = "Cuisine";

    private static final String API_URL = "/api/collection";
    private static final String API_URL_BY_ID = API_URL + "/" + ID;

    private final Collection COLLECTION = new Collection(ID, NOM);
    
    @Autowired
    private MockMvc mockMvc;

    // pour convertir java et json
    @Autowired
    private ObjectMapper objectMapper;

     @MockitoBean
    private IDAOCollection daoCollection;

    @MockitoBean
private IDAOUtilisateur daoUtilisateur;

    // on veut verifier si la creation d'une collection fctionne correctement
    @Test
    @WithMockUser // Simule un utilisateur authentifie qui peut faire un POST
    void shouldCreateStatusOk() throws Exception {
        when(daoCollection.save(any())).thenReturn(COLLECTION);
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)  // simule requete http
                .contentType("application/json") // precise que la requete est en json
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());  //verifie que la reponse est ok
    }

     @Test
    @WithMockUser
    void shouldUpdateStatusOk() throws Exception {
        when(daoCollection.findById(ID)).thenReturn(Optional.of(COLLECTION));
        when(daoCollection.save(any())).thenReturn(COLLECTION);
        mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(COLLECTION)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    
    @Test
    @WithMockUser
    void shouldDeleteStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
