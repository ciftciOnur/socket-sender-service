package tr.edu.yeditepe.socketsender.application.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tr.edu.yeditepe.socketsender.application.NtpService;



@Component
public class NtpServiceImpl implements NtpService{
	
	@Value("${ntp-service.url}")
	private String timeServer;
	
	@Override
	public long getTime() throws IOException {
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(timeServer);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime();
        Date time = new Date(returnTime);
        System.out.println("Time from " + timeServer + ": " + time + "\n" + returnTime + "\n" + System.currentTimeMillis());
        return returnTime;
	}
}
