package likeQQserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Senter {

	private Senter(){}
	private static final Senter senter = new Senter();
	public static Senter getSenter(){
		return senter;
	}
	public int sent(Object tempUser,String IP){
/*
 * "num = 16" means sent successfully ; "num = 17" means fail to connect to server ; 
 */
		int num = 16;
		try {
			Socket skt = new Socket(IP,8887);
			try(ObjectOutputStream outcube = new ObjectOutputStream(skt.getOutputStream())){
				outcube.writeObject(tempUser);
				System.out.println("sented a Integer to : "+IP + "port :"+ 8887);
				outcube.flush();
				skt.close();
			}
		} catch (IOException e) {
			num = 17;
			e.printStackTrace();
		}
		return num;
	}
}
