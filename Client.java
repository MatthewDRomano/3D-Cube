import javax.swing.*;
import java.awt.*;

public class Client
{
    static JFrame gui;
    public static void main(String[] args)
    {
        JPanel screen = createDisplay();
        Cube e = new Cube(new Point(350,200), 200, 50);
        //Ellipse e2 = new Ellipse(new Point(350,300), 200, 50);//
        gui.addKeyListener(new KeyListenerMod(e));
        //gui.addKeyListener(new KeyListenerMod(e2));//
        screen.add(e); 
        //screen.add(e2); 


        while (true) // 'game' loop
        {
            if (e.getRightStatus()) e.turn(true);
            else if (e.getLeftStatus()) e.turn(false);
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