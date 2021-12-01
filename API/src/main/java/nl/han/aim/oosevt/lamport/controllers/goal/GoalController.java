package nl.han.aim.oosevt.lamport.controllers.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
@CrossOrigin
public class GoalController {
    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping()
    public void createGoal(@RequestBody CreateGoalRequestDTO createGoalRequestDTO) {
        goalService.createGoal(createGoalRequestDTO);
    }
}
