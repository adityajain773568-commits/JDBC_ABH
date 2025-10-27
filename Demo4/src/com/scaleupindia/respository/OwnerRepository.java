package com.scaleupindia.respository;

import com.scaleupindia.dto.OwnerDTO;

import java.time.LocalDate;
import java.util.List;

public interface OwnerRepository {
    void saveOwner(OwnerDTO ownerDTO) throws ClassNotFoundException;

    OwnerDTO findOwner(int ownerId);

    List<OwnerDTO> updatePetDetailsWithCallable(String petType);
    List<OwnerDTO> updatePetDetailsWithoutCallable(String petType);

    void deleteOwner(int ownerId);

    List<OwnerDTO> findAllOwners();

    OwnerDTO findOwnerUsingEmailIdAndDate(String emailId, LocalDate petDateOfBirth);

    List<OwnerDTO> findOwners(String petType);
}
