package nl.han.aim.oosevt.lamport.services.intervention;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.create.CreateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateCommandRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update.UpdateQuestionRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterventionService {
    void updateCommand(UpdateCommandRequestDTO updateCommandRequestDTO);

    void createCommand(CreateCommandRequestDTO createCommandRequestDTO);

    void updateQuestion(UpdateQuestionRequestDTO updateQuestionRequestDTO);

    void createQuestion(CreateQuestionRequestDTO createQuestionRequestDTO);

    List<InterventionResponseDTO> getInterventions();
}
