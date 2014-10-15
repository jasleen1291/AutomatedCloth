/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Balvinder
 */
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class recent extends JDialog implements Printable {

    public static JPanel panel;
    JTable table;
    JScrollPane scrollp;
    JLabel label;
    JTextField txtf;
    JButton btn, btn1;
    DefaultTableModel deftm;

    recent(JFrame dialog, String cc) {
        super(dialog, cc);
        setLayout(null);
        setSize(700, 650);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2,
                size.height / 2 - getHeight() / 2);


        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 700, 700);
        panel.setBackground(Color.white);

        String col[] = {"", "", ""};
        table = new JTable();
        deftm = new DefaultTableModel(
                new Object[][]{}, new String[]{"Purchase", "Quantity", "Cost", "Date"});
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
        btn = new JButton("Print");
        btn.setBounds(170, 470, 100, 30);
        btn.setBorder(LineBorder.createGrayLineBorder());
        panel.add(btn);
        btn1 = new JButton("Cancel");
        btn1.setBounds(320, 470, 100, 30);
        btn1.setBorder(LineBorder.createGrayLineBorder());
        panel.add(btn1);

        add(panel);
        try {
            int i = 0;
            Connection con;
            Statement st;
            ResultSet rs;
            Class.forName("com.mysql.jdbc.Driver");
            //       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            st = con.createStatement();
            String user = userinfo.username1;
            String Query;
            if (userinfo.username1.equals("admin")) {
                Query = "select * from billing1";
            } else {
                Query = "select * from " + userinfo.username1;
            }
            rs = st.executeQuery(Query);
            while (rs.next()) {
                deftm.addRow(new Object[][]{});

                table.setValueAt(rs.getString("idnum"), i, 0);
                table.setValueAt(rs.getString("quantity"), i, 1);
                table.setValueAt(rs.getString("cost"), i, 2);
                table.setValueAt(rs.getString("purchaseDate"), i, 3);
                // int a=(int)table.getValueAt(i,1);

                i++;
            }

        } catch (Exception es) {
            System.out.println(es);
        }
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                printtext();
            }
        });


        setVisible(true);
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
        job.setPrintable(this);
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
}