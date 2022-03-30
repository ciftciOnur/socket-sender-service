package tr.edu.yeditepe.socketsender.interfaces.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tr.edu.yeditepe.socketsender.application.AesService;
import tr.edu.yeditepe.socketsender.interfaces.SocketSenderServiceFacade;

@Component
public class SocketSenderServiceFacadeImpl implements SocketSenderServiceFacade{
	
	@Autowired
	AesService aesService;
	
	@Override
//	@Scheduled(cron = "* * * * * *")
	public void receive() throws UnknownHostException,IOException{

        ServerSocket serverSocket = null;
        System.out.println("Receiving packadges");
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        
        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }
        
        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream("test.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
	}
	@Override
	//@Scheduled(cron = "0 * * * * *")
	public void send() throws UnknownHostException, IOException {
		
		DatagramSocket socket = null;
		InetAddress group;
        String host = "192.168.15.176";
        File file = new File("HeavyPayload.txt");
        System.out.println("sended   ");
        InputStream in = new FileInputStream(file);
        byte[] multicastMessage = in.readAllBytes();
        String multicastEndMessage="end";
        byte[] buffer;
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buffer = multicastEndMessage.getBytes();
        int i=0;
        while(i<999999999) {
        	DatagramPacket packet = new DatagramPacket(multicastMessage, multicastMessage.length, group, 4446);
        	socket.send(packet);
        	i++;
        }
        socket.close();
        System.out.println("sending done ");
	}
	
	
	
	@Override
	public String applyAesEncryption(String input,String otp) {
		String algorithm = "AES/ECB/PKCS5Padding";
		try {
			SecretKey secretKey = aesService.getKeyFromPassword(otp, "101010");
			System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()) + "  "+aesService.encrypt(algorithm, input, secretKey));
			return aesService.encrypt(algorithm, input, secretKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
	
	

}
