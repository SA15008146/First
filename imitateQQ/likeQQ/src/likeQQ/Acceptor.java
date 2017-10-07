package likeQQ;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Acceptor {
	public volatile static int Feedback = 0 ;
	public volatile static List<User> Friendlist;
	public static User host;
	public static ListPanel listpanel ;
	public static List<Message> msgSet = new ArrayList<>(100);
	public static List<userFrame> frameList = new ArrayList<>();
	
	private Acceptor(){}
	private static final Acceptor acceptor = new Acceptor();
	public static Acceptor getInstance(){
		return acceptor;
	}
	public void acceptFeedback(){
		new Thread(new Runnable() {
			public void run() {
				try(ServerSocket ssocket = new ServerSocket(8887)){
					//ServerSocket ssocket = new ServerSocket(8887);
					while(true){
						System.out.println("system listener start...");
						Socket skt = ssocket.accept();
						System.out.println("get the feed back from server£º"+skt.getInetAddress());
/**
 * utilize new feature of JAVA 8 ; here can close the stream
 * automatically ;
 */
						try(ObjectInputStream incube=new ObjectInputStream(skt.getInputStream()))
						{
							Object obj = incube.readObject();
							/**
							 * here get the User Object ;
							 */
							if(obj instanceof Integer){
								System.out.println("feedback Msg :"+(Integer)obj);
								Feedback = (Integer) obj ;
							}
							else if(obj instanceof Message){
								Message msg = (Message) obj;								
/************************************************************************************************
 * 	traverse  frameList ,get the target frame and add new MsgPanel and repaint ;
 */
								if(frameList.size()!=0){
									for(userFrame theFrame : frameList){
										System.out.println(theFrame.getFriend());
										if(theFrame.getHost().getID()==msg.getAccepter().getID()){
											theFrame.addpanel(msg.getMsg(),5);
											theFrame.repaint();
										}
									}
								}
/************************************************************************************************
*	set the target panel of friendList border red ;
*	notice here : 	(msg.getSenter().getID()+"").equals(panel.getName()) ;
*					I used the User.ID to name the panel we created ;
*/
								if(!listpanel.getPanelList().isEmpty()){
									for (JPanel panel :listpanel.getPanelList()){
										if((msg.getSenter().getID()+"").equals(panel.getName())){
											panel.setBorder(BorderFactory.createLineBorder(Color.RED,3));
											listpanel.repaint();
											msgSet.add(msg);
										}
									}
								}
/***********************************************************************************************/
							}else if (obj instanceof List){
								Friendlist =(List<User>) obj;
								Set<Long> friendsID = host.getFriends();
								for (User user : Friendlist)	friendsID.add(user.getID());
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}