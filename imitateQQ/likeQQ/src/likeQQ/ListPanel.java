package likeQQ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ListPanel extends JFrame{
	
private int friendListLocation_Y = 55;
private	Font font = new Font("Informal Roman",Font.BOLD,30);
private	List<JPanel> panelList = new ArrayList<>() ;
	
private ListPanel(List<User> users){
/**
 * setUndecorated(true); this option can vanish title block ,but also you need to add
 * operations by yourself ;
 */
	setSize(300,600);
	setDefaultCloseOperation(3);
	setLocation(300,30);

	JScrollPane JSP = new JScrollPane();
	JSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JSP.setOpaque(true);
	JPanel jp = new JPanel(){
/**
 * 	ATTENTION : here we override a inner class function ;
 * 				its a great idea to set JPanel's background ;
 */
		protected void paintComponent(Graphics g) {
			try {
				g.drawImage(ImageIO.read(this.getClass().
						getResource("/materials/ListPanelBK.png")), 0, 0, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	jp.setLayout(null);
	jp.setSize(300,600);
	jp.setLocation(0, 0);
//********************************* create a list of JPanel *************************************
	if (users!=null) for(User user : users) panelList.add(addfriendList(jp,user));
//************************************ add friend button ****************************************
	JButton addFriendBtn = new JButton();
	addFriendBtn.setSize(30,30);
	addFriendBtn.setLocation(220,20);
	addFriendBtn.setOpaque(false);
	addFriendBtn.setIcon(new ImageIcon(this.getClass().getResource("/materials/addFriendbtn2.png")));
	addFriendBtn.addActionListener((ActionEvent e)-> new AddFriendsPanel());
	addFriendBtn.setToolTipText(" add Friend ");
//***********************************************************************************************
	jp.add(JSP);
	jp.add(addFriendBtn);
	add(jp);
	setResizable(false);
	setLayout(null);
	setVisible(true);
}

private JPanel addfriendList(JPanel jp,User user){
	String name = user.getUserName();
	JPanel friendlist = new JPanel();
	friendlist.setName(user.getID()+"");
	friendlist.setBorder(null);
	friendlist.setSize(300,50);
	friendlist.setLocation(0, friendListLocation_Y);
	friendListLocation_Y +=55;
	friendlist.setLayout(null);
	//friendlist.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	friendlist.setOpaque(false);
	JLabel piclabel = new JLabel(new ImageIcon(this.getClass().
										getResource("/materials/headp.png")));
	piclabel.setSize(50,50);
	piclabel.setLocation(0,0);
	
	JLabel namelabel = new JLabel(name);
	namelabel.setSize(245,26);
	namelabel.setLocation(55,0);
	namelabel.setFont(font);
	//here ought to get user.getStatement() ;
	JLabel signlabel = new JLabel("The heroic monarch of ancient England ");
	signlabel.setSize(245,25);
	signlabel.setLocation(55,25);
	friendlist.add(signlabel);
	friendlist.add(namelabel);
	friendlist.add(piclabel);
//*********************************** MouseListener *************************************
	friendlist.addMouseListener(new MouseAdapter() {
		 public void mouseEntered(MouseEvent e) {
			 if(friendlist.getBorder()==null)
				 friendlist.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		 }
		 public void mouseExited(MouseEvent e) {
			 if(friendlist.getBorder()!=null&&
					 ((LineBorder) friendlist.getBorder()).getLineColor()==Color.GRAY)
			 friendlist.setBorder(null);
		 }
		 public void mouseClicked(MouseEvent e) {
			 if(friendlist.getBorder()!=null)
				 friendlist.setBorder(null);
			 boolean isExist =false;
			 if(Acceptor.frameList.size()!=0){
				 for(userFrame theFrame:Acceptor.frameList)
						if(user.getID()==theFrame.getFriend().getID()){
							isExist=true;
							theFrame.toFront();
						}
			 }
			 if(!isExist)	Acceptor.frameList.add(new userFrame(Acceptor.host,user));
		 }
	});
	jp.add(friendlist);
	return friendlist;
}

public static ListPanel getInstance(List<User> users){
	return new ListPanel(users);
}
public List<JPanel> getPanelList() {
	return panelList;
}
public void setPanelList(List<JPanel> panelList) {
	this.panelList = panelList;
}
}