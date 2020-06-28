package code.begin.englishbackend.services;
import code.begin.englishbackend.dtos.IrregularVerbSearchDTO;
import code.begin.englishbackend.models.IrregularVerb;

public interface IrregularVerbService {
    void createIrregularVerb(IrregularVerb irregularVerb);
    void getIrregularVerbs(IrregularVerbSearchDTO irregularVerbSearchDTO);
}
