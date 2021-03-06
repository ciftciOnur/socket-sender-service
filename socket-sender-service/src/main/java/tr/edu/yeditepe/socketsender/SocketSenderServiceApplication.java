package tr.edu.yeditepe.socketsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {"tr.edu.yeditepe.socketsender.interfaces"})
public class SocketSenderServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SocketSenderServiceApplication.class, args);
		
	}
	

}
