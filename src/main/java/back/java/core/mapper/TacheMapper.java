package back.java.core.mapper;

import back.java.core.datas.TacheData;
import back.java.core.dto.TacheDTO;
import back.java.core.dto.UserDTO;

public class TacheMapper {

    public static TacheDTO toDTO(TacheData data, UserDTO createurTache, UserDTO executeurTache) {
        if (data == null) {
            return null;
        }

        TacheDTO dto = new TacheDTO();
        dto.setId(data.getId());
        dto.setNom(data.getNom());
        dto.setDescription(data.getDescription());
        dto.setDateDebut(data.getDateDebut());
        dto.setDateFin(data.getDateFin());
        dto.setType(data.getType());
        dto.setCreateurTache(createurTache);
        dto.setExecuteurTache(executeurTache);

        return dto;
    }

    public static TacheData toData(TacheDTO dto) {
        if (dto == null) {
            return null;
        }

        TacheData data = new TacheData();
        data.setId(dto.getId());
        data.setNom(dto.getNom());
        data.setDescription(dto.getDescription());
        data.setDateDebut(dto.getDateDebut());
        data.setDateFin(dto.getDateFin());
        data.setType(dto.getType());
        data.setCreateurTacheId(dto.getCreateurTache().getId());
        data.setExecuteurTacheId(dto.getExecuteurTache().getId());

        return data;
    }
}
