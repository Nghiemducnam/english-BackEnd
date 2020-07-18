package code.begin.englishbackend.services;

import code.begin.englishbackend.dtos.ProductDetailInputDTO;
import code.begin.englishbackend.exceptions.LogicException;

public interface ProductService {
    void updateUserProfile(ProductDetailInputDTO productDTO) throws LogicException;
}
