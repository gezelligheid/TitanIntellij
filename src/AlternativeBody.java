public class AlternativeBody {
    String name; // bodyName
    Vector position; // meters
    Vector velocity; // meters per second
    double mass; // kilograms
    double radius; // meters

    public AlternativeBody(String name, Vector position, Vector velocity, double mass, double radius) {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.radius = radius;
    }

    /**
     * computes the acceleration induced by this body on an object at location (x, y, z) some 3D coordinate system
     *
     * @param otherPosition position of some object of mass
     * @return acceleration contributed by this mass object
     */

    public Vector compute_acceleration(Vector otherPosition) throws CloneNotSupportedException {
        final Vector distances = ((Vector) this.position.clone()).subtract(otherPosition);
        Vector normDistances = (Vector) distances.clone();
        normDistances = normDistances.normalize();
        return normDistances.multiply((Physics.G * this.mass) / distances.squareLength());
    }

    /**
     * moves the body under influence of some other mass object
     * @param dt time step
     * @param otherBody the other mass object
     * */
    public void step(double dt, AlternativeBody otherBody) throws CloneNotSupportedException {
        final Vector k1Pos = (Vector) this.velocity.clone(); // k1 for the change in position vector
        final Vector k1Vel = otherBody.compute_acceleration(this.position);// k1 for the change velocity vector
        final Vector k2Pos = ((Vector) this.velocity.clone()).sum(((Vector) k1Vel.clone()).multiply(dt/2));
        final Vector k2Vel = otherBody.compute_acceleration(((Vector) this.position.clone()).sum(((Vector) k1Pos.clone()).multiply(dt/2)));
        final Vector k3Pos = ((Vector) this.velocity.clone()).sum(((Vector) k2Vel.clone()).multiply(dt/2));
        final Vector k3Vel = otherBody.compute_acceleration(((Vector) this.position.clone()).sum(((Vector) k2Pos.clone()).multiply(dt/2)));
        final Vector k4Pos = ((Vector) this.velocity.clone()).sum(((Vector) k3Vel.clone()).multiply(dt));
        final Vector k4Vel = otherBody.compute_acceleration(((Vector) this.position.clone()).sum(((Vector) k3Pos.clone()).multiply(dt)));

        Vector kPosTotal = new Vector(0,0,0);
        Vector kVelTotal = new Vector(0,0,0);

        kPosTotal.add(k1Pos);
        kPosTotal.add(((Vector) k2Pos.clone()).multiply(2.0));
        kPosTotal.add(((Vector) k3Pos.clone()).multiply(2.0));
        kPosTotal.add(k4Pos);
        kPosTotal.scale(dt/6);

        kVelTotal.add(k1Vel);
        kVelTotal.add(((Vector) k2Vel.clone()).multiply(2.0));
        kVelTotal.add(((Vector) k3Vel.clone()).multiply(2.0));
        kVelTotal.add(k4Vel);
        kVelTotal.scale(dt/6);

        this.position.add(kPosTotal);
        this.velocity.add(kVelTotal);
//        this.position = ((Vector)this.position.clone()).sum((((Vector)k1Pos.clone()).sum(((Vector)k2Pos.clone())
//                .multiply(2)).sum(((Vector)k3Pos.clone()).multiply(2)).sum((Vector)k4Pos.clone()).multiply(1/6)));
//        this.velocity = ((Vector)this.velocity.clone()).sum((((Vector)k1Vel.clone()).sum(((Vector)k2Vel.clone())
//                .multiply(2)).sum(((Vector)k3Vel.clone()).multiply(2)).sum((Vector)k4Vel.clone())).multiply(1/6));

    }

}
