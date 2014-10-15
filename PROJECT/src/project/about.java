/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author jasleen
 */
import java.awt.*;
import javax.swing.*;
public class about extends JDialog
{
    JPanel panel;
     JLabel label,label1;

about(JFrame dialog,String cc)
{
    super(dialog,cc);
    setLayout(null);
    setSize(400,400);
     Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2,
		size.height/2 - getHeight()/2);
String lyrics="<html><b>Version 2011©Clothing Corp.</b><br>"+
        "All rights reserved.<br><br>"+
        ".</html>";
    panel=new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.white);
    panel.setBounds(0,0,400,400);
    label=new JLabel(" ");
    ImageIcon i=new ImageIcon(getClass().getResource("/images/logo.jpg"));
    label.setIcon(i);
    label.setBounds(0,10,300,250);
    label.setLayout(null);
    panel.add(label);
    label1 = new JLabel(lyrics);
        label1.setFont(new Font("Arial", Font.PLAIN, 12));
        label1.setBounds(5,250,180,100);
        label1.setForeground(Color.DARK_GRAY);
        label1.setLayout(null);
panel.add(label1);
    add(panel);
    setVisible(true);
}
public static void main(String arg[])
{
    new about(null,"About");
}
}
