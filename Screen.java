import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;

public class Screen extends JPanel{
    JTextField result = new JTextField();
    JTextField screen = new JTextField();
    Screen(){
        
        screen.setPreferredSize(new Dimension(340,40));
        screen.setBackground(Color.white);
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setFont(new Font("Arial",Font.BOLD,22));
        screen.setText("");
        screen.setEditable(false);
        
        result.setPreferredSize(new Dimension(340,70));
        result.setBackground(Color.white);
        result.setHorizontalAlignment(SwingConstants.RIGHT);
        result.setEditable(false);
        result.setForeground(Color.red);
        result.setFont(new Font("Arial",Font.BOLD,27));
        result.setText("");

        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setPreferredSize(new Dimension(340,120));
        this.setBackground(new Color(247,137,255,100));
        this.setOpaque(false);
        this.add(screen);
        this.add(result);
    }
    public void turnOff(){
        screen.setText("");
        result.setText("");
    }
}