/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

/**
 *
 * @author Balvinder
 */
import java.awt.*;
import javax.swing.*;
public class select_item extends JDialog
{
 public static  JPanel panel;
    JScrollPane scrollp;
    public static JButton [] items=new JButton[50];
select_item(JFrame dialog,String cc)
{
    setLayout(null);
    setVisible(false);
    setSize(350,250);
    Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2,
		size.height/2 - getHeight()/2); 
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(500,500));
        scrollp=new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollp.setBounds(0,0,340,220);
        add(scrollp,BorderLayout.CENTER);
   
}
public static void main(String ar[])
{
    new select_item(null,"Select Image");
}
}
