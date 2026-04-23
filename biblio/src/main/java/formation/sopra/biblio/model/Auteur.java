package formation.sopra.biblio.model;

@Entity
@Table(name = "auteur")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auteur_id")
    private int id;


    private String nom;
    private String prenom;
    private String nationalité;


    // Mapping avec la classe Livre (1 auteur peut avoir plusieurs livres)
    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    @Version
    private int version;

    

    public Auteur() {
    }


    public Auteur(String nom, String prenom, String nationalité) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalité = nationalité;
    }


    public Auteur(int id, String nom, String prenom, String nationalité) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalité = nationalité;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNationalité() {
        return nationalité;
    }


    public void setNationalité(String nationalité) {
        this.nationalité = nationalité;
    }


    public List<Livre> getLivres() {
        return livres;
    }


    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }


    public int getVersion() {
        return version;
    }


    public void setVersion(int version) {
        this.version = version;
    }

    



    
}
