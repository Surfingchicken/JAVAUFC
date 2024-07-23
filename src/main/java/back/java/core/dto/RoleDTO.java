package back.java.core.dto;

import java.util.List;

public class RoleDTO {

    private long id;
    private String name;
    private List<UserDTO> users;

    public RoleDTO() {
    }

    public RoleDTO(long id, String name, List<UserDTO> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
