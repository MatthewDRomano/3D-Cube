public class Vector {
    public int x;
    public int y;
    public int z;

    public Vector(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double[][] toMatrix() { return new double[][]{{x}, {y}, {z}}; }
    public void toVector(double[][] coords) { x = (int)coords[0][0]; y = (int)coords[1][0]; z = (int)coords[2][0]; }
}