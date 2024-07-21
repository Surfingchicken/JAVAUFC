package back.java.core.datas;

public class TokenData {

    private long id;
    private String token;
    private UserData user;

    public TokenData(){
    }

    public TokenData(long id, String token, UserData user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public long getId() {
        return id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
