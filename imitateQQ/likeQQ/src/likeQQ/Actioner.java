package likeQQ;

import javax.swing.JOptionPane;
/**
 * here Integer represent :
 * 		0 : silence ;
 * 		1 : Login success ;
 * 		2 : Login failed ;
 * 		3 : Register success ;
 * 		4 : Register failed ;
 * 		16: server send successfully ;
 * 		17: client offline ;
 * @author crf
 */
public class Actioner {
	public void act(){
		new Thread(new Runnable(){
			public void run() {
				if(Acceptor.Feedback == 1)
				{
					JOptionPane.showMessageDialog(null,"Login Successful");
					LoginPanel.getLoginPanel().dispose();
					Acceptor.Feedback = 0;
				}
				else if(Acceptor.Feedback == 2)
				{
					JOptionPane.showMessageDialog(null,"Login failed");
					Acceptor.Feedback = 0;
				}
				else if(Acceptor.Feedback == 3)
				{
					JOptionPane.showMessageDialog(null,"Register Successful");
					RegisterPanel.getRegisterPanel().dispose();
					Acceptor.Feedback = 0;
				}
				else if(Acceptor.Feedback == 4)
				{
					JOptionPane.showMessageDialog(null,"Register failed");
					Acceptor.Feedback = 0;
				}
				else if(Acceptor.Feedback == 5)
				{
					
					Acceptor.Feedback = 0;
				}
				else if(Acceptor.Feedback == 6)
				{
					
					Acceptor.Feedback = 0;
				}
			}
		}).start();

	}
}
