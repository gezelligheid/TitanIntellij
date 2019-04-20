import org.junit.jupiter.api.Test;

public class IntegratorRKNTest {

    @Test
    public void test(){
        FlyingBall ball = new FlyingBall();
        SecondDerivative acceleration = (SecondDerivative) ball;

        double t0 = 0;
        double tmax = 10;
        double y0 = 0;
        double h = 0.5;
        double v0 = 50;
        double a0 = acceleration.getValue(t0, y0, v0);
        IntegratorRKN integratorRKN = new IntegratorRKN(acceleration, t0, y0, v0,
                h, a0);

        while (integratorRKN.getT0() <= tmax){
            System.out.println("time ,t (s): " +integratorRKN.getT0()+ " pos, y (m): " + integratorRKN.getY0() +
                    " velocity (m/s): " + integratorRKN.getDy0() + "acceleration: m/s^2: " + integratorRKN.getD2y0());
            integratorRKN.step();
        }

    }
    }

