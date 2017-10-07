package likeQQserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import likeQQ.AddFriendQuery;
import likeQQ.Message;
import likeQQ.User;
/**
 * 	Server get the Message from Client and then classify the Message and finish the
 * 	corresponding mission ;
 * 	@author crf
 */
public class Server {
	public static void main(String[] args) {
		Inquirer inquirer =Inquirer.getInstance();
		try{
			ServerSocket ssocket = new ServerSocket(8888);
			while(true){
				System.out.println("服务器等待...");
				/**
				 * utilize new feature of JAVA 8 ; here can close the stream
				 * automatically ;
				 */
				Socket skt = ssocket.accept();
				String inetIP = skt.getInetAddress().toString().substring(1);
				System.out.println("已经获得了客户端接入："+inetIP);
				try(ObjectInputStream incube=new ObjectInputStream(skt.getInputStream()))
				{
					Object obj = incube.readObject();
					/**
					 * here get the User Object ;
					 */
					if(obj instanceof User){
						User theUser = (User) obj;
						theUser.setIP(inetIP);
						if(theUser.getEmail()==null)
						{
							int result= inquirer.Logincheck(theUser,inetIP);
							System.out.println("sented a Integer to client : "+result);
							if(result==1){
/*
 *  	here if we sent List<User>firnedList directly there will be a bug in client ;
 * 		BUG: java.lang.ClassNotFoundException: org.hibernate.collection.PersistentSet
 * 	It seems that the elements of firnedList is PersistentSet , because i haven't add
 * 	hibernate.jar into the client , it can't find the corresponding class ;  
 *  here's a good explain :
 *  	http://www.blogjava.net/rain1102/archive/2008/01/16/175733.html		*/
								List<User>firnedList = inquirer.getFriendList(theUser);
								ArrayList<User> friendList=new ArrayList<>();
								for(User user : firnedList){
									User newUser = new User();
									newUser.setID(user.getID());
									newUser.setUserName(user.getUserName());
									newUser.setNickname(user.getNickname());
									newUser.setEmail(user.getEmail());
									friendList.add(newUser);
								}
								Senter.getSenter().sent(friendList,inetIP);
							}
							Senter.getSenter().sent(new Integer(result),inetIP);
						}
						else
						{
							int result = inquirer.register(theUser);
							System.out.println("sented a Integer to client : "+result);
							Senter.getSenter().sent(new Integer(result),inetIP);
						}
					}
					else if(obj instanceof Message){
						Message msg = (Message) obj;
						
						System.out.println("Server get your message"+msg);
						
						String ip = Inquirer.getInstance().getIP(msg.getAccepter());
						System.out.println(ip);
						if(ip!=null){
							int feedback = Senter.getSenter().sent(msg,ip);
							if (feedback==17){
								System.out.println("Accepter offline");
								//Inquirer.getInstance().setIP(msg.getAccepter(), null);
								Inquirer.getInstance().saveMsg(msg);
							}
						}
					}else if(obj instanceof AddFriendQuery){
						AddFriendQuery afq = (AddFriendQuery) obj;
						Inquirer.getInstance().addFriend(afq);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}