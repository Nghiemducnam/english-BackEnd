package code.begin.englishbackend.controllers;

import code.begin.englishbackend.models.HttpResult;
import code.begin.englishbackend.models.IrregularVerb;
import code.begin.englishbackend.services.IrregularVerbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
