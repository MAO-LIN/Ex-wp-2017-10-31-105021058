import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.*;

public class Frame2 extends JFrame {
    private int screenH= Toolkit.getDefaultToolkit().getScreenSize().height;
    private int screenW= Toolkit.getDefaultToolkit().getScreenSize().width;
    private JMenuBar jmb=new JMenuBar();
    private JMenuBar jTextmb=new JMenuBar();
    private JMenu jTextmF=new JMenu("File");
    private JMenuItem jTextmFLoad=new JMenuItem("Load");
    private JMenu jmF=new JMenu("File");
    private JMenu jmA=new JMenu("About");
    private JMenu jmS=new JMenu("Set");
    private JMenuItem jmFExit=new JMenuItem("Exit");
    private JMenuItem jmFLoto=new JMenuItem("Loto");
    private JMenuItem jmSFamily=new JMenuItem("Family");
    private JMenuItem jmFText=new JMenuItem("Text");
    private JInternalFrame jinFrame=new JInternalFrame();
    private JInternalFrame jTextinFrame=new JInternalFrame();
    private JDesktopPane jdktpane= new JDesktopPane();
    private JPanel jinPanlC=new JPanel(new GridLayout(1,6,5,5));
    private JLabel jlbLoto[]=new JLabel[6];
    private JPanel jinPanlS=new JPanel(new GridLayout(1,2,15,10));
    private JButton jinbtnClose=new JButton("Exit");
    private JButton jinbtnGo=new JButton("Go");
    private JPanel setPane=new JPanel(new GridLayout(2,3,5,5));
    private String jlbtext[]={"Font","Family","Size"};
    private JLabel jlbFont[]=new JLabel[3];
    private String family[]={"PLALT","BOLD","ITALIT","BOLD+ITALIT"};
    private JComboBox comboBox=new JComboBox(family);
    private JTextField OpFont=new JTextField("");
    private JTextField OpSize=new JTextField("12");
    private Random rdm=new Random(System.currentTimeMillis());
    private LoginFrame lfm;

    public Frame2(LoginFrame LoginFrame){
        lfm = LoginFrame;
        initComp();
    }
    private void initComp(){
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(screenW/2-600/2,screenH/2-450/2,600,450);
        this.setJMenuBar(jmb);
        this.setContentPane(jdktpane);
        jinFrame.setBounds(0,0,300,150);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmA);
        jmF.add(jmFExit);
        jmF.add(jmFLoto);
        jmF.add(jmFText);
        jmS.add(jmSFamily);
        for(int i=0;i<jlbtext.length;i++){
            jlbFont[i]=new JLabel(jlbtext[i]);
            setPane.add(jlbFont[i]);
        }
        setPane.add(OpFont);
        setPane.add(comboBox);
        setPane.add(OpSize);
        jmFLoto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jinFrame.setLayout(new BorderLayout(5,5));
        jinFrame.add(BorderLayout.CENTER,jinPanlC);
        for(int i=0;i<6;i++){
            jlbLoto[i]=new JLabel("null",JLabel.CENTER);
            jinPanlC.add(jlbLoto[i]);
            jlbLoto[i].setBackground(new Color(255, 222, 185));
        }
        setLoto();
        jdktpane.add(jinFrame);
        jinFrame.add(BorderLayout.SOUTH,jinPanlS);
        jinPanlS.add(jinbtnClose);

        jTextinFrame.setBounds(0,0,300,150);
        jTextinFrame.add(jTextmb);
        jTextmb.add(jTextmF);
        jTextmF.add(jTextmFLoad);

        jmFText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdktpane.add(jTextinFrame);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Frame2.this.setVisible(false);
                lfm.setVisible(true);
            }
        });

        jinbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jinFrame.setVisible(false);
            }
        });
        jinPanlS.add(jinbtnGo);

        jmFExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame2.this.setVisible(false);

            }
        });
        jmFLoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jinFrame.setVisible(true);
            }
        });
        jinbtnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLoto();
            }
        });
        jmSFamily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showInputDialog(Frame2.this,setPane,"Family",JOptionPane.OK_CANCEL_OPTION);
               int result = JOptionPane.showConfirmDialog(Frame2.this,setPane,"Family",JOptionPane.OK_CANCEL_OPTION);
                int fontStyle=0;
                switch (comboBox.getSelectedIndex()){
                    case 0:
                        fontStyle=Font.PLAIN;
                        break;
                    case 1:
                        fontStyle= Font.BOLD;
                        break;
                    case 2:
                        fontStyle=Font.ITALIC;
                        break;
                    case 3:
                        fontStyle=Font.BOLD+Font.ITALIC;
                        break;
                }
               if(result==JOptionPane.OK_OPTION) {
                   UIManager.put("Menu.font", new Font(OpFont.getText(), fontStyle, Integer.parseInt(OpSize.getText())));
               }
            }
        });

    }
    private void setLoto(){
        for(int i=0;i<6;i++){
            jlbLoto[i].setText(Integer.toString(rdm.nextInt(42)+1));
            for(int j=0;j<i;j++){
                if(jlbLoto[i].getText().equals(jlbLoto[j].getText())){
                    i=i-1;
                    break;
                }
            }
        }
    }
}