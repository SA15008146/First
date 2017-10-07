package likeQQ;

import java.io.Serializable;

public class AddFriendQuery implements Serializable {

	String friendMsg ;
	User applicant;
	public AddFriendQuery(){}
	public String getFriendMsg() {
		return friendMsg;
	}
	public void setFriendMsg(String friendMsg) {
		this.friendMsg = friendMsg;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	@Override
	public String toString() {
		return "AddFriendQuery [friendMsg=" + friendMsg + ", applicant=" + applicant + "]";
	}
	
}
