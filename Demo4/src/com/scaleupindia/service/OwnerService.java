package com.scaleupindia.service;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.exceptions.DuplicateOwnerException;
import com.scaleupindia.exceptions.OwnerNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException, ClassNotFoundException;

    List<OwnerDTO> updatePetDetails(PetType petType, boolean useCallable) throws OwnerNotFoundException;

    void deleteOwner(int OwnerId) throws OwnerNotFoundException;

    List<OwnerDTO> findAllOwners();

    OwnerDTO findOwnerUsingEmailAndDate(String emailId, LocalDate petDateOfBirth);

    List<OwnerDTO> findOwners(PetType petType);

}
