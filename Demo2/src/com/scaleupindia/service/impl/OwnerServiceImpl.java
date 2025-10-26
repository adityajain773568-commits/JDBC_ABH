package com.scaleupindia.service.impl;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.exceptions.DuplicateOwnerException;
import com.scaleupindia.exceptions.OwnerNotFoundException;
import com.scaleupindia.respository.OwnerRepository;
import com.scaleupindia.respository.impl.OwnerRepositoryImpl;
import com.scaleupindia.service.OwnerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository ownerRepository;
    private static final String OWNER_ALREADY_EXISTS = "Owner already exists with ownerId ";
    private static final String OWNER_NOT_FOUND= "Can't find owner with ownerId ";


    public OwnerServiceImpl(){
        this.ownerRepository = new OwnerRepositoryImpl();
    }

    @Override
    public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException, ClassNotFoundException {
        OwnerDTO existingOwnerDTO = ownerRepository.findOwner(ownerDTO.getId());
        if (Objects.nonNull(existingOwnerDTO)){
            throw new DuplicateOwnerException(OWNER_ALREADY_EXISTS + ownerDTO.getId());
        }else {
            ownerRepository.saveOwner(ownerDTO);
        }
    }

    @Override
    public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
        OwnerDTO ownerDTO = ownerRepository.findOwner(ownerId);
        if (Objects.isNull(ownerDTO)){
            throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);

        }else {
            return ownerDTO;
        }
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
        OwnerDTO ownerDTO = ownerRepository.findOwner(ownerId);
        if (Objects.isNull(ownerDTO)){
            throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
        }else {
            ownerRepository.updatePetDetails(ownerId,petName);
        }
    }

    @Override
    public void deleteOwner(int ownerId) throws OwnerNotFoundException {
        OwnerDTO ownerDTO = ownerRepository.findOwner(ownerId);
        if (Objects.isNull(ownerDTO)){
            throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);

        }else {
            ownerRepository.deleteOwner(ownerId);
        }
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        return ownerRepository.findAllOwners();
    }

    @Override
    public OwnerDTO findOwnerUsingEmailAndDate(String emailId, LocalDate petDateOfBirth) {
        OwnerDTO ownerDTO = ownerRepository.findOwnerUsingEmailIdAndDate( emailId,  petDateOfBirth);
        if (Objects.isNull(ownerDTO)){
            throw new RuntimeException();
        }else {
            return ownerDTO;
        }
    }
}
