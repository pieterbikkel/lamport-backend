package nl.han.aim.oosevt.lamport.services.franchise;

import nl.han.aim.oosevt.lamport.controllers.franchise.dto.CreateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.UpdateFranchiseRequestDTO;
import nl.han.aim.oosevt.lamport.data.dao.franchise.FranchiseDAO;
import nl.han.aim.oosevt.lamport.data.entity.Franchise;
import nl.han.aim.oosevt.lamport.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FranchiseServiceImpl implements FranchiseService {
    private final FranchiseDAO franchiseDAO;

    private void assertValidFranchise(int id) {
        if (this.franchiseDAO.getFranchiseById(id) == null) {
            throw new NotFoundException();
        }
    }

    @Autowired
    public FranchiseServiceImpl(FranchiseDAO franchiseDAO) {
        this.franchiseDAO = franchiseDAO;
    }

    @Override
    public void createFranchise(CreateFranchiseRequestDTO franchise) {
        franchise.validate();
        this.franchiseDAO.createFranchise(franchise.getName());
    }

    @Override
    public void updateFranchise(UpdateFranchiseRequestDTO newData) {
        newData.validate();
        assertValidFranchise(newData.getId());

        this.franchiseDAO.updateFranchise(newData.getId(), newData.getName());
    }

    @Override
    public void deleteFranchise(int id) {
        assertValidFranchise(id);
        this.franchiseDAO.deleteFranchise(id);
    }

    @Override
    public FranchiseResponseDTO getFranchiseById(int id) {
        final Franchise franchise = franchiseDAO.getFranchiseById(id);

        if(franchise == null) {
            throw new NotFoundException();
        }

        return FranchiseResponseDTO.fromData(franchise);
    }

    @Override
    public List<FranchiseResponseDTO> getFranchises() {
        return franchiseDAO
                .getFranchises()
                .stream()
                .map(FranchiseResponseDTO::fromData)
                .collect(Collectors.toList());
    }
}
