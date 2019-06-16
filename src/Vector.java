public class Vector implements Cloneable{
    private double X;
    private double Y;
    private double Z;

    public Vector(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public Vector() {

    }

    public Vector(Vector v) {
        this.setX(v.getX());
        this.setY(v.getY());
        this.setZ(v.getZ());
    }

    //distance between this vector and vector v.
    public double distance(Vector v) {
        double distance = Math.sqrt(Math.pow(v.getX()-this.X, 2)+Math.pow(v.getY()-this.Y, 2)+Math.pow(v.getZ()-this.Z, 2));
        return distance;
    }

    //sun between this vector and vector v.
    public Vector sum(Vector v) {
        if (v != null) {
            X += v.X;
            Y += v.Y;
            Z += v.Z;
        }
        return this;
    }
    public void add(Vector v){
        if (v != null) {
            X += v.X;
            Y += v.Y;
            Z += v.Z;
        }
    }

    //difference between this vector and vector v.
    public Vector subtract(Vector v) {
        Vector subtract = new Vector(X-v.getX(),Y-v.getY(),Z-v.getZ());
        return subtract;
    }

    public double length() {
        return Math.sqrt(squareLength());
    }

    public double squareLength() {
        double xx = 0;
        double yy = 0;
        double zz = 0;
        if (this.X != 0 ) {
            xx = this.X*this.X;
        }
        if (this.Y != 0 ) {
            yy = this.Y*this.Y;
        }
        if (this.Z != 0 ) {
            zz = this.Z*this.Z;
        }
        return xx + yy + zz;
    }
    public void scale(double scalar){
        this.X *= scalar;
        this.Y *= scalar;
        this.Z *= scalar;
    }

    public Vector multiply(double factor) {
        if (X != 0) {
            this.X *= factor;
        }
        if (Y != 0) {
            this.Y *= factor;
        }
        if (Z != 0) {
            this.Z *= factor;
        }
        return this;
    }


    public double dot(Vector v) {
        return X * v.getX() + Y * v.getY() + Z * v.getZ();
    }

    public Vector cross(Vector v) {
        //from cross product formula
        return new Vector(Y * v.getZ() - Z * v.getY(), Z * v.getX() - X * v.getZ(), X * v.getY() - Y * v.getX());
    }

    public Vector rotate(Vector axis, double rad) {
        //Euler angle	-> gimball lock, we need to calculate the three angles
        //Axis - angle	-> we already have the direction and the angle (from torque momentum)
        //Quaternion rotation	-> no gimball lock, but we need a new class for quaternions

        //Axis - angle (from formula)
        Vector v = axis.normalize();
        return	this.multiply(Math.cos(rad)).sum(v.cross(this).multiply(Math.sin(rad))).sum(v.multiply(v.dot(this) * (1 - Math.cos(rad))));
    }

    public Vector normalize() {
        return new Vector(multiply(1 / length()));
    }

    public double getZ() { return Z; }
    public void setZ(double z) {
        Z = z;
    }
    public double getY() {
        return Y;
    }
    public void setY(double y) {
        Y = y;
    }
    public double getX() {
        return X;
    }
    public void setX(double x) {
        X = x;
    }
    public String toString() {
        return X+" "+Y+" "+Z;
    }
    public void set(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }
    public Vector div(double factor) {
        if (X != 0) {
            this.X /= factor;
        }
        if (Y != 0) {
            this.Y /= factor;
        }
        if (Z != 0) {
            this.Z /= factor;
        }
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}