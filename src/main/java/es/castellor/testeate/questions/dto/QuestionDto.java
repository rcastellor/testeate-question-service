package es.castellor.testeate.questions.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDto {
    @NotEmpty
    private String text;
    @NotEmpty
    @NotNull
    private List<ResponseDto> responses = new ArrayList<>();
}
