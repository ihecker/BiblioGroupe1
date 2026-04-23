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

import formation.sopra.biblio.config.SecurityConfig;

@WebMvcTest(controllers = LivreController.class)
@Import(SecurityConfig.class)
public class LivreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // @MockitoBean
    // private JpaUserDetailsService jpaUserDetailsService;

    // @MockitoBean
    // private JwtUtils jwtUtils;

    private static final Integer ID = 1;
    private static final String TITRE = "Un titre de livre intriguant";
    private static final String RESUME = "Un résumé intriguant de livre intriguant";
    private static final Integer ANNEE = 2026;
    private static final Integer ID_AUTEUR = 10;
    private static final Integer ID_EDITEUR = 20;
    private static final Integer ID_GENRE = 30;
    private static final Integer ID_COLLECTION = 40;

    private static final String API_URL = "/api/livres";
    private static final String API_URL_BY_ID = API_URL + "/" + ID;

    @Test
    @WithMockUser
    void shouldCreateStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
                .contentType("application/json")
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
                .contentType("application/json")
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldPatchStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(API_URL_BY_ID)
                .contentType("application/json")
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
