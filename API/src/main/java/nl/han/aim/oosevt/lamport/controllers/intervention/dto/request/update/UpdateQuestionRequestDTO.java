package nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.update;

import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.AnswerRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.request.shared.QuestionRequestDTO;

import java.util.List;

public class UpdateQuestionRequestDTO extends QuestionRequestDTO {
    private int id;

    public UpdateQuestionRequestDTO(int id, String name, String question, List<AnswerRequestDTO> answers) {
        super(name, answers, question);
        this.id = id;
    }

    public UpdateQuestionRequestDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}
