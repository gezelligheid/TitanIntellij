import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Color;


public class Display extends JComponent {

    double altitude = 0;
    double massRocket = 538; //in tones

    double acceleration=0;
    double Gravity = -0.892857; // Newtons per tons
    double Thruster = 0; // Newtons per tons

    private int enginesNumber = 0;


    static Timer timer;
    public int countTimer;
    boolean chronoTimer = false;


    private int step = 10;    //step size for each event

    Graphics g;


    public Display(){

    /*
    @   Makes a timer that will make the computations at each moment
    @   according to the step size

    */
        twocomponent componentTimer2 = new twocomponent();
        //add(componentTimer2);
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask()  {
            public void run() {
                chronoTimer = true;
                onBoardComputer();
                repaint();
            } }, step, step);

    paint(g);

    }

    public boolean onBoardComputer() {

    /*
    @   Here we will put our open-loop.
    @   This method will be called at each step.
    @   The goal will be to put the conditions as to control the speed
    @   e.g. to manage the power of the rocket
    @   It is most likely that we will solve our trajectory equation here

    @   Returns true if it is okay and should return false to stop the program.
    @   e.g. false will be used later on to switch to the planetary stage, so not right now
     */

            updateStep();
            if(altitude + acceleration >=0) {
                altitude += acceleration;
                return true;
            }
            else{
                altitude = 0;   //makes the last step to land
                return true;
            }

    }

    public void updateStep(){

        /*
        @   Computes the forces at each step-size (step)
         */

        acceleration = 0; //resets the acceleration

        //adds the gravity force (1kg = 1N.kg on earth, so just the mass)
        acceleration += -massRocket;

        //adds the force of the thrusters
        acceleration = acceleration + (Thruster*enginesNumber);

        //a=F/m, so we devide the sum of the forces by the mass of the rocket
        acceleration = acceleration / massRocket;

        //prints on the terminal the informations
        System.out.println("Force = " + acceleration + " | " + (Thruster*enginesNumber) + " Engines: " + enginesNumber + " Altitude: " + altitude);

    }


    public void addThrust(double power){

        /*
        @   The max power of a SpaceX Merlin thruster is 934N/kg
        @   Here we work in tons, so it is converted
        @   The slider is scaled from 0 up to 100 (slider1)
        @   Thus we multiply the percentage of power to the max power of the rocket,
        @   as to have 934N/kg at max power
        @   We multiply the power of the engines times the number of engines also
        @   chosen from the engine slider (slider2).
         */

        Thruster = power * 0.934;

    }

    public void changeNumberOfEngines(int enginesNumber){

        /*
        @   Sets the number of engines from the GUI's slider2
         */

        this.enginesNumber = enginesNumber;

    }



    public void paintComponent(Graphics g){

        /*
        @   Makes the GUI
        @   The rocket should be made using Polygons as to work on its orientation
        @   during the flight. Or if it is possible to rotate rectangles in java
        @   It would be cool to make an animation for the flames of the rocket
        @   And later for the design of Titan

        @   The rocket is actually not moving, because we make the ground below it
        @   move and then giving the impression that the rocket is flying.

        @   Adding clouds or something could be could, maybe drawing the wind from the simulator
         */

        g.setColor(new Color(0, 200, 250));      //SKY
        g.fillRect(0, 0, 2000, 1500 );

        g.setColor(new Color(200, 250, 100));        //GROUND
        g.fillRect(0, 700 + (int) altitude, 2000, 1000 );

        g.setColor(Color.LIGHT_GRAY);   //BASE
        g.fillRect(700, 700 + (int) altitude, 100, 40);

        g.setColor(new Color(40, 120, 0));
        g.fill3DRect(740, 550, 20, 150, true);

        int[] xCone = {
                740, 760, 750
        };
        int[] yCone = {
                550, 550, 500
        };
        g.setColor(Color.RED);
        g.fillPolygon(xCone, yCone, 3);

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));

        //gives indications about the Thrust and the altitude
        g.drawString("Thrust: " + Thruster*enginesNumber, 1000, 800);
        g.drawString("Altitude: " + altitude, 1200, 100);

        //if(onBoardComputer()){repaint();} //not useful anymore
    }


    class west extends JPanel {

        /*
        @   The class for the timer event
        @   No idea how it works but I would not advise to change it lol
         */

        public west() {

        }
    }

    class twocomponent extends JComponent{
        //static Timer timer;
        west one;
        public twocomponent() {
            one = new west();
            countTimer = 100;
            timercountdown();
        }


        public void timercountdown(){
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    countTimer--;
                    //if(countTimer==0){lost=true;}
                } }, 1000, 1000);

        }
    }




}


