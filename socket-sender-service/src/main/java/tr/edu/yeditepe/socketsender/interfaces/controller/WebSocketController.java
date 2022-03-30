package tr.edu.yeditepe.socketsender.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import tr.edu.yeditepe.socketsender.interfaces.SocketSenderServiceFacade;
import tr.edu.yeditepe.socketsender.interfaces.VerifierServiceClient;
import tr.edu.yeditepe.socketsender.interfaces.dto.LocationProofDTO;
import tr.edu.yeditepe.socketsender.interfaces.dto.LocationRequestDTO;
import tr.edu.yeditepe.socketsender.interfaces.dto.RequestDto;

@Controller
public class WebSocketController {
	
	@Autowired
	SocketSenderServiceFacade socketSenderServiceFacade;
	
	@Autowired
	VerifierServiceClient verifierServiceClient;

	@MessageMapping("/proof-request")
	@SendTo("/topic/get-proof")
	public String getProof(String message) throws Exception {
		verifierServiceClient.sendRequest(RequestDto.builder()
				.encryptedString(message)
				.build());
		return message;
	  }
	
	@MessageMapping("/proof-witness")
	public void receiveProof(String message) throws Exception {
		verifierServiceClient.sendWitness(RequestDto.builder()
				.encryptedString(message)
				.build());
	  }
	  

}
