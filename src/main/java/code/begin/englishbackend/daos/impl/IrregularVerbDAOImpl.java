package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.IrregularVerbDAO;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.models.IrregularVerb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class IrregularVerbDAOImpl extends AbstractBaseDAO implements IrregularVerbDAO {
    private static final Logger logger = LoggerFactory.getLogger(IrregularVerbDAOImpl.class);
    @Override
    public void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        sqlBuilder.append(" irr.id,");
        sqlBuilder.append(" irr.verb,");
        sqlBuilder.append(" irr.verb1,");
        sqlBuilder.append(" irr.verb2,");
        sqlBuilder.append(" irr.meaning,");
        sqlBuilder.append(" irr.verb_category");
        sqlBuilder.append(" from Irregular_verb as irr");
        sqlBuilder.append(" where irr.verb_category =:id ");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", 1);
        searchAndCountTotal(irregularVerbSearchDTO, sqlBuilder.toString(), parameters, IrregularVerb.class);

//    @Override
//    public void createIrregularVerb(IrregularVerb irregularVerb) {
//        Session currentSession = getSession();
//        currentSession.save(irregularVerb);
    }

//    @Override
//    public void updateIrregularVerb(IrregularVerb irregularVerb) throws LogicException {
//            logger.info("IrregularVerbDAOImpl {}", irregularVerb);
//        Optional<IrregularVerb> irregularVerbDB =
//
//    }
}
