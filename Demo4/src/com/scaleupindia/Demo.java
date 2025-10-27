package com.scaleupindia;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.exceptions.DuplicateOwnerException;
import com.scaleupindia.exceptions.OwnerNotFoundException;
import com.scaleupindia.service.OwnerService;
import com.scaleupindia.service.impl.OwnerServiceImpl;
import com.scaleupindia.util.InputUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Demo {
    static void main(String[] args) {
        Demo demo = new Demo();
        demo.run(args);
    }
    public void run(String... args){
        OwnerService ownerService = new OwnerServiceImpl();
        try(Scanner scanner = new Scanner(System.in)) {
            do{
                System.out.println("Welcome to Petistaan");
                int menuOption = InputUtil.acceptMenuOption(scanner);

                switch (menuOption){
                    //find pet owners using pet type
                    case 1:
                        PetType petType = InputUtil.acceptPetTypeToOperate(scanner);
                        List<OwnerDTO> ownerDTOList = ownerService.findOwners(petType);
                        System.out.println("There are " + ownerDTOList.size() + " owners with the pet type as " + petType);
                        ownerDTOList.forEach(System.out::println);
                        break;

                    //update pet details
                    case 2:
                         petType = InputUtil.acceptPetTypeToOperate(scanner);
                         ownerDTOList = ownerService.updatePetDetails(petType,InputUtil.wantToUseCallable(scanner));
                        System.out.println("Updated details of "+ownerDTOList.size() + " pets with pet type as " + petType);
                        ownerDTOList.forEach(System.out::println);
                        break;

                    //default case
                    default:
                        System.out.println("Invalid option entered!");
                        break;


                }

            }while (InputUtil.wantToContinue(scanner));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
