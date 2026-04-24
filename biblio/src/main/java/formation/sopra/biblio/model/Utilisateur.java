package formation.sopra.biblio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(length = 25, nullable = false, unique = true)
    protected String login;

    @Column(length = 100, nullable = false)
    protected String password;


    public Utilisateur() {
    }

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public Utilisateur(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password +"]";
    }

}
