package formation.sopra.biblio.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import formation.sopra.biblio.config.SecurityConfig;
import formation.sopra.biblio.model.Avis;
import formation.sopra.biblio.model.Editeur;
import formation.sopra.biblio.model.Livre;
import formation.sopra.biblio.repository.IDAOAvis;

@WebMvcTest(controllers = AvisController.class)
public class AvisControllerTest {

    private static final int AVIS_ID = 1;
    private static final int AVIS_NOTE = 10;
    private static final LocalDate AVIS_DATE = LocalDate.parse("2026-02-01");
    private static final String AVIS_COMMENTAIRE = "Commentaire 1";
    private static final Auteur AUTEUR = new Auteur(1, "Doe", "John", "Française");
    private static final Editeur EDITEUR = new Editeur(1, "Test editeur", "France");
    private static final Collection COLLECTION = new Collection(1, "Collection 1");
    private static final Genre GENRE = new Genre(1, "Test libelle genre");
    private static final Livre AVIS_LIVRE = new Livre(1, "Titre 1", "Résumé 1", 2022, AUTEUR, EDITEUR, COLLECTION,
            GENRE);

    private static final String API_URL = "/api/avis";
    private static final String API_URL_BY_ID = API_URL + "/" + AVIS_ID;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOAvis daoAvis;

    @Test
    void shouldFindAllStatusOkAndAttibutesOk() throws Exception {
        // given
        Avis a1 = new Avis(AVIS_ID, AVIS_NOTE, AVIS_COMMENTAIRE, AVIS_DATE, AVIS_LIVRE);

        Mockito.when(this.daoAvis.findAll()).thenReturn(List.of(a1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].note").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].commentaire").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].date").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].livre").doesNotExist());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].livreId").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].livreTitre").exists());

        Mockito.verify(this.daoAvis).findAll();
    }

}
