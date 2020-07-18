package code.begin.englishbackend.services.impl;

import code.begin.englishbackend.daos.ProductDAO;
import code.begin.englishbackend.dtos.ProductDetailInputDTO;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.models.Product;
import code.begin.englishbackend.services.FileUploadService;
import code.begin.englishbackend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final FileUploadService fileUploadService;

    @Autowired
    private ProductDAO productDAO;
    @Override
    public void updateUserProfile(ProductDetailInputDTO productDTO) throws LogicException {
//        logger.info("Service to update user profile: {}", userDTO);
//        Long userId = SecurityUtils.getCurrentUserIdLogin();
        // get user info
//        User user = getUserById(userId);
        Product product = new Product();
        if (productDTO.getImageFile() != null) {
            product.setImage(fileUploadService.uploadFile(productDTO.getImageFile()));
        }
        if(productDTO.getName() != null){
            product.setName(productDTO.getName());
        }
        if(productDTO.getPrice() != null){
            product.setPrice(productDTO.getPrice());
        }
        productDAO.save(product);

        }

    }

