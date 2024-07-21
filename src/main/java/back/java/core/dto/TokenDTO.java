package back.java.core.dto;

public class TokenDTO {

    private long id;
    private String token;
    private UserDTO user;

    public TokenDTO(){
    }

    public TokenDTO(long id, String token, UserDTO user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
