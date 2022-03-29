package es.castellor.testeate.questions.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Question {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pack pack;

    @Column(name = "created", columnDefinition = "DATETIME")
    private LocalDateTime created;

    @Column(name = "updated", columnDefinition = "DATETIME")
    private LocalDateTime updated;

    @ElementCollection
    private List<Response> responses = new ArrayList<>();
}
