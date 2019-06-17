import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AlternativeBodyTest {

    @Test
    void compute_acceleration() throws CloneNotSupportedException {
        final double SUN_MASS = 1.989e30;
        final double SUN_RADIUS = 695700000.0;
        double EARTH_X0 = -147_095_000_000.0;
        double EARTH_Y0 = 0.0;
        Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
        double EARTH_VX0 = 0.0;
        double EARTH_VY0 = -30300.0;
        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
        final double EARTH_MASS = 5.972e24;
        final double EARTH_RADIUS = 6371000.0;
        final double dt = 86400.0; // 1 earth day in seconds
        // sun is at zero

        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
        AlternativeBody earth = new AlternativeBody("Earth", new Vector(1000000000.0, 500000000.0, 0),
                earthVelocity, EARTH_MASS, EARTH_RADIUS);
        List<AlternativeBody> miniverse = new ArrayList<>();
        miniverse.add(sun);
        miniverse.add(earth);


//        Vector acceleration_test = sun.compute_acceleration(new Vector(1000000000.0, 500000000.0, 0));

//
//        assertEquals(-94.98634391137153, acceleration_test.getX());
//        assertEquals(-47.49317195568577, acceleration_test.getY());
//        assertEquals(0, acceleration_test.getZ());
    }

//    @Test
//    void testStep() throws CloneNotSupportedException {
//        final double SUN_MASS = 1.989e30;
//        final double SUN_RADIUS = 695700000.0;
//        double EARTH_X0 = -147_095_000_000.0;
//        double EARTH_Y0 = 0.0;
//        Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
//        double EARTH_VX0 = 0.0;
//        double EARTH_VY0 = -30300.0;
//        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
//        final double EARTH_MASS = 5.972e24;
//        final double EARTH_RADIUS = 6371000.0;
//        final double dt = 86400.0; // 1 earth day in seconds
//
//        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
//                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
//        AlternativeBody earth = new AlternativeBody("Earth", earthPosition, earthVelocity, EARTH_MASS, EARTH_RADIUS);
//
//
////        // simulate 1 earth year
//        for (int i = 0; i <= 366; i++) {
//            System.out.println(i + ". x: " + earth.position.getX() + ", y: " + earth.position.getY());
//            earth.step(dt, sun);
//        }


// this should output after first step:
//        assertEquals(-147072101026.950928,earth.position.getX());
//        assertEquals(-2617784148.577663,earth.position.getY());
//        assertEquals(530.054352,earth.velocity.getX());
//        assertEquals(-30295.283069,earth.velocity.getX());
//        System.out.printf("x: %f\n", earth.position.getX()); // x: -147072101026.950928
//        System.out.printf("y: %f\n", earth.position.getY()); // y: -2617784148.577663
//        System.out.printf("vx: %f\n", earth.velocity.getX()); // vx: 530.054352
//        System.out.printf("vy: %f\n", earth.velocity.getY()); // vy: -30295.283069
//    }

    //    @Test
//    void testMultiPlanetIndependent() throws CloneNotSupportedException {
//        final double SUN_MASS = 1.989e30;
//        final double SUN_RADIUS = 695700000.0;
//        double EARTH_X0 = -147_095_000_000.0;
//        double EARTH_Y0 = 0.0;
//        Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
//        double EARTH_VX0 = 0.0;
//        double EARTH_VY0 = -30300.0;
//        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
//        final double EARTH_MASS = 5.972e24;
//        final double EARTH_RADIUS = 6371000.0;
//        final double dt = 86400.0; // 1 earth day in seconds
//
//        final Vector MERCURY_POS0 = new Vector(-46000000000., 0, 0);
//        final Vector VENUS_POS0 = new Vector(-107480000000., 0, 0);
//        final Vector MARS_POS0 = new Vector(-206620000000., 0, 0);
//        final Vector JUPITER_POS0 = new Vector(-740520000000., 0, 0);
//        final Vector SATURN_POS0 = new Vector(-1352550000000., 0, 0);
//        final Vector URANUS_POS0 = new Vector(-2741300000000., 0, 0);
//        final Vector NEPTUNE_POS0 = new Vector(-4444450000000., 0, 0);
//
//        final Vector MERCURY_VEL0 = new Vector(0., -58980, 0);
//        final Vector VENUS_VEL0 = new Vector(0., -35260, 0);
//        final Vector MARS_VEL0 = new Vector(0., -26500, 0);
//        final Vector JUPITER_VEL0 = new Vector(0., -13720, 0);
//        final Vector SATURN_VEL0 = new Vector(0., -10180, 0);
//        final Vector URANUS_VEL0 = new Vector(0., -7110, 0);
//        final Vector NEPTUNE_VEL0 = new Vector(0., -5500, 0);
//
//        final double MERCURY_MASS = .3301e24;//kilograms
//        final double VENUS_MASS = 4.8675e24;
//        final double MARS_MASS = 6.4171e23;
//        final double JUPITER_MASS = 1898.19e24;
//        final double SATURN_MASS = 568.34e24;
//        final double URANUS_MASS = 86.813e24;
//        final double NEPTUNE_MASS = 102.413e24;
//
//        final double MERCURY_RADIUS = 2439700;//meters
//        final double VENUS_RADIUS = 6051800;
//        final double MARS_RADIUS = 3389500;
//        final double JUPITER_RADIUS = 71492000;
//        final double SATURN_RADIUS = 54364000;
//        final double URANUS_RADIUS = 24973000;
//        final double NEPTUNE_RADIUS = 24341000;
//
//
//        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
//                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
//        AlternativeBody earth = new AlternativeBody("Earth", earthPosition, earthVelocity, EARTH_MASS, EARTH_RADIUS);
//        AlternativeBody mercury = new AlternativeBody("Mercury", MERCURY_POS0, MERCURY_VEL0, MERCURY_MASS, MERCURY_RADIUS);
//        AlternativeBody venus = new AlternativeBody("Venus", VENUS_POS0, VENUS_VEL0, VENUS_MASS, VENUS_RADIUS);
//        AlternativeBody mars = new AlternativeBody("Mars", MARS_POS0, MARS_VEL0, MARS_MASS, MARS_RADIUS);
//        AlternativeBody jupiter = new AlternativeBody("Jupiter", JUPITER_POS0, JUPITER_VEL0, JUPITER_MASS, JUPITER_RADIUS);
//        AlternativeBody saturn = new AlternativeBody("Saturn", SATURN_POS0, SATURN_VEL0, SATURN_MASS, SATURN_RADIUS);
//        AlternativeBody uranus = new AlternativeBody("Uranus", URANUS_POS0, URANUS_VEL0, URANUS_MASS, URANUS_RADIUS);
//        AlternativeBody neptune = new AlternativeBody("Neptune", NEPTUNE_POS0, NEPTUNE_VEL0, NEPTUNE_MASS, NEPTUNE_RADIUS);
//
//        List<AlternativeBody> solarSystem = new ArrayList<>();
//        solarSystem.add(earth);
//        solarSystem.add(mercury);
//        solarSystem.add(mars);
//        solarSystem.add(venus);
//        solarSystem.add(jupiter);
//        solarSystem.add(saturn);
//        solarSystem.add(uranus);
//        solarSystem.add(neptune);
//
//        // simulate 1 earth year for all planets interacting with the sun only
//
//        for (int i = 0; i <= 366; i++) {
//
//            for (AlternativeBody planet : solarSystem
//            ) {
//                System.out.println("day " + i + ", " + planet.name + ". x: " + planet.position.getX() +
//                        ", y: " + planet.position.getY() + ", z:" + planet.position.getZ());
//                planet.step(dt, sun);
//
//            }
//
//        }
//
//
//    }
    @Test
    void testAcceleration2() throws CloneNotSupportedException {
        final double SUN_MASS = 1.989e30;
        final double SUN_RADIUS = 695700000.0;
        double EARTH_X0 = -147_095_000_000.0;
        double EARTH_Y0 = 0.0;
        Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
        double EARTH_VX0 = 0.0;
        double EARTH_VY0 = -30300.0;
        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
        final double EARTH_MASS = 5.972e24;
        final double EARTH_RADIUS = 6371000.0;
        final double dt = 86400.0; // 1 earth day in seconds
        // sun is at zero

        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
        AlternativeBody earth = new AlternativeBody("Earth", new Vector(1000000000.0, 500000000.0, 0),
                earthVelocity, EARTH_MASS, EARTH_RADIUS);
        List<AlternativeBody> miniVerse = new ArrayList<>();
        miniVerse.add(sun);
        miniVerse.add(earth);

        Vector testAccel = earth.compute_acceleration(miniVerse);

        System.out.println(testAccel);
        //        assertEquals(-94.98634391137153, acceleration_test.getX());
        //        assertEquals(-47.49317195568577, acceleration_test.getY());
        //        assertEquals(0, acceleration_test.getZ());
    }

    @Test
    void dependentSystemTest() throws CloneNotSupportedException {
        final double SUN_MASS = 1.989e30;
        final double SUN_RADIUS = 695700000.0;
        double EARTH_X0 = -147_095_000_000.0;
        double EARTH_Y0 = 0.0;
        Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
        double EARTH_VX0 = 0.0;
        double EARTH_VY0 = -30300.0;
        Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
        final double EARTH_MASS = 5.972e24;
        final double EARTH_RADIUS = 6371000.0;
        final double dt = 86400.0; // 1 earth day in seconds

        final Vector MERCURY_POS0 = new Vector(-46000000000., 0, 0);
        final Vector VENUS_POS0 = new Vector(-107480000000., 0, 0);
        final Vector MARS_POS0 = new Vector(-206620000000., 0, 0);
        final Vector JUPITER_POS0 = new Vector(-740520000000., 0, 0);
        final Vector SATURN_POS0 = new Vector(-1352550000000., 0, 0);
        final Vector URANUS_POS0 = new Vector(-2741300000000., 0, 0);
        final Vector NEPTUNE_POS0 = new Vector(-4444450000000., 0, 0);

        final Vector MERCURY_VEL0 = new Vector(0., -58980, 0);
        final Vector VENUS_VEL0 = new Vector(0., -35260, 0);
        final Vector MARS_VEL0 = new Vector(0., -26500, 0);
        final Vector JUPITER_VEL0 = new Vector(0., -13720, 0);
        final Vector SATURN_VEL0 = new Vector(0., -10180, 0);
        final Vector URANUS_VEL0 = new Vector(0., -7110, 0);
        final Vector NEPTUNE_VEL0 = new Vector(0., -5500, 0);

        final double MERCURY_MASS = .3301e24;//kilograms
        final double VENUS_MASS = 4.8675e24;
        final double MARS_MASS = 6.4171e23;
        final double JUPITER_MASS = 1898.19e24;
        final double SATURN_MASS = 568.34e24;
        final double URANUS_MASS = 86.813e24;
        final double NEPTUNE_MASS = 102.413e24;

        final double MERCURY_RADIUS = 2439700;//meters
        final double VENUS_RADIUS = 6051800;
        final double MARS_RADIUS = 3389500;
        final double JUPITER_RADIUS = 71492000;
        final double SATURN_RADIUS = 54364000;
        final double URANUS_RADIUS = 24973000;
        final double NEPTUNE_RADIUS = 24341000;


        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
        AlternativeBody earth = new AlternativeBody("Earth", earthPosition, earthVelocity, EARTH_MASS, EARTH_RADIUS);
        AlternativeBody mercury = new AlternativeBody("Mercury", MERCURY_POS0, MERCURY_VEL0, MERCURY_MASS, MERCURY_RADIUS);
        AlternativeBody venus = new AlternativeBody("Venus", VENUS_POS0, VENUS_VEL0, VENUS_MASS, VENUS_RADIUS);
        AlternativeBody mars = new AlternativeBody("Mars", MARS_POS0, MARS_VEL0, MARS_MASS, MARS_RADIUS);
        AlternativeBody jupiter = new AlternativeBody("Jupiter", JUPITER_POS0, JUPITER_VEL0, JUPITER_MASS, JUPITER_RADIUS);
        AlternativeBody saturn = new AlternativeBody("Saturn", SATURN_POS0, SATURN_VEL0, SATURN_MASS, SATURN_RADIUS);
        AlternativeBody uranus = new AlternativeBody("Uranus", URANUS_POS0, URANUS_VEL0, URANUS_MASS, URANUS_RADIUS);
        AlternativeBody neptune = new AlternativeBody("Neptune", NEPTUNE_POS0, NEPTUNE_VEL0, NEPTUNE_MASS, NEPTUNE_RADIUS);

        List<AlternativeBody> solarSystem = new ArrayList<>();
        solarSystem.add(earth);
        solarSystem.add(mercury);
        solarSystem.add(mars);
        solarSystem.add(venus);
        solarSystem.add(jupiter);
        solarSystem.add(saturn);
        solarSystem.add(uranus);
        solarSystem.add(neptune);
        solarSystem.add(sun);

        // simulate 1 earth year for all planets interacting with the sun only

        for (int i = 0; i <= 366; i++) {

            for (AlternativeBody planet : solarSystem
            ) {
                System.out.println("day " + i + ", " + planet.name + ". x: " + planet.position.getX() +
                        ", y: " + planet.position.getY() + ", z:" + planet.position.getZ());
                planet.step(dt, solarSystem);

            }

        }

    }

}