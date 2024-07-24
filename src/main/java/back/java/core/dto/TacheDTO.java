package back.java.core.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TacheDTO {

    private long id;
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty dateDebut = new SimpleStringProperty();
    private final StringProperty dateFin = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private UserDTO createurTache;
    private UserDTO executeurTache;

    public TacheDTO() {
    }

    public TacheDTO(long id, String nom, String description, String dateDebut, String dateFin, String type, UserDTO createurTache, UserDTO executeurTache) {
        this.id = id;
        setNom(nom); // Utilisation des setters
        setDescription(description);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setType(type);
        this.createurTache = createurTache;
        this.executeurTache = executeurTache;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    @JsonProperty("date_debut")
    public String getDateDebut() {
        return dateDebut.get();
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public StringProperty dateDebutProperty() {
        return dateDebut;
    }
    @JsonProperty("date_fin")
    public String getDateFin() {
        return dateFin.get();
    }

    public void setDateFin(String dateFin) {
        this.dateFin.set(dateFin);
    }

    public StringProperty dateFinProperty() {
        return dateFin;
    }
    @JsonProperty("type")
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }
    @JsonProperty("createur")

    public UserDTO getCreateurTache() {
        return createurTache;
    }

    public void setCreateurTache(UserDTO createurTache) {
        this.createurTache = createurTache;
    }
    @JsonProperty("executeur")

    public UserDTO getExecuteurTache() {
        return executeurTache;
    }

    public void setExecuteurTache(UserDTO executeurTache) {
        this.executeurTache = executeurTache;
    }
}
