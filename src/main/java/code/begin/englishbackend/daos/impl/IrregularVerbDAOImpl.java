package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.IrregularVerbDAO;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.models.IrregularVerb;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IrregularVerbDAOImpl extends AbstractBaseDAO implements IrregularVerbDAO {
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
}
