package back.java.core.dto;

import java.util.List;

public class RoleDTO {

    private long id;
    private String nom;
    private List<UserDTO> users;

    public RoleDTO() {
    }

    public RoleDTO(long id, String nom, List<UserDTO> users) {
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
