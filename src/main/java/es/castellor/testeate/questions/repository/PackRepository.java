package es.castellor.testeate.questions.repository;

import es.castellor.testeate.questions.model.Pack;
import es.castellor.testeate.questions.projections.PackView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PackRepository extends CrudRepository<Pack, UUID> {

    List<PackView> findByActive(Boolean active);

    List<PackView> findAllProjectedBy();
}
