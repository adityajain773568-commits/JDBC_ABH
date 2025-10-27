package com.scaleupindia.util;

import com.scaleupindia.enums.PetType;

import java.util.Arrays;
import java.util.Scanner;

public class InputUtil {

    private InputUtil(){}

    public static int acceptMenuOption(Scanner scanner){

        System.out.println("Press 1 to fetch owners details using PetType .");
        System.out.println("Press 2 to update pet details of owner.");

        int menuOption = scanner.nextInt();

        if (1<=menuOption && menuOption<=2){
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










    public static PetType acceptPetTypeToOperate(Scanner scanner) {
        System.out.println("Enter any of pet type from the following : "+Arrays.asList(PetType.values()));
        String petType = scanner.next().toUpperCase();
        return PetType.valueOf(petType);
    }

    public static boolean wantToUseCallable(Scanner scanner) {
        System.out.println("Enter true or false : ");
        String useCallable = scanner.next().toLowerCase();
        return Boolean.parseBoolean(useCallable);
    }
}
