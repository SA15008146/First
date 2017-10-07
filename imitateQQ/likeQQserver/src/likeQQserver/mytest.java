package likeQQserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import likeQQ.Message;
import likeQQ.User;

public class mytest {

	public static void main(String[] args) {
		
/*		User senter = new User();
		senter.setID(6);
		User accepter = new User();
		accepter.setID(1);
		String msg = "mytest";
		Message test = new Message();
		test.setSenter(senter);
		test.setAccepter(accepter);
		test.setMsg(msg);
		test.setTime(System.currentTimeMillis());
	
		Configuration config = new Configuration().configure("likeQQ/hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(test);
		tx.commit();
		if(session.isOpen()) session.close();*/
		
		/*Configuration config = new Configuration().configure("likeQQ/hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user  = new User();
		user.setUserName("Dagonet");
		user.setPassword("123456");
		user.setEmail("535045781@qq.com");
		try {
			user.setIP(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Message message  = new Message();
		message.setTime(System.currentTimeMillis());
		message.setAccepter(user);
		Message message2  = new Message();
		message2.setTime(System.currentTimeMillis()+1);
		message2.setAccepter(user);
		Set<Message> tempMsg = new HashSet<>();
		tempMsg.add(message);
		tempMsg.add(message2);
		user.setTempMsg(tempMsg);
		Set<Integer> friends = new HashSet<Integer>();
		friends.add(1);
		friends.add(2);
		friends.add(3);
		user.setFriends(friends);
		session.persist(user);
		tx.commit();
		if(session.isOpen()) session.close(); */
		
/*		User user = new User();
		user.setID(6l);
		List<User> list=Inquirer.getInstance().getFriendList(user);
		if (list!=null){
			for(User user0 : list){
				System.out.println(user0);
			}
		}*/
	} 
}
