package ai.prama.javas3demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootApplication
public class JavaS3DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaS3DemoApplication.class, args);
    }

    /**
     * Creates an {@link S3Client} bean for use in the application. It will be shared across the application. It will
     * also get automatically destroyed and resources closed when the Spring context is destroyed. This bean is
     * effectively tied to a region. If you want to use a different region, create a new instance of the application
     * with a different profile. It is not meant to support multiple regions at the same time in the same running
     * application.
     *
     * @param region AWS {@link Region}. Defaults to us-east-1 if not specified.
     */
    @Bean
    public S3Client s3Client(@Value("${aws.region:us-east-1}") Region region) {
        return S3Client.builder()
            .region(region)
            .build();
    }

}
