package formation.sopra.biblio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="avis")
public class Avis {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="avis_id")
    private Integer id;

    @Column(nullable = false)
    private Integer note;

    @Column(length=500)
    private String commentaire;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="livre_id")
    @Column(nullable = false)
    private Livre livre;
    
    public Avis(Integer id, int note, String commentaire, LocalDate date, Livre livre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.date = date;
        this.livre = livre;
    }

    public Avis(int note, String commentaire, LocalDate date, Livre livre) {
        this.note = note;
        this.commentaire = commentaire;
        this.date = date;
        this.livre = livre;
    }

    public Avis() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Avis [id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", date=" + date + ", livre="
                + livre + "]";
    }

}
