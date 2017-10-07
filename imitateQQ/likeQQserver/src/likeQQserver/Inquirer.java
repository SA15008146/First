package likeQQserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import likeQQ.AddFriendQuery;
import likeQQ.Message;
import likeQQ.User;
/**
 * here int result :
 * 		0 : silence ;
 * 		1 : Login success ;
 * 		2 : Login failed ;
 * 		3 : Register success ;
 * 		4 : Register failed ;
 * 		5 : message sent success ;
 * 		6 : message sent failed ;
 * 		20: add friend success;
 * 		21: add friend failed;
 * @author crf
 */
public class Inquirer {
	private static Configuration config = new Configuration().configure("likeQQ/hibernate.cfg.xml");
	private static SessionFactory sessionFactory = config.buildSessionFactory();
	
	private Inquirer(){}
	private static final Inquirer inquirer = new Inquirer();
	public static final Inquirer getInstance(){
		return inquirer;
	}
	/**
	 * used in login ;get the id by the userName and password;
	 * and update the IP;
	 * @param theUser
	 * @return
	 */
	public int Logincheck(User theUser,String IP){
		Session session = sessionFactory.openSession();
		String hql = "from User where userName ="
				+ " :name and password = :password";
		Query<User> query = session.createQuery(hql);
		query.setParameter("name", theUser.getUserName());
		query.setParameter("password", theUser.getPassword());
		Object result = query.uniqueResult();
		int num = result!= null? 1:2;
		if (result!= null) {
			theUser.setID(((User)result).getID());
			theUser.setIP(IP);//set IP ;
			session.clear();
			session.update(theUser);
		}
		if(session.isOpen()) session.close();
		return num;
	}
	
	public int register(User theUser){
		Session session = sessionFactory.openSession();
		String hql = "from User where userName = :name";
		Query<User> query = session.createQuery(hql);
		query.setParameter("name", theUser.getUserName());
		Object result = query.uniqueResult();
		int num ;
		if(result==null)
		{
			session.persist(theUser);
			session.flush();
			num =3;
		}else	num=4;
		
		if(session.isOpen()) session.close();
		return num;
	}
	/**
	 * depends on the user's id get the friendList; 
	 * @param user
	 * @return
	 */
	public List<User> getFriendList(User user){
		Session session = sessionFactory.openSession();
		User user2 = session.get(User.class, user.getID());
		List<User> result0 =new ArrayList<>();
		Set<Long> result = user2.getFriends();
		if(result.size()!=0){
		String hql0 = "from User where ID in (:IDs)";
		Query<User> query0 = session.createQuery(hql0);
		query0.setParameterList("IDs", result);
		result0 = query0.list();
		}
		session.clear();
		if(session.isOpen()) session.close();
		return result0;
	}
	
	public String getIP(User user){
		Session session = sessionFactory.openSession();
		String hql = "select IP from User where ID = :ID";
		Query<User> query = session.createQuery(hql);
		query.setParameter("ID", user.getID());
		Object result = query.uniqueResult();
		if(session.isOpen()) session.close();
		return result==null ? null:(String)result;
	}
	
	public void setIP(User user,String ip){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update User set IP = :IP  where ID = :ID";
		Query<User> query = session.createQuery(hql);
		query.setParameter("ID", user.getID());
		query.setParameter("IP", ip);
		query.executeUpdate();
		tx.commit();
		if(session.isOpen()) session.close();
	}
	
	public void saveMsg(Message msg){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(msg);
		tx.commit();
		session.clear();
		if(session.isOpen()) session.close();
	}
/**
 * @param 	afq contains two parts : 	1. host , type :User ;
 * 								   		2. message , it may be a Long or a String ;
 * @return	20: add friend success ;
 * 			21: add friend failed ;
 */
	public int addFriend(AddFriendQuery afq){
		String friendMsg = afq.getFriendMsg();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User host = afq.getApplicant();
		int num = 20;
		try{
			Long l = Long.valueOf(friendMsg);
			String hql = "select userName from User  where ID = :ID";
			Query<Long> query = session.createQuery(hql);
			query.setParameter("ID", l);
			Object result = query.uniqueResult();
			
			if(result!=null)	host.getFriends().add(l);
			else				num=21;
			
		}catch(Exception e){
			String hql = "select ID from User  where userName = :userName";
			Query<Long> query = session.createQuery(hql);
			query.setParameter("userName", friendMsg);
			Object result = query.uniqueResult();
			
			if(result!=null)	host.getFriends().add((Long)result);
			else				num=21;
			
		}
		if (num==20){
			session.update(host);
			tx.commit();
		}
		session.clear();
		if(session.isOpen()) session.close();
		return num;
	}
}