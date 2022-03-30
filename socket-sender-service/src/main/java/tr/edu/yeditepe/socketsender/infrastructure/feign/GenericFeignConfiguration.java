package tr.edu.yeditepe.socketsender.infrastructure.feign;

import feign.Contract;
import feign.Request;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GenericFeignConfiguration {

    private static final int CONNECT_TIMEOUT_MS = 1000000;
    private static final int READ_TIMEOUT_MS = 1000000;

    @Bean
    public Contract useSpringMvcAnnotations() {
        return new SpringMvcContract();
    }

    @Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS, READ_TIMEOUT_MS, TimeUnit.MILLISECONDS, true);
    }
}
