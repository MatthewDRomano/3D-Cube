import javax.swing.*;
import java.awt.*;

public class Client
{
    static JFrame gui;
    public static void main(String[] args)
    {
        JPanel screen = createDisplay();
        Cube e = new Cube();
        //Ellipse e2 = new Ellipse(new Point(350,300), 200, 50);//
        gui.addKeyListener(new KeyListenerMod(e));
        //gui.addKeyListener(new KeyListenerMod(e2));//
        screen.add(e); 
        //screen.add(e2); 


        while (true) // 'game' loop
        {
            if (e.turningRight) e.rotate(Axis.Y, true);
            if (e.turningLeft) e.rotate(Axis.Y, false);
            if (e.turningDown) e.rotate(Axis.X, true);
            if (e.turningUp) e.rotate(Axis.X, false);
            if (e.faceLeft) e.rotate(Axis.Z, true);
            if (e.faceRight) e.rotate(Axis.Z, false);

            //if (e.getUpStatus()) e.rotate2(true);//
            //else if (e.getDownStatus()) e.rotate2(false);//

            e.repaint();

            //if (e2.getRightStatus()) e2.turn(true);//
            //else if (e2.getLeftStatus()) e2.turn(false);//
            //e2.repaint();//
        }
    }
    public static JPanel createDisplay()
    {
        gui = new JFrame()
        {
            {
                setTitle("Cube");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(858, 525);
                setResizable(false);
            }
        };

        JPanel screen = new JPanel();
        screen.setBackground(Color.black);
        screen.setLayout(null);

        gui.add(screen);
        gui.setVisible(true);   
      
        return screen;
}
}