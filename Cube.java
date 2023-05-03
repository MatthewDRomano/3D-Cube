import javax.swing.*;
import java.awt.*;

public class Cube extends JPanel
{
    private Point loc;
    private int len, height;
    private Point[] points = new Point[4];
    private double degreesRotated = 0;
    private boolean turningRight, turningLeft;

    public Cube(Point location, int x, int y)
    {
        loc = location;
        len = x;
        height = y;
        setBackground(Color.black);
        setBounds(loc.x, loc.y, x+1, y+1+150);//bounds = box around ellipse
        plotPoints();
    }

    public void setRightStatus(Boolean status) { turningRight = status; }
    public void setLeftStatus(Boolean status) { turningLeft = status; }
    public boolean getRightStatus() { return turningRight; }
    public boolean getLeftStatus() { return turningLeft; }

    public void plotPoints()
    {
        Point center = new Point(len/2, height/2);
        double theta = (Math.PI * 2 / 4) + Math.toRadians(degreesRotated);

        for (int i = 0; i < 4; i++)
        {
            int x = (int)((len/2) * Math.cos(theta)), y = (int)((height/2) * Math.sin(theta));
            points[i] = new Point(center.x + x, center.y - y);
            theta += (Math.PI * 2 / 4);
        }
    }

    public void turn(boolean turnRight)
    {
        if (turnRight) degreesRotated += 0.00005;
        else degreesRotated -= 0.00005;
        plotPoints();  
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.white);

        for (int i = 1; i < 4; i++)
        {
            g.drawLine(points[i-1].x, points[i-1].y, points[i].x, points[i].y);
            g.drawLine(points[i-1].x, points[i-1].y, points[i-1].x, points[i-1].y+120);
        }
        g.drawLine(points[3].x, points[3].y, points[0].x, points[0].y);  
        g.drawLine(points[3].x, points[3].y, points[3].x, points[3].y+120);  
        for (int i = 1; i < 4; i++)
        {
            g.drawLine(points[i-1].x, points[i-1].y+120, points[i].x, points[i].y+120);
        }
        g.drawLine(points[3].x, points[3].y+120, points[0].x, points[0].y+120);
        
            
        g.setColor(Color.green);//
        if (turningLeft || turningRight) 
        {
            g.drawOval(0,0, len-1, height-1);//
            g.drawOval(0,120, len-1, height-1);//
        }
    }
}
