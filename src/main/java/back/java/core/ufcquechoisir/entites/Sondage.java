package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Sondage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    private Date date;

    @ManyToMany(mappedBy = "sondages")
    private List<Meeting> meetings;
    @ElementCollection
    private List<Questions> questions;

    public Sondage() {
    }
    public Sondage(long id, String nom, List<Meeting> meetings, List<Questions> questions) {
        this.id = id;
        this.nom = nom;
        this.meetings = meetings;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }
}


