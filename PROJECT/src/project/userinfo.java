/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.*;
import javax.swing.border.LineBorder;

public class userinfo extends JDialog {
 public JLabel id,name, lname, email, address, username, password, phoneno, gender;
public static JTextField namefield, lnamefield, emailfield, usernamefield, phonenofield,passwordfield,genderselect;

    public JButton ok, update;

    public static JTextArea addressfield;
   

    public static String name1, lname1, email1, address1, username1, password1, gender1,phone1;

    userinfo()
{
    try {
            final Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");

       
        setSize(400, 500);
        setLayout(null);
        setTitle("User Info");
        setBackground(Color.WHITE);
        getContentPane().setBackground(Color.WHITE);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        Font font = new Font("ARIAL", Font.PLAIN, 15);
        name = new JLabel("Name:");
        lname = new JLabel("Last Name:");
        email = new JLabel("Email:");
        address = new JLabel("Address:");
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        phoneno = new JLabel("Phone Number:");
        namefield = new JTextField(name1);
        lnamefield = new JTextField(lname1);
        emailfield = new JTextField(email1);
        usernamefield = new JTextField(username1);
        phonenofield = new JTextField(phone1);
        ok = new JButton("OK");
         update= new JButton("Update");
        passwordfield = new JPasswordField(password1);

        addressfield = new JTextArea(address1);
        gender = new JLabel("Gender");
        genderselect = new JTextField(gender1);

        name.setFont(font);
        lname.setFont(font);
        email.setFont(font);
        address.setFont(font);
        username.setFont(font);
        password.setFont(font);
        phoneno.setFont(font);
        gender.setFont(font);

        addressfield.setBorder(LineBorder.createBlackLineBorder());
        namefield.setBorder(LineBorder.createBlackLineBorder());
        lnamefield.setBorder(LineBorder.createBlackLineBorder());
        emailfield.setBorder(LineBorder.createBlackLineBorder());
        usernamefield.setBorder(LineBorder.createBlackLineBorder());
        phonenofield.setBorder(LineBorder.createBlackLineBorder());
        ok.setBorder(LineBorder.createBlackLineBorder());
        update.setBorder(LineBorder.createBlackLineBorder());
        passwordfield.setBorder(LineBorder.createBlackLineBorder());
        genderselect.setBorder(LineBorder.createBlackLineBorder());


        name.setBounds(10, 10, 150, 20);
        lname.setBounds(10, 50, 150, 20);
        gender.setBounds(10, 90, 150, 20);
        email.setBounds(10, 130, 150, 20);
        address.setBounds(10, 170, 150, 20);
        phoneno.setBounds(10, 230, 150, 20);
        username.setBounds(10, 270, 150, 20);
        password.setBounds(10, 310, 150, 20);

        genderselect.setBounds(170, 90, 150, 20);
//        female.setBounds(250, 90, 80, 20);
//        male.setBackground(Color.WHITE);
//        female.setBackground(Color.WHITE);


        namefield.setBounds(170, 10, 150, 20);
        lnamefield.setBounds(170, 50, 150, 20);
        emailfield.setBounds(170, 130, 150, 20);
        addressfield.setBounds(170, 170, 150, 40);

        phonenofield.setBounds(170, 230, 150, 20);
        usernamefield.setBounds(170, 270, 150, 20);
        passwordfield.setBounds(170, 310, 150, 20);

        ok.setBounds(50, 400, 100, 25);
        update.setBounds(200, 400, 100, 25);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        }) ;



        update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (namefield.getText().equals(" ") || lnamefield.getText().equals(" ") || emailfield.getText().equals(" ") || usernamefield.getText().equals(" ") || phonenofield.getText().equals(" ") || addressfield.getText().equals(" ") || passwordfield.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "All Fields are mandatory");
                    } else {
                         name1 = namefield.getText().toString().trim();

                        lname1 = lnamefield.getText().toString().trim();
                        email1 = emailfield.getText().toString().trim();
                        address1 = addressfield.getText().toString().trim();
                        username1 = usernamefield.getText().toString().trim();
                        password1 = passwordfield.getText().toString().trim();
                        phone1 = phonenofield.getText().toString().trim();

                        

                        

            }}
        });

        add(name);
        add(lname);
        add(email);
        add(address);
        add(username);
        add(password);
        add(phoneno);
        add(namefield);
        add(lnamefield);
        add(emailfield);
        add(usernamefield);
        add(phonenofield);
        add(ok);
        add(update);
        add(addressfield);
        add(passwordfield);
        add(gender);
        add(genderselect);
         setVisible(true);
        
     
}
    catch (Exception p) {
            System.out.println(p);
        }
}
    
    public static void main(String a[])
    {
        new userinfo();
    }
}
