package com.scaleupindia.util;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InputUtil {

    private InputUtil(){}

    public static int acceptMenuOption(Scanner scanner){
        System.out.println("Press 1 to run saveOwnersAutomatically method.");
        System.out.println("Press 2 to run saveOwnersManually method.");
        System.out.println("Press 3 to run saveOwnersManuallyWithSavepoint method.");


        int menuOption = scanner.nextInt();

        if (1<=menuOption && menuOption<=6){
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

    public static List<OwnerDTO> generateOwners() {

        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        int count =1;
        for (int i =1 ; i<=5; i++){
            OwnerDTO ownerDTO = new OwnerDTO();
            ownerDTO.setId(count);
            ownerDTO.setFirstName("FistName" + count);
            ownerDTO.setLastName("LastName"+count);
            ownerDTO.setGender(Gender.M);
            ownerDTO.setCity("City"+count);
            ownerDTO.setState("State"+count);
            if (i%2==0){
                ownerDTO.setMobileNumber("MobileEven");
                ownerDTO.setEmailId("EmailEven");
            }else {
                ownerDTO.setMobileNumber("MobileOdd");
                ownerDTO.setEmailId("EmailOdd");

            }
            ownerDTO.setPetId(count);
            ownerDTO.setPetName("PetName"+count);
            ownerDTO.setPetBirthDate(LocalDate.now());
            ownerDTO.setPetGender(Gender.F);
            ownerDTO.setPetType(PetType.RABBIT);
            ownerDTOList.add(ownerDTO);
            count++;
        }
        return ownerDTOList;
    }
}
