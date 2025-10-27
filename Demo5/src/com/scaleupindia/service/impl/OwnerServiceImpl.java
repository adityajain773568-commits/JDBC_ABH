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

public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(){
        this.ownerRepository = new OwnerRepositoryImpl();
    }

    @Override
    public void saveOwnersAutomatically(List<OwnerDTO> ownerDTOS) {
        ownerRepository.saveOwnersAutomatically(ownerDTOS);
    }

    @Override
    public void saveOwnersManually(List<OwnerDTO> ownerDTOS) {
        ownerRepository.saveOwnersManually(ownerDTOS);
    }

    @Override
    public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOS) {
        ownerRepository.saveOwnersManuallyWithSavepoint(ownerDTOS);
    }
}
