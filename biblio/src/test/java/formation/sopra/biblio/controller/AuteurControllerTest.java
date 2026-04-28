package formation.sopra.biblio.controller;

import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import formation.sopra.biblio.repository.IDAOUtilisateur;
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
import formation.sopra.biblio.dto.Auteur.AuteurRequest;
import formation.sopra.biblio.dto.Auteur.AuteurResponse;
import formation.sopra.biblio.model.Auteur;
import formation.sopra.biblio.repository.IDAOAuteur;


@WebMvcTest(controllers = AuteurController.class)
public class AuteurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IDAOUtilisateur daoUtilisateur;

    //@MockitoBean
    //private JpaUserDetailsService jpaUserDetailsService;

    //@MockitoBean
    //private JwtUtils jwtUtils;

    @MockitoBean
    private IDAOAuteur daoAuteur;
    

    private static final Integer AUTEUR_ID = 1;
    private static final String NATIONALITE = "Un titre de livre intriguant";
    private static final String NOM = "Un nom d'auteur intriguant";
    private static final String PRENOM = "Un prénom d'auteur intriguant";

    private static final String API_URL = "/api/auteur";
    private static final String API_URL_BY_ID = API_URL + "/" + AUTEUR_ID;


    private final Auteur AUTEUR = new Auteur(AUTEUR_ID, NATIONALITE, NOM, PRENOM);
    private final AuteurResponse AUTEUR_RESPONSE = new AuteurResponse(AUTEUR_ID, NATIONALITE, NOM, PRENOM);
    private final AuteurRequest AUTEUR_REQUEST = new AuteurRequest(NATIONALITE, NOM, PRENOM);

     private final AuteurRequest AUTEUR_REQUEST_INVALID = new AuteurRequest(null, null, null);



    @Test
    public void shouldgetAllAuteurReturnUnauthorized() throws Exception {
        //given 

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL));
        //then 

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void shouldgetAllAuteurReturnOk() throws Exception {

        //given 
        Mockito.when(daoAuteur.findAll()).thenReturn(List.of(AUTEUR));

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(daoAuteur).findAll();
    }


    //BYID

    @Test
    public void shouldgetAuteurByIdReturnUnauthorized() throws Exception {
        //given 

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));
        //then 

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void shouldgetAuteurByIdReturnNotFound() throws Exception {

        //given 
        Mockito.when(daoAuteur.findById(AUTEUR_ID)).thenReturn(Optional.empty());

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        //then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(daoAuteur).findById(AUTEUR_ID);
    }

    @Test
    @WithMockUser
    public void shouldgetAuteurByIdReturnOk() throws Exception {

        //given 
        Mockito.when(daoAuteur.findById(AUTEUR_ID)).thenReturn(Optional.of(AUTEUR));

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(daoAuteur).findById(AUTEUR_ID);
    }


    //CREATE

    @Test
    public void shouldaddAuteurReturnUnauthorized() throws Exception {
        //given 

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
        .with(csrf()));
        //then 

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void shouldaddAuteurReturnOk() throws Exception {

        //given 
        Mockito.when(daoAuteur.save(Mockito.any(Auteur.class))).thenReturn(AUTEUR);
        String json = objectMapper.writeValueAsString(AUTEUR_REQUEST);

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
        .contentType("application/json")
            .content(json)
            .with(csrf()));

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(daoAuteur).save(Mockito.any(Auteur.class));
    }

    @Test
    @WithMockUser
    public void shouldaddAuteurReturnBadRequest() throws Exception {

        //given 
        String json = objectMapper.writeValueAsString(AUTEUR_REQUEST_INVALID);

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
            .contentType("application/json")
            .content(json)
            .with(csrf()));
        //then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }





    //UPDATE 

    @Test
    public void shouldupdateAuteurReturnUnauthorized() throws Exception {
        //given 

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID).with(csrf()));
        //then 

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void shouldupdateAuteurReturnOk() throws Exception {

        //given 
        Mockito.when(daoAuteur.findById(Mockito.anyInt())).thenReturn(Optional.of(AUTEUR));
        Mockito.when(daoAuteur.save(Mockito.any(Auteur.class)))
       .thenReturn(AUTEUR);
        String json = objectMapper.writeValueAsString(AUTEUR_REQUEST);

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
            .contentType("application/json")
            .content(json)
            .with(csrf())
        );

        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(daoAuteur).save(Mockito.any(Auteur.class));
    }

    @Test
    @WithMockUser   
    public void shouldupdateAuteurReturnBadRequest() throws Exception {

        //given 
        String json = objectMapper.writeValueAsString(AUTEUR_REQUEST_INVALID);

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(API_URL_BY_ID)
            .contentType("application/json")
            .content(json)
            .with(csrf())
            
        );
        
        //then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    //DELETE

    @Test
    public void shoulddeleteAuteurReturnUnauthorized() throws Exception {
        //given 

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID)
        .with(csrf())    
    );
        //then 

        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void shoulddeleteAuteurReturnOk() throws Exception {

        //given 
        Mockito.doNothing().when(daoAuteur).deleteById(AUTEUR_ID);

        //when 
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete(API_URL_BY_ID)
            .with(csrf())
        );
        
        //then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        verify(daoAuteur).deleteById(AUTEUR_ID);
    }

}
