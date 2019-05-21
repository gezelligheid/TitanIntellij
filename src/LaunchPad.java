import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class LaunchPad {

    private static JFrame frame;

    public static void main(String[] args){

        frame = new JFrame("Launch Sequence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        //our GUI + physics
        Display disp = new Display();


        /*
        @   This is our first slider that will deal with the Thrust Power
        @   It gives the same amount of power to every engine at the same time
        @   Goes from 0 to 100. Those are percentages of power
         */
        JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(10);
        slider.setPaintLabels(true);
        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        position.put(0, new JLabel("0"));
        position.put(50, new JLabel("Mid"));
        position.put(100, new JLabel("Max Thrust"));
        slider.setLabelTable(position);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                //calls the method from Disp to get the new Thrust value
                if(!disp.isAutopilot()){disp.addThrust( ((JSlider)e.getSource()).getValue());}
                else{
                    slider.setValue(disp.getAutoPilotPower());
                    disp.addThrust( ((JSlider)e.getSource()).getValue());
                }
            }
        });


        /*
        @   Second slider to give the number of engines used
        @   We currently have 9 different engines
        @   Goes from 0 to 9, 0 being no engines turned on and 9 all engines up
         */
        JSlider slider2 = new JSlider(JSlider.VERTICAL, 0, 9, 0);
        slider2.setMajorTickSpacing(25);
        slider2.setMinorTickSpacing(10);
        slider2.setPaintLabels(true);
        Hashtable<Integer, JLabel> position2 = new Hashtable<Integer, JLabel>();
        position2.put(0, new JLabel("Engines Down"));
        position2.put(5, new JLabel("5 Engines"));
        position2.put(9, new JLabel("9 Engines"));
        slider2.setLabelTable(position2);

        slider2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                //calls the method to change the number of engines being used
                if(!disp.isAutopilot()){disp.changeNumberOfEngines( ((JSlider)e.getSource()).getValue());}
            else{
                slider2.setValue(disp.getAutoPilotEngine());
                disp.changeNumberOfEngines( ((JSlider)e.getSource()).getValue());
                }
            }
        });


       /*
       @    We set a GridLayout to display our sliders at the right location on the left of the screen
       @    A larger number of columns will result with thiner sliders
        */
        disp.setLayout(new GridLayout(0, 10));
        disp.add(slider);
        disp.add(slider2);

        //ads the GUI to the frame
        frame.setContentPane(disp);
        frame.setVisible(true);

    }

}
