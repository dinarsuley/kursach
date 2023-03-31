import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class EnterF extends JFrame {
 
    String b = "";
    double x1 = -5;
    double x2 = 5;
    double y1 = -5;
    double y2 = 5;
    TextField Tx1;
    TextField Tx2;
    TextField Tf;
    JFrame JF;
    JPanel JP;
    JButton bt;
    JLabel er;
 
    EnterF() {
        JF = new JFrame();
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JP = new JPanel();
        JF.add(JP);
        JP.setBackground(Color.green);
        JP.setLayout(null);
        bt = new JButton("GO");
        JF.setSize(260, 260);
        JF.setTitle("Строим графики");
        Tf = new TextField();
        Tx1 = new TextField("", 40);
        Tx2 = new TextField("", 40);
        Tf.setLocation(50, 60);
        Tf.setSize(200, 20);

        JP.add(Tf);
 
 
        JLabel Lf = new JLabel("Введите b:");
        JLabel Lfx = new JLabel("b =");
        JLabel Lx1 = new JLabel("X от:");
        JLabel Lx2 = new JLabel("до:");
 
        Tx1.setLocation(60, 100);
        Tx2.setLocation(180, 100);
        Tx1.setSize(50, 20);
        Tx2.setSize(50, 20);
        JP.add(Tx1);
        JP.add(Tx2);

        Lf.setLocation(20, 20);
        Lf.setSize(140, 20);
        JP.add(Lf);
 
        Lfx.setLocation(10, 60);
        Lfx.setSize(40, 20);
        JP.add(Lfx);

        Lx1.setLocation(14, 100);
        Lx1.setSize(40, 20);
        JP.add(Lx1);
 
        Lx2.setLocation(150, 100);
        Lx2.setSize(40, 20);
        JP.add(Lx2);
 
        bt.setLocation(190, 180);
        bt.setSize(60, 50);
        JP.add(bt);
 
 
 
        er = new JLabel("");
        er.setLocation(10, 210);
        er.setSize(170, 20);
        JP.add(er);
 
        JF.setVisible(true);
        JF.setResizable(false);
 
 
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
 
 
 
                b = Tf.getText();
                if (!b.equals("")) {
                    set("");
                    try {
                        if (x1 < x2) {
                            set("");
                            try {
                                if (y1 < y2) {
                                    set("");
                                    set("УРА!");
                                    try {
                                        Paint p = new Paint(x1, x2, y1, y2, b);
                                    } catch (Exception w3) {
                                        set("класс Paint - говно!");
                                        System.out.println(w3);
                                    }
                                } else {
                                    set("некорректный интервал Y");
                                }
                            } catch (Exception w1) {
                                set("некорректные координаты");
                            }
                        } else {
                            set("некорректный интервал X");
                        }
 
                    } catch (Exception w2) {
                        set("некорректные координаты");
                    }
                } else {
                    set("ВВедите функцию");
                }
 
            }
        });
    }
 
    public void set(String s) {
        er.setText(s);
    }

}