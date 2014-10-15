/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author Balvinder
 */
public class add_item extends JDialog {

    JPanel panel;
    JLabel label, label1, label2, label3, label4, label5, label6;
    JButton btn, btn1, btn2;
    JTextField txtf, txtf2, prid;
    public JComboBox combobx, combobx1;
    public static Boolean d = false;
    static int j = 10, i = 0;
    public static JFileChooser chooser;
    public static BufferedImage cpimg;
    public static Image image;
    public static Boolean bool = false;
    public static Connection con;

    add_item(JFrame dialog, String cc) {
        super(dialog, cc);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
            setLayout(null);
            setSize(550, 450);
            // setVisible(true);
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation(size.width / 2 - getWidth() / 2,
                    size.height / 2 - getHeight() / 2);
            Font f = new Font("Arial", Font.PLAIN, 15);
            panel = new JPanel();
            panel.setBounds(0, 0, 550, 450);
            panel.setBackground(Color.white);
            panel.setLayout(null);
            btn = new JButton("Select Image");
            btn.setFont(f);
            btn.setBounds(8, 25, 130, 25);

            panel.add(btn);


            label1 = new JLabel(" ");
            label1.setLayout(null);
            label1.setBorder(LineBorder.createGrayLineBorder());
            label1.setBounds(250, 20, 250, 250);
            panel.add(label1);
            label2 = new JLabel("Category:");
            label2.setBounds(8, 75, 70, 20);
            label2.setFont(f);
            panel.add(label2);
            combobx = new JComboBox();
            combobx.addItem("Men");
            combobx.addItem("Women");
            combobx.addItem("BabyGirl");
            combobx.addItem("BabyBoy");
            combobx.setBounds(8, 99, 140, 25);
            combobx.setBorder(LineBorder.createGrayLineBorder());
            panel.add(combobx);
            label3 = new JLabel("Colour:");
            label3.setFont(f);
            label3.setBounds(8, 140, 70, 20);
            panel.add(label3);
            combobx1 = new JComboBox();
            combobx1.addItem("Black");
            combobx1.addItem("Blue");
            combobx1.addItem("Purple");
            combobx1.addItem("Red");
            combobx1.addItem("White");
            combobx1.addItem("Jean");
            combobx1.setBounds(8, 165, 140, 25);
            combobx1.setBorder(LineBorder.createGrayLineBorder());
            combobx1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (combobx.getSelectedItem().toString().trim().equals("BabyGirl") || combobx.getSelectedItem().toString().trim().equals("BabyBoy")) {
                        if (combobx1.getSelectedItem().toString().trim().equals("Jean")) {
                            JOptionPane.showMessageDialog(rootPane, "Invalid Type");
                            combobx1.setSelectedItem("Black");
                        }
                    }
                }
            });
            panel.add(combobx1);
            label4 = new JLabel("Product ID: ");
            label4.setFont(f);
            label4.setBounds(8, 205, 90, 20);
            panel.add(label4);
            prid = new JTextField("");
            prid.setBounds(108, 205, 120, 25);
            panel.add(prid);
            label5 = new JLabel("Cost:");
            label5.setFont(f);
            label5.setBounds(8, 275, 90, 20);
            panel.add(label5);
            label6 = new JLabel("Rs.");
            label6.setFont(f);
            label6.setBounds(8, 300, 70, 20);
            panel.add(label6);
            txtf2 = new JTextField("0");
            txtf2.setBounds(40, 300, 120, 25);
            txtf2.setBorder(LineBorder.createGrayLineBorder());
            panel.add(txtf2);


            btn1 = new JButton("Submit");
            btn1.setFont(f);
            btn1.setBounds(210, 320, 100, 25);
            panel.add(btn1);
            btn1.setEnabled(false);
            btn2 = new JButton("Cancel");
            btn2.setFont(f);
            btn2.setBounds(320, 320, 100, 25);
            btn2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            panel.add(btn2);
            add(panel);
            setVisible(true);
        } catch (Exception e2) {
            System.out.println(e2);
        }
        btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.showOpenDialog(new JDialog());
                try {
                    File file = chooser.getSelectedFile();
                    image = ImageIO.read(file);
                    cpimg = bufferImage(image, BufferedImage.TYPE_INT_RGB);
                    label1.setIcon(new ImageIcon(cpimg));
                    btn1.setEnabled(true);
                } catch (Exception e4) {
                }
            }
        });
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String proid = new String();
                    proid = prid.getText().toUpperCase();
                    String cost = new String();
                    cost = txtf2.getText();
                    if (proid.equals("") || cost.equals("")) {
                        bool = false;
                        JOptionPane.showMessageDialog(null, "Product Id and Cost are Mandatory fields");
                    }
                    try {
                        String Query = "Select * from items where Id like '" + prid.getText() + "'";

                        Statement s;
                        s = con.createStatement();
                        ResultSet rs = s.executeQuery(Query);
                        if (rs.next()) {
                            bool = false;
                            JOptionPane.showMessageDialog(null, "Product Id already assigned\nChoose a different Product id");
                        } else {
                            try {
                                String Query2 = "Insert into items value ('"   + prid.getText().toUpperCase() + "' ,'" +combobx.getSelectedItem().toString().trim() + "','"+ combobx1.getSelectedItem().toString().trim() + "','" + txtf2.getText().trim() + "')";
                                System.out.println(Query2);
                                Statement s2;
                                s2 = con.createStatement();
                                s2.executeUpdate(Query2);
                                Graphics g = cpimg.createGraphics();
                                cpimg = createResizedCopy(image, 300, 300, true);
                                File f1 = new File("src/images/BIG/JPEG/" + proid + ".jpg");
                                f1.createNewFile();
                                ImageIO.write(cpimg, "jpg", f1);
                                g = cpimg.createGraphics();
                                cpimg = createResizedCopy(image, 70, 70, true);
                                File f2 = new File("src/images/SMALL/" + proid + ".jpg");
                                 f2.createNewFile();
                                ImageIO.write(cpimg, "jpg", f2);
                                JOptionPane.showMessageDialog(null, "Item added to database");
                            } catch (Exception a) {
                                System.out.println(a);
                            }
                        }

                    } catch (Exception r) {
                        System.out.print(r);
                    }
//                    
                } catch (Exception e2) {
                }
            }
        });
    }

    public static void main(String arg[]) {
        new add_item(null, "Add Item");
    }

    public static BufferedImage bufferImage(Image image, int type) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);

        return bufferedImage;
    }

    public static BufferedImage createResizedCopy(Image originalImage,
            int scaledWidth, int scaledHeight,
            boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
