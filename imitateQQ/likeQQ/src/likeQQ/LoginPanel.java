package likeQQ;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtAc = new  JTextField(15);
	private JPasswordField txtPW = new  JPasswordField(15);
	private JButton LoginBtn = new JButton();
	private JButton RegisterBtn = new JButton();
	private ImageIcon icon,registerImg;
	
	private LoginPanel(){
		setSize(500,300);
		setDefaultCloseOperation(3);
		setLocation(300,30);
//*********************************** Listener ********************************************
		LoginBtn.addActionListener((ActionEvent e)-> this.judgement());
		RegisterBtn.addActionListener((ActionEvent e)-> RegisterPanel.getRegisterPanel());
		txtAc.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)	txtPW.requestFocus();
			}});
		txtPW.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)	judgement();
			}});
//******************************** setButtonProps *****************************************
		icon = new ImageIcon(this.getClass().getResource("/materials/btn1.png"));
		LoginBtn.setLocation(210,150);
		LoginBtn.setSize(70, 25);
		LoginBtn.setIcon(icon);
		LoginBtn.setOpaque(false);
		LoginBtn.setContentAreaFilled(false);
		registerImg = new ImageIcon(this.getClass().getResource("/materials/btn2.png"));
		RegisterBtn.setLocation(295,150);
		RegisterBtn.setSize(70, 25);
		RegisterBtn.setIcon(registerImg);
		RegisterBtn.setOpaque(false);
		RegisterBtn.setContentAreaFilled(false);
//******************************** backgroundPanel ****************************************
		JPanel jp = new JPanel(){
			protected void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(this.getClass().
							getResource("/materials/bk.png")), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.setColor(Color.GRAY);
				g.setFont(new Font("Brush script MT",Font.BOLD,30));
				g.drawString("Account   :",90,100);
				g.drawString("PassWord:",90,130);
			}
		};
		jp.setSize(500,300);
		jp.setLocation(0, 0);
		jp.setLayout(null);
		jp.add(txtAc);
		jp.add(txtPW);
		jp.add(LoginBtn);
		jp.add(RegisterBtn);
//********************************* setTextProps *****************************************
		txtAc.setLocation(240, 85);
		txtPW.setLocation(240, 110);
		txtAc.setSize(120,23);
		txtPW.setSize(120,23);
		add(jp);
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
//******************************** Eager singleton ***************************************
	private static final LoginPanel loginpanel = new LoginPanel();
	public static LoginPanel getLoginPanel(){
		return loginpanel;
	}
//************************************ Judgment ******************************************
	private void judgement(){
		User localUser = new User();
		if(txtAc.getText()!=null || String.valueOf(txtPW.getPassword())!=null
				||!"".equals(txtAc.getText())||!"".equals(String.valueOf(txtPW.getPassword()))){
			localUser.setUserName(txtAc.getText());
			localUser.setPassword(String.valueOf(txtPW.getPassword()));
			Senter.getInstance().sent(localUser);
		}
		int count=0;
		while (Acceptor.Feedback==0)
		{
			count++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count>200){
				JOptionPane.showMessageDialog(null,"Network connection timeout...");
				break;
			}
		}
		if(Acceptor.Feedback==1 ){
			//&& Acceptor.Friendlist!=null
			Acceptor.host = localUser;
			Acceptor.listpanel = ListPanel.getInstance(Acceptor.Friendlist);
			Acceptor.listpanel.setTitle(txtAc.getText());
			this.dispose();
			Acceptor.Feedback=0;
		}
	else if(Acceptor.Feedback==2)
		JOptionPane.showMessageDialog(null,"Login Failed");
		Acceptor.Feedback=0;
	}
}