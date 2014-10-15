/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class d extends JDialog {

    public String[] img = new String[50];
    public static JLabel label, id, id1, cost, cost1, total, total1, qty;
    public static JButton OK, remove, cancel, update;
    public static JPanel panel;
    public static JPanel jp = new JPanel();
    public static int cntr = 0, cnt;
    public static int j, sum = 0, cst;
    public static ImageIcon[] imageicon;
    public static JButton[] btn = new JButton[100];
    public static JSpinner spinner;

    d(JFrame dialog, String cc) {

        super(dialog, cc);

        try {

            Class.forName("com.mysql.jdbc.Driver");
            final Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            setSize(650, 650);
            setBackground(Color.white);
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
            setLayout(null);
            panel = new JPanel();
            panel.setBackground(Color.white);
            panel.setLayout(null);
            label = new JLabel(new ImageIcon(getClass().getResource("/images/cart.jpg")));
            label.setBounds(50, 50, 250, 250);
            j = 100;
            id = new JLabel("Product Id :");
            id1 = new JLabel("");
            cost = new JLabel("Cost :");
            cost1 = new JLabel();
            OK = new JButton("Ok");
            remove = new JButton("Remove Item");
            update = new JButton("Update");
            cancel = new JButton("Cancel");
            total = new JLabel("Total :");
            qty = new JLabel("Quantity:");
            spinner = new JSpinner();
            ite();
            try {
                Statement s;
                s = con.createStatement();

                String table = "SELECT SUM(amount) FROM billing";
                ResultSet rs = s.executeQuery(table);
                if (rs.next()) {
                    sum = Integer.parseInt(rs.getString(1));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            total1 = new JLabel(Integer.toString(sum));
            id.setBounds(400, 80, 150, 20);
            id1.setBounds(550, 80, 100, 20);
            cost.setBounds(400, 130, 150, 20);
            cost1.setBounds(550, 130, 100, 20);
            panel.setBounds(0, 0, 700, 700);
            OK.setBounds(150, 570, 150, 20);
            cancel.setBounds(350, 570, 150, 20);
            total.setBounds(400, 180, 150, 20);
            total1.setBounds(550, 180, 150, 20);
            qty.setBounds(400, 220, 150, 20);
            spinner.setBounds(550, 220, 30, 20);
            remove.setBounds(350, 280, 120, 20);
            update.setBounds(500, 280, 120, 20);
            remove.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        String idno = label.getText().trim();

                        cnt = RightPanel.cntr;
                        for (int p = 0; p < cnt; p++) {
                            if (btn[p].getText().equals(idno)) {

                                jp.remove(btn[p]);
                                //RightPanel.j = RightPanel.j - 100;

                                for (int q = p; q < cnt - 1; q++) {
                                    // btn[q + 1].setBounds(btn[q].getBounds());
                                    btn[q] = btn[q + 1];
                                    Rectangle a = btn[q].getBounds();
                                    a.x = a.x - 100;
                                    btn[q].setBounds(a);

                                }
                                for (int w = p; w < cnt; w++) {
                                    RightPanel.sel[w] = RightPanel.sel[w + 1];

                                }

                                RightPanel.cntr--;

                                jp.repaint();
                                try {
                                    Calendar cal = new GregorianCalendar();
                                    int month = cal.get(Calendar.MONTH);
                                    int year = cal.get(Calendar.YEAR);
                                    int day = cal.get(Calendar.DAY_OF_MONTH);
                                    String date = "Current date : " + day + "/" + (month + 1) + "/" + year;

                                    Statement st, st2, st3;
                                    ResultSet rs;
                                    st = con.createStatement();
                                    st2 = con.createStatement();
                                    st3 = con.createStatement();
                                    String table = "delete from billing where idnum like'" + idno + "'";
                                    String table2 = "delete from " + userinfo.username1 + " where idnum like '" + idno + "' && purchaseDate like '" + date + "'";
                                    String table3 = "SELECT SUM(amount) FROM billing";
                                    //System.out.println(table2);
                                    st.executeUpdate(table);
                                    st2.executeUpdate(table2);
                                    rs = st3.executeQuery(table3);
                                    if (rs.next()) {
                                        sum = Integer.parseInt(rs.getString(1));
                                    }
                                    total1.setText(Integer.toString(sum));
                                    validate();
                                } catch (Exception es) {
                                    System.out.println(es);
                                }

                            }
                            ite();
                        }
                    } catch (Exception er) {
                        System.out.println(er);
                    }
                }
            });
            Font font = new Font("ARIAL", Font.BOLD, 15);
            Font font1 = new Font("ARIAL", Font.BOLD, 15);
            update.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        if (id1.getText().trim().toString().equals("")) {
                        } else {
                        }

                        Statement s, s1, s3;
                        s = con.createStatement();
                        s1 = con.createStatement();
                        s3 = con.createStatement();
                        int cost = Integer.parseInt(cost1.getText().toString());
                        String table = "select quantity,cost from billing where idnum like '" + id1.getText().trim().toString() + "'";
                        ResultSet rs = s1.executeQuery(table);
                        if (rs.next()) {
                            int pqty = Integer.parseInt(rs.getString(1));
                            cst = Integer.parseInt(rs.getString(2));

                        }

                        int qty = Integer.parseInt(spinner.getValue().toString());

                        String updt = "update billing set quantity =" + qty + ",amount=" + cst * qty + " where idnum like '" + id1.getText().trim().toString() + "'";

                        s.executeUpdate(updt);
                        String table3 = "SELECT SUM(amount) FROM billing";
                        ResultSet rs3 = s3.executeQuery(table3);
                        while (rs3.next()) {
                            sum = Integer.parseInt(rs3.getString(1));
                        }
                        total1.setText(Integer.toString(sum));
                        validate();
                        JOptionPane.showMessageDialog(null, "Insertion Successful");
                    } catch (Exception e2) {
                        System.out.println(e2);
                    }
                }
            });


            id.setFont(font);
            id1.setFont(font1);
            cost.setFont(font);
            cost1.setFont(font1);
            total.setFont(font);
            total1.setFont(font1);
            qty.setFont(font);
            panel.add(id);
            panel.add(OK);
            panel.add(qty);
            panel.add(cancel);
            panel.add(id1);
            panel.add(cost);
            panel.add(cost1);
            panel.add(label);
            panel.add(remove);
            panel.add(update);
            panel.add(total);
            panel.add(total1);
            panel.add(spinner);
            jp.setBackground(Color.WHITE);
            jp.setLayout(null);
            OK.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setVisible(false);

                }
            });

            jp.setPreferredSize(new Dimension(10000, 10000));
            int v = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
            int h = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
            JScrollPane jsp = new JScrollPane(jp, h, v);
            jsp.setBackground(Color.white);
            jsp.setBounds(0, 380, 640, 150);
            add(jsp, BorderLayout.CENTER);
            add(panel);

        } catch (Exception e) {
            System.out.println(e);
        }
        setVisible(true);
    }

    public static void main(String arg[]) {
        new d(null, "Shopping Cart");
    }

    public void ite() {
     for(int as2=0;as2<RightPanel.cntr;as2++)
     {
         System.out.println(btn[as2].getText());
     }
    }
}
