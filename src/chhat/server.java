package chhat;
//created by Nikhil Nihore , IET DAVV
import javax.swing.*; // swing package contains jframe class which will allow us to edit the frame 
import java.awt.*; // to use graphics from system resource
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class server extends JFrame implements ActionListener { // sever class will extend jframe class which is imported from javaswing package

    JPanel p1; // created panel 
    JTextField t1; // created text field 
    JButton b1; // created button 
   static JTextArea a1; // created text area
    
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    server() {

        //inserting and editing panel
        p1 = new JPanel(); // panel object creation
        p1.setLayout(null); // to avoid using default border layout we set it as null
        p1.setBackground(new Color(7, 94, 84)); // inserting color to the panel
        p1.setBounds(0, 0, 450, 70);
        add(p1);



        // inserting and editing the arrow image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chhat/icons/3.png")); // it will select the image we want to use in the frame 
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3); // to use a particular image label is used to set that particular image in the frame

        l1.setBounds(5, 17, 30, 30); // now swing provides many default layout for the image 
        // and to create our own layout we use setbounds method
        p1.add(l1); // it is most important, to use imaghe in the frame 'add' must be used 

        // adding mouse listner event in label 1
        l1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });



        // inserting and editing the person image    
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chhat/icons/billgates.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);

        l2.setBounds(40, 5, 60, 60);

        p1.add(l2);



        // inserting and editing the videocall image    
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chhat/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l3 = new JLabel(i9);

        l3.setBounds(320, 17, 30, 30);

        p1.add(l3);



        // inserting and editing the calling option image    
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chhat/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l4 = new JLabel(i12);

        l4.setBounds(370, 17, 30, 30);

        p1.add(l4);



        // inserting name of the person
        JLabel lname = new JLabel("Bill Gates");
        lname.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        lname.setForeground(Color.WHITE);
        lname.setBounds(110, 15, 100, 18);
        p1.add(lname);



        // inserting status of person (online / offline)
        JLabel lname1 = new JLabel("Online");
        lname1.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        lname1.setForeground(Color.WHITE);
        lname1.setBounds(110, 35, 100, 20);
        p1.add(lname1);



        //text area editing, text area - the area/place where the input texts will be shown or printed
        a1 = new JTextArea(); //text area object
        a1.setBounds(5, 75, 440, 570);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16)); //display of fonts in text area
        a1.setEditable(false); // * we used editable(false) so that we are no longer able to update the text in the text area *
        a1.setLineWrap(true); // 
        a1.setWrapStyleWord(true);
        add(a1);



        //inserting and adding functionality to the text field
        t1 = new JTextField(); // text field - the area/place where we will insert the text that is needed to be sent
        t1.setBounds(5, 655, 310, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);



        //inserting and adding functionality to the send button
        b1 = new JButton("Send"); // send button configuration 
        b1.setBounds(320, 655, 120, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this); // added functionality to the send button so that , whenever we press send button the           ↓
        add(b1);



        setLayout(null); // if you wish not to use an^y default layouts of swing we need to set the layout as null
        setSize(450, 700); // it will set the dimensions of the frame
        setLocation(300, 50); // it will set the coordinates of the frame
        setUndecorated(true); // to remove window tab from frame
        setVisible(true); // by default all the frames are false and to shjow them we declare them as true in order to see them
    }

    @Override                                                                                                                      //↓     
    public void actionPerformed(ActionEvent ae) {                                                                                  //← this method gets called  
     
        try{
        
        String out = t1.getText(); // the text in text field will be taken out from text field and will be added to text area
        a1.setText(a1.getText() + "\n" + out); // taking string out of the text field area and getting it and then →
        dout.writeUTF(out);
        t1.setText(""); //→ setting it in text area
    }catch(Exception e){}
        
    }

    public static void main(String args[]) {

        new server().setVisible(true);
        
        String msginput = "";
        
        try{
            skt = new ServerSocket(3306);
            s = skt.accept();
            System.out.println(s);
            din = new DataInputStream(s.getInputStream());
                System.out.println(din);
            dout = new DataOutputStream(s.getOutputStream());
            msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
            skt.close();
            s.close();
            
        }
        catch(Exception e){}
    }

}
