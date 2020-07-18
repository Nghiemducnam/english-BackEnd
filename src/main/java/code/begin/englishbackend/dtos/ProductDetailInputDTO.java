package code.begin.englishbackend.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@ToString
public class ProductDetailInputDTO {
    private Long id;
    private String name;
    private Long price;
    private String supplier;
    private String image;
    private MultipartFile imageFile;
}
