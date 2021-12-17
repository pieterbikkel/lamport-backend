package nl.han.aim.oosevt.lamport.controllers.intervention.dto.response;

import nl.han.aim.oosevt.lamport.data.entity.Questionnaire;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionnaireResponseDTO extends InterventionResponseDTO {
    private List<QuestionResponseDTO> questions;

    public QuestionnaireResponseDTO(int id, String name, List<QuestionResponseDTO> questions) {
        super(id, name);
        this.questions = questions;
    }

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

    public static QuestionnaireResponseDTO fromData(Questionnaire questionnaire) {
        return new QuestionnaireResponseDTO(
                questionnaire.getId(),
                questionnaire.getName(),
                questionnaire
                    .getQuestions()
                    .stream()
                    .map(QuestionResponseDTO::fromData)
                    .collect(Collectors.toList())
        );
    }
}
