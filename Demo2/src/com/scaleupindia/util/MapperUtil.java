package com.scaleupindia.util;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperUtil {
    private MapperUtil(){}

    public static OwnerDTO convertOwnerResultSetToDTO(ResultSet resultSet) throws SQLException {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(resultSet.getInt("id"));
        ownerDTO.setFirstName(resultSet.getString("first_name"));
        ownerDTO.setLastName(resultSet.getString("last_name"));
        ownerDTO.setGender(Gender.valueOf(resultSet.getString("gender")));
        ownerDTO.setCity(resultSet.getString("city"));
        ownerDTO.setState(resultSet.getString("state"));
        ownerDTO.setMobileNumber(resultSet.getString("mobile_number"));
        ownerDTO.setEmailId(resultSet.getString("email_id"));
        ownerDTO.setPetId(resultSet.getInt("pet_id"));
        ownerDTO.setPetName(resultSet.getString("pet_name"));
        ownerDTO.setPetBirthDate(resultSet.getDate("pet_date_of_birth").toLocalDate());
        ownerDTO.setPetGender(Gender.valueOf(resultSet.getString("pet_gender") ));
        ownerDTO.setPetType(PetType.valueOf(resultSet.getString("pet_type")));

        return ownerDTO;
    }
}
