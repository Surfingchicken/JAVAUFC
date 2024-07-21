package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    // À modifier
    @OneToOne(mappedBy = "planning")
    private Compte compte;
    @ManyToMany
    @JoinTable(
            name = "planning_tache",
            joinColumns = @JoinColumn(name = "planning_id"),
            inverseJoinColumns = @JoinColumn(name = "tache_id")
    )
    private List<Tache> taches;
    @ManyToMany
    @JoinTable(
            name = "planning_meeting",
            joinColumns = @JoinColumn(name = "planning_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    private List<Meeting> meetings;

    public Planning(){
    }

    public Planning(long id, String nom, Compte compte, List<Tache> taches, List<Meeting> meetings) {
        this.id = id;
        this.nom = nom;
        this.compte = compte;
        this.taches = taches;
        this.meetings = meetings;
    }

    public Long getId() {
        return id;
    }
}
