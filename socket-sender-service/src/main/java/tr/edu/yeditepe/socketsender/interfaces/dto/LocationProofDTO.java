package tr.edu.yeditepe.socketsender.interfaces.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationProofDTO {
	
	private long timeInterval;
	private String otpKey;
	private String privateId;
	private String witnessId;
	
}
