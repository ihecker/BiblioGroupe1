package formation.sopra.biblio.config;

import formation.sopra.biblio.repository.IDAOUtilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JpaUserDetailsService implements UserDetailsService {

    private final IDAOUtilisateur daoUtilisateur;
    public JpaUserDetailsService(IDAOUtilisateur daoUtilisateur) {
        this.daoUtilisateur = daoUtilisateur;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.daoUtilisateur
                .findByUsernameOptional(login)
                .map(u -> User.builder()
                        .username(login)
                        .password(u.getPassword())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas!"))
                ;
    }
}
