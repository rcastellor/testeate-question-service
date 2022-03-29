package es.castellor.testeate.questions.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class PredefinedTest {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @ManyToOne
    private Pack pack;

    @Column
    private String title;

    @ManyToMany
    @JoinTable(
            name = "test_questions",
            joinColumns = @JoinColumn(name = "test_uuid"),
            inverseJoinColumns = @JoinColumn(name = "question_uuid"))
    private List<Question> questions = new ArrayList<>();

    @Column(name = "created", columnDefinition = "DATETIME")
    private LocalDateTime created;

    @Column(name = "updated", columnDefinition = "DATETIME")
    private LocalDateTime updated;
}
