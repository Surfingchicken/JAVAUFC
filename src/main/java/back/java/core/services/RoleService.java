package back.java.core.services;

import back.java.core.dto.RoleDTO;
import back.java.core.utils.*;
import back.java.core.utils.HttpClientUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class RoleService {
    private static final String API_URL = "http://localhost:3000";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<RoleDTO> listRoles() throws IOException, SecurityException {
        try {
            String token = TokenManager.getInstance().getToken();
            String response = HttpClientUtil.sendGetRequest(API_URL + "/roles", token);
            RoleListResponse roleListResponse = objectMapper.readValue(response, RoleListResponse.class);
            return roleListResponse.getRole();
        } catch (IOException e) {
            if (e.getMessage().contains("Server returned HTTP response code: 401")) {
                throw new SecurityException("Permissions insuffisantes");
            }
            throw e;
        }
    }

    private static class RoleListResponse {
        private List<RoleDTO> role;

        public List<RoleDTO> getRole() {
            return role;
        }

        public void setRole(List<RoleDTO> role) {
            this.role = role;
        }
    }
}
