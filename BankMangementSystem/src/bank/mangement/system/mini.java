package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin,cardno,forno;
    JButton button;
    mini(String pin){
        this.pin = pin;
        getContentPane().setBackground(new Color(222, 225, 228));
        setSize(400,600);
        setLocation(300,20);
        setUndecorated(true);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("Transction Histoy");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(50,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label5= new JLabel();
        label5.setBounds(20,100,300,20);
        add(label5);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        try{
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("select * from login where Pin = '"+pin+"'");
            while (resultSet.next()){
                cardno=resultSet.getString("Card_no");
                label3.setText("Card Number:  "+ resultSet.getString("Card_no").substring(0,4) + "XXXXXXXX"+ resultSet.getString("Card_no").substring(12));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        try{
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("select  Form_no from signupthree where card_no = '"+cardno+"'");
            while (resultSet.next()){
                forno=resultSet.getString("Form_no");
            }
            ResultSet res = c.statement.executeQuery("select  name from signup where form_no = '"+forno+"'");
            while (res.next()){
                label5.setText("COUSTUMER NAME:  "+ res.getString("name"));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

        try{
            int balance =0;
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){

                label1.setText(label1.getText() + "<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+ "<br><br><html>");

                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label4.setText("Your Total Balance is Rs "+balance);
        }catch (Exception e){
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20,500,100,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
