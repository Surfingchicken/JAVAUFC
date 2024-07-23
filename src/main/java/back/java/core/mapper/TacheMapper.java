package back.java.core.mapper;

import back.java.core.datas.TacheData;
import back.java.core.dto.TacheDTO;
import back.java.core.dto.UserDTO;

public class TacheMapper {

    public static TacheDTO toDTO(TacheData tacheData) {
        TacheDTO tacheDTO = new TacheDTO();
        tacheDTO.setId(tacheData.getId());
        tacheDTO.setNom(tacheData.getNom());
        tacheDTO.setDescription(tacheData.getDescription());
        tacheDTO.setDateDebut(tacheData.getDateDebut());
        tacheDTO.setDateFin(tacheData.getDateFin());
        tacheDTO.setType(tacheData.getType());
        tacheDTO.setCreateurTache(tacheData.getCreateurTache());
        tacheDTO.setExecuteurTache(tacheData.getExecuteurTache());
        return tacheDTO;
    }

    public static TacheDTO toDTO(TacheData tacheData, UserDTO createurTache, UserDTO executeurTache) {
        TacheDTO tacheDTO = new TacheDTO();
        tacheDTO.setId(tacheData.getId());
        tacheDTO.setNom(tacheData.getNom());
        tacheDTO.setDescription(tacheData.getDescription());
        tacheDTO.setDateDebut(tacheData.getDateDebut());
        tacheDTO.setDateFin(tacheData.getDateFin());
        tacheDTO.setType(tacheData.getType());
        tacheDTO.setCreateurTache(createurTache);
        tacheDTO.setExecuteurTache(executeurTache);
        return tacheDTO;
    }

}
