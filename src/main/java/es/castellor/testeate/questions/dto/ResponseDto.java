package es.castellor.testeate.questions.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ResponseDto {

    @NotEmpty
    private String text;
    @NotNull
    private Integer order;
    @NotNull
    private Boolean correct;
}
