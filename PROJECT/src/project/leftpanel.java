package project;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class leftpanel extends JPanel {

    static int cntr = 0, x = 0, y = 0;
    String top, bottom;
    JPanel panel, panel2, panel3, panel4, panel5, panel6;
    JLabel all, women, men, bb, bg;
    public static JButton btn, btn1, btn2, btn3, btn4;
    Font f;
    public JCheckBox uppers, lowers, red, blue, pp, white, black;
    JButton submit;
    String ar[] = new String[247];
    String ar2[] = new String[247];
    JButton items[] = new JButton[247];
    JLabel items2[] = new JLabel[247];
    String cost, cost1;

    leftpanel() {

        setLayout(null);
        setSize(500, 400);
        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.setBounds(0, 0, 250, 1500);
        f = new Font("Arial", Font.PLAIN, 15);
        Font font = new Font("ARIAL", Font.PLAIN, 15);

        uppers = new JCheckBox();
        uppers.setIcon(new ImageIcon(getClass().getResource("/images/upper.jpg")));
        lowers = new JCheckBox("Female");
        lowers.setIcon(new ImageIcon(getClass().getResource("/images/lower.jpg")));
        red = new JCheckBox();

        red.setIcon(new ImageIcon(getClass().getResource("/images/red.jpg")));

        red.setSelectedIcon(new ImageIcon(getClass().getResource("/images/redb.jpg")));

        blue = new JCheckBox();
        blue.setIcon(new ImageIcon(getClass().getResource("/images/blue.jpg")));
        blue.setSelectedIcon(new ImageIcon(getClass().getResource("/images/blueb.jpg")));
        pp = new JCheckBox();
        pp.setIcon(new ImageIcon(getClass().getResource("/images/purpin.jpg")));
        pp.setSelectedIcon(new ImageIcon(getClass().getResource("/images/purpinb.jpg")));
        white = new JCheckBox();
        white.setIcon(new ImageIcon(getClass().getResource("/images/white.jpg")));
        white.setSelectedIcon(new ImageIcon(getClass().getResource("/images/whiteb.jpg")));
        black = new JCheckBox();
        black.setIcon(new ImageIcon(getClass().getResource("/images/black.jpg")));
        black.setSelectedIcon(new ImageIcon(getClass().getResource("/images/blackb.jpg")));
        submit = new JButton("Submit");

        uppers.setFont(font);
        lowers.setFont(font);
        red.setFont(font);
        blue.setFont(font);
        pp.setFont(font);
        white.setFont(font);
        submit.setFont(font);


        uppers.setBounds(20, 100, 40, 40);
        lowers.setBounds(120, 100, 40, 40);
        red.setBounds(10, 180, 40, 40);
        blue.setBounds(60, 180, 40, 40);
        pp.setBounds(110, 180, 40, 40);
        white.setBounds(160, 180, 40, 40);
        black.setBounds(210, 180, 40, 40);
        submit.setBounds(30, 250, 100, 40);
        red.setVisible(false);
        blue.setVisible(false);
        pp.setVisible(false);
        white.setVisible(false);
        black.setVisible(false);
        panel2 = new JPanel();
        panel2.setBounds(0, 200, 250, 600);
        all = new JLabel();
        all.setIcon(new ImageIcon(getClass().getResource("/images/all.jpg")));
        all.setLayout(null);

        panel.add(panel2);
        panel2.add(all);

        panel2.setVisible(false);
        panel3 = new panel3();
        panel3.setBounds(0, 200, 250, 600);

        panel3.setVisible(false);

        panel.add(panel3);
        panel4 = new panel4();
        panel4.setBounds(0, 200, 250, 600);

        panel4.setVisible(false);

        panel.add(panel4);
        panel5 = new panel5();
        panel5.setBounds(0, 200, 250, 600);

        panel5.setVisible(false);
        panel.add(panel5);
        panel6 = new panel6();

        panel6.setBounds(0, 200, 250, 600);

        panel6.setVisible(false);

        panel.add(panel6);
        btn = new JButton("All Collections");
        btn.setFont(f);
        btn.setBounds(0, 0, 250, 40);
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                x = 0;
                y = 0;
                RightPanel.jp.setVisible(false);
                RightPanel.jp.removeAll();
                revalidate();
                panel2.setVisible(true);
                panel3.setVisible(false);
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(false);
                RightPanel.cost11.setVisible(true);
                RightPanel.cost12.setVisible(true);
                RightPanel.id1.setVisible(true);
                RightPanel.prod1.setVisible(true);
                RightPanel.addcart2.setVisible(true);
                RightPanel.spinner2.setVisible(true);
                RightPanel.top.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/MB006.jpg")));
                RightPanel.id.setText("MB006");
                RightPanel.cost1.setText("975");
                RightPanel.bottom.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/MJ002.jpg")));
                RightPanel.cost12.setText("850");

                RightPanel.id1.setText("MJ002");
                int i = 0;

                Connection con;
                Statement st;
                ResultSet rs;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
                    st = con.createStatement();
                    String Query;
                    Query = "select id from items order by id";
                    rs = st.executeQuery(Query);
                    while (rs.next()) {
                        ar[i] = rs.getString(1);

                        ar2[i] = "/images/SMALL/" + ar[i] + ".jpg";

                        items[i] = new JButton(ar[i], new ImageIcon(getClass().getResource(ar2[i])));

                        items[i].setForeground(Color.WHITE);

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
                                if (a.startsWith("MJ") || a.startsWith("FJ")) {
                                    bottom = "/images/BIG/JPEG/" + a + ".jpg";
                                    RightPanel.bottom.setIcon(new ImageIcon(getClass().getResource(bottom)));
                                    RightPanel.addcart2.setVisible(true);
                                    RightPanel.id1.setVisible(true);
                                    RightPanel.cost11.setVisible(true);
                                    RightPanel.cost12.setVisible(true);
                                    RightPanel.prod1.setVisible(true);
                                    RightPanel.cost12.setText(cost);
                                    RightPanel.id1.setText(a);
                                } else if (a.startsWith("BB") || a.startsWith("BG")) {
                                    top = "/images/BIG/JPEG/" + a + ".jpg";
                                    RightPanel.top.setIcon(new ImageIcon(getClass().getResource(top)));
                                    RightPanel.bottom.setIcon(null);
                                    RightPanel.cost11.setVisible(false);
                                    RightPanel.cost12.setVisible(false);
                                    RightPanel.id1.setVisible(false);
                                    RightPanel.prod1.setVisible(false);
                                    RightPanel.cost1.setText(cost);
                                    RightPanel.addcart2.setVisible(false);
                                    RightPanel.id.setText(a);
                                    RightPanel.spinner2.setVisible(false);
                                } else {
                                    top = "/images/BIG/JPEG/" + a + ".jpg";
                                    RightPanel.top.setIcon(new ImageIcon(getClass().getResource(top)));
                                    RightPanel.bottom.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/MJ002.jpg")));
                                    RightPanel.cost1.setText(cost);
                                    RightPanel.id.setText(a);
                                    RightPanel.addcart2.setVisible(true);
                                    RightPanel.cost12.setVisible(true);
                                    RightPanel.id1.setVisible(true);
                                    RightPanel.prod1.setVisible(true);
                                    RightPanel.cost11.setVisible(true);
                                }
                            }
                        });


                    }
                    st.close();
                    con.close();
                } catch (Exception eq) {
                }
                RightPanel.jp.setVisible(true);
            }
        });
        panel.add(btn);
        btn1 = new JButton("Mens Wardrobe");
        btn1.setFont(f);
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                RightPanel.jp.removeAll();
                revalidate();
                uppers.setVisible(true);
                lowers.setVisible(true);
                panel2.setVisible(false);
                panel3.setVisible(true);
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(false);
                RightPanel.addcart2.setVisible(true);
                RightPanel.cost12.setVisible(true);
                RightPanel.id1.setVisible(true);
                RightPanel.prod1.setVisible(true);
                RightPanel.cost11.setVisible(true);
                RightPanel.spinner2.setVisible(true);

                RightPanel.top.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/MB006.jpg")));
                RightPanel.id.setText("MB006");
                RightPanel.cost1.setText("975");
                RightPanel.bottom.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/MJ002.jpg")));
                RightPanel.cost12.setText("850");
                RightPanel.id1.setText("MJ002");



            }
        });
        btn1.setBounds(0, 40, 250, 40);
        panel.add(btn1);
        btn2 = new JButton("Womens Wardrobe");
        btn2.setFont(f);
        btn2.setBounds(0, 80, 250, 40);
        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                RightPanel.jp.removeAll();
                revalidate();

                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(true);
                panel5.setVisible(false);
                panel6.setVisible(false);
                RightPanel.addcart2.setVisible(true);
                RightPanel.cost12.setVisible(true);
                RightPanel.id1.setVisible(true);
                RightPanel.prod1.setVisible(true);
                RightPanel.cost11.setVisible(true);
                RightPanel.spinner2.setVisible(true);
                RightPanel.top.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/FB006.jpg")));
                RightPanel.id.setText("FB006");
                RightPanel.cost1.setText("630");
                RightPanel.bottom.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/FJ002.jpg")));
                RightPanel.cost12.setText("1375");
                RightPanel.id1.setText("FJ002");
            }
        });
        panel.add(btn2);

        btn3 = new JButton("Baby Girls Wardrobe");
        btn3.setFont(f);
        btn3.setBounds(0, 120, 250, 40);
        btn3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                RightPanel.jp.removeAll();
                revalidate();

                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(false);
                panel5.setVisible(true);
                panel6.setVisible(false);
                RightPanel.addcart2.setVisible(false);
                RightPanel.cost12.setVisible(false);
                RightPanel.id1.setVisible(false);
                RightPanel.prod1.setVisible(false);
                RightPanel.cost11.setVisible(false);
                RightPanel.spinner2.setVisible(false);
                red.setVisible(true);
                blue.setVisible(true);
                black.setVisible(true);
                white.setVisible(true);
                pp.setVisible(true);
                RightPanel.top.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/BGB006.jpg")));
                RightPanel.bottom.setIcon(null);




            }
        });
        panel.add(btn3);
        btn4 = new JButton("Baby Boys Wardrobe");
        btn4.setFont(f);
        btn4.setBounds(0, 160, 250, 40);
        btn4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                RightPanel.jp.removeAll();
                revalidate();

                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(false);
                panel5.setVisible(false);
                panel6.setVisible(true);
                red.setVisible(true);
                RightPanel.addcart2.setVisible(false);
                RightPanel.spinner2.setVisible(false);
                RightPanel.cost12.setVisible(false);
                RightPanel.id1.setVisible(false);
                RightPanel.prod1.setVisible(false);
                RightPanel.cost11.setVisible(false);
                blue.setVisible(true);
                black.setVisible(true);
                white.setVisible(true);
                pp.setVisible(true);
                RightPanel.top.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/BBB006.jpg")));
                RightPanel.bottom.setIcon(null);




            }
        });
        panel.add(btn4);


        add(panel);
        setVisible(true);
    }

    public static void main(String arg[]) {
        new leftpanel();
    }
}
