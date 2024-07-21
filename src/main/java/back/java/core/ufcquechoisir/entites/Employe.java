package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "employe")
    private List<Compte> comptes;

    public Employe(){
    }

    public Employe(long id, String nom, String prenom, List<Compte> comptes) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.comptes = comptes;
    }


    public Long getId() {
        return id;
    }
    // Many to one au niveau des comptes, un employé peut avoir plusieurs compte ( admin, test, normal)
}
