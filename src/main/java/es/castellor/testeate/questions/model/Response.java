package es.castellor.testeate.questions.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Response {
    private String text;
    private Integer questionOrder;
    private Boolean correct;
}
