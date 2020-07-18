package code.begin.englishbackend.services;

import code.begin.englishbackend.exceptions.LogicException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file) throws LogicException;

//    CommonResult delete(String objectName) throws LogicException;
}
