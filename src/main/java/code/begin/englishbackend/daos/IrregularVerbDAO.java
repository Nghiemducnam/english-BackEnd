package code.begin.englishbackend.daos;

import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.models.IrregularVerb;

public interface IrregularVerbDAO extends BaseDAO{
//    void createIrregularVerb(IrregularVerb irregularVerb);
    void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO);
//    void updateIrregularVerb(IrregularVerb irregularVerb);
}
