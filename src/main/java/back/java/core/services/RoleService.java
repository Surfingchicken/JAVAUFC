package back.java.core.services;

import back.java.core.dto.RoleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import back.java.core.utils.*;
import java.io.IOException;
import java.util.List;

public class RoleService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_URL = "http://localhost:3000";

    public static class RoleListResponse {
        private List<RoleDTO> role;

        public List<RoleDTO> getRole() {
            return role;
        }

        public void setRole(List<RoleDTO> role) {
            this.role = role;
        }
    }

    public List<RoleDTO> listRoles() throws IOException {
        String token = TokenManager.getInstance().getToken();
        String response = HttpClientUtil.sendGetRequest(API_URL + "/roles", token);
        RoleListResponse roleListResponse = objectMapper.readValue(response, RoleListResponse.class);
        return roleListResponse.getRole();
    }
}
