import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExperimentalObjectTest {

    /*
      testing strategy
      methods are tested against some expected return values
      */

    ExperimentalFlyingObject myObject = new ExperimentalFlyingObject(100);

    @Test
    public void testSealevel() {
        assertEquals(1.29, myObject.getAirDensity());
    }
    @Test
    public void test11km(){
        myObject.setAirDensity(11);
        assertEquals(0.36391, myObject.getAirDensity());
    }
}
