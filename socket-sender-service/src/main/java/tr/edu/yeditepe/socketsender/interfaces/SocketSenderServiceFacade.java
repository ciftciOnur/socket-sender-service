package tr.edu.yeditepe.socketsender.interfaces;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public interface SocketSenderServiceFacade {

	void send() throws IOException;

	void receive() throws UnknownHostException, IOException;

	String applyAesEncryption(String input, String otp);

}
