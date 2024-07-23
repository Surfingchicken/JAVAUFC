package back.java.core.services;

import back.java.core.dto.UserDTO;
import back.java.core.utils.HttpClientUtil;
import back.java.core.utils.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfileService {
    private static final String API_URL = "http://localhost:3000";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserDTO updateUser(String username, String email, String password) throws IOException, SecurityException {
        try {
            String token = TokenManager.getInstance().getToken();
            Map<String, String> updateParams = new HashMap<>();

            if (username != null && !username.isEmpty()) {
                updateParams.put("username", username);
            }
            if (email != null && !email.isEmpty()) {
                updateParams.put("email", email);
            }
            if (password != null && !password.isEmpty()) {
                updateParams.put("password", password);
            }

            String jsonPayload = objectMapper.writeValueAsString(updateParams);
            System.out.println("JSON Payload: " + jsonPayload); // Debug log

            String response = HttpClientUtil.sendPutRequest(API_URL + "/user/update", jsonPayload, token);
            return objectMapper.readValue(response, UserDTO.class);
        } catch (IOException e) {
            if (e.getMessage().contains("Server returned HTTP response code: 401")) {
                throw new SecurityException("Permissions insuffisantes");
            }
            throw e;
        }
    }
}
