package formation.sopra.biblio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import formation.sopra.biblio.repository.*;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import formation.sopra.biblio.config.SecurityConfig;
import formation.sopra.biblio.model.Auteur;
import formation.sopra.biblio.model.Collection;
import formation.sopra.biblio.model.Editeur;
import formation.sopra.biblio.model.Genre;
import formation.sopra.biblio.model.Livre;

@WebMvcTest(controllers = LivreController.class)
@Import(SecurityConfig.class)
public class LivreControllerTest {
    @MockitoBean
    private IDAOLivre daoLivre;

    @MockitoBean
    private IDAOAuteur daoAuteur;

    @MockitoBean
    private IDAOEditeur daoEditeur;

    @MockitoBean
    private IDAOGenre daoGenre;

    @MockitoBean
    private IDAOCollection daoCollection;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IDAOUtilisateur daoUtilisateur;

    // @MockitoBean
    // private JpaUserDetailsService jpaUserDetailsService;

    // @MockitoBean
    // private JwtUtils jwtUtils;

    private static final Integer ID = 1;
    private static final String TITRE = "Un titre de livre intriguant";
    private static final String RESUME = "Un résumé intriguant de livre intriguant";
    private static final Integer ANNEE = 2026;
    private static final Integer ID_AUTEUR = 1;
    private static final Integer ID_EDITEUR = 1;
    private static final Integer ID_GENRE = 1;
    private static final Integer ID_COLLECTION = 1;
    private static final Auteur AUTEUR = new Auteur(ID_AUTEUR, "Nom", "Prenom", "Nationalite");
    private static final Editeur EDITEUR = new Editeur(ID_EDITEUR, "Nom", "Pays");
    private static final Genre GENRE = new Genre(ID_GENRE, "Libelle genre", null);
    private static final Collection COLLECTION = new Collection(ID_COLLECTION, "Nom collection", null);
    private static final Livre LIVRE = new Livre(ID, TITRE, RESUME, ANNEE, AUTEUR, EDITEUR, COLLECTION, GENRE);

    private static final String API_URL = "/api/livres";
    private static final String API_URL_BY_ID = API_URL + "/" + ID;

    @Test
    void shouldFindAllStatusUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(API_URL))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldFindByIdStatusUnauthorized() throws Exception {
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldCreateStatusUnauthorized() throws Exception {
        when(daoLivre.save(any())).thenReturn(LIVRE);
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldUpdateStatusUnauthorized() throws Exception {
        when(daoLivre.save(any())).thenReturn(LIVRE);
        mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldDeleteByIdStatusUnauthorized() throws Exception {
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser
    void shouldCreateStatusOk() throws Exception {
        when(daoLivre.save(any())).thenReturn(LIVRE);
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
                .contentType("application/json")
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateStatusOk() throws Exception {
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        when(daoLivre.save(any())).thenReturn(LIVRE);
        mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(LIVRE)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteStatusOk() throws Exception {
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldCreateAndAttributesOk() throws Exception {
        // given
        when(daoAuteur.findById(ID_AUTEUR)).thenReturn(Optional.of(AUTEUR));
        when(daoEditeur.findById(ID_EDITEUR)).thenReturn(Optional.of(EDITEUR));
        when(daoGenre.findById(ID_GENRE)).thenReturn(Optional.of(GENRE));
        when(daoCollection.findById(ID_COLLECTION)).thenReturn(Optional.of(COLLECTION));
        when(daoLivre.save(any())).thenReturn(LIVRE);

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
                .contentType("application/json")
                .content("{}"));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.titre").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.resume").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.annee").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idAuteur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idEditeur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idCollection").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idGenre").exists());

        result.andExpect(MockMvcResultMatchers.jsonPath(".livres").doesNotExist());

        Mockito.verify(daoLivre).save(Mockito.any());
    }

    @Test
    @WithMockUser
    void shouldUpdateAndAttributesOk() throws Exception {
        // given
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        when(daoAuteur.findById(ID_AUTEUR)).thenReturn(Optional.of(AUTEUR));
        when(daoEditeur.findById(ID_EDITEUR)).thenReturn(Optional.of(EDITEUR));
        when(daoGenre.findById(ID_GENRE)).thenReturn(Optional.of(GENRE));
        when(daoCollection.findById(ID_COLLECTION)).thenReturn(Optional.of(COLLECTION));
        when(daoLivre.save(any())).thenReturn(LIVRE);

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(LIVRE)));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.titre").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.resume").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.annee").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idAuteur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idEditeur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idCollection").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath(".idGenre").exists());

        result.andExpect(MockMvcResultMatchers.jsonPath(".livres").doesNotExist());

        Mockito.verify(daoLivre).save(Mockito.any());
    }

    @Test
    @WithMockUser
    void shouldDeleteAndServiceCalled() throws Exception {
        // given
        when(daoLivre.findById(ID)).thenReturn(Optional.of(LIVRE));
        Mockito.doNothing().when(daoLivre).deleteById(ID);

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID));

        // then
        Mockito.verify(daoLivre).delete(LIVRE);
    }

}