package nl.han.aim.oosevt.lamport.data.dao.goal;

import nl.han.aim.oosevt.lamport.data.entity.Goal;

import java.util.List;

public interface GoalDAO {
    void createGoal(String name);
    Goal getGoalById(int goalId);
    List<Goal> getGoals();
    List<Goal> getGoalsBySearch(String query);
    void updateGoal(int goalId, String name);
    void deleteGoal(int goalId);
}
