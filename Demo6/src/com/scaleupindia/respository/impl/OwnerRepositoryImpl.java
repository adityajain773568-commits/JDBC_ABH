package com.scaleupindia.respository.impl;

import com.scaleupindia.config.DatabaseConfig;
import com.scaleupindia.config.PropertiesConfig;
import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exceptions.InternalServiceException;
import com.scaleupindia.respository.OwnerRepository;
import com.scaleupindia.util.MapperUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepositoryImpl implements OwnerRepository {

    private static final String DATABASE_DRIVER = "database.driver";
    private static final PropertiesConfig propertiesConfig = PropertiesConfig.getInstance();
    @Override
    public void saveOwner(OwnerDTO ownerDTO) {
        String sql = """
                Insert into owner_table (id , first_name , last_name , gender , city ,
                state , mobile_number , email_id , pet_id , pet_name , pet_date_of_birth,
                pet_gender , pet_type ) VALUES (? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? ,?)""";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            preparedStatement.setInt(1, ownerDTO.getId());
            preparedStatement.setString(2, ownerDTO.getFirstName());
            preparedStatement.setString(3, ownerDTO.getLastName());
            preparedStatement.setString(4, ownerDTO.getGender().toString());
            preparedStatement.setString(5, ownerDTO.getCity());
            preparedStatement.setString(6, ownerDTO.getState());
            preparedStatement.setString(7, ownerDTO.getMobileNumber());
            preparedStatement.setString(8, ownerDTO.getEmailId());
            preparedStatement.setInt(9, ownerDTO.getPetId());
            preparedStatement.setString(10, ownerDTO.getPetName());
            preparedStatement.setDate(11, Date.valueOf(ownerDTO.getPetBirthDate()));
            preparedStatement.setString(12, ownerDTO.getPetGender().toString());
            preparedStatement.setString(13, ownerDTO.getPetType().toString());
            preparedStatement.executeUpdate();
//            System.out.println(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) {
        String sql = "select * from owner_table where id = ?";
        OwnerDTO ownerDTO = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            preparedStatement.setInt(1, ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new InternalServiceException(e.getMessage());
        }
        return ownerDTO;
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
        String sql = "UPDATE owner_table SET pet_name = ? WHERE id = ? ";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            preparedStatement.setString(1, petName);
            preparedStatement.setInt(2, ownerId);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOwner(int ownerId) {
        String sql = "DELETE FROM owner_table WHERE id = " + ownerId;
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new InternalServiceException(e.getMessage());
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        String sql = "select * from owner_table ";
        OwnerDTO ownerDTO = null;
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ownerDTOList;
    }

    @Override
    public List<OwnerDTO> updatePetDetails(String petType) {
        String sql = "CALL add_prefix_to_pet_name(?)";
        List<OwnerDTO> ownerList = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql);) {
            Class.forName(propertiesConfig.getProperty(DATABASE_DRIVER));
            callableStatement.setString(1, petType);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                OwnerDTO owner = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerList.add(owner);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            throw new InternalServiceException(exception.getMessage());
        }
        return ownerList;
    }

}
