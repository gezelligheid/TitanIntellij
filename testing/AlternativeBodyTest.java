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
//        Vector EARTH_POS0 = new Vector(EARTH_X0, EARTH_Y0, 0);
//        double EARTH_VX0 = 0.0;
//        double EARTH_VY0 = -30300.0;
//        Vector EARTH_VEL0 = new Vector(EARTH_VX0, EARTH_VY0, 0);
//        final double EARTH_MASS = 5.972e24;
//        final double EARTH_RADIUS = 6371000.0;
//        final double dt = 86400.0; // 1 earth day in seconds
//
//        AlternativeBody sun = new AlternativeBody("Sun", new Vector(0, 0, 0),
//                new Vector(0, 0, 0), SUN_MASS, SUN_RADIUS);
//        AlternativeBody earth = new AlternativeBody("Earth", EARTH_POS0, EARTH_VEL0, EARTH_MASS, EARTH_RADIUS);
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
//        Vector EARTH_POS0 = new Vector(EARTH_X0, EARTH_Y0, 0);
//        double EARTH_VX0 = 0.0;
//        double EARTH_VY0 = -30300.0;
//        Vector EARTH_VEL0 = new Vector(EARTH_VX0, EARTH_VY0, 0);
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
//        AlternativeBody earth = new AlternativeBody("Earth", EARTH_POS0, EARTH_VEL0, EARTH_MASS, EARTH_RADIUS);
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
        final int divideDt = 1; // example: dt = h = 1, divideDt = 2 gives dt/divideDt = 1/2


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

        final double dt = 86400.0; // 1 earth day in seconds
        final int divideDt = 1; // example: dt = 1, divideDt = 2 gives dt/divideDt = h = 1/2


        final Vector SUN_POS0 = new Vector(-3.314343410245739E+08, 1.139474540626986E+09, -2.931042498752882E+06);
        final Vector MERCURY_POS0 = new Vector(-4.381491758075624E+10, -5.095427167075149E+10, -2.706828308744654E+08);
        final Vector VENUS_POS0 = new Vector(4.740682614132057E+10, 9.792575530560824E+10, -1.429759475436002E+09);
        final Vector EARTH_POS0 = new Vector(1.387862413329253E+10, -1.502743731222846E+11, 4.380991097539663E+06);
        final Vector MOON_POS0 = new Vector(1.421942994545356E+10, -1.500802690007858E+11, -3.100827236741036E+07);
        final Vector MARS_POS0 = new Vector(-1.589250225889461E+11, 1.898631083654149E+11, 7.842822564780533E+09);
        final Vector JUPITER_POS0 = new Vector(-1.301450500286332E+11, -7.800582192024357E+11, 6.146384448979557E+09);
        final Vector SATURN_POS0 = new Vector(4.286537306199825E+11, -1.439848147833939E+12, 7.971773931893587E+09);
        final Vector TITAN_POS0 = new Vector(4.283742844521295E+11, -1.440891854834355E+12, 8.537522025083423E+9);
        final Vector URANUS_POS0 = new Vector(2.489269804576205E+12, 1.617145487687750E+12, -2.624269838609433E+10);
        final Vector NEPTUNE_POS0 = new Vector(4.355122045053928E+12, -1.037750547224149E+12, -7.899771752769661E+10);

        final Vector SUN_VEL0 = new Vector(-1.455742265779304E+01, 1.323658286241915E-01, 3.785824592599174E-01);
        final Vector MERCURY_VEL0 = new Vector(2.750293030951233E+04, -2.895910747600353E+04, -4.890354985933278E+03);
        final Vector VENUS_VEL0 = new Vector(-3.153567246042259E+04, 1.533380639871332E+04, 2.029779511550747E+03);
        final Vector EARTH_VEL0 = new Vector(2.916511476000697E+04, 2.661587955480921E+03, -1.079275362508847E-01);
        final Vector MOON_VEL0 = new Vector(2.862709919111373E+04,  3.498638936086098E+03,  2.409477179423636E+01);
        final Vector MARS_VEL0 = new Vector(-1.765139793891791E+04, -1.352281707749128E+04, 1.497639484169078E+02);
        final Vector JUPITER_VEL0 = new Vector(1.272903766626355E+04, -1.526795715815108E+03, -2.784243094219157E+02);
        final Vector SATURN_VEL0 = new Vector(8.723239997512566E+03, 2.726789469098915E+03, -3.947371385903475E+02);
        final Vector TITAN_VEL0 = new Vector(1.416955908131419E+04, 1.503346198931311E+03, -3.059367073094467E+2);
        final Vector URANUS_VEL0 = new Vector(-3.760110169043917E+03, 5.393368703793284E+03, 6.882942177822859E+01);
        final Vector NEPTUNE_VEL0 = new Vector(1.223069800605603E+03, 5.318950735190921E+03, -1.377458252387322E+02);

        final double SUN_MASS = 1.989e30; // kilograms
        final double MERCURY_MASS = .3301e24;
        final double VENUS_MASS = 4.8675e24;
        final double EARTH_MASS = 5.972e24;
        final double MOON_MASS = 7.349e22;
        final double MARS_MASS = 6.4171e23;
        final double JUPITER_MASS = 1898.19e24;
        final double SATURN_MASS = 568.34e24;
        final double TITAN_MASS = 13455.3e19;
        final double URANUS_MASS = 86.813e24;
        final double NEPTUNE_MASS = 102.413e24;

        final double SUN_RADIUS = 695700000.0; // meters
        final double MERCURY_RADIUS = 2439700;
        final double VENUS_RADIUS = 6051800;
        final double EARTH_RADIUS = 6371000.0;
        final double MOON_RADIUS = 1738000;
        final double MARS_RADIUS = 3389500;
        final double JUPITER_RADIUS = 71492000;
        final double SATURN_RADIUS = 54364000;
        final double TITAN__RADIUS = 2575500;
        final double URANUS_RADIUS = 24973000;
        final double NEPTUNE_RADIUS = 24341000;


        final Vector SPACECRAFT_VEL0 = new Vector(35000, -35000, 0);
        final Vector SPACECRAFT_POS0 = new Vector(EARTH_POS0.getX() + EARTH_RADIUS, EARTH_POS0.getY() - EARTH_RADIUS, EARTH_POS0.getZ());
        final double SPACECRAFT_MASS = 7080; // kg
        final double SPACECRAFT_RADIUS = 3; // meters


        AlternativeBody sun = new AlternativeBody("Sun", SUN_POS0, SUN_VEL0, SUN_MASS, SUN_RADIUS);
        AlternativeBody earth = new AlternativeBody("Earth", EARTH_POS0, EARTH_VEL0, EARTH_MASS, EARTH_RADIUS);
        AlternativeBody moon = new AlternativeBody("Moon", MOON_POS0,MOON_VEL0,MOON_MASS,MOON_RADIUS);
        AlternativeBody mercury = new AlternativeBody("Mercury", MERCURY_POS0, MERCURY_VEL0, MERCURY_MASS, MERCURY_RADIUS);
        AlternativeBody venus = new AlternativeBody("Venus", VENUS_POS0, VENUS_VEL0, VENUS_MASS, VENUS_RADIUS);
        AlternativeBody mars = new AlternativeBody("Mars", MARS_POS0, MARS_VEL0, MARS_MASS, MARS_RADIUS);
        AlternativeBody jupiter = new AlternativeBody("Jupiter", JUPITER_POS0, JUPITER_VEL0, JUPITER_MASS, JUPITER_RADIUS);
        AlternativeBody saturn = new AlternativeBody("Saturn", SATURN_POS0, SATURN_VEL0, SATURN_MASS, SATURN_RADIUS);
        AlternativeBody titan = new AlternativeBody("Titan", TITAN_POS0, TITAN_VEL0, TITAN_MASS, TITAN__RADIUS);
        AlternativeBody uranus = new AlternativeBody("Uranus", URANUS_POS0, URANUS_VEL0, URANUS_MASS, URANUS_RADIUS);
        AlternativeBody neptune = new AlternativeBody("Neptune", NEPTUNE_POS0, NEPTUNE_VEL0, NEPTUNE_MASS, NEPTUNE_RADIUS);
        AlternativeBody spacecraft = new AlternativeBody("Spacecraft", SPACECRAFT_POS0, SPACECRAFT_VEL0, SPACECRAFT_MASS, SPACECRAFT_RADIUS);

        List<AlternativeBody> solarSystem = new ArrayList<>();
        solarSystem.add(earth);
//        solarSystem.add(moon);
        solarSystem.add(mercury);
        solarSystem.add(mars);
        solarSystem.add(venus);
        solarSystem.add(jupiter);
        solarSystem.add(saturn);
        solarSystem.add(titan);
        solarSystem.add(uranus);
        solarSystem.add(neptune);
        solarSystem.add(sun);

        // simulate 1 earth year for all planets interacting with the sun only

        for (int i = 0; i <= 366 * divideDt; i++) {

            List<AlternativeBody> tempSolarSystem = (List<AlternativeBody>) ((ArrayList<AlternativeBody>) solarSystem).clone();
            for (AlternativeBody planet : tempSolarSystem
            ) {
                if (i % divideDt == 0) {
                    System.out.println("day " + i / divideDt + ", " + planet.name + ". x: " + planet.position.getX() +
                            ", y: " + planet.position.getY() + ", z:" + planet.position.getZ());
                }
                planet.step(dt / divideDt, solarSystem);

            }
            solarSystem = tempSolarSystem;

        }

    }

    @Test
    void addImpulseTest(){
        final Vector SPACECRAFT_VEL0 = new Vector(0, 0, 0);
        final Vector SPACECRAFT_POS0 = new Vector(0,0,0);
        final double SPACECRAFT_MASS = 100; // kg
        double initialFuel = 30000; // kg
        final double SPACECRAFT_RADIUS = 3; // meters

        AlternativeBody spacecraft = new AlternativeBody("Spacecraft", SPACECRAFT_POS0, SPACECRAFT_VEL0, SPACECRAFT_MASS, SPACECRAFT_RADIUS);

        spacecraft.setFuelMass(initialFuel);

        spacecraft.addImpulse(-2836.06, -9890.517, 0);
        System.out.println("fuel after move is: " + spacecraft.getFuelMass());
        System.out.println("new velocity is: " + spacecraft.getVelocity());

        spacecraft.addImpulse(10,0, 0);
        System.out.println("fuel after move is: " + spacecraft.getFuelMass());
        System.out.println("new velocity is: " + spacecraft.getVelocity());
    }

}