package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private Date date_debut;
    private Date date_fin;
    // À modifier
    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte organisateur;
    @ManyToMany
    @JoinTable(
            name = "meeting_participant",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "compte_id")
    )
    private List<Compte> participants;
    @ManyToMany(mappedBy = "meetings")
    private List<Planning> planning_id;
    @ManyToMany
    @JoinTable(
            name = "sondage_meeting",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "sondage_id")
    )
    private List<Sondage> sondages;

    public Meeting(){
    }

    public Meeting(long id, String nom, Date date_debut, Date date_fin, Compte organisateur, List<Compte> participants, List<Planning> planning_id, List<Sondage> sondages) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.organisateur = organisateur;
        this.participants = participants;
        this.planning_id = planning_id;
        this.sondages = sondages;
    }


    public Long getId() {
        return id;
    }
}
