package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.*;
import controller.*;

public class LoginFrm extends JFrame implements ActionListener {
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private ClientCtr mySocket;

	public LoginFrm(ClientCtr socket) {
		super("TCP Login MVC");
		mySocket = socket;

		txtUsername = new JTextField(15);
		txtPassword = new JPasswordField(15);
		txtPassword.setEchoChar('*');
		btnLogin = new JButton("Login");

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		content.add(new JLabel("Username:"));
		content.add(txtUsername);
		content.add(new JLabel("Password:"));
		content.add(txtPassword);
		content.add(btnLogin);
		btnLogin.addActionListener(this);

		this.setContentPane(content);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_LOGIN_USER, this));
	}

	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnLogin))) {
			// pack the entity
			Player user = new Player();
			user.setUsername(txtUsername.getText());
			user.setPassword(txtPassword.getText());

			// sending data
			mySocket.sendData(new ObjectWrapper(ObjectWrapper.LOGIN_USER, user));

			// receive data

		}
	}

	public void receiveDataLogin(ObjectWrapper obj) {
		if (obj.getData() instanceof String) {
			String result = (String)obj.getData();
			if (result.equals("ok")) {
				JOptionPane.showMessageDialog(this, "Login succesfully!");
				HomeFrm hf = new HomeFrm(mySocket);
				hf.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
			}
		}
	}
}