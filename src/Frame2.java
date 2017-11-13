import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

public class Frame2 extends JFrame {
    private int screenH= Toolkit.getDefaultToolkit().getScreenSize().height;
    private int screenW= Toolkit.getDefaultToolkit().getScreenSize().width;
    private JMenuBar jmb=new JMenuBar();
    private JMenu jmF=new JMenu("File");
    private JMenu jmS=new JMenu("Set");
    private JMenu jmG=new JMenu("Game");
    private JMenu jmA=new JMenu("About");
    private JMenuItem jmFExit=new JMenuItem("Exit");
    private JMenuItem jmGLoto=new JMenuItem("Loto");
    private JMenuItem jmSFont=new JMenuItem("Font");
    private JInternalFrame jinFrame=new JInternalFrame();
    private JDesktopPane jdktpane= new JDesktopPane();
    private JPanel jinPanlC=new JPanel(new GridLayout(1,6,5,5));
    private JLabel jlbLoto[]=new JLabel[6];
    private JPanel jinPanlS=new JPanel(new GridLayout(1,2,15,10));
    private JButton jinbtnClose=new JButton("Exit");
    private JButton jinbtnGo=new JButton("Go");
    private Random rdm=new Random(System.currentTimeMillis());
    private LoginFrame log;
    //internalFrame(keyboard)
    private JInternalFrame jinFrameK=new JInternalFrame();
//    private JDesktopPane jdtP= new JDesktopPane();
    private JPanel conter=new JPanel(new GridLayout(4,3,5,5));
    private JTextField jtf=new JTextField();
    private JButton btn[]=new JButton[12];
    private String data[]={"0","1","2","3","4","5","6","7","8","9"};
    private JMenuItem jmFKeyB=new JMenuItem("keyboard");
    private JButton jinFrameKExit=new JButton("Exit");
    //
    //------------setFontFamily---------
    private JPanel setPane=new JPanel(new GridLayout(2,3,5,5));
    private String jlbtext[]={"Font","Family","Size"};
    private JLabel jlbFont[]=new JLabel[3];
    private String family[]={"PLALT","BOLD","ITALIT","BOLD+ITALIT"};
    private JComboBox comboBox=new JComboBox(family);
    private JTextField OpFont=new JTextField("Default");
    private JTextField OpSize=new JTextField("12");
    //----------------------------------
    //---------------------Book--------------------------
    private JMenuBar jinmB=new JMenuBar();
    private JMenu jinF=new JMenu("File");
    private JInternalFrame jInternalFrame=new JInternalFrame();
    private JTextArea bookArea=new JTextArea();
    private JScrollPane bookScroll=new JScrollPane(bookArea);
    private JMenuItem jinFSave=new JMenuItem("Save");
    private JMenuItem jinFLoad=new JMenuItem("Load");
    private JMenuItem jinFExit=new JMenuItem("Exit");
    private JMenuItem jmGBook= new JMenuItem("Book");
    //--------------------------------------------------


    public Frame2(LoginFrame LoginFrame){
        log=LoginFrame;
        initComp();
    }
    private void initComp(){
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(screenW/2-600/2,screenH/2-450/2,600,450);
        this.setJMenuBar(jmb);
        this.setContentPane(jdktpane);
        jdktpane.setBackground(new Color(0,0,0));
        jinFrame.setBounds(0,0,300,150);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmFExit);
        jmS.add(jmSFont);
        jmG.add(jmGLoto);
        jmG.add(jmFKeyB);
        jmGLoto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jinFrame.setLayout(new BorderLayout(5,5));
        jinFrame.add(BorderLayout.CENTER,jinPanlC);
        for(int i=0;i<6;i++){
            jlbLoto[i]=new JLabel("null",JLabel.CENTER);
            jlbLoto[i].setFont(new Font("",Font.BOLD,24));
            jlbLoto[i].setBackground(new Color(175, 231,255));
            jlbLoto[i].setOpaque(true);
            jinPanlC.add(jlbLoto[i]);
            jlbLoto[i].setBackground(new Color(255, 222, 185));
        }
        setLoto();
        jdktpane.add(jinFrame);
        jinFrame.add(BorderLayout.SOUTH,jinPanlS);
        jinPanlS.add(jinbtnClose);
        //internalFrame(Keyboard)
        jinFrameK.setBounds(0,0,300,370);
        jinFrameK.setLayout(new BorderLayout(3,3));
        jinFrameK.add(BorderLayout.NORTH,jtf);
        jinFrameK.add(BorderLayout.CENTER,conter);
        jdktpane.add(jinFrameK);
        jtf.setEditable(false);
        jinFrameK.add(BorderLayout.SOUTH,jinFrameKExit);

        String tmp;
        for(int i=0;i<10;i++){
            int index=rdm.nextInt(8);
            tmp=data[i];
            data[i]=data[index];
            data[index]=tmp;
        }
        for(int i=0;i<10;i++){
            btn[i]=new JButton(data[i]);
            conter.add(btn[i]);
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tempBtn= (JButton)e.getSource();
                    jtf.setText(jtf.getText()+tempBtn.getText());
                }
            });
        }
        //----------------------------
        //------------setFontFamily----------
        for(int i=0;i<jlbtext.length;i++){
            jlbFont[i]=new JLabel(jlbtext[i]);
            setPane.add(jlbFont[i]);
        }
        setPane.add(OpFont);
        setPane.add(comboBox);
        setPane.add(OpSize);
        jmSFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result= JOptionPane.showConfirmDialog(Frame2.this,setPane,"Family",JOptionPane.OK_CANCEL_OPTION);
                int fontStyle=0;
                switch (comboBox.getSelectedIndex()){
                    case 0:
                        fontStyle=Font.PLAIN;
                        break;
                    case 1:
                        fontStyle=Font.BOLD;
                        break;
                    case 2:
                        fontStyle=Font.ITALIC;
                        break;
                    case 3:
                        fontStyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                if(result==JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(OpFont.getText(),fontStyle,Integer.parseInt(OpSize.getText())));
                }
            }
        });
        //------------------------------------------------
        //--------------------Book------------------------
        jmG.add(jmGBook);
        jInternalFrame.setBounds(0,0,500,350);
        jInternalFrame.setJMenuBar(jinmB);
        jInternalFrame.add(bookScroll);
        jinmB.add(jinF);
        jinF.add(jinFSave);
        jinF.add(jinFLoad);
        jinF.add(jinFExit);
        jdktpane.add(jInternalFrame);
        jmGBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.setVisible(true);
            }
        });
        jinFSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.setVisible(false);
            }
        });
        jinFLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "txt", "txt");
                jfc.setFileFilter(filter);
                int select=jfc.showOpenDialog(Frame2.this);
                if(select==JFileChooser.APPROVE_OPTION){
                    try {
                        File filea=jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(filea));
                        String line;
                        while((line=br.readLine())!=null){
                            bookArea.append(line+"\n");
                        }
                    }catch(IOException ie){
                         ie.printStackTrace();
                    }
                }
            }
        });
        jinFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.setVisible(false);
                bookArea.setText(null);
            }
        });

        //------------------------------------------------

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Frame2.this.setVisible(false);
                log.setVisible(true);
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
                System.exit(0);
            }
        });
        jmGLoto.addActionListener(new ActionListener() {
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
        btn[10]=new JButton("<");
        conter.add(btn[10]);
        btn[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String tmp=jtf.getText().substring(0,jtf.getText().length()-1);
                if(!jtf.getText().equals("")) {
                    jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 1));
                }
            }
        });
        btn[11]=new JButton("Clear");
        conter.add(btn[11]);
        btn[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf.setText("");
            }
        });
        jmFKeyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jinFrameK.setVisible(true);
            }
        });
        jinFrameKExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jinFrameK.setVisible(false);
            }
        });
        //

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
