package code.begin.englishbackend.controllers;

import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.models.IrregularVerb;
import code.begin.englishbackend.services.IrregularVerbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/irregularVerb")
public class IrregularVerbController {
    private static final Logger logger = LoggerFactory.getLogger(IrregularVerbController.class);
    @Autowired
    private IrregularVerbService irregularVerbService;

    @PostMapping("/create")
    public ResponseEntity<?> createIrregularVerb(@RequestBody IrregularVerb irregularVerb) {
        logger.info("RESET request to create irregularVerb: {}", irregularVerb);
        this.irregularVerbService.createIrregularVerb(irregularVerb);
        if (irregularVerb == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<IrregularVerbSearchDTO> getIrregularVerbList(@RequestBody IrregularVerbSearchDTO irregularVerbSearchDTO) {
        logger.info("Controller for getByEmployerId");
        this.irregularVerbService.getIrregularVerbs(irregularVerbSearchDTO);
        return new ResponseEntity<>(irregularVerbSearchDTO, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateIrregular(@RequestBody IrregularVerb irregularVerb) throws LogicException {
        logger.info("Controller for update irregular {}", irregularVerb);
        irregularVerbService.updateIrregularVerb(irregularVerb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIrregular(@PathVariable Long id) throws LogicException{
        logger.info("Controller for delete irregular with id {}", id);
        irregularVerbService.deleteIrregularVerb(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
