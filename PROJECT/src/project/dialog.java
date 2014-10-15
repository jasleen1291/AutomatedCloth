package project;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.sql.*;

public class dialog extends JFrame {

    dialog() {
        setVisible(false);
        setLayout(null);
        this.new dialog1(null, " ").setVisible(true);
    }

    public class dialog1 extends JDialog {

        JPanel pnl;
        JLabel lbl, lbl1, lbl2, lbl3;
        JTextField txtf;
        JPasswordField pwdf;
        JButton btn, btn1, btn2;
        public String a;

        dialog1(JFrame dialog, String cc) {
            super(dialog, cc);
            setLayout(null);
            try {
                final Connection con;
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
                pnl = new JPanel();
                pnl.setLayout(null);
                pnl.setBounds(0, 0, 450, 400);
                pnl.setBackground(Color.white);
                Font font = new Font("ARIAL", Font.PLAIN, 15);
                lbl = new JLabel("Username:");
                lbl.setFont(font);
                lbl.setBounds(30, 50, 100, 25);
                pnl.add(lbl);
                txtf = new JTextField();
                txtf.setBounds(150, 50, 200, 25);
                txtf.setBorder(LineBorder.createGrayLineBorder());
                pnl.add(txtf);
                lbl1 = new JLabel("Required field is mandatory");
                lbl1.setBounds(150, 76, 200, 20);
                lbl1.setFont(font);
                lbl1.setForeground(Color.red);
                lbl1.setVisible(false);
                pnl.add(lbl1);
                lbl2 = new JLabel("Password:");
                lbl2.setFont(font);
                lbl2.setBounds(30, 120, 100, 25);
                pnl.add(lbl2);
                pwdf = new JPasswordField();
                pwdf.setBounds(150, 120, 200, 25);
                pwdf.setBorder(LineBorder.createGrayLineBorder());
                pnl.add(pwdf);
                lbl3 = new JLabel("Required field is mandatory");
                lbl3.setBounds(150, 146, 200, 20);
                lbl3.setFont(font);
                lbl3.setForeground(Color.red);
                lbl3.setVisible(false);

                pnl.add(lbl3);
                btn = new JButton("Submit");

                btn.setFont(font);
                btn.setBounds(50, 220, 100, 30);
                btn.setBorder(LineBorder.createBlackLineBorder());
                btn.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent e) {
                        if (txtf.getText().equals("")) {
                            lbl1.setVisible(true);

                        }
                        if (pwdf.getText().equals("")) {
                            lbl3.setVisible(true);
                        } else {
                            try {
                                Statement st;
                                ResultSet rs;
                                String Query;
                                String username = txtf.getText().toString().trim();
                                String password = pwdf.getText().toString().trim();
                               
                                final int i = 0;
                                st = con.createStatement();
                                Query = "select * from user where username like'" + username + "'&& password like'" + password + "'";
                              

                                rs = st.executeQuery(Query);
                                
                                if (rs.next()) {
                                    
                                    a = rs.getString(1);
                                    String b = rs.getString(2);
                                    userinfo.name1 = a;
                                    userinfo.lname1 = rs.getString(2);
                                    userinfo.email1 = (rs.getString(4));
                                    userinfo.username1 = rs.getString(7);
                                    if( userinfo.username1.equals("admin") )
                                    {
                                        Main.Update.setEnabled(true);
                                    }
                                    userinfo.phone1 = rs.getString(6);
                                    userinfo.password1 = rs.getString(8);
                                    userinfo.gender1 = rs.getString(3);
                                    userinfo.address1 = rs.getString(5);
                                   
                                    Main.main.setVisible(true);
                                    
                                    Main.main.setTitle("Welcome " + a + " " + b);
                                    setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Incorrect username or password. Please re-enter");
                                }
                               


                            } catch (Exception eq) {
                                System.out.println(eq);
                            }

                        }
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void mousePressed(MouseEvent e) {
                        //    throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void mouseReleased(MouseEvent e) {
                        //  throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void mouseEntered(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    public void mouseExited(MouseEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }
                });

                pnl.add(btn);
                btn1 = new JButton("Cancel");
                btn1.setFont(font);
                btn1.setBounds(170, 220, 100, 30);
                btn1.setBorder(LineBorder.createBlackLineBorder());
                btn1.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);

                    }
                });

                pnl.add(btn1);
                btn2 = new JButton("New User");

                btn2.setFont(font);
                btn2.setBounds(290, 220, 100, 30);
                btn2.setBorder(LineBorder.createBlackLineBorder());
                btn2.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        new Newuser();
                        setVisible(false);
                    }
                });
                pnl.add(btn2);
                add(pnl);
                setSize(450, 350);
                setVisible(true);
                Toolkit toolkit = getToolkit();
                Dimension size = toolkit.getScreenSize();
                setLocation(size.width / 2 - getWidth() / 2,
                        size.height / 2 - getHeight() / 2);

            } catch (Exception p) {
            }
        }
    }

    public static void main(String ar[]) {
        dialog d = new dialog();

    }
}
