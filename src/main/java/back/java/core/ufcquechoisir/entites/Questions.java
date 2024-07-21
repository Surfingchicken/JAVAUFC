package back.java.core.ufcquechoisir.entites;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import java.util.List;
@Embeddable
public class Questions {
    private String texte;
    @ElementCollection
    private List<String> reponsesPossibles;

    public Questions(){
    }

    public Questions(String texte, List<String> reponsesPossibles) {
        this.texte = texte;
        this.reponsesPossibles = reponsesPossibles;
    }
}
