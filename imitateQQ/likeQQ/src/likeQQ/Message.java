package likeQQ;

import java.io.Serializable;
/**
 * copy from likeQQ ;
 * @author crf
 *
 */
public class Message implements Serializable{
	private long ID;
	private User senter;//发送方
	private User accepter;//接收方
	
	private String msg;
	private long time;
	public Message(){}
//************************************系统自动创建区*************************************
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User getSenter() {
		return senter;
	}
	public void setSenter(User senter) {
		this.senter = senter;
	}
	public User getAccepter() {
		return accepter;
	}
	public void setAccepter(User acceper) {
		this.accepter = acceper;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((accepter == null) ? 0 : accepter.getID()));
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (accepter == null) {
			if (other.accepter != null)
				return false;
		} else if (!accepter.equals(other.accepter))
			return false;
		if (time != other.time)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Message [ID=" + ID + ", senter=" + senter.getUserName() + ", acceper=" + 
				accepter.getUserName()+ ", msg=" + msg + ", time=" + time + "]";
	}
}