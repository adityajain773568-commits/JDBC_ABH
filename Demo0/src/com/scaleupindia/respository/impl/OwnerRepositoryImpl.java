package com.scaleupindia.respository.impl;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.Gender;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.respository.OwnerRepository;
import com.scaleupindia.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerRepositoryImpl implements OwnerRepository {
    private static List<OwnerDTO> ownerDTOList;

    static {
        ownerDTOList = new ArrayList<>();
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(1);
        ownerDTO.setFirstName("Aditya ");
        ownerDTO.setLastName("Jain");
        ownerDTO.setGender(Gender.M);
        ownerDTO.setCity("Indore");
        ownerDTO.setState("Madhya Pradesh");
        ownerDTO.setMobileNumber("9755268776");
        ownerDTO.setEmailId("adityajain77356@gmail.com");
        ownerDTO.setPetId(1);
        ownerDTO.setPetName("Chikki");
        ownerDTO.setPetBirthDate(InputUtil.convertStringToDate("20-07-2025"));
        ownerDTO.setPetGender(Gender.F);
        ownerDTO.setPetType(PetType.RABBIT);
        ownerDTOList.add(ownerDTO);

    }

    @Override
    public void saveOwner(OwnerDTO ownerDTO) {
        ownerDTOList.add(ownerDTO);
    }

    @Override
    public Optional<OwnerDTO> findOwner(int ownerId) {
        return ownerDTOList.stream().filter(owner -> owner.getId() == ownerId).findFirst();
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
        ownerDTOList.stream().filter(owner -> owner.getId()==ownerId ).findFirst().ifPresent(owner -> owner.setPetName(petName) );
    }

    @Override
    public void deleteOwner(int ownerId) {
        ownerDTOList.remove(ownerId);
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        return ownerDTOList ;
    }
}
