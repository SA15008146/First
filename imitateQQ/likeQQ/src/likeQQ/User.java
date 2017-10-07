package likeQQ;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable{
	private long ID;
	private String userName;
	private String password;
	private String email;
	private Set<Message> TempMsg = new HashSet<>();
	private String IP;
	private String nickname;
	private Set<Long> friends = new HashSet<>();
	private String statement ;
//************************************系统自动创建区*************************************
	public User(){}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Message> getTempMsg() {
		return TempMsg;
	}
	public void setTempMsg(Set<Message> tempMsg) {
		TempMsg = tempMsg;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Set<Long> getFriends() {
		return friends;
	}
	public void setFriends(Set<Long> ids) {
		this.friends = ids;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", UserName=" + userName + ", password=" + password +
				", Email=" + email + ", TempMsg=" + TempMsg + ", IP=" + IP + "]";
	}
}