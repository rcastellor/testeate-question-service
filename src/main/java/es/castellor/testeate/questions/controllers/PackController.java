package es.castellor.testeate.questions.controllers;

import es.castellor.testeate.questions.dto.PackDto;
import es.castellor.testeate.questions.projections.PackView;
import es.castellor.testeate.questions.services.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PackController {

    @Autowired
    private PackService packService;

    @GetMapping("pack/active")
    public List<PackView> findActivePacks() {
        return this.packService.findActivePacks();
    }

    @GetMapping("pack")
    public List<PackView> findPacks() {
        return this.packService.findAllPacks();
    }

    @PostMapping("pack")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createPack(@Valid @RequestBody PackDto packDto) {
        try {
            this.packService.createPack(packDto);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Pack already exist", illegalArgumentException);
        }
    }

}
