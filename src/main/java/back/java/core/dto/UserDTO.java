package back.java.core.dto;

import java.util.List;

public class UserDTO {

    private long id;
    private String username;
    private String email;
    private List<TokenDTO> tokens;
    private RoleDTO roles;
    private List<TacheDTO> tachesCreer;
    private List<TacheDTO> tachesExecute;

    // Default constructor
    public UserDTO() {
    }

    // Parameterized constructor
    public UserDTO(long id, String username, String email, List<TokenDTO> tokens, RoleDTO roles, List<TacheDTO> tachesCreer, List<TacheDTO> tachesExecute) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tokens = tokens;
        this.roles = roles;

        this.tachesCreer = tachesCreer;
        this.tachesExecute = tachesExecute;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TokenDTO> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenDTO> tokens) {
        this.tokens = tokens;
    }

    public RoleDTO getRoles() {
        return roles;
    }

    public void setRoles(RoleDTO roles) {
        this.roles = roles;
    }
    
    public List<TacheDTO> getTachesCreer() {
        return tachesCreer;
    }

    public void setTachesCreer(List<TacheDTO> tachesCreer) {
        this.tachesCreer = tachesCreer;
    }

    public List<TacheDTO> getTachesExecute() {
        return tachesExecute;
    }

    public void setTachesExecute(List<TacheDTO> tachesExecute) {
        this.tachesExecute = tachesExecute;
    }
}
