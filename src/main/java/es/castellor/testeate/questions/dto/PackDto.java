package es.castellor.testeate.questions.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PackDto {
    @NotNull
    private UUID uuid;
    @NotNull
    @NotEmpty
    private String name;

    private List<QuestionDto> questions = new ArrayList<>();
}
