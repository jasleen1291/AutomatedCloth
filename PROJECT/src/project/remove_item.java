/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

/**
 *
 * @author Balvinder
 */
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.LineBorder;
public class remove_item extends JDialog
{
    JPanel panel;
    JButton submit,cancel,browse;
    JLabel Id,preview;
    JTextField idfield;
  static  Boolean a=false;
remove_item(JFrame dialog,String cc)
{
super(dialog,cc);
try{
  final Connection con;
  Class.forName("com.mysql.jdbc.Driver");
  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");

setLayout(null);
setSize(500,350);
 Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2,
		size.height/2 - getHeight()/2);
        Font f=new Font("Arial",Font.PLAIN,15);
 panel=new JPanel();
panel.setBounds(0,0,550,450);
panel.setBackground(Color.white);
panel.setLayout(null);
Id=new JLabel("Enter Product ID:");
Id.setFont(f);
Id.setBounds(8,10,150,20);
panel.add(Id);
idfield=new JTextField("");
idfield.setBounds(6,37,190,25);
idfield.setBorder(LineBorder.createGrayLineBorder());
panel.add(idfield);
browse=new JButton("Browse");
browse.setBounds(6,80,80,25);
browse.setBorder(LineBorder.createGrayLineBorder());
panel.add(browse);
preview=new JLabel(" ");
preview.setLayout(null);
preview.setBorder(LineBorder.createGrayLineBorder());
preview.setBounds(230,20,250,250);
panel.add(preview);

submit=new JButton("Submit");
panel.add(submit);
submit.setEnabled(false);
browse.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               if(idfield.getText().equals(""))
                   JOptionPane.showMessageDialog(null, "Insert a value first");
               else
               {
                  
                        try {
                             String query="Select * from items where Id like '"+idfield.getText().toString().toUpperCase().trim()+"'";
                             Statement s;
                             ResultSet rs;
                            s=con.createStatement();
                           rs= s.executeQuery(query);
                            if(rs.next())
                            {
                                a=true;
                                preview.setIcon(new ImageIcon(getClass().getResource("/images/BIG/JPEG/" + idfield.getText().toString().trim()+ ".jpg")));
                                submit.setEnabled(true);
                                                                
                            }
                            
                        } catch (SQLException ex) {
                           System.out.println(ex);
                        }
                        if(a==false)
                        {
                            JOptionPane.showMessageDialog(null, "Selected Item does not exist");
                        }
               }
                   
            }
        });    
submit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                   String item=idfield.getText().toString().trim();
                   try
                   {
                       Statement s2;
                       String qu2="Delete  from items where Id like '"+item+"'";
                       s2=con.createStatement();
                       s2.executeUpdate(qu2);
                       JOptionPane.showMessageDialog(null, "Item removed");
                   }
                   catch(Exception ei)
                   {
                       System.out.println(ei);
                   }
                }
            });
submit.setBounds(30,240,80,25);
submit.setFont(f);

cancel=new JButton("Cancel");
cancel.setBounds(130,240,80,25);
cancel.setFont(f);
cancel.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
               setVisible(false);          }
    
});
panel.add(cancel);

add(panel);
        setVisible(true);

}
catch(Exception ex)
{
    System.out.println(ex);
}
}
public static void main(String arg[])
{
    new remove_item(null,"Remove Item");
}
}
