import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Color;

public class Calculator{
    public static void main(String[] args) {

        JFrame calcFrame = new JFrame();
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setTitle("Calculator by Hung");
        calcFrame.setSize(380,500);
        calcFrame.setLocationRelativeTo(null);
        calcFrame.setResizable(false);
        calcFrame.setLayout(new FlowLayout(FlowLayout.CENTER,5,30));
        calcFrame.getContentPane().setBackground(new Color(116,223,255));
        Screen screen =  new Screen();
        calcFrame.add(screen);
        calcFrame.add(new KeyBoard(screen));
        calcFrame.setVisible(true);
    }
}