package back.java.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import back.java.core.utils.HttpClientUtil;
import back.java.core.utils.TokenManager;

import java.util.List;
import java.util.Map;

public class AuthService {

    private static final String API_URL = "http://localhost:3000"; // Endpoint de base de votre API Node.js
    private final ObjectMapper objectMapper;

    public AuthService() {
        this.objectMapper = new ObjectMapper();
    }

    public String login(String username, String password) {
        try {
            String payload = objectMapper.writeValueAsString(new LoginRequest(username, password));
            System.out.println("Login Payload: " + payload);
            String response = HttpClientUtil.sendPostRequest(API_URL + "/auth/login", payload, null);
            System.out.println("Login Response: " + response);
            LoginResponse loginResponse = objectMapper.readValue(response, LoginResponse.class);
            String token = loginResponse.getToken();
            System.out.println("Token: " + token);
            TokenManager.getInstance().setToken(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String signup(String username, String email, String password) {
        try {
            String payload = objectMapper.writeValueAsString(new SignupRequest(username, email, password));
            HttpClientUtil.sendPostRequest(API_URL + "/auth/signup", payload, null);
            return login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void logout() {
        try {
            String token = TokenManager.getInstance().getToken();
            HttpClientUtil.sendDeleteRequest(API_URL + "/logout", token);
            TokenManager.getInstance().clearToken(); // Supprimer le token
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long userId) {
        try {
            String token = TokenManager.getInstance().getToken();
            HttpClientUtil.sendDeleteRequest(API_URL + "/users/" + userId, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> listUsers(int page, int result) {
        try {
            String token = TokenManager.getInstance().getToken();
            String response = HttpClientUtil.sendGetRequest(API_URL + "/users?page=" + page + "&result=" + result, token);
            UserListResponse userListResponse = objectMapper.readValue(response, UserListResponse.class);
            return userListResponse.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Classes internes statiques pour représenter les demandes et les réponses de connexion et d'inscription
    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Getters et setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SignupRequest {
        private String username;
        private String email;
        private String password;

        public SignupRequest(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        // Getters et setters
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class LoginResponse {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class UserListResponse {
        private List<Map<String, Object>> users;

        // Getters et setters
        public List<Map<String, Object>> getUsers() {
            return users;
        }

        public void setUsers(List<Map<String, Object>> users) {
            this.users = users;
        }
    }
}
