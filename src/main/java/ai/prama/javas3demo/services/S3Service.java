package ai.prama.javas3demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String getS3Object(String bucket, String key) {
        final ResponseInputStream<GetObjectResponse> response = s3Client.getObject(b -> b.bucket(bucket).key(key));
        try {
            return StreamUtils.copyToString(response, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed while retrieving bytes. bucket=%s key=%s", bucket, key), e);
        }
    }
}
