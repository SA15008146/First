package likeQQ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterPanel extends JFrame{
	private static final long serialVersionUID = 1L;
	int Y_pos=70;
	Font LabelFont = new Font("Brush script MT",Font.BOLD,25);
	Font noteFont = new Font("Arial",Font.BOLD,12);
	
	JTextField nameTxt,pwTxt,EmailTxt;
	
	private RegisterPanel(){
		setSize(450,500);
		setDefaultCloseOperation(1);
		setLocation(300,30);
		setTitle("Register panel");
/*	ATTENTION : here we override a inner class function ;
 * 				its a great idea to set JPanel's background ;
 */
		JPanel jp = new JPanel(){
			protected void paintComponent(Graphics g) {
				try {
					g.drawImage(ImageIO.read(this.getClass().
							getResource("/materials/registerPanelBK.png")), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		jp.setSize(450,500);
		jp.setLocation(0, 0);
		jp.setLayout(null);
		
		JButton SubmitBtn = addBtn("Submit",jp);
		addLabel("User name      :",jp);
		addLabel("User password :",jp);
		addLabel("User Email    :",jp);
		Y_pos = 72;
		nameTxt = addText(jp);
		pwTxt = addText(jp);
		EmailTxt = addText(jp);
		Y_pos = 95;
		addNote("Don't use chinese !",jp);
		addNote("No limit .",jp);
		addNote("Ensure the Email available .",jp);
		add(jp);
		
		SubmitBtn.addActionListener((ActionEvent e)-> submitNewUser());
		
		nameTxt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)	pwTxt.requestFocus();
			}
		});
		
		pwTxt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)	EmailTxt.requestFocus();
			}
		});
		
		EmailTxt.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER)	submitNewUser();
			}
		});
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
	}
public static RegisterPanel getRegisterPanel(){
	return new RegisterPanel();
}
	
public JButton addBtn(String BtnName,JPanel jp){
	JButton tempBtn = new JButton(BtnName);
	tempBtn.setLocation(180,400);
	tempBtn.setForeground(Color.GRAY);
	tempBtn.setFont(LabelFont);
	tempBtn.setSize(100, 33);
	tempBtn.setFocusPainted(false);
	tempBtn.setContentAreaFilled(false);
	jp.add(tempBtn);
	return tempBtn;
	}

public void addLabel(String LabelName,JPanel jp){
	JLabel tempLabel = new JLabel(LabelName);
	tempLabel.setFont(LabelFont);
	tempLabel.setForeground(Color.BLACK);
	tempLabel.setLocation(20,Y_pos);
	Y_pos = Y_pos+80;
	tempLabel.setSize(200, 30);
	tempLabel.setOpaque(false);
	jp.add(tempLabel);
	}
/**
 * JTextField may be used in other function ; so we need to have a return to get its
 * address ;
 * @param jp
 * @return
 */
public JTextField addText(JPanel jp){
	JTextField jtf = new JTextField();
	jtf.setLocation(210,Y_pos);
	Y_pos = Y_pos+80;
	jtf.setSize(200, 28);
	jtf.setForeground(Color.BLACK);
	jp.add(jtf);
	return jtf;
}

public void addNote(String LabelName,JPanel jp){
	JLabel tempLabel = new JLabel(LabelName);
	tempLabel.setFont(noteFont);
	tempLabel.setForeground(Color.BLACK);
	tempLabel.setLocation(220,Y_pos);
	Y_pos = Y_pos+80;
	tempLabel.setSize(200, 30);
	tempLabel.setOpaque(false);
	jp.add(tempLabel);
	}

public void submitNewUser(){
	if(nameTxt.getText()==null||pwTxt.getText()==null||EmailTxt.getText()==null||
						"".equals(nameTxt.getText())||"".equals(pwTxt.getText())||
													"".equals(EmailTxt.getText())){
		JOptionPane.showMessageDialog(null,"please full them all ");
		return;
	}
	User tempUser = new User();
	tempUser.setUserName(nameTxt.getText());
	tempUser.setPassword(pwTxt.getText());
	tempUser.setEmail(EmailTxt.getText());
	Senter.getInstance().sent(tempUser);
	int count=0;
	while (Acceptor.Feedback==0)
	{
		count++;
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(count>100){
			JOptionPane.showMessageDialog(null,"Network connection timeout...");
			break;
		}
	}
	if(Acceptor.Feedback==3){
		JOptionPane.showMessageDialog(null,"Register successed");
		this.dispose();
		Acceptor.Feedback=0;
	}
	else if(Acceptor.Feedback==4){
		JOptionPane.showMessageDialog(null,"Register Failed");
		Acceptor.Feedback=0;
	}
	}
}