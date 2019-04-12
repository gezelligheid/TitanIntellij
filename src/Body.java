
/**
 * Implementation of a generic body that moves in a three-dimensional coordinate system.
 * The movement is caused by applying forces on the body.
 *
 */
public class Body {
    public Vector location;
    public Vector velocity;
    public Vector acceleration;

    /** mass in kilograms */
    public double mass;

    /** radius in meters */
    public double radius;

    public String name;


    public Body() {
        if (acceleration == null) {
            acceleration = new Vector();
        }
        if (velocity == null) {
            velocity = new Vector();
        }
        if (location == null) {
            location = new Vector();
        }
    }

    public Body(Vector location, Vector velocity, double radius, double mass) {
        this();
        this.location = location;
        this.velocity = velocity;
        this.radius = radius;
        this.mass = mass;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getLocation() {
        return location;
    }

    /**
     * Adds a force vector to the body which implies a change in acceleration depending on mass.
     *
     *  Newton's second law: F = m * a, a = F/m
     *
     *  - F = force acting on the body in newtons
     *  - m = mass of body in kilograms
     *  - a = acceleration in m/s²
     *
     * @param force
     *
     */
    public void addAccelerationByForce(Vector force) {
        Vector accByForce = new Vector(force);
        accByForce.div(mass);
        acceleration.sum(accByForce);
    }



    /**
     * Calculates the gravitational force between this body and another body and
     * accumulates the force.
     *
     * @param other
     */
    public void addAccelerationByGravityForce(Body other) {
        addAccelerationByForce(calculateGravitationalForce(other));
    }

    /**
     * Resets acceleration vector so that addAccelerationByGravityForce() can accumulate forces during a new timeslice.
     */
    public void resetAcceleration() {
        acceleration = new Vector();
    }

    /**
     * Calculates a new velocity and location for the body for current acceleration and
     * a given timeslice.
     *
     * Note: the velocity calculated by applying the acceleration during the timeslice
     * will be the final velocity. To calculate the new location we will use the average
     * velocity instead in order to get a better approximation.
     *
     * The accuracy of the calculated velocity and location will be dependent on how how long
     * the timeslice is and how constant the acceleration is during the timeslice.
     *
     * @param timeSlice the timeslice for which current acceleration should affect the body.
     */
    public void updateVelocityAndLocation(double timeSlice) {
        // caluclate final velocity when the time slice has occurred
        Vector oldVelocity = new Vector(this.velocity);
        updateVelocity(timeSlice);

        // updateVelocityAndLocation location using average velocity
        Vector changedVelocityAverage = new Vector(this.velocity).subtract(oldVelocity).div(2.0);
        Vector averageVelocity = new Vector(oldVelocity).sum(changedVelocityAverage);
        updateLocation(timeSlice, averageVelocity);
    }

    /**
     * Calculates the gravitational force between this body and another body.
     *
     * Newton's law of universal gravitation: F = G * (m1 * m2)/r²
     *  - F = force between bodies in newtons. The force is a vector pointing towards the current body (attracting)
     *  - m1 = mass of body1 in kilograms
     *  - m2 = mass of body2 in kilograms
     *  - G = gravitaional constant (6.674×10−11 N · (m/kg)² )
     *  - r = distance between the centers of the masses in meters
     *
     *
     * @param other
     */
    protected Vector calculateGravitationalForce(Body other) {
        // get direction vector
        Vector directionVect = new Vector(this.location);
        directionVect.subtract(other.location).normalize().multiply(-1);

        // distance between bodies
        double r = this.location.distance(other.location);

        // calculate force
        Vector grativationalForce = new Vector(directionVect);
        grativationalForce.multiply(Physics.G).multiply(this.mass).multiply(other.mass).div(r*r);

        return grativationalForce;
    }

    /**
     * Calculates the final velocity when current accumulated acceleration has been applied during a timeslice.
     *
     * @param timeSlice
     */
    protected void updateVelocity(double timeSlice) {
        Vector velocityByAcc = new Vector(acceleration).multiply(timeSlice);
        velocity.sum(velocityByAcc);
    }

    /**
     * Calulates a new location given that an velocity has been applied a given timeslice.
     *
     * @param timeSlice the timeslice for which given average velocity should affect the body.
     * @param averageVelocity the average velocity during the timeslice
     */
    protected void updateLocation(double timeSlice, Vector averageVelocity) {
        Vector locationByVelocity = new Vector(averageVelocity).multiply(timeSlice);
        location.sum(locationByVelocity);
    }



}