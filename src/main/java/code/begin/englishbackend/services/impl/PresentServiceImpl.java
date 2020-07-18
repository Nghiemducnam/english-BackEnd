package code.begin.englishbackend.services.impl;

import code.begin.englishbackend.daos.PresentDAO;
import code.begin.englishbackend.models.Present;
import code.begin.englishbackend.services.PresentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PresentServiceImpl implements PresentService {
    private static final Logger logger = LoggerFactory.getLogger(PresentServiceImpl.class);
    @Autowired
    private PresentDAO presentDAO;

    @Override
    public void addPresent(Present present) {
        logger.info("PresentModel {}", present);
        Present presentModel  = new Present();
        presentModel.setCategoryId(present.getCategoryId());
        presentModel.setPresentName(present.getPresentName());
        presentModel.setContent(present.getContent());
        this.presentDAO.save(present);
    }

    @Override
    public Present findPresentById(Long id) {
        return null;
    }

    @Override
    public void updatePresent(Present present) {

    }
}
