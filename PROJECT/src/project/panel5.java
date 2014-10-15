/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class panel5 extends JPanel {

    JCheckBox[] color = new JCheckBox[5];
    JLabel bb;
    JButton submit;
    String cost;
    String ar[] = new String[60];
    String ar2[] = new String[60];
    JButton items[] = new JButton[60];
    JLabel items2[] = new JLabel[60];
    String[] col = {"red", "blue", "white", "black", "purple"};
    String[] col2 = {"redb", "blueb", "whiteb", "blackb", "purpleb"};

    panel5() {
        int e = 0;
        setVisible(false);
        try {
            final Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/images/bg.jpg")));
            bg.setLayout(null);
            for (int q = 0; q <= 4; q++) {
                color[q] = new JCheckBox(col[q]);
                color[q].setIcon(new ImageIcon(getClass().getResource("/images/" + col[q] + ".jpg")));
                color[q].setSelectedIcon(new ImageIcon(getClass().getResource("/images/" + col2[q] + ".jpg")));
                color[q].setBounds(e, 100, 40, 40);
                e = e + 50;
                bg.add(color[q]);
            }
            submit = new JButton("Submit");
            submit.setBounds(70, 200, 100, 30);
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    RightPanel.jp.setVisible(false);
                    RightPanel.jp.removeAll();
                    revalidate();
                    int cntr = 0;
                    int x = 0;
                    int y = 0;
                    for (int q = 0; q <= 4; q++) {
                        if (color[q].isSelected()) {
                            try {
                                Connection con;
                                Statement st;
                                ResultSet rs;
                                String Query;
                                final int i = 0;
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
                                st = con.createStatement();
                                Query = "select id from items where category like'babygirl'&& color like '" + col[q] + "' ";
                                rs = st.executeQuery(Query);
                                while (rs.next()) {
                                    cntr++;
                                    ar[i] = rs.getString(1);
                                    ar2[i] = "/images/SMALL/" + ar[i] + ".jpg";
                                    items[i] = new JButton(ar[i], new ImageIcon(getClass().getResource(ar2[i])));
                                    items[i].setForeground(Color.WHITE);
                                    items[i].addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            String a = e.getActionCommand();
                                            Connection con;
                                            Statement st;
                                            ResultSet rs;
                                            try {
                                                Class.forName("com.mysql.jdbc.Driver");
                                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
                                                st = con.createStatement();
                                                String Query;
                                                Query = "select cost from items where id like '" + a + "'";
                                                rs = st.executeQuery(Query);
                                                while (rs.next()) {
                                                    cost = rs.getString(1);
                                                }
                                            } catch (Exception d) {
                                            }
                                            String top;

                                            top = "/images/BIG/JPEG/" + a + ".jpg";
                                            RightPanel.top.setIcon(new ImageIcon(getClass().getResource(top)));
                                            RightPanel.id.setText(a);
                                            RightPanel.cost1.setText(cost);
                                        }
                                    });
                                    items[i].setBackground(Color.WHITE);
                                    items[i].setBounds(x, y, 70, 70);
                                    items2[i] = new JLabel(ar[i]);
                                    items2[i].setBounds(x, y + 45, 70, 70);
                                    repaint();
                                    if (x >= 600) {
                                        y = y + 130;
                                        x = 0;
                                    } else {
                                        x = x + 100;
                                    }
                                    RightPanel.jp.add(items[i]);
                                    RightPanel.jp.add(items2[i]);
                                }
                                st.close();
                                
                            } catch (Exception eq) {
                               
                            }
                            RightPanel.jp.setVisible(true);
                        }

                    }
                }
            });
            bg.add(submit);
            add(bg);

        } catch (Exception z) {
            System.out.println(z);
        }

    }
}
