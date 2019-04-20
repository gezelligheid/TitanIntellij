public class FlyingBall implements SecondDerivative {

    private double ballDiameter;    // meters
    private double ballDensity;     // kg/m^3
    private double dragCoefficient;
    private double gravAccel;       // m/s^2
    private double airDensity;
    private double ballMass;        // kg
    private double bCArea;          // Ball cross-section area, m^2

    public FlyingBall() {
        this.ballDiameter = 0.1;
        this.ballDensity = 600;
        this.dragCoefficient = 0.1;
        this.gravAccel = 9.81;
        this.airDensity = 1.29;


        double v = Math.PI * Math.pow(ballDiameter, 3) / 6;
        this.ballMass = ballDensity * v;
        this.bCArea =  0.25* Math.PI * ballDiameter * ballDiameter;
    }

    /**
     * @return the second derivative for the movement of this flying ball*/
    @Override
    public double getValue(double independentVariable, double dependentVariable, double firstDerivativeInitial) {
        double f = -this.ballMass * this.gravAccel -Math.signum(firstDerivativeInitial) * 0.5 * this.dragCoefficient *
                this.airDensity * Math.pow(firstDerivativeInitial,2) * this.bCArea;
        return f / this.ballMass;
    }
}
