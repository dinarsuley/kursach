import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
 
public class Paint extends JPanel {
 
    double[] X;
    double x1, x2, y1, y2, step_x, step_y;
    double xg1, xg2;
    int WIDTH;
    int HEIGHT;
    int lastX;
    int lastY;
    

    func fun;
 
    
 
    Paint(double x1, double x2, double y1, double y2, String b) {
        
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.xg1 = x1;
        this.xg2 = x2;
        
        HEIGHT = 480;
        WIDTH = 640;
        
        step_x = 1;
        step_y = 1;
        
        lastX = 0;
        lastY = 0;
        
        fun = new func(b);
        frameOp();
 
    }
 
    public void frameOp() {
 
        JFrame JF = new JFrame("test_title");
        JF.setBounds(100, 100, WIDTH + 6, HEIGHT + 28);
        JF.setLayout(null);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setVisible(true);
        JF.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        JF.add(this);
        this.setBackground(Color.WHITE);
 
        MouseAdapter MA = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                lastX = evt.getX();
                lastY = evt.getY();
            }
 
            @Override
            public void mousePressed(MouseEvent evt) {
                lastX = evt.getX();
                lastY = evt.getY();
            }
 
            @Override
            public void mouseDragged(MouseEvent evt) {
                if (evt.getModifiers() == evt.BUTTON1_MASK) {
                    int newX = evt.getX();
                    int newY = evt.getY();
 
                    double dx = (x2 - x1) / WIDTH * (lastX - newX);
                    double dy = -(y2 - y1) / HEIGHT * (lastY - newY);
 
                    x1 += dx;
                    x2 += dx;
 
                    y1 += dy;
                    y2 += dy;
 
                    lastX = newX;
                    lastY = newY;
 
                    repaint();
                } else if (evt.getModifiers() == evt.BUTTON3_MASK) {
 
                    int newX = evt.getX();
                    int newY = evt.getY();
 
                    double dx = (x2 - x1) / WIDTH * (lastX - newX);
                    double dy = -(y2 - y1) / HEIGHT * (lastY - newY);
 
                    //x1 += dx;
                    x2 += dx;
 
                    y1 += dy;
                    //y2 += dy;
 
                    lastX = newX;
                    lastY = newY;
 
                    repaint();
                }
            }
        };
 
        addMouseListener(MA);
        addMouseMotionListener(MA);
 
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
 
                int r = e.getWheelRotation();
 
                double dx = (x2 - x1) / (10 + Math.abs(r + 1) / 2);
                double dy = (y2 - y1) / (10 + Math.abs(r + 1) / 2);
 
                x1 -= r * dx * lastX / WIDTH;
                x2 += r * dx * (WIDTH - lastX) / WIDTH;
                y1 -= r * dy * (HEIGHT - lastY) / HEIGHT;
                y2 += r * dy * lastY / HEIGHT;
 
                repaint();
 
            }
        });
 
 
    }
 
    @Override
    public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
 
        paintD(g);
 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

 
        paintF(g, Color.BLUE);
        paintF2(g, Color.RED);
 
    }
 
    public void paintD(Graphics g) {
        g.setColor(Color.GRAY);
 
        int OX = (int) (-x1 / (x2 - x1) * WIDTH);
        int OY = (int) (HEIGHT + y1 / (y2 - y1) * HEIGHT);
 
        for (int i = (int) Math.floor(x1 / step_x); i <= Math.floor(x2 / step_x); i++) {
            int positionX = (int) (-(x1 - step_x * i) / (x2 - x1) * WIDTH);
 
            g.drawLine(positionX, 0, positionX, HEIGHT);
 
            int positionY;
 
            if (OY < 12) {
                positionY = 10;
            } else if (OY > HEIGHT - 2) {
                positionY = HEIGHT - 2;
            } else {
                positionY = OY - 2;
            }
 
            int format = (int) (HEIGHT / 5 / (x2 - x1) / step_x);
 
            if (step_x * i == (int) (step_x * i)) {
                format = 0;
            } else if (format > 10) {
                format = 10;
            }
 
            g.drawString(String.format("%." + String.valueOf(format) + "f", i * step_x), positionX + 2, positionY);
        }
 
        for (int i = (int) Math.floor(y1 / step_y); i <= Math.floor(y2 / step_y); i++) {
            int positionY = (int) (HEIGHT + (y1 - step_y * i) / (y2 - y1) * HEIGHT);
 
            g.drawLine(0, positionY, WIDTH, positionY);
 
            int positionX;
 
            int format = (int) (HEIGHT / 5 / (x2 - x1) / step_y);
 
            if (step_y * i == (int) (step_y * i)) {
                format = 0;
            } else if (format > 10) {
                format = 10;
            }
 
            String formated_value = String.format("%." + String.valueOf(format) + "f", i * step_y);
 
            int len = (int) new TextLayout(formated_value, g.getFont(), new FontRenderContext(null, true, true)).getBounds().getWidth();
 
            if (OX < 1) {
                positionX = 2;
            } else if (OX > WIDTH - 7 - len) {
                positionX = WIDTH - 5 - len;
            } else {
                positionX = OX + 2;
            }
 
            g.drawString(formated_value, positionX, positionY - 1);
        }
 
        g.setColor(Color.BLACK);
        g.fillRect(OX - 1, 0, 3, HEIGHT);
        g.fillRect(0, OY - 1, WIDTH, 3);
 
    }
    

    public void paintF(Graphics g, Color c) {       
        double xn = -3;
        double xk = 3;
        double dx = 0.0001;
        int gx1, gx2, q1, q2;
        g.setColor(c);

        Font fnt = new Font("default", Font.BOLD, 16);
        g.setFont(fnt);
        g.drawString("f=y(x); e=0.0001", 50, 50);

        while(xn <= xk){
            if(Math.abs(xn) > 1){
                q1 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (fun.Y(xn) - y1));
                gx1 = (int)(-(x1 - step_x * xn) / (x2 - x1) * WIDTH);
                double i2 = fun.Y(xn+=dx);
                q2 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (i2 - y1));
                gx2 = (int)(-(x1 - step_x * (xn+dx)) / (x2 - x1) * WIDTH);
                g.drawLine(gx1, q1, gx2, q2);
            } 
            xn+= dx;
        }
    }
    public void paintF2(Graphics g, Color c) {       
        double xn = -3;
        double xk = 3;
        double dx = 0.0001;
        int gx1, gx2, q1, q2;
        g.setColor(c);

        Font fnt = new Font("default", Font.BOLD, 16);
        g.setFont(fnt);
        g.drawString("f=z(x, b)", 50, 70);

        while(xn <= xk){
            if(Math.abs(xn) > 1){
                q1 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (fun.Z(xn) - y1));
                gx1 = (int)(-(x1 - step_x * xn) / (x2 - x1) * WIDTH);
                double i2 = fun.Z(xn+=dx);
                q2 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (i2 - y1));
                gx2 = (int)(-(x1 - step_x * (xn+dx)) / (x2 - x1) * WIDTH);
                g.drawLine(gx1, q1, gx2, q2);
            } 
            xn+= dx;
        }
    }
}
