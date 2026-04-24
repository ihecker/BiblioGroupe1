package formation.sopra.biblio.controller;

import formation.sopra.biblio.config.JwtUtils;
import formation.sopra.biblio.dto.AuthRequest;
import formation.sopra.biblio.dto.TokenResponse;
import formation.sopra.biblio.model.Utilisateur;
import formation.sopra.biblio.repository.IDAOUtilisateur;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IDAOUtilisateur daoUtilisateur;

    public UtilisateurController( AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IDAOUtilisateur daoUtilisateur) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.daoUtilisateur = daoUtilisateur;
    }

    @PostMapping("/auth/register")
    public TokenResponse register(@RequestBody AuthRequest authRequest) throws AuthenticationException{

        Utilisateur user = new Utilisateur(); // ou Fermier selon logique
        user.setLogin(authRequest.getUsername());

        if (daoUtilisateur.findByUsernameOptional(user.getLogin()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));


        daoUtilisateur.save(user);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getLogin(),null);

        return new TokenResponse(JwtUtils.generate(authentication));

    }

    @PostMapping("/auth")
    public TokenResponse auth(@RequestBody AuthRequest request) throws AuthenticationException {


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());


        return new TokenResponse(JwtUtils.generate(authenticationManager.authenticate(authentication)));
    }


}
