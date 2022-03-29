package es.castellor.testeate.questions.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pack {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column
    private String name;

    @Column
    private Boolean active;

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "pack")
    private List<PredefinedTest> predefinedTests = new ArrayList<>();

    @Column(name = "created", columnDefinition = "DATETIME")
    private LocalDateTime created;

    @Column(name = "updated", columnDefinition = "DATETIME")
    private LocalDateTime updated;

}
