package esp.dic2.archi;

import esp.dic2.archi.soapclient.proxy.AuthenticationSoapController;
import esp.dic2.archi.soapclient.proxy.AuthenticationSoapService;
import esp.dic2.archi.soapclient.proxy.SQLException_Exception;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.HashMap;

public class LoginPage implements ActionListener {

    private AuthenticationSoapController authenticationSoapController = new AuthenticationSoapService().getAuthenticationSoapControllerPort();

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField user_field = new JTextField();
    JPasswordField pass_field = new JPasswordField();
    JLabel user_label = new JLabel("Nom d'utilisateur:");
    JLabel pass_label = new JLabel("Mot de Passe:");
    JLabel message = new JLabel("Veuillez renseigner tous les champs");

    public LoginPage() {

        user_label.setBounds(50, 100, 150, 50);
        pass_label.setBounds(50, 150, 150, 50);

        message.setBounds(125, 250, 250, 35);
        message.setFont(new Font(null, Font.ITALIC, 25));

        user_field.setBounds(180, 115, 150, 25);
        pass_field.setBounds(180, 162, 150, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(user_label);
        frame.add(pass_label);
        frame.add(message);
        frame.add(user_field);
        frame.add(pass_field);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== resetButton){
            user_field.setText("");
            pass_field.setText("");
        }
        if(e.getSource() == loginButton){
            String userText;
            String passText;
            userText = user_field.getText();
            passText = String.valueOf(pass_field.getPassword());

            //[TODO REQUEST]
            try {
                String result = authenticationSoapController.authentification(userText, passText);
                if ( result.equals("SUCCESS") ) {
                    message.setText("Login successful");
                    message.setForeground(Color.green);
                    frame.dispose();
                    HomeFrame homeFrame = new HomeFrame();
                }else {
                    message.setText("Login failed");
                    message.setForeground(Color.red);
                }

            } catch (SQLException_Exception ex) {
                throw new RuntimeException(ex);
            }


//
        }
    }
}
