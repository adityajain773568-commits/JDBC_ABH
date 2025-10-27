package com.scaleupindia;

import com.scaleupindia.service.OwnerService;
import com.scaleupindia.service.impl.OwnerServiceImpl;
import com.scaleupindia.util.InputUtil;

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
                    case 1:
                        ownerService.saveOwnersAutomatically(InputUtil.generateOwners());
                        System.out.println("Owner has been saved automatically successfully.");
                        break;

                    case 2:
                        ownerService.saveOwnersManually(InputUtil.generateOwners());
                        System.out.println("Owner has been saved manually successfully.");
                        break;

                    //Update details
                    case 3:
                        ownerService.saveOwnersManuallyWithSavepoint(InputUtil.generateOwners());
                        System.out.println("Owner has been saved manually with savepoint successfully.");
                        break;
                    //default case
                    default:
                        System.out.println("Invalid Option Entered");
                        break;
                }
            }while (InputUtil.wantToContinue(scanner));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
