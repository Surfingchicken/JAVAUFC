package back.java.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import back.java.core.datas.TacheData;
import back.java.core.dto.TacheDTO;
import back.java.core.dto.UserDTO;
import back.java.core.mapper.TacheMapper;
import back.java.core.utils.HttpClientUtil;
import back.java.core.utils.TokenManager;

import java.util.List;
import java.util.stream.Collectors;

public class TacheService {

    private static final String API_URL = "http://localhost:3300";
    private final ObjectMapper objectMapper;
    private final String token;

    public TacheService() {
        this.objectMapper = new ObjectMapper();
        this.token = TokenManager.getInstance().getToken();
    }

    public TacheDTO getTache(Long id) {
        try {
            String response = HttpClientUtil.sendGetRequest(API_URL + "/taches/" + id, token);
            TacheData tacheData = objectMapper.readValue(response, TacheData.class);
            UserDTO createurTache = getUserById(tacheData.getCreateurTacheId());
            UserDTO executeurTache = getUserById(tacheData.getExecuteurTacheId());
            return TacheMapper.toDTO(tacheData, createurTache, executeurTache);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createTache(TacheDTO tacheDTO) {
        try {
            String payload = objectMapper.writeValueAsString(tacheDTO);
            HttpClientUtil.sendPostRequest(API_URL + "/taches", payload, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTache(Long id, TacheDTO tacheDTO) {
        try {
            String payload = objectMapper.writeValueAsString(tacheDTO);
            HttpClientUtil.sendPatchRequest(API_URL + "/taches/" + id, payload, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTache(Long id) {
        try {
            HttpClientUtil.sendDeleteRequest(API_URL + "/taches/" + id, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TacheDTO> listTaches(Long userId) {
        try {
            String response = HttpClientUtil.sendGetRequest(API_URL + "/taches/list?userId=" + userId, token);
            List<TacheData> tacheDataList = objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, TacheData.class));
            List<TacheDTO> tacheDTOList = tacheDataList.stream().map(tacheData -> {
                UserDTO createurTache = getUserById(tacheData.getCreateurTacheId());
                UserDTO executeurTache = getUserById(tacheData.getExecuteurTacheId());
                return TacheMapper.toDTO(tacheData, createurTache, executeurTache);
            }).collect(Collectors.toList());
            return tacheDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private UserDTO getUserById(Long userId) {
        try {
            String response = HttpClientUtil.sendGetRequest(API_URL + "/user/" + userId, token);
            return objectMapper.readValue(response, UserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
