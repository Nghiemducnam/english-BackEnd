package code.begin.englishbackend.services.impl;

import code.begin.englishbackend.exceptions.ErrorCode;
import code.begin.englishbackend.exceptions.LogicException;
import code.begin.englishbackend.services.FileUploadService;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    @Value("${minio.url}")
    private String ENDPOINT;
    @Value("${minio.buckek.name}")
    private String BUCKET_NAME;
    @Value("${minio.access.name}")
    private String ACCESS_KEY;
    @Value("${minio.access.secret}")
    private String SECRET_KEY;

    @Override
    public String uploadFile(MultipartFile file) throws LogicException {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                LOGGER.info("Bucket exist!");
            } else {
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            String objectName = sdf.format(new Date()) + "/" + filename;
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            return String.format("%s/%s/%s", ENDPOINT, BUCKET_NAME, objectName);
        } catch (Exception e) {
            throw new LogicException(ErrorCode.UPLOAD_FILE_FAILED);
        }
    }

//    public CommonResult delete(@RequestParam("objectName") String objectName) throws LogicException {
//        try {
//            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
//            minioClient.removeObject(BUCKET_NAME, objectName);
//            return CommonResult.success(null);
//        } catch (Exception e) {
//            throw new LogicException(ErrorCode.DELETE_FILE_FAILED);
//        }
//    }
}

