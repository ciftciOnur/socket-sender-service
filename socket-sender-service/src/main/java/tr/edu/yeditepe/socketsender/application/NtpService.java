package tr.edu.yeditepe.socketsender.application;

import java.io.IOException;

public interface NtpService {

	long getTime() throws IOException;

}
