package likeQQ;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class userFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel txt = new  JPanel();
	private JTextArea txtEdible = new  JTextArea(2,34);
	JScrollPane jsp_txtEdible = new JScrollPane(txtEdible);
	JScrollPane jsp_txt = new JScrollPane(txt);
	private JButton sentBtn = new JButton("SENT");
	private User host;//主
	private User friend;//接收方
	Message Msg = new Message();
	private int y=3;
	
	public userFrame(User host,User friend)
	{
		this.host = host;
		this.friend = friend;
		Msg.setAccepter(friend);
		Msg.setSenter(host);
		jsp_txtEdible.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp_txtEdible.setSize(380,45);
		jsp_txtEdible.setLocation(0,201);
		jsp_txt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp_txt.setSize(475,200);
		jsp_txt.setLocation(0,0);

		txtEdible.setLineWrap(true);//设置滚动条
		txtEdible.setWrapStyleWord(true);//设置自动换行
		txtEdible.setSize(20,10);
		
		txt.setSize(400,150);
		txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txt.setBackground(Color.WHITE);
		txt.setLayout(null);
		sentBtn.setSize(70,35);
		sentBtn.setLocation(390,205);
		setSize(480,280);
		setTitle(host.getUserName()+"-->"+friend.getUserName());//设定标题；
		setResizable(false);//不可修改窗体大小；
		//setDefaultCloseOperation(2);//设置窗口的关闭按钮的功能；
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				for(int i= 0;i<Acceptor.frameList.size();i++){
					if(friend.equals(Acceptor.frameList.get(i).getFriend())){
						Acceptor.frameList.remove(i);
						break;
					}
				}
				dispose();
			}
		});
		add(jsp_txt);
		add(jsp_txtEdible);
		add(sentBtn);
		sentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sent();
			}
		});
		txtEdible.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)	sent();
			}});		
/***********************************************************************************************
 * 	when u open the UserFrame traverse the msgSet,and show them	*/
		if(Acceptor.msgSet.size()!=0)
			for (Message msg : Acceptor.msgSet){
				if(msg.getSenter().getID()==this.friend.getID())	addpanel(msg.getMsg(),5);
				if(msg.getAccepter().getID()==this.friend.getID())	addpanel(msg.getMsg(),260);
			}
		
		setLayout(null);//设置按钮的布局为与开始边对齐；
		setVisible(true);
	}
	
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	private void sent(){
		if(txtEdible.getText()!=null || !("".equals(txtEdible.getText()))){
			addpanel(txtEdible.getText(),260) ;
			Senter.getInstance().sent(Msg);
			txtEdible.setText("");
		}
	}
	/**
	 * the method can create a new panel on the JPanel txt ;
	 * @param txtedible : message
	 * @param x_pos : X coordinate of panel in the dialog box ; x_pos can be 5(if
	 * 	Message comes from the opposite side) or 260 (if it comes from your side)
	 */
	public void addpanel(String txtedible,int x_pos){
		String msgs = txtedible;
		int len = 200 ;//set max length of per row ;
		int hight = 18*(msgs.length()/16+1);
		Msg.setMsg(msgs);
		Msg.setTime(System.currentTimeMillis());
		JPanel p = new JPanel();
		p.setSize(len,hight);
		p.setLocation(x_pos,y);
		p.setBackground(Color.ORANGE);
		p.setOpaque(true);
		p.setLayout(null);
		Msg.getMsg().length();
		JTextArea MsgLabel = new JTextArea(msgs);
		MsgLabel.setEditable(false);
		MsgLabel.setForeground(Color.BLACK);
		MsgLabel.setSize(len,hight);
		MsgLabel.setLocation(5,0);
		MsgLabel.setOpaque(false);
		MsgLabel.setLineWrap(true);
		p.add(MsgLabel);
		txt.add(p);
		txt.repaint();
		y=y+hight+5;
	}
}