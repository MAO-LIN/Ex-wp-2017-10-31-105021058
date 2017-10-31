import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {
    private JLabel jlbID =new JLabel("ID",JLabel.RIGHT);
    private JLabel jlbPW =new JLabel("Password",JLabel.RIGHT);
    private JTextField jtfID =new JTextField("h304");
    private JPasswordField jtfPW =new JPasswordField("23323456");
    private JButton Login=new JButton("Login");
    private JButton Exit=new JButton("Exit");
    private Container cp;
    private int LoginH=150,LoginW=300;
    private Dimension dim =Toolkit.getDefaultToolkit().getScreenSize();
    private int screenW= dim.width;
    private int screenH= dim.height;
    public LoginFrame(){
        initComp();
    }
    private void initComp(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(screenW/2-LoginW/2,screenH/2-LoginH/2,LoginW,LoginH);
        cp = this.getContentPane();
        cp.setLayout(new GridLayout(3,2,5,5));
        cp.add(jlbID);
        cp.add(jtfID);
        cp.add(jlbPW);
        cp.add(jtfPW);
        cp.add(Exit);
        cp.add(Login);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtfID.getText().equals("h304")&& new String(jtfPW.getPassword()).equals("23323456")) {
                    Frame2 mf2 = new Frame2(LoginFrame.this);
                    mf2.setVisible(true);
                    LoginFrame.this.setVisible(false);
                    jtfID.setText("");
                    jtfPW.setText("");
                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this,"ID or Password Error");
                }

            }
        });

    }
}