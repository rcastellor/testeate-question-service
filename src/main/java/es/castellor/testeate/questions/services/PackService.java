package es.castellor.testeate.questions.services;

import es.castellor.testeate.questions.dto.PackDto;
import es.castellor.testeate.questions.dto.QuestionDto;
import es.castellor.testeate.questions.dto.ResponseDto;
import es.castellor.testeate.questions.model.Pack;
import es.castellor.testeate.questions.model.Question;
import es.castellor.testeate.questions.model.Response;
import es.castellor.testeate.questions.projections.PackView;
import es.castellor.testeate.questions.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public void createPack(PackDto packDto) {
        Optional<Pack> retrievedPack = this.packRepository.findById(packDto.getUuid());
        checkArgument(retrievedPack.isEmpty(), "Pack already exists");
        Pack newPack = new Pack();
        newPack.setUuid(packDto.getUuid());
        newPack.setName(packDto.getName());
        newPack.setActive(false);
        LocalDateTime creationDate = LocalDateTime.now();
        newPack.setCreated(creationDate);
        newPack.setUpdated(creationDate);
        for (QuestionDto questionDto : packDto.getQuestions()) {
            checkArgument(!questionDto.getResponses().isEmpty(), "Question has no responses");
            Question question = new Question();
            question.setUuid(UUID.randomUUID());
            question.setCreated(creationDate);
            question.setUpdated(creationDate);
            question.setText(questionDto.getText());
            for(ResponseDto responseDto : questionDto.getResponses()) {
                Response response = new Response();
                response.setText(responseDto.getText());
                response.setQuestionOrder(responseDto.getOrder());
                response.setCorrect(responseDto.getCorrect());
                question.getResponses().add(response);
            }
            question.setPack(newPack);
            newPack.getQuestions().add(question);
        }
        this.packRepository.save(newPack);
    }

    public List<PackView> findAllPacks() {
        return this.packRepository.findAllProjectedBy();
    }

    public List<PackView> findActivePacks() {
        return this.packRepository.findByActive(true);
    }
}
