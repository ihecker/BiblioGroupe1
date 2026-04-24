package formation.sopra.biblio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import formation.sopra.biblio.repository.IDAOCollection;

@WebMvcTest(controllers = CollectionController.class)
@Import(SecurityConfig.class)
public class CollectionControllerTest {
    private static final int ID = 1;
    private static final String NOM = "Cuisine";

    private static final String API_URL = "/api/collection";
    private static final String API_URL_BY_ID = API_URL + "/" + ID;

    @Autowired
    private MockMvc mockMvc;

     @MockitoBean
    private IDAOCollection daoCollection;

    // on veut verifier si la creation d'une collection fctionne correctement
    @Test
    @WithMockUser // Simule un utilisateur authentifie
    void shouldCreateStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)  // simule requete http
                .contentType("application/json") // precise que la requete est en json
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());  //verifie que la reponse est ok
    }

    @Test
    @WithMockUser
    void shouldDeleteStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
