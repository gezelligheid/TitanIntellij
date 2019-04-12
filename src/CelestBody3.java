
public class CelestBody3 {

    private Vector Pos = new Vector(0,0,0);
    private Vector Vel;
    private Vector Acc = new Vector(0,0,0);
    private Vector pullForce;

    private final double MASS;
    public final static double G = 6.67*10E-11;

    CelestBody3[] planets = new CelestBody3[10];

    public CelestBody3(double mass, Vector Vel) {
        this.MASS = mass;
        this.Vel = Vel;
    }

    public void calculateGravityForce() {
        System.out.println("4");
        for(int i=0; i<planets.length; i++) {
            if(!this.equals(planets[i])) {
                CelestBody3 p = planets[i];
                Vector direction = this.getPosition().subtract(p.getPosition()).normalize();
                double mass = p.getMass();
                double distance = this.Pos.distance(p.getPosition());
                double force = G*((this.MASS*mass)/Math.pow(distance, 2));
                Vector forceVector = direction.multiply(force);
                p.setPull(forceVector);
                //System.out.println(forceVector);
                p.addAcceleration();
            }
        }
    }



    public void setPos(Vector Pos) {
        this.Pos = Pos;
    }

    public void sendPlanets(CelestBody3[] planets) {
        this.planets = planets;
    }

    public Vector getPosition() {
        return this.Pos;
    }
    public void setPosition(Vector Pos) {
        this.Pos = Pos;
    }
    public void setAcceleration(Vector Acc) {
        this.Acc = Acc;
    }
    public void setVelocity(Vector Vel) {		//WARNING THIS MIGHT CHANGE ALL OF THE ORBITS OF THE PLANETS
        this.Vel = Vel;
    }
    public void addAcceleration() {
        Acc = Acc.sum(pullForce.multiply(1/MASS));
    }
    public void addAcceleration(Vector Acc) {
        Acc = Acc.sum(Acc);
    }
    public Vector getAcceleration() {
        return this.Acc;
    }
    public double getMass() {
        return MASS;
    }

    public void setPull(Vector pull) {
        pullForce = pull;
    }
    public Vector getForce() {
        return this.pullForce;
    }
    public void updateVelPos(double increment){
        Vel = Vel.sum(Vel.sum(Acc.multiply(increment))).multiply(0.5);
        System.out.println("new vect speed = " + Vel.getX());
        Pos = Pos.sum(Pos.sum(Vel.multiply(increment))).multiply(0.5);
    }


}
