package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private boolean DoubleFA;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "tachesAttribues")
    private List<Tache> tachesAttribues;
    @OneToMany(mappedBy = "tachesAssignees")
    private List<Tache> tachesAssignees;
    @OneToOne
    @JoinColumn(name = "planning_id")
    private Planning planning;
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public Compte(){
    }

    public Compte(long id, String username, String password, String email, boolean doubleFA, Role role, List<Tache> tachesAttribues, List<Tache> tachesAssignees, Planning planning, Employe employe) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        DoubleFA = doubleFA;
        this.role = role;
        this.tachesAttribues = tachesAttribues;
        this.tachesAssignees = tachesAssignees;
        this.planning = planning;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }
}
