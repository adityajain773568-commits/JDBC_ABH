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
    public void saveOwnersAutomatically(List<OwnerDTO> owners) {
        String sql = """
                Insert into owner_table (id , first_name , last_name , gender , city ,
                state , mobile_number , email_id , pet_id , pet_name , pet_date_of_birth,
                pet_gender , pet_type ) VALUES (? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? ,?)""";

        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            for (OwnerDTO ownerDTO : owners){
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
            }

//            System.out.println(sql);
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
    }

    @Override
    public void saveOwnersManually(List<OwnerDTO> owners) {
        String sql = """
                Insert into owner_table (id , first_name , last_name , gender , city ,
                state , mobile_number , email_id , pet_id , pet_name , pet_date_of_birth,
                pet_gender , pet_type ) VALUES (? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? ,?)""";

        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (OwnerDTO ownerDTO : owners){
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
            }
            connection.commit();

//            System.out.println(sql);
        } catch (ClassNotFoundException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
    }

    @Override
    public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> owners) {
        String sql = """
                Insert into owner_table (id , first_name , last_name , gender , city ,
                state , mobile_number , email_id , pet_id , pet_name , pet_date_of_birth,
                pet_gender , pet_type ) VALUES (? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? ,?)""";
        Savepoint savepoint = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (OwnerDTO ownerDTO : owners){
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
                if (ownerDTO.getId()%2==1){
                    savepoint = connection.setSavepoint("savepoint after owner id" + ownerDTO.getId());
                }
            }
            connection.commit();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                connection.rollback(savepoint);
                connection.commit();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
    }

}
