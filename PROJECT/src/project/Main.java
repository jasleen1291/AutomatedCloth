/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Chawla
 */
public class Main {

    static JMenu File;
    static JMenu Clothing;
    static JMenu Update;
    static JMenu Help;
    static JMenuBar jMenuBar1;
    static JMenuItem Print;
    static JMenuItem Close;
    static JMenuItem Shop_cart;
    static JMenuItem Add;
    static JMenuItem Remove;
    static JMenuItem switchuser;
    static JMenuItem newuser;
    static JMenuItem userinfo;
    static JMenuItem recent;
    static JMenuItem About_us;
    public static JFrame main;
    //public  JDesktopPane main;

    public static void main(String ar[]) {

        progressbar pb = new progressbar();
        dialog dialog = new dialog();
       
        try {
            final Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            main = new JFrame();
            

            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jMenuBar1 = new JMenuBar();
            File = new JMenu();
            Print = new JMenuItem();
            Close = new JMenuItem();
            Clothing = new JMenu();
            Shop_cart = new JMenuItem();
            Update = new JMenu();
            Add = new JMenuItem();
            Help = new JMenu();
            About_us = new JMenuItem();
            Remove = new JMenuItem();
            switchuser = new JMenuItem("Switch User");
            newuser = new JMenuItem("New User");
            userinfo = new JMenuItem("User Info");
            recent = new JMenuItem("Recent Purchases");



            File.setText("File");
            File.setMnemonic(KeyEvent.VK_F);

            Print.setText("View my Bill");
            Print.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    new billing(null, "Billing");

                }
            });

            File.add(Print);
            Print.setText("View my Bill");
            switchuser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Main.main.setVisible(false);
                    try {

                        Statement st;
                        st = con.createStatement();
                        String table = "Truncate table billing";
                        st.executeUpdate(table);

                    } catch (Exception e2) {
                        System.out.println("error");
                    }
                    dialog a = new dialog();

                }
            });
            newuser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Main.main.setVisible(false);
                    try {

                        Statement st;
                        ResultSet rs;
                        st = con.createStatement();
                        String table = "Truncate table billing";
                        st.executeUpdate(table);

                    } catch (Exception e2) {
                        System.out.println("error");
                    }
                    new Newuser();

                }
            });
            userinfo.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    new userinfo();

                }
            });
            recent.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    new recent(null, "Recent");

                }
            });

            File.add(switchuser);
            File.add(newuser);
            File.add(userinfo);
            File.add(recent);


            Close.setText("Close");
            Close.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {

                        Statement st;
                        ResultSet rs;
                        st = con.createStatement();
                        String table = "Truncate table billing";
                        st.executeUpdate(table);

                    } catch (Exception e2) {
                        System.out.println("error");
                    }
                    System.exit(0);

                }
            });
            File.add(Close);

            jMenuBar1.add(File);

            Clothing.setText("Clothing");
            Clothing.setMnemonic(KeyEvent.VK_C);
            Shop_cart.setText("View My Cart");
            Shop_cart.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    new d(null, "Shopping Cart");

                }
            });
            Clothing.add(Shop_cart);

            Add.setText("Add Item");
            Add.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    new add_item(null, "Add Item").setVisible(true);

                }
            });

            Remove.setText("Remove Item");
            Remove.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    new remove_item(null, "Remove Item").setVisible(true);
                }
            });

            Update.add(Add);
            Update.add(Remove);
            Update.setEnabled(false);
            jMenuBar1.add(Clothing);
            Update.setText("Update");
            Update.setMnemonic(KeyEvent.VK_U);
            jMenuBar1.add(Update);

            Help.setText("Help");
            Help.setMnemonic(KeyEvent.VK_H);
            About_us.setText("About Us");

            Help.add(About_us);

            jMenuBar1.add(Help);

            leftpanel left = new leftpanel();
            left.setMinimumSize(new Dimension(250, 680));
            RightPanel right = new RightPanel();
            right.setPreferredSize(new Dimension(1100, 680));
            //  main.setResizable(false);
            JSplitPane splt = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
            main.setJMenuBar(jMenuBar1);
            main.addWindowListener(new WindowListener() {

                public void windowOpened(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void windowClosing(WindowEvent e) {
                    try {

                        Statement st;
                        ResultSet rs;
                        st = con.createStatement();
                        String table = "Truncate table billing";
                        st.executeUpdate(table);

                    } catch (Exception e2) {
                        System.out.println("error");
                    }
                }

                public void windowClosed(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void windowIconified(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void windowDeiconified(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void windowActivated(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void windowDeactivated(WindowEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }
            });
            main.add(splt);
            main.setExtendedState(JFrame.MAXIMIZED_BOTH);
            leftpanel.btn.doClick();
            //  main.setResizable(false);
            main.pack();
        } catch (Exception z) {
            System.out.println("main"+z);
        }
    }
}