package back.java.core.datas;

import java.util.List;

public class UserData {

    private long id;
    private String username;
    private String email;
    private List<TokenData> tokens;
    private RoleData roles;
    private List<TacheData> tachesCreer;
    private List<TacheData> tachesExecute;


    public UserData() {
    }

    // Parameterized constructor
    public UserData(long id, String username, String email, List<TokenData> tokens, RoleData roles, List<TacheData> tachesCreer, List<TacheData> tachesExecute) {
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

    public List<TokenData> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenData> tokens) {
        this.tokens = tokens;
    }

    public RoleData getRoles() {
        return roles;
    }

    public void setRoles(RoleData roles) {
        this.roles = roles;
    }

    public List<TacheData> getTachesCreer() {
        return tachesCreer;
    }

    public void setTachesCreer(List<TacheData> tachesCreer) {
        this.tachesCreer = tachesCreer;
    }

    public List<TacheData> getTachesExecute() {
        return tachesExecute;
    }

    public void setTachesExecute(List<TacheData> tachesExecute) {
        this.tachesExecute = tachesExecute;
    }
}
