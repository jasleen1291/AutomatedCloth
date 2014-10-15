/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 *
 * @author Chawla
 */
public class array extends  JDialog{
public static JPanel a;
public static JButton a1=new JButton();
    array()

    {
    setVisible(true);
    setLayout(null);
    setSize(150,150);
     a=new JPanel();
    a.setBounds(0,0,100,100);
    a.setLayout(null);
    a1=new JButton("jkd");
    a1.setBounds(0,0,100,100);
    a.add(a1);
    add(a);

}
}
