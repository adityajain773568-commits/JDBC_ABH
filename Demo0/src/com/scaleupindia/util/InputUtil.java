package com.scaleupindia.util;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class InputUtil {

    private InputUtil(){}

    public static int acceptMenuOption(Scanner scanner){
        System.out.println("Press 1 to add new Owner. ");
        System.out.println("Press 2 to fetch owner details.");
        System.out.println("Press 3 to update pet details of owner.");
        System.out.println("Press 4 to delete owner details.");
        System.out.println("Press 5 to fetch all owners.");

        int menuOption = scanner.nextInt();

        if (1<=menuOption && menuOption<=5){
            return menuOption;
        }else {
            return acceptMenuOption(scanner);
        }

    }

    public static boolean wantToContinue(Scanner scanner){
        System.out.println("Print Y to continue and N to exit.");
        char choice = scanner.next().toUpperCase().charAt(0);
        return 'Y'==choice;
    }

    public static OwnerDTO acceptOwnerDetailToSave(Scanner scanner) {

        System.out.println("Enter id of Owner : ");
        int id = scanner.nextInt();
        System.out.println("Enter first name of owner : ");
        String firstName = scanner.next();
        System.out.println("Enter last name of owner : ");
        String lastName = scanner.next();
        System.out.println("Enter gender of Owner : " + Arrays.asList(Gender.values()));
        String gender = scanner.next().toUpperCase();
        System.out.println("Enter city of owner : ");
        String city = scanner.next();
        System.out.println("Enter state of owner : ");
        String state = scanner.next();
        System.out.println("Enter Mobile Number of Owner : ");
        String mobileNumber = scanner.next();
        System.out.println("Enter email id of owner : ");
        String emailId = scanner.next();
        System.out.println("Enter id of pet : ");
        int petId = scanner.nextInt();
        System.out.println("Enter name of pet : ");
        String petName = scanner.next();
        System.out.println("Enter date of birth of pet (dd-MM-yyyy) : ");
        String petDateOfBirth = scanner.next();
        System.out.println("Enter gender of pet : " + Arrays.toString(Gender.values()));
        String petGender = scanner.next().toUpperCase();
        System.out.println("Enter pet type : " + Arrays.asList(PetType.values()));
        String petType = scanner.next().toUpperCase();
        try {
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setId(id);
            ownerDTO.setFirstName(firstName);
            ownerDTO.setLastName(lastName);
            ownerDTO.setGender(Gender.valueOf(gender));
            ownerDTO.setCity(city);
            ownerDTO.setState(state);
            ownerDTO.setMobileNumber(mobileNumber);
            ownerDTO.setEmailId(emailId);
            ownerDTO.setPetId(petId);
            ownerDTO.setPetName(petName);
            ownerDTO.setPetBirthDate(convertStringToDate(petDateOfBirth));
            ownerDTO.setPetGender(Gender.valueOf(petGender));
            ownerDTO.setPetType(PetType.valueOf(petType));
            return ownerDTO;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return acceptOwnerDetailToSave(scanner);
        }


    }

    public static LocalDate convertStringToDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate,format);

    }


    public static int acceptOwnerIdToOperate(Scanner scanner) {
        System.out.println("Enter id of owner : ");
        return  scanner.nextInt();
    }


    public static String acceptPetDetailsToUpdate(Scanner scanner) {
        System.out.println("Enter pet name to update : ");
        return scanner.next();
    }
}
