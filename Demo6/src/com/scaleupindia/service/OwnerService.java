package com.scaleupindia.service;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.exceptions.DuplicateOwnerException;
import com.scaleupindia.exceptions.OwnerNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException, ClassNotFoundException;

    OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

    void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(int OwnerId) throws OwnerNotFoundException;

    List<OwnerDTO> findAllOwners();

    List<OwnerDTO> updatePetDetails(PetType petType);
}
