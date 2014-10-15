/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
public class progressbar extends JWindow{
 JPanel panel;
 JProgressBar progressbar;
 progressbar()
    {
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 350);
        panel.setBackground(Color.white);
        progressbar = new JProgressBar(0, 1000);
        progressbar.setStringPainted(true);
        Color n=new Color(0,170,153);
        ImageIcon a=new ImageIcon(getClass().getResource("/images/pgbr.jpg"));
        JLabel l=new JLabel(a);
        l.setLayout(null);
        l.setBounds(0, 0, 450, 350);


        progressbar.setLayout(null);
        progressbar.setBounds(0, 150, 450, 15);
        progressbar.setBackground(n);
        progressbar.setForeground(Color.WHITE);

        panel.add(l);
        progressbar.setBorder(LineBorder.createGrayLineBorder());
        l.add(progressbar);


        add(panel);
         setSize(450, 350);
        setVisible(true);
         Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2,
                size.height / 2 - getHeight() / 2);
        call();
         

    }

    void call() {
        int x = 0;
        while (x <=1000) {
            try {
                Thread.sleep(50);
                progressbar.setValue(x);
                x += 25;
            } catch (Exception ex) {
            }
        }
if(x>=1000)
{
    setVisible(false);
}
    }
}
