package com.scaleupindia.respository;

import com.scaleupindia.dto.OwnerDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    void saveOwnersAutomatically(List<OwnerDTO> ownerDTOS);

    void saveOwnersManually(List<OwnerDTO> ownerDTOS);

    void saveOwnersManuallyWithSavepoint(List<OwnerDTO> ownerDTOS);


}
