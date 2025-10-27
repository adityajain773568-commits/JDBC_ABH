package com.scaleupindia.respository.impl;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exceptions.InternalServiceException;
import com.scaleupindia.respository.OwnerRepository;
import com.scaleupindia.util.MapperUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnerRepositoryImpl implements OwnerRepository {

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/petistaan_jdbc";
    private static final String DATABASE_PASS = "@*Aa+d-itya12345";
    private static final String DATABASE_USERNAME = "root";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;

    @Override
    public void saveOwner(OwnerDTO ownerDTO) {
        String sql = """
                Insert into owner_table (id , first_name , last_name , gender , city ,
                state , mobile_number , email_id , pet_id , pet_name , pet_date_of_birth,
                pet_gender , pet_type ) VALUES (? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? ,?)""";

        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);

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
            throw new RuntimeException(e);
        } finally {

            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) {
        String sql = "select * from owner_table where id = ?";
        OwnerDTO ownerDTO = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return ownerDTO;
    }

    @Override
    public List<OwnerDTO> updatePetDetailsWithCallable(String petType) {
        String sql = "CALL add_prefix_to_pet_name(?)";
        Connection connection = null;
        OwnerDTO ownerDTO = null;
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        CallableStatement callableStatement = null;
        try {
            // Load database driver
            Class.forName(DATABASE_DRIVER);

            // Establish connection
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1,petType);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new InternalServiceException(e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(callableStatement)) {
                    callableStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return ownerDTOList;
    }

    @Override
    public List<OwnerDTO> updatePetDetailsWithoutCallable(String petType) {
        String updateSql = """ 
                UPDATE owner_table
                                  SET pet_name = CASE pet_gender
                                      WHEN 'M' THEN CONCAT('Mr.', pet_name)
                                      WHEN 'F' THEN CONCAT('Miss', pet_name)
                                      ELSE pet_name
                                  END
                                  WHERE pet_type = ?;
                """;
        String readSql = "SELECT * FROM owner_table WHERE pet_type = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        OwnerDTO ownerDTO = null;
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        try {
            // Load database driver
            Class.forName(DATABASE_DRIVER);

            // Establish connection
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1,petType);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(readSql);
            preparedStatement.setString(1,petType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new InternalServiceException(e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return ownerDTOList;
    }


    @Override
    public void deleteOwner(int ownerId) {
        String sql = "DELETE FROM owner_table WHERE id = " + ownerId;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (Objects.nonNull(statement)) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }

            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        String sql = "select * from owner_table ";
        OwnerDTO ownerDTO = null;
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return ownerDTOList;
    }

    @Override
    public OwnerDTO findOwnerUsingEmailIdAndDate(String emailId, LocalDate petDateOfBirth) {
        String sql = "Select * from owner_table where email_id = ? and pet_date_of_birth = ? ";
        OwnerDTO ownerDTO = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, emailId);
            preparedStatement.setDate(2,Date.valueOf(petDateOfBirth));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return ownerDTO;

    }

    @Override
    public List<OwnerDTO> findOwners(String petType) {
        String sql = "Select * from owner_table where pet_type = ?";
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        OwnerDTO ownerDTO = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,petType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)){
                    preparedStatement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection)){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ownerDTOList;
    }
}
