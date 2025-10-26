package com.scaleupindia.respository;

import com.scaleupindia.dto.OwnerDTO;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    void saveOwner(OwnerDTO ownerDTO);

    Optional<OwnerDTO> findOwner(int ownerId);

    void updatePetDetails(int ownerId , String petName);

    void deleteOwner(int ownerId);

    List<OwnerDTO> findAllOwners();

}
