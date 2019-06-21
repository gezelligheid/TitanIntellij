import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
public class Solar extends Application{

    public static Sphere SUN = new Sphere(40);
    public static Sphere EARTH = new Sphere(25);
    public static Sphere VENUS = new Sphere(25);
    public static Sphere MARS = new Sphere(25);
    public static Sphere JUPITER = new Sphere(25);
    public static Sphere URANUS = new Sphere(25);
    public static Sphere NEPTUNE = new Sphere(25);
    public static Sphere SATURN = new Sphere(25);
    public static Sphere MERCURY = new Sphere(25);
    public static Sphere SPACECRAFT = new Sphere(25);
    public static Sphere TITAN = new Sphere(20);
    public static Sphere[] Planets = new Sphere[11];






    final double SUN_MASS = 1.989e30;
    final double SUN_RADIUS = 695700000.0;
    double EARTH_X0 = -147_095_000_000.0;
    double EARTH_Y0 = 0.0;
    Vector earthPosition = new Vector(EARTH_X0, EARTH_Y0, 0);
    Vector spacePos = new Vector(EARTH_X0+500, EARTH_Y0, 0);
    double EARTH_VX0 = 0.0;
    double EARTH_VY0 = -30300.0;
    Vector earthVelocity = new Vector(EARTH_VX0, EARTH_VY0, 0);
    final double EARTH_MASS = 5.972e24;
    final double EARTH_RADIUS = 6371000.0;
    final double dt = 86400.0/3; // 1 earth day in seconds 86400.0

    final Vector SUN_POS0 = new Vector(-3.314343410245739E+08,  1.139474540626986E+09, -2.931042498752882E+06);
    final Vector MERCURY_POS0 = new Vector(-4.381491758075624E+10, -5.095427167075149E+10, -2.706828308744654E+08);
    final Vector VENUS_POS0 = new Vector(-107480000000., 0, 0);
    final Vector MARS_POS0 = new Vector(-206620000000., 0, 0);
    final Vector JUPITER_POS0 = new Vector(-740520000000., 0, 0);
    final Vector SATURN_POS0 = new Vector(-1352550000000., 0, 0);
    final Vector TITAN_POS0 = new Vector(4.283742844521295E+11, -1.440891854834355E+12,  8.537522025083423E+9);
    final Vector URANUS_POS0 = new Vector(-2741300000000., 0, 0);
    final Vector NEPTUNE_POS0 = new Vector(-4444450000000., 0, 0);

    final Vector SUN_VEL0 = new Vector(-1.455742265779304E+01,  1.323658286241915E-01,  3.785824592599174E-01);
    final Vector MERCURY_VEL0 = new Vector(2.750293030951233E+04, -2.895910747600353E+04, -4.890354985933278E+03);
    final Vector VENUS_VEL0 = new Vector(0., -35260, 0);
    final Vector MARS_VEL0 = new Vector(0., -26500, 0);
    final Vector JUPITER_VEL0 = new Vector(0., -13720, 0);
    final Vector SATURN_VEL0 = new Vector(0., -10180, 0);
    final Vector TITAN_VEL0 = new Vector(1.416955908131419E+04,  1.503346198931311E+03, -3.059367073094467E+2);
    final Vector URANUS_VEL0 = new Vector(0., -7110, 0);
    final Vector NEPTUNE_VEL0 = new Vector(0., -5500, 0);

    final double MERCURY_MASS = .3301e24;//kilograms
    final double VENUS_MASS = 4.8675e24;
    final double MARS_MASS = 6.4171e23;
    final double JUPITER_MASS = 1898.19e24;
    final double SATURN_MASS = 568.34e24;
    final double TITAN_MASS = 13455.3e19;
    final double URANUS_MASS = 86.813e24;
    final double NEPTUNE_MASS = 102.413e24;

    final double MERCURY_RADIUS = 2439700;//meters
    final double VENUS_RADIUS = 6051800;
    final double MARS_RADIUS = 3389500;
    final double JUPITER_RADIUS = 71492000;
    final double SATURN_RADIUS = 54364000;
    final double TITAN__RADIUS = 2575500;
    final double URANUS_RADIUS = 24973000;
    final double NEPTUNE_RADIUS = 24341000;



    AlternativeBody sun = new AlternativeBody("Sun", SUN_POS0, SUN_VEL0, SUN_MASS, SUN_RADIUS);
    AlternativeBody earth = new AlternativeBody("Earth", earthPosition, earthVelocity, EARTH_MASS, EARTH_RADIUS);
    AlternativeBody mercury = new AlternativeBody("Mercury", MERCURY_POS0, MERCURY_VEL0, MERCURY_MASS, MERCURY_RADIUS);
    AlternativeBody venus = new AlternativeBody("Venus", VENUS_POS0, VENUS_VEL0, VENUS_MASS, VENUS_RADIUS);
    AlternativeBody mars = new AlternativeBody("Mars", MARS_POS0, MARS_VEL0, MARS_MASS, MARS_RADIUS);
    AlternativeBody jupiter = new AlternativeBody("Jupiter", JUPITER_POS0, JUPITER_VEL0, JUPITER_MASS, JUPITER_RADIUS);
    AlternativeBody saturn = new AlternativeBody("Saturn", SATURN_POS0, SATURN_VEL0, SATURN_MASS, SATURN_RADIUS);
    AlternativeBody titan = new AlternativeBody("Titan", TITAN_POS0,TITAN_VEL0,TITAN_MASS,TITAN__RADIUS);
    AlternativeBody uranus = new AlternativeBody("Uranus", URANUS_POS0, URANUS_VEL0, URANUS_MASS, URANUS_RADIUS);
    AlternativeBody neptune = new AlternativeBody("Neptune", NEPTUNE_POS0, NEPTUNE_VEL0, NEPTUNE_MASS, NEPTUNE_RADIUS);
    AlternativeBody spacecraft = new AlternativeBody("spacecraft",  spacePos, new Vector(-150000, -1000, 0), 7080, 3);


    private double daysPassed = 0;
    private double yearsPassed = 0;
    @Override
    public void start(Stage primaryStage) throws Exception, CloneNotSupportedException  {

        Planets[0] = EARTH;
        Planets[1] = VENUS;
        Planets[2] = MARS;
        Planets[3] = JUPITER;
        Planets[4] = URANUS;
        Planets[5] = NEPTUNE;
        Planets[6] = SATURN;
        Planets[7] = MERCURY;
        Planets[8] = SUN;
        Planets[9] = SPACECRAFT;
        Planets[10] = TITAN;

        List<AlternativeBody> solarSystem = new ArrayList<>();
        solarSystem.add(earth);
        solarSystem.add(venus);
        solarSystem.add(mars);
        solarSystem.add(jupiter);
        solarSystem.add(uranus);
        solarSystem.add(neptune);
        solarSystem.add(saturn);
        solarSystem.add(mercury);
        solarSystem.add(sun);
        solarSystem.add(spacecraft);
        solarSystem.add(titan);

        List<Text> planetNames = new ArrayList<>();

        for (int i=0; i<solarSystem.size() ; i++) {
            planetNames.add(new Text(solarSystem.get(i).getName()));
        }



        Group grid = new Group();

        Text t = new Text();
        t.setFont(new Font(100));
        t.translateXProperty().set(50);
        t.translateYProperty().set(-100);
        t.setText("Days: "+daysPassed+" Years: "+yearsPassed);


        for (int i=0; i<solarSystem.size() ; i++) {
            // grid.getChildren().add(planetNames.get(i));
            planetNames.get(i).setFont(new Font(20));
            planetNames.get(i).setStrokeWidth(200);
        }


        double[][] cords = new double[solarSystem.size()][2];
        for(int i=0; i<solarSystem.size(); i++){
            cords[i][0] = solarSystem.get(i).getPosition().getX() / 1.497e11;
            cords[i][1] = solarSystem.get(i).getPosition().getY() / 1.497e11;

            System.out.println(cords[i][0]+" "+cords[i][1]);
        }
        Camera camera = new PerspectiveCamera();
        camera.translateXProperty().set(camera.getTranslateX()-300);
        camera.translateYProperty().set(camera.getTranslateY()-300);
        camera.translateZProperty().set(camera.getTranslateZ()-15000);



        for(int i =1; i<Planets.length; i++){
            Planets[i].translateXProperty().set(cords[i][0]);
            Planets[i].translateYProperty().set(cords[i][1]);
        }

        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long now){
                double[][] cards = new double[solarSystem.size()][2];
                daysPassed += 0.33;

                List<AlternativeBody> solarCopy = // copy solar system so planet move all at once
                        (List<AlternativeBody>) ((ArrayList<AlternativeBody>) solarSystem).clone();
                for(int i=0; i<solarSystem.size(); i++){
                    try{
                        solarSystem.get(i).step(dt, solarCopy);
                    }
                    catch(CloneNotSupportedException e){
                        System.out.println("NO GOOD");
                    }

                    cards[i][0] = solarSystem.get(i).getPosition().getX() / 1.497e8;
                    cards[i][1] = solarSystem.get(i).getPosition().getY() / 1.497e8;
                }

                for(int i=0; i<Planets.length; i++){
                    Planets[i].translateXProperty().set(cards[i][0]);
                    Planets[i].translateYProperty().set(cards[i][1]);

                    //System.out.println(solarSystem.get(i).getName()+" "+solarSystem.get(0).getPosition().toString());

                    // planetNames.get(i).translateXProperty().set(cards[i][0]);
                    // planetNames.get(i).translateYProperty().set(cards[i][0]+5);

                }
                if(daysPassed>365){
                    daysPassed = 0;
                    yearsPassed++;
                }
                t.setText("Days: "+Double.toString(daysPassed)+" Years: "+Double.toString(yearsPassed));
               /*if (Math.abs(solarSystem.get(9).getPosition().getX()-solarSystem.get(0).getX())<1000) {
                   camera.translateZProperty().set(camera.getTranslateZ()+100);
                   camera.translateXProperty().set(solarSystem.get(0)-10000);
                   camera.translateYProperty().set(camera.getTranslateY()-10000);
               }

              // System.out.println("Mercury: "+solarSystem.get(1).getPosition().toString());

              /* camera.translateYProperty().set(cards[9][1]-100);
               camera.translateXProperty().set(cards[9][0]-100);*/
            }
        };
        animator.start();



        // camera.translateYProperty().set(425);
        //camera.translateYProperty().set(250);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case U:
                    camera.translateZProperty().set(camera.getTranslateZ()+100);
                    break;
                case J:
                    camera.translateZProperty().set(camera.getTranslateZ()-100);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX()+50);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX()-50);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY()+50);
                    break;
                case W:
                    camera.translateYProperty().set(camera.getTranslateY()-50);
                    break;
                case T:
                    animator.stop();
                    break;
                case O:
                    animator.start();
                    break;
            }
        } );

        grid.getChildren().add(SUN);
        grid.getChildren().addAll(EARTH, VENUS, MARS, JUPITER, URANUS, NEPTUNE, SATURN, MERCURY, SPACECRAFT, TITAN,t);

        Scene scene = new Scene(grid, 1000, 800);
        scene.setCamera(camera);
        primaryStage.setTitle("Solar");
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius Sphere.
        arcTo.setY(centerY - radiusY);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        //arcTo.setXAxisRotation(45);

        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(centerX - radiusX, centerY - radiusY),
                arcTo,
                new ClosePath());
        path.setStroke(Color.BLACK);
        path.getStrokeDashArray().setAll(5d, 5d);
        return path;
    }
    public static void move(Sphere c){
        PathTransition path = new PathTransition();
        path.setDuration(Duration.millis(1500));
        path.setNode(c);
        path.setPath(new Circle(200, 200, 100));
        path.setCycleCount(PathTransition.INDEFINITE);

        path.play();
    }
    public static void main(String[] args) {
        launch(args);

    }

}
