package back.java.core.datas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import back.java.core.dto.*;

public class TacheData {
    private long id;
    private String nom;
    private String description;
    @JsonProperty("date_debut")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")

    private String dateDebut;

    @JsonProperty("date_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")

    private String dateFin;
    private String type;
    private UserDTO createurTache;
    private UserDTO executeurTache;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("date_debut")
    public String getDateDebut() {
        return dateDebut;
    }

    @JsonProperty("date_debut")
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    @JsonProperty("date_fin")
    public String getDateFin() {
        return dateFin;
    }

    @JsonProperty("date_fin")
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("createurTache")
    public UserDTO getCreateurTache() {
        return createurTache;
    }

    @JsonProperty("createurTache")
    public void setCreateurTache(UserDTO createurTache) {
        this.createurTache = createurTache;
    }

    @JsonProperty("executeurTache")
    public UserDTO getExecuteurTache() {
        return executeurTache;
    }

    @JsonProperty("executeurTache")
    public void setExecuteurTache(UserDTO executeurTache) {
        this.executeurTache = executeurTache;
    }
}
