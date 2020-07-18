package code.begin.englishbackend.services;

import code.begin.englishbackend.models.Present;

public interface PresentService {
    void addPresent(Present present);

    Present findPresentById(Long id);

    void updatePresent(Present present);
}
