package back.java.core.dto;

import java.util.Date;

public class TacheDTO {

    private long id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private String type;
    private UserDTO createurTache;
    private UserDTO executeurTache;

    public TacheDTO() {
    }

    public TacheDTO(long id, String nom, String description, String dateDebut, String dateFin, String type, UserDTO createurTache, UserDTO executeurTache) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
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
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDTO getCreateurTache() {
        return createurTache;
    }

    public void setCreateurTache(UserDTO createurTache) {
        this.createurTache = createurTache;
    }

    public UserDTO getExecuteurTache() {
        return executeurTache;
    }

    public void setExecuteurTache(UserDTO executeurTache) {
        this.executeurTache = executeurTache;
    }
}
