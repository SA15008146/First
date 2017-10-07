package likeQQ;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import likeQQ.userFrame;

public class Client {
/**
 * Here i want to build a system like QQ ;
 * It ought to has three parts :
 * 		first : login interface ; 	function : login or register;
 * 		second: user interface ;	function : get message, prompt, sent message;
 * 		third : server ;			function : connect to database,store message and user information;
 * @param args
 */
	public static void main(String[] args) {
		
/*	  	Acceptor.getInstance().acceptFeedback();
		LoginPanel.getLoginPanel();	*/
		
		//new AddFriendsPanel();
		
		List<User> users=new ArrayList<>();
		User Arhhur = new User();
		Arhhur.setUserName("Arhhur");
		Arhhur.setPassword("123456");
		Arhhur.setEmail("535045781@qq.com");
		Arhhur.setIP("192.168.1.137");
		
		User Lancelot = new User();
		Lancelot.setUserName("Lancelot");
		Lancelot.setPassword("123456");
		Lancelot.setEmail("535045781@qq.com");
		Lancelot.setIP("192.168.1.137");
		
		users.add(Arhhur);
		users.add(Lancelot);
		
		ListPanel.getInstance(users);
		
/*	//test userFrame ;
 * 		User Arhhur = new User();
		Arhhur.setUserName("Arhhur");
		Arhhur.setPassword("123456");
		Arhhur.setEmail("535045781@qq.com");
		Arhhur.setIP("192.168.1.137");
		new userFrame(Arhhur,Arhhur);*/
		
/*	//test ListPanel
 * 		List<User> friends = new ArrayList<>();
		
		User Lancelot = new User();
		Lancelot.setUserName("Lancelot");
		Lancelot.setPassword("123456");
		Lancelot.setEmail("535045781@qq.com");
		Lancelot.setIP("192.168.1.137");
		
		User Arhhur = new User();
		Arhhur.setUserName("Arhhur");
		Arhhur.setPassword("123456");
		Arhhur.setEmail("535045781@qq.com");
		Arhhur.setIP("192.168.1.137");
		
		User Arthur = new User();
		Arthur.setUserName("Arthur");
		Arthur.setPassword("123456");
		Arthur.setEmail("535045781@qq.com");
		Arthur.setIP("192.168.1.137");
		
		friends.add(Arthur);
		friends.add(Arhhur);
		friends.add(Lancelot);
		
		ListPanel.getInstance(friends);	*/
		
		//test all system
	
		
		//RegisterPanel.getRegisterPanel();
/*		try {
			InetAddress address = InetAddress.getLocalHost();
			String hostAddress = address.getHostAddress();
			System.out.println(hostAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}*/
	}
}
