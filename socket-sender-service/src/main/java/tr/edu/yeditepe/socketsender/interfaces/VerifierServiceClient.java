package tr.edu.yeditepe.socketsender.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tr.edu.yeditepe.socketsender.infrastructure.feign.GenericFeignConfiguration;
import tr.edu.yeditepe.socketsender.interfaces.dto.RequestDto;

@FeignClient(value = "verifierServiceClient", url = "${verifier-service.url}", configuration = GenericFeignConfiguration.class)
public interface VerifierServiceClient {
	
    @PostMapping("/api/verify/request")
    Boolean sendRequest(@RequestBody RequestDto requestDto);
    
    @PostMapping("/api/verify/witness")
    Boolean sendWitness(@RequestBody RequestDto requestDto);

}
