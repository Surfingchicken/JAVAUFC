package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.List;
import java.util.Date;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String description;
    private String status;
    private Date date_debut;
    private Date date_fin;
    @ManyToOne
    @JoinColumn(name = "tachesAttribues_id")
    private Compte tachesAttribues;
    @ManyToOne
    @JoinColumn(name = "tachesAssignees_id")
    private Compte tachesAssignees;
    @ManyToMany(mappedBy = "taches")
    private List<Planning> plannings;

    public Tache() {
    }

    public Tache(long id, String nom, String description, String status, Date date_debut, Date date_fin, Compte tachesAttribues, Compte tachesAssignees, List<Planning> plannings) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.status = status;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tachesAttribues = tachesAttribues;
        this.tachesAssignees = tachesAssignees;
        this.plannings = plannings;
    }

    public Long getId() {
        return id;
    }
}
