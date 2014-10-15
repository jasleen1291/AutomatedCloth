package project;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.sql.*;

public class Newuser extends JDialog {

    public JLabel name, lname, email, address, username, password, phoneno, gender;
    public JTextField namefield, lnamefield, emailfield, usernamefield, phonenofield;
    public JButton submit, cancel;
    public JPasswordField passwordfield;
    public JTextArea addressfield;
    public JRadioButton male, female;
    public ButtonGroup genderselect;
    public String name1, lname1, email1, address1, username1, password1, gender1;
    public String phoneno1;

    Newuser() {
        try {
            final Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");


            setSize(400, 500);
            setTitle("NEW USER REGISTERATION");
            setLayout(null);
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
            namefield = new JTextField(" ");
            lnamefield = new JTextField(" ");
            emailfield = new JTextField(" ");
            usernamefield = new JTextField(" ");
            phonenofield = new JTextField(" ");
            submit = new JButton("Submit");
            cancel = new JButton("Cancel");
            passwordfield = new JPasswordField();
            passwordfield.setEchoChar('*');
            addressfield = new JTextArea(" ");
            gender = new JLabel("Gender");
            genderselect = new ButtonGroup();
            male = new JRadioButton("Male", true);
            female = new JRadioButton("Female");
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
            submit.setBorder(LineBorder.createBlackLineBorder());
            cancel.setBorder(LineBorder.createBlackLineBorder());
            passwordfield.setBorder(LineBorder.createBlackLineBorder());


            name.setBounds(10, 10, 150, 20);
            lname.setBounds(10, 50, 150, 20);
            gender.setBounds(10, 90, 150, 20);
            email.setBounds(10, 130, 150, 20);
            address.setBounds(10, 170, 150, 20);
            phoneno.setBounds(10, 230, 150, 20);
            username.setBounds(10, 270, 150, 20);
            password.setBounds(10, 310, 150, 20);

            male.setBounds(170, 90, 80, 20);
            female.setBounds(250, 90, 80, 20);
            male.setBackground(Color.WHITE);
            female.setBackground(Color.WHITE);


            namefield.setBounds(170, 10, 150, 20);
            lnamefield.setBounds(170, 50, 150, 20);
            emailfield.setBounds(170, 130, 150, 20);
            addressfield.setBounds(170, 170, 150, 40);

            phonenofield.setBounds(170, 230, 150, 20);
            usernamefield.setBounds(170, 270, 150, 20);
            passwordfield.setBounds(170, 310, 150, 20);

            submit.setBounds(50, 400, 100, 25);
            cancel.setBounds(200, 400, 100, 25);
            submit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (namefield.getText().equals(" ") || lnamefield.getText().equals(" ") || emailfield.getText().equals(" ") || usernamefield.getText().equals(" ") || phonenofield.getText().equals(" ") || addressfield.getText().equals(" ") || passwordfield.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "All Fields are mandatory");
                    } else {
                        setVisible(false);

                        Main.main.setTitle(name1);
                        name1 = namefield.getText().toString().trim();

                        lname1 = lnamefield.getText().toString().trim();
                        email1 = emailfield.getText().toString().trim();
                        address1 = addressfield.getText().toString().trim();
                        username1 = usernamefield.getText().toString().trim();
                        password1 = passwordfield.getText().toString().trim();
                        phoneno1 = phonenofield.getText().toString().trim();

                        if (male.isSelected()) {
                            gender1 = "Male";
                        } else {
                            gender1 = "Female";
                        }

                        Statement st;
                        try {
                            st = con.createStatement();
                            String Query;
                            Query = "insert into user(name,last_name,gender,email,address,phone,username,password) values('" + name1 + "','" + lname1 + "','" + gender1 + "','" + email1 + "','" + address1 + "','" + phoneno1 + "','" + username1 + "','" + password1 + "')";
                            int result = st.executeUpdate(Query);
                            if (result == 1) {

                                JOptionPane.showMessageDialog(null, "Insertion Successful!");

                            } else {
                                JOptionPane.showMessageDialog(null, "Insertion Failed");
                            }
                            st.close();
                            con.close();
                        } catch (Exception eq) {
                            System.out.println("newuser error");
                        }
                        try {
                            String a = usernamefield.getText().toString().trim();
                            
                            Statement st2;
                            st2 = con.createStatement();
                            String table = "create table " + a + "(billno int,idnum varchar(8) ,quantity int,cost int,purchaseDate varchar(50))";
                            st2.executeUpdate(table);

                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, "Username already exists \n Please refill all your details again");
                            new Newuser();
                        }
                        dialog a = new dialog();

                    }
                }
            });

            cancel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dialog a = new dialog();
                }
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
            add(submit);
            add(cancel);
            add(addressfield);
            add(passwordfield);
            add(gender);
            add(male);
            add(female);
            genderselect.add(male);
            genderselect.add(female);
            setVisible(true);
        } catch (Exception p) {
            System.out.println(p);
        }



    }
}
