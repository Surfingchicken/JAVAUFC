package back.java.core.datas;

import java.util.List;
public class RoleData {

    private long id;
    private String nom;
    private List<UserData> users;

    public RoleData() {
    }

    public RoleData(long id, String nom, List<UserData> users) {
        this.id = id;
        this.nom = nom;
        this.users = users;
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

    public List<UserData> getUsers() {
        return users;
    }

    public void setUsers(List<UserData> users) {
        this.users = users;
    }
}
