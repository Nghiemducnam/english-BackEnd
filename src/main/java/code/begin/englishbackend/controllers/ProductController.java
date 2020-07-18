package code.begin.englishbackend.controllers;

import code.begin.englishbackend.dtos.ProductDetailInputDTO;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Void> updateProfile(@ModelAttribute ProductDetailInputDTO productDTO) throws LogicException {
//        logger.info("RESET request to update user profile: {}", userDTO);
        productService.updateUserProfile(productDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
