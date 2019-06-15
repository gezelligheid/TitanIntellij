import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class AlternativeBodyTest {

    @Test
    void compute_acceleration() throws CloneNotSupportedException {
        final double SUN_MASS = 1.989e30;
        final double SUN_RADIUS = 695700000.0;
        // sun is at zero

        AlternativeBody sun = new AlternativeBody("Sun",new Vector(0,0,0),
                new Vector(0,0,0),SUN_MASS,SUN_RADIUS);



        Vector acceleration_test = sun.compute_acceleration(new Vector(1000000000.0, 500000000.0,0));

        assertEquals(-94.98634391137153, acceleration_test.getX());
        assertEquals(-47.49317195568577, acceleration_test.getY());
        assertEquals(0,acceleration_test.getZ());
    }

    @Test
    void testStep() throws CloneNotSupportedException {
        final double SUN_MASS = 1.989e30;
        final double SUN_RADIUS = 695700000.0;
        double EARTH_X0 = -147_095_000_000.0;
        double EARTH_Y0 = 0.0;
        Vector earthPosition = new Vector(EARTH_X0,EARTH_Y0,0);
        double EARTH_VX0 = 0.0;
        double EARTH_VY0 = -30300.0;
        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0,0);
        final double EARTH_MASS = 5.972e24;
        final double EARTH_RADIUS = 6371000.0;
        final double dt = 86400.0; // 1 earth day in seconds

        AlternativeBody sun = new AlternativeBody("Sun",new Vector(0,0,0),
                new Vector(0,0,0),SUN_MASS,SUN_RADIUS);
        AlternativeBody earth = new AlternativeBody("Earth", earthPosition,earthVelocity,EARTH_MASS,EARTH_RADIUS);


//        System.out.println(sun.compute_acceleration(earth.position).multiply(dt));

        earth.step(dt,sun);


// this should output:
//        assertEquals(-147072101026.950928,earth.position.getX());
//        assertEquals(-2617784148.577663,earth.position.getY());
//        assertEquals(530.054352,earth.velocity.getX());
//        assertEquals(-30295.283069,earth.velocity.getX());
        System.out.printf("x: %f\n", earth.position.getX()); // x: -147072101026.950928
        System.out.printf("y: %f\n", earth.position.getY()); // y: -2617784148.577663
        System.out.printf("vx: %f\n", earth.velocity.getX()); // vx: 530.054352
        System.out.printf("vy: %f\n", earth.velocity.getY()); // vy: -30295.283069
    }
}