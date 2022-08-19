package ai.prama.javas3demo.endpoints;

import ai.prama.javas3demo.services.S3Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/aws/v2/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/objects/{bucket}/{key}")
    public String getS3Object(@PathVariable String bucket, @PathVariable String key) {
        return s3Service.getS3Object(bucket, key);
    }

}
