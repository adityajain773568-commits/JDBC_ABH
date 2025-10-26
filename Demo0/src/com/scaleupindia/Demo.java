package com.scaleupindia;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exceptions.DuplicateOwnerException;
import com.scaleupindia.exceptions.OwnerNotFoundException;
import com.scaleupindia.service.OwnerService;
import com.scaleupindia.service.impl.OwnerServiceImpl;
import com.scaleupindia.util.InputUtil;

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
                    //save owner first time
                    case 1:

                        OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailToSave(scanner);
                        ownerService.saveOwner(ownerDTO);
                        System.out.println("Owner has been saved successfully.");
                        break;

                    //fetch owner details
                    case 2:
                        int ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        ownerDTO = ownerService.findOwner(ownerId);
                        System.out.println("Owner has been fetched successfully.");
                        System.out.println(ownerDTO);
                        break;

                    //Update details
                    case 3:
                        ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        String petName = InputUtil.acceptPetDetailsToUpdate(scanner);
                        ownerService.updatePetDetails(ownerId,petName);
                        System.out.println("Pet Details of owner have been updated successfully.");
                        break;

                    //delete owner
                    case 4:
                        ownerId = InputUtil.acceptOwnerIdToOperate(scanner);
                        ownerService.deleteOwner(ownerId);
                        System.out.println("Owner has been deleted successfully.");
                        break;

                    //find all owners
                    case 5:
                        List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
                        System.out.println("There are " + ownerDTOList.size() + " owners.");
                        ownerDTOList.forEach(System.out::println);
                        break;

                    //default case
                    default:
                        break;

                }

            }while (InputUtil.wantToContinue(scanner));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
