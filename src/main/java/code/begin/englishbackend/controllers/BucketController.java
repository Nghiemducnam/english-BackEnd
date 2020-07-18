package code.begin.englishbackend.controllers;

import code.begin.englishbackend.common.api.CommonResult;
import code.begin.englishbackend.dtos.BucketUploadDto;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hanv
 */
@Controller
@RequestMapping("/api")
public class BucketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketController.class);
    @Value("${minio.url}")
    private String ENDPOINT;
    @Value("${minio.buckek.name}")
    private String BUCKET_NAME;
    @Value("${minio.access.name}")
    private String ACCESS_KEY;
    @Value("${minio.access.secret}")
    private String SECRET_KEY;

    @PostMapping("/upload")
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                LOGGER.info("Bucket not exist!");
            } else {
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            String objectName = sdf.format(new Date()) + "/" + filename;
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            BucketUploadDto bucketUploadDto = new BucketUploadDto();
            bucketUploadDto.setName(filename);
            bucketUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(bucketUploadDto);
        } catch (Exception e) {
            LOGGER.info("ex: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

//    @DeleteMapping("/{objectName}")
//    @ResponseBody
//    public CommonResult delete(@PathVariable("objectName") String objectName) {
//        try {
//            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
//            minioClient.removeObject(BUCKET_NAME, objectName);
//            return CommonResult.success(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CommonResult.failed();
//    }
}

