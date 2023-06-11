package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login  extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField pass1;
    JButton btn1,btn2,btn3;
    Login(){
        //title or the JFrame
        super("Bank Management System");
        //image settings bank
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));//Image loading to virtual machine
        Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);//Image scaling
        //For image visibility
        ImageIcon i3= new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);

        //card image
        ImageIcon p1=new ImageIcon(ClassLoader.getSystemResource("icons/card.png"));
        Image p2=p1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon p3= new ImageIcon(p2);
        JLabel crd=new JLabel(p3);
        crd.setBounds(630,350,100,100);
        add(crd);

        //Texts
         l1=new JLabel("WELCOME TO ATM");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("AvantGarde",Font.BOLD,38));
        l1.setBounds(230,125,450,40);
        add(l1);

         l2=new JLabel("Card No :");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Ralway",Font.BOLD,28));
        l2.setBounds(150,190,375,30);
        add(l2);

         l3=new JLabel("PIN:");
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Ralway",Font.BOLD,28));
        l3.setBounds(150,250,375,30);
        add(l3);
        //Input text field
        t1=new JTextField(15);
        t1.setBounds(325,190,280,30);
        t1.setFont(new Font("Arial", Font.BOLD, 28));
        add(t1);

        //   password
        pass1=new JPasswordField(15);
        pass1.setBounds(325,250,280,30);
        pass1.setFont(new Font("Arial",Font.ITALIC,28));
        add(pass1);

        //Buttons
        btn1=new JButton("Signin");
        btn1.setFont(new Font("Areial",Font.BOLD,14));
        btn1.setBounds(300,300,100,30);
        btn1.setForeground(Color.CYAN);
        btn1.setBackground(Color.BLACK);
        btn1.addActionListener(this);
        add(btn1);
//sign up btn
        btn2=new JButton("Signup");
        btn2.setFont(new Font("Areial",Font.BOLD,14));
        btn2.setBounds(300,350,230,30);
        btn2.setForeground(Color.CYAN);
        btn2.setBackground(Color.BLACK);
        btn2.addActionListener(this);
        add(btn2);
//clear btn
        btn3 =new JButton("Clear");
        btn3.setFont(new Font("Areial",Font.BOLD,14));
        btn3.setBounds(430,300,100,30);
        btn3.setForeground(Color.CYAN);
        btn3.setBackground(Color.BLACK);
        btn3.addActionListener(this);
        add(btn3);

        //Background picture  --should in down position for better compatibility
        ImageIcon b1=new ImageIcon(ClassLoader.getSystemResource("icons/backbg.png"));
        Image b2=b1.getImage().getScaledInstance(850,480,Image.SCALE_DEFAULT);
        ImageIcon b3= new ImageIcon(b2);
        JLabel bgr=new JLabel(b3);
        bgr.setBounds(0,0,850,480);
        add(bgr);

        //JFrame settings
        setLayout(null);//frame layout
        setSize(850,480);
        setLocation(450,200);
        setUndecorated(true);
        setVisible(true);//this should be in last else we will not be able to see changes after it

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource()==btn1){
                Connn c = new Connn();
                String cardno = t1.getText();
                String pin =pass1.getText();
                String q = "select * from login where Card_no = '"+cardno+"' and  Pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
                    new main_Class(pin);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }


            }
            else if(e.getSource()==btn2){
                new Signup();
                setVisible(false);
            }
            else if(e.getSource()==btn3){
                 t1.setText("");
                 pass1.setText("");
            }

        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args){
        new  Login();
    }

}
