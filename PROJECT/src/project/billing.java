/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.print.*;
import java.util.*;

public class billing extends JDialog implements Printable {

    JPanel panel, panel2;
    JTable table;
    public JScrollPane scrollp;
    JLabel label, billno;
    JTextField txtf, bill;
    JButton save, print;
    DefaultTableModel deftm;
    String billn;

    billing(JFrame dialog, String cc) {
        super(dialog, cc);
        setLayout(null);
        setSize(700, 650);
        try {
            final Connection con;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
            panel = new JPanel();
            panel2 = new JPanel();
            billno = new JLabel("Bill number");
            bill = new JTextField("1");
            panel.setLayout(null);
            panel.setBounds(0, 0, 700, 500);
            panel2.setBounds(0, 500, 700, 200);
            panel.setBackground(Color.white);
            panel2.setBackground(Color.white);
            panel2.setLayout(null);
            table = new JTable();
            deftm = new DefaultTableModel(new Object[][]{}, new String[]{"Purchase", "Quantity", "Cost", "Amount"});
            table.setModel(deftm);
            scrollp = new JScrollPane(table);
            scrollp.setBounds(70, 70, 500, 275);
            panel.add(scrollp, BorderLayout.CENTER);
            label = new JLabel("Total Cost:");
            label.setBounds(250, 370, 100, 30);
            panel.add(label);
            txtf = new JTextField();
            txtf.setBounds(375, 370, 150, 30);
            txtf.setBorder(LineBorder.createGrayLineBorder());
            panel.add(txtf);
            save = new JButton("Save");
            save.setBounds(170, 20, 100, 30);
            save.setBorder(LineBorder.createGrayLineBorder());
            panel2.add(save);
            print = new JButton("Print");
            print.setBounds(320, 20, 100, 30);
            print.setBorder(LineBorder.createGrayLineBorder());
            billno.setBounds(70, 40, 100, 15);
            bill.setBounds(170, 40, 20, 20);
            panel.add(bill);
            panel.add(billno);
            panel2.add(print);
            print.setEnabled(false);

            add(panel);
            add(panel2);
            try {
                Statement st1;
                ResultSet rs1;
                st1 = con.createStatement();
                String query = "select max(billno)+1 from billing1";
                rs1 = st1.executeQuery(query);
                if (rs1.next()) {
                    billn = rs1.getString(1);
                    if (billn != null) {
                        bill.setText(billn);
                    } else {
                        bill.setText("1");
                    }
                }

            } catch (Exception ae) {
                System.out.println(ae);
            }

            try {
                int i = 0;

                Statement st, st2;
                ResultSet rs, rs2;
                st2 = con.createStatement();
                st = con.createStatement();
                String Query = "select * from billing";
                String Query2 = "select Sum(amount) from billing";
                rs = st.executeQuery(Query);
                rs2 = st2.executeQuery(Query2);
                while (rs.next()) {
                    deftm.addRow(new Object[][]{});

                    table.setValueAt(rs.getString("idnum"), i, 0);
                    table.setValueAt(rs.getString("cost"), i, 2);
                    table.setValueAt(rs.getString("quantity"), i, 1);
                    table.setValueAt(rs.getString("amount"), i, 3);
                    i++;
                }
                while (rs2.next()) {
                    txtf.setText(rs2.getString(1));
                }

            } catch (Exception es) {
                System.out.println(es);
            }
            save.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        Calendar cal = new GregorianCalendar();
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        final String date;
                        date = " " + day + "/" + (month + 1) + "/" + year;
                        Statement st;
                        ResultSet rs;
                        String billn2 = bill.getText().trim().toString();


                        st = con.createStatement();
                        String table = "insert into " + userinfo.username1 + "(billno ,idnum  ,quantity ,cost ,purchaseDate ) select " + billn2 + ",billing.idnum, billing.quantity, billing.cost,'" + date + "' from billing";
                        st.executeUpdate(table);

                    } catch (Exception es) {
                        System.out.println(es);
                    }
                    try {
                        Calendar cal = new GregorianCalendar();
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        final String date;
                        date = " " + day + "/" + (month + 1) + "/" + year;
                        Statement st;
                        String billn2 = bill.getText().trim().toString();
                        st = con.createStatement();
                        String table = "insert into billing1 (billno ,idnum  ,quantity ,cost ,purchaseDate ,user) select " + billn2 + ",billing.idnum, billing.quantity, billing.cost,'" + date + "', '"+userinfo.username1+" ' from billing";
                        st.executeUpdate(table);
                        
                    } catch (Exception es) {
                        System.out.println(es);
                    }
                    print.setEnabled(true);
                }
            });
            print.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    printtext();
                }
            });
            {
            }

            bill.setEditable(false);
            txtf.setEditable(false);
            setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            System.out.println("0");
            return Printable.NO_SUCH_PAGE;
        }
        System.out.println("4");
        RepaintManager mgr = RepaintManager.currentManager(table);
        mgr.setDoubleBufferingEnabled(false);
        System.out.println("5");
        final Graphics2D graphics2D = (Graphics2D) graphics;
        System.out.println("6");
        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        System.out.println("7");
        panel.paintComponents(graphics);

        System.out.println("8");
        //printtext();

        mgr.setDoubleBufferingEnabled(true);

        return Printable.PAGE_EXISTS;
    }

    public void printtext() {
        final PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(billing.this);
        System.out.println("1");
        try {
            if (job.printDialog()) {
                System.out.println("2");
                job.print();
                System.out.println("3");
            } else {
            }
        } catch (PrinterException ex) {
            throw new RuntimeException("Printing Failed.", ex);
        }
    }

    public static void main(String arg[]) {
        new billing(null, "Billing");
    }
}
