import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void normalize() {
        Vector testVector1 = new Vector(4,3,0);
        Vector testResultvector1 = testVector1.normalize();
        assertEquals((double) 4/5,testResultvector1.getX());
        assertEquals((double) 3/5,testResultvector1.getY());

    }
}