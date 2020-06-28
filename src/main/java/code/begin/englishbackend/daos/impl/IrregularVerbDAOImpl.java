package code.begin.englishbackend.daos.impl;

import code.begin.englishbackend.daos.IrregularVerbDAO;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.models.IrregularVerb;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class IrregularVerbDAOImpl extends AbstractBaseDAO implements IrregularVerbDAO {
    private static final Logger logger = LoggerFactory.getLogger(IrregularVerbDAOImpl.class);
    private static final String VERB = "verb";

    @Override
    public void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        sqlBuilder.append(" irr.id,");
        sqlBuilder.append(" irr.verb,");
        sqlBuilder.append(" irr.verb1,");
        sqlBuilder.append(" irr.verb2,");
        sqlBuilder.append(" irr.meaning,");
        sqlBuilder.append(" irr.spelling_verb,");
        sqlBuilder.append(" irr.spelling_verb1,");
        sqlBuilder.append(" irr.spelling_verb2,");
        sqlBuilder.append(" irr.verb_category");
        sqlBuilder.append(" from Irregular_verb as irr");
        sqlBuilder.append(" where 1 = 1 ");
        if (irregularVerbSearchDTO.getOrders() != null && !irregularVerbSearchDTO.getOrders().isEmpty()) {
            sqlBuilder.append(" order by ");
            irregularVerbSearchDTO.getOrders().forEach(order -> {
                String property = StringUtils.trimToEmpty(order.getProperty());
                switch (property) {
//    /*attention*/
                    case VERB:
                        sqlBuilder.append(" irr.verb ").append(getOrderBy(order.isAscending())).append(",");
                        break;
                    case "verb1":
                        sqlBuilder.append(" irr.verb1 ").append(getOrderBy(order.isAscending())).append(",");
                        break;
                    case "verb2":
                        sqlBuilder.append(" irr.verb2 ").append(getOrderBy(order.isAscending())).append(",");
                        break;
                    default:
                }
            });
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        } else {
            sqlBuilder.append(" order by irr.verb ");
        }
        Map<String, Object> parameters = new HashMap<>();
        searchAndCountTotal(irregularVerbSearchDTO, sqlBuilder.toString(), parameters, IrregularVerb.class);
    }
}
