/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RightPanel extends JPanel {

    public static JButton addcart1, addcart2;
    public static JPanel jp, display;
    public static JLabel top, bottom, prod, id, cost, cost1, prod1, id1, cost11, cost12;
    public static int i, cntr, j;
    public static String sel[] = new String[50];
    public static Boolean add = true;
    public static JSpinner spinner, spinner2;
    public static String billn;
    public static int qty;

    RightPanel() {
        setLayout(null);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            cntr = 0;
            j = 0;
            try {
                Statement st1;
                ResultSet rs1;
                st1 = con.createStatement();
                String query = "select max(billno)+1 from billing1";
                rs1 = st1.executeQuery(query);
                if (rs1.next()) {
                    final String bn = rs1.getString(1);

                }

            } catch (Exception ae) {
                System.out.println(ae);
            }
            jp = new JPanel();
            jp.setBackground(Color.WHITE);
            jp.setLayout(null);
            jp.setPreferredSize(new Dimension(1100, 10000));
            int v = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
            int h = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
            JScrollPane jsp = new JScrollPane(jp, h, v);
            jsp.setBounds(0, 0, 750, 680);
            add(jsp, BorderLayout.CENTER);
            display = new JPanel();
            display.setBounds(750, 0, 350, 680);
            display.setBackground(Color.WHITE);
            display.setLayout(null);
            top = new JLabel();
            top.setBounds(75, 10, 250, 250);
            bottom = new JLabel();
            bottom.setBounds(75, 350, 250, 250);
            display.add(top);
            Font f = new Font("Arial", Font.BOLD, 12);
            prod = new JLabel("Product ID");
            id = new JLabel();
            cost = new JLabel("Cost");
            cost1 = new JLabel();

            prod.setFont(f);
            id.setFont(f);
            cost.setFont(f);
            cost1.setFont(f);
            prod1 = new JLabel("Product ID");
            id1 = new JLabel();
            cost11 = new JLabel("Cost");
            cost12 = new JLabel();
            spinner = new JSpinner();
            spinner2 = new JSpinner();
            spinner.setValue(new Integer(1));
            spinner2.setValue(new Integer(1));
            addcart1 = new JButton("Add to cart");


            addcart1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    final String b, f, g;
                    final int c;
                    add = true;
                    i = cntr;
                    b = id.getText().toString().trim();
                    sel[i] = b;

                    f = cost1.getText().trim();
                    for (int q = 0; q < i; q++) {
                        if (sel[q].equals(b)) {
                            add = false;
                            JOptionPane.showMessageDialog(null, "This item is already in the list!!!");

                        }
                    }

                    if (add == true) {

                        d.btn[i] = new JButton(b);
                        d.btn[i].setIcon(new ImageIcon(getClass().getResource("/images/SMALL/" + b + ".jpg")));
                        d.btn[i].setBounds(j, 0, 80, 80);
                        d.btn[i].setBackground(Color.WHITE);
                        d.btn[i].setForeground(Color.WHITE);

                        //  System.out.println("dhdhd"+billn);
                        try {

                            Statement st;
                            st = con.createStatement();
                            qty = Integer.parseInt(spinner.getValue().toString());
                            c = Integer.parseInt(cost1.getText().trim());
                            //g = Integer.toString(d.sum = d.sum + c * qty);
                            String table = "insert into billing(idnum,quantity,cost,amount) values ('" + b + "'," + qty + ", " + c + ","+qty*c + ")";
                            JOptionPane.showMessageDialog(null, "Item added to cart");
                            st.executeUpdate(table);


                        } catch (Exception es) {
                            System.out.println(es);
                        }

                        d.btn[i].addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                d.label.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/" + b + ".jpg")));
                                d.label.setForeground(Color.WHITE);
                                d.spinner.setValue(new Integer(qty));
                                d.label.setText(b);
                                d.id1.setText(b);
                                d.cost1.setText(f);
                                try {

                                    Statement st;
                                    ResultSet rs;
                                    st = con.createStatement();
                                    String table = "select quantity from billing where idnum like '" + b + "'";
                                    rs = st.executeQuery(table);
                                    while (rs.next()) {
                                        d.spinner.setValue(new Integer(rs.getString(1)));
                                        d.spinner.validate();
                                        
                                    }



                                } catch (Exception es) {
                                    System.out.println(es);
                                }




                            }
                        });
                        d.jp.add(d.btn[i]);

                        d.jp.repaint();
                        cntr++;
                        j = j + 100;
                    }
                }
            });
            {
            }

            addcart2 = new JButton("Add to cart");
            addcart2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    final String b, f, g;
                    final int c;
                    i = cntr;
add =true;
                    b = id1.getText().toString().trim();
                    sel[i] = b;
                    f = cost12.getText().trim();
                    for (int q = 0; q < i; q++) {
                        if (sel[q].equals(b)) {
                            add = false;
                            JOptionPane.showMessageDialog(null, "This item is already in the list!!!");

                        }
                    }

                    if (add == true) {
                        try{
                        d.btn[i] = new JButton(b);
                        d.btn[i].setForeground(Color.WHITE);
                        d.btn[i].setIcon(new ImageIcon(getClass().getResource("/images/SMALL/" + b + ".jpg")));
                        d.btn[i].setBounds(j, 0, 80, 80);
                        d.btn[i].setBackground(Color.WHITE);

                        try {

                            Statement st;
                            st = con.createStatement();
                            qty = Integer.parseInt(spinner.getValue().toString());
                            c = Integer.parseInt(cost1.getText().trim());
                            String table = "insert into billing(idnum,quantity,cost,amount) values ('" + b + "'," + qty + ", " + c + " ,"+ qty*c+")";
                            JOptionPane.showMessageDialog(null, "Item added to cart");
                            st.executeUpdate(table);


                        } catch (Exception es) {
                            System.out.println(es);
                        }}
                        catch(Exception  q)
                        {
                            System.out.println(q);
                        }

                        d.btn[i].addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                d.label.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/" + b + ".jpg")));
                                d.label.setForeground(Color.WHITE);
                                d.label.setText(b);
                                d.id1.setText(b);
                                d.cost1.setText(f);
                                try {

                                    Statement st;
                                    ResultSet rs;
                                    st = con.createStatement();
                                    String table = "select quantity from billing where idnum like '" + b + "'";
                                    rs = st.executeQuery(table);
                                    while (rs.next()) {
                                        d.spinner.setValue(new Integer(rs.getString(1)));
                                        d.spinner.validate();

                                    }

                                } catch (Exception es) {
                                    System.out.println(es);
                                }

                            }
                        });
                        d.jp.add(d.btn[i]);

                        d.jp.repaint();
                        cntr++;
                        j = j + 100;
                    }
                }
            });
            {
            }
            
            prod1.setFont(f);
            id1.setFont(f);
            cost11.setFont(f);
            cost12.setFont(f);
            prod.setBounds(10, 260, 100, 15);
            id.setBounds(120, 260, 100, 15);
            cost.setBounds(10, 280, 100, 15);
            cost1.setBounds(120, 280, 100, 15);
            addcart1.setBounds(100, 300, 100, 20);
            addcart2.setBounds(100, 650, 100, 20);
            prod1.setBounds(10, 610, 100, 15);
            id1.setBounds(120, 610, 100, 15);
            cost11.setBounds(10, 630, 100, 15);
            cost12.setBounds(120, 630, 100, 15);
            spinner.setBounds(225, 260, 50, 20);
            spinner2.setBounds(225, 610, 50, 20);
            display.add(spinner);
            display.add(spinner2);
            display.add(prod);
            display.add(id);
            display.add(cost);
            display.add(cost1);
            display.add(prod1);
            display.add(id1);
            display.add(cost11);
            display.add(cost12);
            display.add(bottom);
            display.add(addcart1);
            display.add(addcart2);
            addcart1.setFont(new Font("Arial", Font.BOLD, 12));
            addcart2.setFont(new Font("Arial", Font.BOLD, 12));
            add(display);
            setVisible(true);


        } catch (Exception a) {
        }
    }
}
