package nl.han.aim.oosevt.lamport.services.goal;

import nl.han.aim.oosevt.lamport.controllers.goal.dto.GoalResponseDTO;

import java.util.List;

public interface GoalService {
    List<GoalResponseDTO> getGoals();
    GoalResponseDTO getGoal(int id);
}
