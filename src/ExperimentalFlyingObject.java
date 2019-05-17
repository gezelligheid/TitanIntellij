public class ExperimentalFlyingObject implements SecondDerivative {
    private double objectDiameter;    // meters
    private double objectDensity;     // kg/m^3
    private double dragCoefficient;
    private double gravAccel;       // m/s^2
    private double airDensity;
    private double fuelMass; // kg
    private double objectMass;// kg
    private double oCrossArea;// object cross-section area, m^2

    public ExperimentalFlyingObject(double initialFuel) {
        this.objectDiameter = 0.1;
        this.objectDensity = 600;
        this.dragCoefficient = 0.1;
        // gravitational acceleration and airdensity change with altitude/position
        this.gravAccel = 9.81;
        this.airDensity = 1.29;
        this.fuelMass = initialFuel; // initial filling


        double v = Math.PI * Math.pow(objectDiameter, 3) / 6;
        this.objectMass = objectDensity * v + fuelMass;
        this.oCrossArea = 0.25 * Math.PI * objectDiameter * objectDiameter;
    }

    /**
     * @return the second derivative for the movement of this flying ball
     */
    @Override
    public double getValue(double independentVariable, double dependentVariable, double firstDerivativeInitial) {
        double f = -this.objectMass * this.gravAccel - Math.signum(firstDerivativeInitial) * 0.5 * this.dragCoefficient *
                this.airDensity * Math.pow(firstDerivativeInitial, 2) * this.oCrossArea;
        return f / this.objectMass;
    }

    /**
     * sets the gravitational acceleration the object is dealing with by
     * formula for adjusting the gravitational acceleration near planet earth.
     * 6400km is the distance between the core of the earth and sea level.
     *
     * @param distanceFromSeaInKM distance from earths surface at sea level*/
    public void setGravAccel(double distanceFromSeaInKM) {
        this.gravAccel = 9.81 / Math.pow((6400 + distanceFromSeaInKM)/6400, 2);
    }

    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }

    /**
     * access te fuel tank of the flying object
     * @param kgsToBurn The amount of fuel to "burn".
     * */
    public void burnFuel(double kgsToBurn) {
        try {
            if (kgsToBurn > 0) {
                if (kgsToBurn <= this.fuelMass) {
                    this.fuelMass -= kgsToBurn;
                    this.objectMass -= kgsToBurn;
                } else {
                    throw new ArithmeticException("out of fuel");
                }
            } else
                throw new ArithmeticException("can't burn negative fuel amount");
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
