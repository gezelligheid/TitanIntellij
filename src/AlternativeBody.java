import java.util.List;

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
     * computes total acceleration influence from a list of bodies on the body passed as a parameter
     *
     * @param solarSystem all body influencing this body
     * @return acceleration resulting from all gravitational forces
     */
    public Vector compute_acceleration(List<AlternativeBody> solarSystem) throws CloneNotSupportedException {
        Vector resultAcceleration = new Vector(0, 0, 0);
        for (AlternativeBody body : solarSystem
        ) { if (!this.name.equals(body.name)){
            final Vector distances = ((Vector) body.position.clone()).subtract(this.position);
            Vector normDistances = ((Vector) distances.clone()).normalize();
            Vector currentAcceleration = normDistances.multiply((Physics.G * body.mass) / distances.squareLength());
            resultAcceleration.add(currentAcceleration);
        }

        }
        return resultAcceleration;
    }



    /**
     * moves the body under influence of a list of other mass objects
     *
     * @param dt        time step
     * @param alternativeBodies the other mass objects
     */
    public void step(double dt, List<AlternativeBody> alternativeBodies) throws CloneNotSupportedException {
        final Vector k1Pos = (Vector) this.velocity.clone(); // k1 for the change in position vector
        final Vector k1Vel = this.compute_acceleration(alternativeBodies);// k1 for the change velocity vector
        final Vector k2Pos = ((Vector) this.velocity.clone()).sum(((Vector) k1Vel.clone()).multiply(dt / 2));
        AlternativeBody k1StateBody = new AlternativeBody(this.name,
                ((Vector) this.position.clone()).sum(((Vector) k1Pos.clone()).multiply(dt / 2)),this.velocity,this.mass,this.radius);
        final Vector k2Vel = k1StateBody.compute_acceleration(alternativeBodies);
        final Vector k3Pos = ((Vector) this.velocity.clone()).sum(((Vector) k2Vel.clone()).multiply(dt / 2));
        AlternativeBody k2StateBody = new AlternativeBody(this.name,
                ((Vector) this.position.clone()).sum(((Vector) k2Pos.clone()).multiply(dt / 2)),this.velocity,this.mass,this.radius);
        final Vector k3Vel = k2StateBody.compute_acceleration(alternativeBodies);
        final Vector k4Pos = ((Vector) this.velocity.clone()).sum(((Vector) k3Vel.clone()).multiply(dt));
        AlternativeBody k3StateBody = new AlternativeBody(this.name,
                ((Vector) this.position.clone()).sum(((Vector) k3Pos.clone()).multiply(dt)),this.velocity,this.mass,this.radius);
        final Vector k4Vel = k3StateBody.compute_acceleration(alternativeBodies);

        Vector kPosTotal = new Vector(0, 0, 0);
        Vector kVelTotal = new Vector(0, 0, 0);

        kPosTotal.add(k1Pos); // Runge-Kutta coefficient sums
        kPosTotal.add(((Vector) k2Pos.clone()).multiply(2.0));
        kPosTotal.add(((Vector) k3Pos.clone()).multiply(2.0));
        kPosTotal.add(k4Pos);
        kPosTotal.scale((double) dt / 6);

        kVelTotal.add(k1Vel);
        kVelTotal.add(((Vector) k2Vel.clone()).multiply(2.0));
        kVelTotal.add(((Vector) k3Vel.clone()).multiply(2.0));
        kVelTotal.add(k4Vel);
        kVelTotal.scale((double) dt / 6);

        this.position.add(kPosTotal); // add the change
        this.velocity.add(kVelTotal);


    }

}
