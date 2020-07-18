package code.begin.englishbackend.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hanv
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BucketUploadDto {
    private String url;
    private String name;
}

