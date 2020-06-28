package code.begin.englishbackend.services.impl;

import code.begin.englishbackend.daos.IrregularVerbDAO;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.exceptions.ErrorCode;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.models.HttpResult;
import code.begin.englishbackend.models.IrregularVerb;
import code.begin.englishbackend.services.IrregularVerbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class IrregularVerbServiceImpl implements IrregularVerbService {

    private static final Logger logger = LoggerFactory.getLogger(IrregularVerbServiceImpl.class);

    @Autowired
    private IrregularVerbDAO irregularVerbDAO;

    @Override
    public void createIrregularVerb(IrregularVerb irregularVerb) {
        logger.info("SERVICE for add irregularVerb:{}", irregularVerb);
        IrregularVerb irregularVerbDB = new IrregularVerb();
        irregularVerbDB.setVerb(irregularVerb.getVerb());
        irregularVerbDB.setVerb1(irregularVerb.getVerb1());
        irregularVerbDB.setVerb2(irregularVerb.getVerb2());
        irregularVerbDB.setMeaning(irregularVerb.getMeaning());
        irregularVerbDB.setSpellingVerb(irregularVerb.getSpellingVerb());
        irregularVerbDB.setSpellingVerb1(irregularVerb.getSpellingVerb1());
        irregularVerbDB.setSpellingVerb2(irregularVerb.getSpellingVerb2());
        irregularVerbDB.setVerbCategory(irregularVerb.getVerbCategory());
        irregularVerbDB.setId(irregularVerb.getId());

            this.irregularVerbDAO.save(irregularVerbDB);
    }

    @Override
    public void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO) {
        logger.info("Controller for getByEmployerId: {}",irregularVerbSearchDTO);
        this.irregularVerbDAO.getIrregularVerbs(irregularVerbSearchDTO);
    }

    @Override
    public void updateIrregularVerb(IrregularVerb irregularVerb) throws LogicException {
        logger.info("updateIrregularVerb form serviceImpl {}", irregularVerb);
        if(irregularVerb.getId() != null){
            Optional<IrregularVerb> currentIrregularVerb
                    = irregularVerbDAO.findById(irregularVerb.getId(), IrregularVerb.class);
            if(!currentIrregularVerb.isPresent()){
                throw new LogicException(ErrorCode.RECORD_NOT_FOUND);
            }else {
                IrregularVerb irregularVerbDB = currentIrregularVerb.get();
                irregularVerbDB.setVerb1(irregularVerb.getVerb1());
                irregularVerbDB.setVerb(irregularVerb.getVerb());
                irregularVerbDB.setVerb2(irregularVerb.getVerb2());
                irregularVerbDB.setMeaning(irregularVerb.getMeaning());
                irregularVerbDB.setSpellingVerb(irregularVerb.getSpellingVerb());
                irregularVerbDB.setSpellingVerb1(irregularVerb.getSpellingVerb1());
                irregularVerbDB.setSpellingVerb2(irregularVerb.getSpellingVerb2());
                irregularVerbDB.setVerbCategory(irregularVerb.getVerbCategory());
                irregularVerbDAO.update(irregularVerbDB);
            }
        }else
            throw new LogicException(ErrorCode.BAD_REQUEST);
    }

    @Override
    public void deleteIrregularVerb(Long id) throws LogicException {
        Optional<IrregularVerb> currentIrregular = this.irregularVerbDAO.findById(id, IrregularVerb.class);
        if(currentIrregular.isPresent()){
            IrregularVerb irregularVerb = currentIrregular.get();
            irregularVerbDAO.delete(irregularVerb);
        }else {
            throw new LogicException(ErrorCode.RECORD_NOT_FOUND);
        }
    }
}
