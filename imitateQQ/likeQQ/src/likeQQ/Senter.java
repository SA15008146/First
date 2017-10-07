package likeQQ;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Senter {

	public int sent(Object tempUser){
		int num = 16;
		try {
			System.out.println("client sented IP:"+InetAddress.getLocalHost().getHostAddress());
			Socket skt = new Socket(InetAddress.getLocalHost().getHostAddress(),8888);
			try(ObjectOutputStream outcube = new ObjectOutputStream(skt.getOutputStream())){
				outcube.writeObject(tempUser);
				outcube.flush();
				skt.close();
			}
		} catch (IOException e) {
			num = 17;
			e.printStackTrace();
		}
		return num;
	}
	private Senter(){}
	public static final Senter senter = new Senter();
	public static Senter getInstance(){
		return senter;
	}
}
