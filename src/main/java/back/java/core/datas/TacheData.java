package back.java.core.datas;

import java.util.Date;
import java.util.List;

public class TacheData {
    private long id;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private long createurTacheId;
    private long executeurTacheId;


    public long getId() {
        return id;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCreateurTacheId() {
        return createurTacheId;
    }

    public void setCreateurTacheId(long createurTacheId) {
        this.createurTacheId = createurTacheId;
    }

    public long getExecuteurTacheId() {
        return executeurTacheId;
    }

    public void setExecuteurTacheId(long executeurTacheId) {
        this.executeurTacheId = executeurTacheId;
    }

    public void setId(long id) {
        this.id = id;
    }
}