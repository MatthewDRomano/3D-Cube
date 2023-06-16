import javax.swing.*;
import java.awt.*;

public class Cube extends JPanel
{
    public boolean turningRight, turningLeft, turningUp, turningDown, faceLeft, faceRight;
    private Point scrnCntr = new Point(858/2, 525/2);
    private Vector[] updatedVectors = new Vector[8];
    private Vector[] vectors = new Vector[8];
    private Point[] points = new Point[8];
    private double aX = 0, aY = 0, aZ = 0;

    public Cube()
    {
        setBackground(Color.black);
        setBounds(0, 0, 858, 525);//bounds = box around ellipse
        plotPoints();
    }

    public void plotPoints()
    {
        vectors[0] = new Vector(-1, -1,-1);
        vectors[1] = new Vector(1, -1, -1);
        vectors[2] = new Vector(-1, 1, -1);
        vectors[3] = new Vector(1, 1, -1);
        vectors[4] = new Vector(-1, -1,1);
        vectors[5] = new Vector(1, -1, 1);
        vectors[6] = new Vector(-1, 1, 1);
        vectors[7] = new Vector(1, 1, 1);  
        
        for (int i = 0; i < vectors.length; i++)
        updatedVectors[i] = new Vector(vectors[i].x, vectors[i].y, vectors[i].z);               

        rotate(Axis.X, true);
        rotate(Axis.X, false);
    }

    public void rotate(Axis axis, boolean positive)
    {
        double incr = -0.000005;
        if (!positive) incr *= -1;

        switch (axis)
        {
            case X -> aX += incr;
            case Y -> aY += incr;
            case Z -> aZ += incr;
        }

        double[][] Rx = 
        {
            {1, 0, 0},
            {0, Math.cos(aX), -Math.sin(aX)},
            {0, Math.sin(aX), Math.cos(aX)}
        };

        double[][] Ry = 
        {
            {Math.cos(aY), 0, -Math.sin(aY)},
            {0, 1, 0},
            {Math.sin(aY), 0, Math.cos(aY)}
        };

        double[][] Rz = 
        {
            {Math.cos(aZ), -Math.sin(aZ), 0},
            {Math.sin(aZ), Math.cos(aZ), 0},
            {0, 0, 1}
        };

        
        for (int i = 0; i < points.length; i++)
        {
            double[][] matrix = vectors[i].toMatrix();

            double[][] rotated;

            rotated = Matrix.multiply(Rx, matrix);
            rotated = Matrix.multiply(Ry, rotated);
            rotated = Matrix.multiply(Rz, rotated);
            

            double distance = 4;// 2 //changeable based on multiplyer
            double z = 1/(distance - rotated[2][0]);
            double[][] perspective = 
            {
            {z, 0, 0},
            {0, z, 0}
            };

            double[][] projected2d = Matrix.multiply(perspective, rotated);

            for (int k = 0; k < projected2d.length; k++)
                for (int q = 0; q< projected2d[k].length; q++)
                    projected2d[k][q] *= 400;// 100

            Point p = new Point((int)projected2d[0][0], (int)projected2d[1][0]);

            points[i] = new Point(p.x + scrnCntr.x, p.y + scrnCntr.y);

            updatedVectors[i].x = (int)rotated[0][0];
            updatedVectors[i].y = (int)rotated[1][0];
            updatedVectors[i].z = (int)rotated[2][0];
        }               
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
       
        Vector highZ = new Vector(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (int i = 0; i < updatedVectors.length; i++)
            if (updatedVectors[i].z > highZ.z) highZ = updatedVectors[i]; 

        Face[] faces = new Face[6];
        faces[0] = (new Face(0, 1, 2, 3, Color.white));
        faces[1] = (new Face(0, 1, 4, 5, Color.yellow));
        faces[2] = (new Face(2, 3, 6, 7, Color.red));
        faces[3] = (new Face(4, 5, 6, 7, Color.green));
        faces[4] = (new Face(0, 2, 4, 6, Color.orange));
        faces[5] = (new Face(1, 3, 5, 7, Color.blue));

        for (int i = 0; i < faces.length; i++)
            if (faces[i].contains(highZ)) 
            {
                g2.setColor(faces[i].color);
                g2.fillPolygon(faces[i].to2DPolygon());
            }   

    }    
    public class Face{
        public int[] ind; 
        public Color color;

        public Face(int i1, int i2, int i3, int i4, Color c)
        {
            ind = new int[4];
            ind[0] = i1;
            ind[1] = i2;
            ind[2] = i3;
            ind[3] = i4;

            color = c;
        }
        public boolean contains(Vector ident)
        {
            for (int i = 0; i < ind.length; i++)
                if (updatedVectors[ind[i]].equals(ident)) return true;
            return false;
        }
        public Polygon to2DPolygon()
        {
            Polygon poly = new Polygon();

            for (int q = 1; q < ind.length-1; q++)                        
                poly.addPoint(points[ind[q]].x, points[ind[q]].y);
            for (int q = 0; q < ind.length; q++)                        
                poly.addPoint(points[ind[q]].x, points[ind[q]].y);
            return poly;
        }
    }
}