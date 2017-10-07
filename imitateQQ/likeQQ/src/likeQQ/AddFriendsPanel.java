package likeQQ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFriendsPanel extends JFrame {
	JTextField jtf;
	public AddFriendsPanel(){
		setSize(500,300);
		setDefaultCloseOperation(2);
		setLocation(300,30);
		setTitle("Add new friend");
//*************************************** add panel ***************************************
		JPanel p = new JPanel(){
			protected void paintComponent(Graphics g){
				try {
					g.drawImage(ImageIO.read(this.getClass().
							getResource("/materials/bk.png")), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		p.setLayout(null);
		p.setSize(500,300);
		p.setLocation(0, 0);
//*************************************** add label ***************************************
		JLabel label = new JLabel();
		label.setText("please input an ID or UserName ");
		label.setSize(250,40);
		label.setLocation(110, 40);
		label.setFont(new Font("Arial",Font.BOLD,16));
		label.setForeground(Color.GRAY);
		p.add(label);
//************************************* add textField *************************************
		jtf = new JTextField(15);
		jtf.setLocation(100, 90);
		jtf.setSize(250, 30);
		jtf.setBorder(null);
		jtf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)	;	//add function
			}});
		p.add(jtf);
//***************************************** Button ****************************************
		JButton jbtn = new JButton();
		jbtn.setIcon(new ImageIcon(this.getClass().getResource("/materials/btn_+.png")));
		jbtn.setSize(40,28);
		jbtn.setLocation(370, 90);
		p.add(jbtn);
//*********************************** Listener ********************************************
		jtf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)	query();
			}
		});
		jbtn.addActionListener((ActionEvent e)->	query());
//*****************************************************************************************
		add(p);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
/**
 * sent an AddFriendQuery to Server ;
 */
	private void query(){
		String str = jtf.getText();
		if (str!=null||!"".equals(str)){
			AddFriendQuery afq = new AddFriendQuery();
			afq.setFriendMsg(str);
			afq.setApplicant(Acceptor.host);
			Senter.getInstance().sent(afq);
		}
	}
}