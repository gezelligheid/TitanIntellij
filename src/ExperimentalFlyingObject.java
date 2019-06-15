public class ExperimentalFlyingObject implements SecondDerivative {
    private final double objectDiameter;    // meters
    private final double objectDensity;     // kg/m^3
    private final double dragCoefficient;
    private double gravAccel;       // m/s^2
    private double airDensity;
    private double fuelMass; // kg
    private double objectMass;// kg
    private final double oCrossArea;// object cross-section area, m^2

    public ExperimentalFlyingObject(double initialFuel) {
        //TODO properly set object dimensions
        //
        this.objectDiameter = 10.3; //soyuz 10.3 meters
        this.objectDensity = 12; // kg/m^3 (capsule/payload only)
        //TODO properly set drag coefficient
        this.dragCoefficient = 0.1; // approximation for well streamlined body
        // gravitational acceleration and air density change with altitude/position see code below
        this.gravAccel = 9.81;
        this.airDensity = 1.29;
        //TODO determine the rate of burning etc
        this.fuelMass = initialFuel; // initial filling


        double v = Math.PI * Math.pow(objectDiameter, 3) / 6;
        this.objectMass = objectDensity * v + fuelMass;
        this.oCrossArea = 0.25 * Math.PI * objectDiameter * objectDiameter;
    }

    /**
     * @return the second derivative value for the movement of this flying object at a given speed
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
     * @param distanceFromSeaInKM distance from earths surface at sea level
     */
    public void setGravAccel(double distanceFromSeaInKM) {
        this.gravAccel = 9.81 / Math.pow((6400 + distanceFromSeaInKM) / 6400, 2);
    }

    /**
     * determines the air density at a given altitude by formula for different atmosphere layers
     *
     * @param distanceFromSeaInKM the distance from the earths surface
     */
    public void setAirDensity(double distanceFromSeaInKM) {
        // base height and density, and standard temperature used to determine density in each layer
        double baseHeight = 0; // km (sea level)
        double baseDensity = 1.29; // kg/m^3 (sea level)
        double standardTemperature = 288.15; // kelvin (sea level)
        double temperatureLapseRate = 0.0065; // Kelvin/m

        // some physical constants needed
        final double molarAirMass = 0.0289644; // kg/mol
        final double universalGasConstant = 8.3144598; // N·m/(mol·K)
        final double gravAccelSea = 9.80665; // m/s^2

        // represents different atmospheric layers
        if (distanceFromSeaInKM >= 71) {
            baseHeight = 71;
            baseDensity = 0.000064;
            standardTemperature = 214.65;
            temperatureLapseRate = -0.002;
        } else if (distanceFromSeaInKM >= 51) {
            baseHeight = 51;
            baseDensity = 0.00086;
            standardTemperature = 270.65;
            temperatureLapseRate = -0.0028;
        } else if (distanceFromSeaInKM >= 47) {
            baseHeight = 47;
            baseDensity = 0.00143;
            standardTemperature = 270.65;
            temperatureLapseRate = 0;
        } else if (distanceFromSeaInKM >= 32) {
            baseHeight = 32;
            baseDensity = 0.01322;
            standardTemperature = 228.65;
            temperatureLapseRate = 0.0028;
        } else if (distanceFromSeaInKM >= 20) {
            baseHeight = 20;
            baseDensity = 0.08803;
            standardTemperature = 216.65;
            temperatureLapseRate = 0.001;
        } else if (distanceFromSeaInKM >= 11) {
            baseHeight = 11;
            baseDensity = 0.36391;
            standardTemperature = 216.65;
            temperatureLapseRate = 0;
        }
        this.airDensity = baseDensity *
                Math.exp((gravAccelSea * molarAirMass * (distanceFromSeaInKM - baseHeight)) /
                        (universalGasConstant * standardTemperature));

    }

    /**
     * access te fuel tank of the flying object
     *
     * @param kgsToBurn The amount of fuel to "burn".
     */
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

    public double getAirDensity() {
        return airDensity;
    }

    public double getCapsuleMass(){
        return objectDensity *(Math.PI * Math.pow(objectDiameter, 3) / 6);
    }

    public double getGravAccel() {
        return gravAccel;
    }
}
