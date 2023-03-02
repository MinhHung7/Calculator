import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class KeyBoard extends JPanel implements ActionListener{
    Queue<Queue<Integer>>queue;
    JButton[] button = new JButton[20];
    String[] name = {"OFF","D","C","ON",
                     "7","8","9","*",
                     "4","5","6","-",
                     "1","2","3","+",
                     "0",".","/","="};
    Screen screen;
    KeyBoard (Screen screen){
        this.screen =screen;
        this.setLayout(new GridLayout(5,4,10,10));
        this.setPreferredSize(new Dimension(340,260));
        this.setBackground(new Color(247,137,255,100));
        this.setOpaque(false);

        for(int i=0;i<20;++i){
            button[i] = new JButton();
            button[i].setText(name[i]);
            button[i].setPreferredSize(new Dimension(50,200));
            //button[i].setFont(new Font("Arial",Font.BOLD,20));
            if(i==19 || i==3){
                button[i].setBackground(Color.green);
                button[i].setForeground(Color.white);
                button[i].setFont(new Font("Arial",Font.BOLD,22));
            }
            else if(i==0){
                button[i].setBackground(Color.lightGray);
                button[i].setFont(new Font("Arial",Font.BOLD,22));
            }
            else if(i==2){
                button[i].setForeground(Color.red);
                button[i].setBackground(Color.white);
                button[i].setFont(new Font("Arial",Font.BOLD,22));
            }
            else if(i==7 || i==11 || i== 15 || i==18){
                button[i].setForeground(Color.green);
                button[i].setBackground(Color.white);
                button[i].setFont(new Font("Arial",Font.BOLD,25));
            }
            else{
                button[i].setBackground(Color.white);
                button[i].setFont(new Font("Arial",Font.BOLD,22));
            }
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            this.add(button[i]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button[0]){
            for(int  i=0;i<20;++i){
                if(i!=3){
                    button[i].setEnabled(false);
                    screen.result.setText("");
                    screen.screen.setText("");
                }
            }
        }
        else if(e.getSource()==button[3]){
            for(int  i=0;i<20;++i){
                button[i].setEnabled(true);
                screen.result.setText("");
                screen.screen.setText("");
            }
        }
        else if(e.getSource()==button[1]){
            if(screen.screen.getText().equals("")==false){
                screen.screen.setText(screen.screen.getText().substring(0,screen.screen.getText().length()-1));
            }
        }
        else if(e.getSource()==button[2]){
            screen.screen.setText("");
            screen.result.setText("");
        }
        else if(e.getSource()==button[19]){
            if(screen.screen.getText().charAt(screen.screen.getText().length()-1)=='+' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='-' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='*' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='/'){
                        new JOptionPane().showConfirmDialog(null,"Something went wrong!!", "Calculator",-1);
            }
            else{
                tinhToan(screen.screen.getText());
            }
        }
        else if(e.getSource()==button[7] || e.getSource()==button[11] || e.getSource()==button[15] || e.getSource()==button[18]){
            if(screen.screen.getText().equals("")==true){
                new JOptionPane().showConfirmDialog(null,"Something went wrong!!", "Calculator",-1);
            }
            else{
                if(screen.screen.getText().charAt(screen.screen.getText().length()-1)=='+' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='-' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='*' ||
                    screen.screen.getText().charAt(screen.screen.getText().length()-1)=='/'){
                    screen.screen.setText(screen.screen.getText().substring(0,screen.screen.getText().length()-1));
                }
                if(e.getSource()==button[7]) screen.screen.setText(screen.screen.getText()+name[7]);
                else if(e.getSource()==button[11]) {
                    screen.screen.setText(screen.screen.getText()+name[11]);
                }
                else if(e.getSource()==button[15]) screen.screen.setText(screen.screen.getText()+name[15]);
                else  screen.screen.setText(screen.screen.getText()+name[18]);
            }
        }
        else if(e.getSource()==button[17]){
            String text="";
            if(screen.screen.getText().equals("")==true ||
            screen.screen.getText().charAt(screen.screen.getText().length()-1)=='-' ||
            screen.screen.getText().charAt(screen.screen.getText().length()-1)=='*' ||
            screen.screen.getText().charAt(screen.screen.getText().length()-1)=='/' ||
            screen.screen.getText().charAt(screen.screen.getText().length()-1)=='+'){
                text+="0.";
            }
            else{
                text+=".";
            }
            screen.screen.setText(screen.screen.getText()+text);
        }
            
        for(int i=4;i<=16;++i){
            if(i!=7 && i!=11 && i!=15 && e.getSource()==button[i]){
                screen.screen.setText(screen.screen.getText()+name[i]);
            }
        }
    }
    public void tinhToan(String s){
        ArrayList<Queue<Double>>list = new ArrayList<Queue<Double>>();
        Queue<Double>queue = new LinkedList<>();
        String number="";
        boolean dauChia=false;
        for(int i=0;i<s.length();++i){
            if(s.charAt(i)=='+'||s.charAt(i)=='-'){
                Double num = Double.parseDouble(number);
                if(dauChia==true){
                    num = Math.pow(num,-1);
                    dauChia=false;
                }
                queue.add(num);
                Queue<Double>temp = new LinkedList<>(queue);
                list.add(temp);
                queue.clear();
                if(s.charAt(i)=='-'){
                    number="-";
                }
                else number="";
            }
            else if (s.charAt(i)=='*'||s.charAt(i)=='/'){
                double num = Double.parseDouble(number);
                if(dauChia==true){
                    num=Math.pow(num,-1);
                    dauChia=false;
                }
                queue.add(num);
                number="";
                if(s.charAt(i)=='/'){
                    dauChia=true;
                }
            }
            else{
                number+=s.charAt(i);
            }
        }
        double num = Double.parseDouble(number);
        if(dauChia==true){
            num=Math.pow(num,-1);
            dauChia=false;
        }
        queue.add(num);
        Queue<Double>temp = new LinkedList<>(queue);
        list.add(temp);
        queue.clear();
        double result=0;
        for(int i=0;i<list.size();++i){
            double res=1;
            while(list.get(i).isEmpty()==false){
                res=res*list.get(i).peek();
                list.get(i).remove();
            }
            result=result+res;
        }
        screen.result.setText(result+"");
    }
}
