

public class Model4 {

    static CelestBody3[] planets = new CelestBody3[11];


    CelestBody3 Sun;
    CelestBody3 Mercury;
    CelestBody3 Venus;
    CelestBody3 Earth;
    CelestBody3 Mars;
    CelestBody3 Jupiter;
    CelestBody3 Saturn;
    CelestBody3 Uranus;
    CelestBody3 Neptune;
    CelestBody3 Titan;
    CelestBody3 SpaceCraft;

    static double[][] SUMFVectors;
    static double[][][] PVectors;
    static double[][][] FVectors;


    public final static double G = 6.67*10E-11;


    public Model4() {
        start();
    }

    public static void start() {
        CelestBody3 Sun 	= new CelestBody3(1.989*10E30, 	new Vector(0,0,0));
        CelestBody3 Mercury = new CelestBody3(0.33*10E24, 	new Vector(6.69*10E8, -4.3*10E8,-4.17));
        CelestBody3 Venus 	= new CelestBody3(4.87*10E24, 	new Vector(3.477728421647656E+01*10000,-9.612123998925466E-01*10000,-2.020103291838695E+00*10000));
        CelestBody3 Earth 	= new CelestBody3(5.87*10E24, 	new Vector(-6.271192280390987E-02*10000,-2.988491242814953E+01*10000,1.101633412416092E-03*10000));
        CelestBody3 Mars	= new CelestBody3(0.682*10E24, 	new Vector(-2.319279679672309E+01*10000,4.479321568516172E+00*10000,6.629375340168080E-01*10000));
        CelestBody3 Jupiter = new CelestBody3(1898*10E24, 	new Vector(1.233529763939601E+01*10000,-3.252405720855331E+00*10000,-2.624998782087296E-01*10000));
        CelestBody3 Saturn	= new CelestBody3(568*10E24, 	new Vector(8.868556258040849E+00*10000,2.246063396151365E+00*10000,-3.919338649448527E-01*10000));
        CelestBody3 Uranus	= new CelestBody3(86.8*10E24, 	new Vector(-3.638527457446034E+00*10000,5.459445391405725E+00*10000,6.723572113095622E-02*10000));
        CelestBody3 Neptune = new CelestBody3(102*10E24, 	new Vector(1.292292607032268E+00*10000,5.304279525500773E+00*10000,-1.390977388629209E-01*10000));
        CelestBody3 Titan	= new CelestBody3(0.13*10E24, 	new Vector(1.208193089270527E+01*10000,-1.813839579262785E+00*10000,1.381017323560965E+00*10000));
        CelestBody3 SpaceCraft = new CelestBody3(1*10E4, 	new Vector(0,11*10E3,0));

        planets[0] = Sun;
        planets[1] = Mercury;
        planets[2] = Venus;
        planets[3] = Earth;
        planets[4] = Mars;
        planets[5] = Jupiter;
        planets[6] = Saturn;
        planets[7] = Uranus;
        planets[8] = Neptune;
        planets[9] = Titan;
        planets[10] = SpaceCraft;

        for(int i=0; i<planets.length; i++) {
            planets[i].sendPlanets(planets);
        }


        for(int i=0; i<planets.length; i++) {
            planets[i].calculateGravityForce();
        }


    }

    public void sendCoords(double x, double y, int i){

        planets[i].setPos( new Vector(x,y,0) );
        //if(i==3) {System.out.println("COORD X EARTH = " + x);}

    }
    public int getX() {
        //	System.out.println("SpaceCraft x = " + (int) planets[10].getPosition().getX());
        return (int) planets[10].getPosition().getX();
    }
    public int getY() {
        //System.out.println("SpaceCraft y = " + (int) planets[10].getPosition().getY());
        return (int) planets[10].getPosition().getY();
    }
    /*public Vector getFeedBack(int i) {
        return new Vector(SUMFVectors[i][0], SUMFVectors[i][1], SUMFVectors[i][2]);
    }*/
    public void printStartFB(int i) {
        printFSC(i);
    }
    public static void printFSC(int i) {
        System.out.println("Force on x = " + SUMFVectors[i][0]);
        System.out.println("Force on y = " + SUMFVectors[i][1]);
        System.out.println("Force on z = " + SUMFVectors[i][2]);
    }

    public void update() {

        for(int i=0; i<planets.length; i++) {
            planets[i].sendPlanets(planets);
        }

        for(int i = 0; i < planets.length; i++) {
            planets[i].setAcceleration(new Vector(0,0,0));
            planets[i].setPull(new Vector(0,0,0));

        }
        //get acceleration	-> we don't need force
        //Calculate acceleration of each pair of object only once
        for(int i = 0; i < planets.length; i++) {	//first planet
            for(int j = 1; j < planets.length - i; j++) {        //other planet
                Vector distance = planets[i].getPosition().subtract(planets[i + j].getPosition());	//from j to i
                double d = distance.squareLength();
                //System.out.println("Distance1 = " + distance.length());
                distance = distance.normalize();
                planets[i + j].addAcceleration(distance.multiply(G * planets[i].getMass() / d));
                planets[i].addAcceleration(distance.multiply(-1 * G * planets[i + j].getMass() / d));
            }
        }

        for(int i=0; i<planets.length; i++)
            planets[i].updateVelPos(5*1000);


        Forces();

    }

    public static void Forces(){

        PVectors = new double[planets.length][planets.length][3];
        FVectors = new double[planets.length][planets.length][3];
        SUMFVectors = new double[planets.length][3];

        for(int main=0; main<planets.length; main++) {
            for(int i=0; i<planets.length; i++) {

                if(main!=i) {
                    PVectors[main][i][0] = (planets[i].getPosition().getX() - planets[main].getPosition().getX() ) ;
                    PVectors[main][i][1] = (planets[i].getPosition().getY() - planets[main].getPosition().getY() ) ;
                    PVectors[main][i][2] = (planets[i].getPosition().getZ() - planets[main].getPosition().getZ() ) ;

                    //System.out.println(PVectors[main][i][0]);
                    //System.out.println(PVectors[main][i][1]);
                    //System.out.println(PVectors[main][i][2]);
                }


            }

            for(int j=0; j<3; j++) {
                for(int i=0; i<planets.length; i++) {
                    if(main!=i && PVectors[main][i][j] != 0) {
                        FVectors[main][i][j] = G*( (planets[main].getMass() * planets[i].getMass())
                                * (PVectors[main][i][j] / (Math.abs(Math.pow(PVectors[main][i][j], 3)))));
                        //System.out.println("Force " + main + " - " + i + "  " + j + " is = " + FVectors[main][i][j]);
                    }
                }

            }

            for(int j=0; j<3; j++) {
                for(int i=0; i<planets.length; i++) {
                    if(i!=main) {
                        SUMFVectors[main][j] += (double) (FVectors[main][i][j]);
                    }
                }
                System.out.println(main + " xyz " + j + " "+ SUMFVectors[main][j]);
            }

        }
        //printFSC(3);
    }




}
