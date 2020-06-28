package code.begin.englishbackend.services.impl;

import code.begin.englishbackend.daos.IrregularVerbDAO;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.models.HttpResult;
import code.begin.englishbackend.models.IrregularVerb;
import code.begin.englishbackend.services.IrregularVerbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        irregularVerbDB.setVerbCategory(irregularVerb.getVerbCategory());
        irregularVerbDB.setId(irregularVerb.getId());

            this.irregularVerbDAO.save(irregularVerbDB);
    }

    @Override
    public void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO) {
        logger.info("Controller for getByEmployerId: {}",irregularVerbSearchDTO);
        this.irregularVerbDAO.getIrregularVerbs(irregularVerbSearchDTO);
    }


}
