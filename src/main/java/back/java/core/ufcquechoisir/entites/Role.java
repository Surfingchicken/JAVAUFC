package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private boolean accesTemporaire;
    // À modifier
    @OneToMany(mappedBy = "role")
    private List<Compte> comptes;

    public Role() {
    }

    public Role(long id, String nom, boolean accesTemporaire, List<Compte> comptes) {
        this.id = id;
        this.nom = nom;
        this.accesTemporaire = accesTemporaire;
        this.comptes = comptes;
    }


    public Long getId() {
        return id;
    }
}
