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
    Statement statement = null;

    @Override
    public void saveOwner(OwnerDTO ownerDTO) {
        String sql = "INSERT INTO owner_table "
                + "(id, first_name, last_name, gender, city, state, mobile_number, email_id, "
                + "pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type) VALUES ("
                + ownerDTO.getId() + ", '"
                + ownerDTO.getFirstName() + "', '"
                + ownerDTO.getLastName() + "', '"
                + ownerDTO.getGender() + "', '"
                + ownerDTO.getCity() + "', '"
                + ownerDTO.getState() + "', '"
                + ownerDTO.getMobileNumber() + "', '"
                + ownerDTO.getEmailId() + "', "
                + ownerDTO.getPetId() + ", '"
                + ownerDTO.getPetName() + "', '"
                + Date.valueOf(ownerDTO.getPetBirthDate()) + "', '"
                + ownerDTO.getPetGender() + "', '"
                + ownerDTO.getPetType() + "')";

        try {
            Class.forName(DATABASE_DRIVER);
//            System.out.println("yeeeeeeee -1 ");
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new InternalServiceException(e.getMessage());

        } finally {
            if (Objects.nonNull(statement)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) {
        OwnerDTO ownerDTO = null;
        String sql = "select * from owner_table where id = " + ownerId;

        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ownerDTO;
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
        String sql = "UPDATE owner_table SET pet_name = '" + petName + "' WHERE id = " + ownerId;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(statement)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void deleteOwner(int ownerId) {
        String sql = "Delete from owner_table  where id = " + ownerId;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(statement)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        OwnerDTO ownerDTO = null;
        String sql = "select * from owner_table ";
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
                ownerDTOList.add(ownerDTO);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (Objects.nonNull(statement)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ownerDTOList;
    }

    @Override
    public OwnerDTO findOwnerUsingEmailIdAndDate(String emailId, LocalDate petDateOfBirth) {
        String sql = "SELECT * FROM owner_table WHERE email_id = '"
                + emailId + " ' AND pet_date_of_birth = '"
                + petDateOfBirth + "'";
        System.out.println(sql);
        OwnerDTO ownerDTO = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ownerDTO = MapperUtil.convertOwnerResultSetToDTO(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (Objects.nonNull(statement)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ownerDTO;
    }
}
