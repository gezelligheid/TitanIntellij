import javafx.animation.KeyFrame;
import javafx.animation.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.Group;


import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXApplication3 extends Application {

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private double timer = 100;

    private boolean firstLaunch = false;
    private double[] SpaceCraftCoords = new double[2];

    private double oldCheck = 0;

    Sphere sun;
    Sphere Mercury;
    Sphere venus;
    Sphere earth;
    Sphere mars;
    Sphere jupiter;
    Sphere satrun;
    Sphere uranus;
    Sphere neptune;
    Sphere titan;
    Sphere SpaceCraft;


    Sphere[] planets = new Sphere[11];

    Model4 model;

    @Override
    public void start(Stage primaryStage) {

        sun = new Sphere(34.7*0.5);
        Mercury = new Sphere(2*0.5);
        venus = new Sphere(3*0.5);
        earth = new Sphere(3.15*0.5);
        mars = new Sphere(1.7*0.5);
        jupiter = new Sphere(35*0.5);
        satrun = new Sphere(27.8*0.5);
        uranus = new Sphere(3*12.6*0.5);
        neptune = new Sphere(12.3*0.5);
        titan = new Sphere(1.28*0.5);
        SpaceCraft = new Sphere(70);

        //  model = new Model3();




        Ellipse pathMercury = createPath(sun.getBoundsInLocal().getWidth() / 2.0 + 1 * (290*0.5), sun.getBoundsInLocal().getHeight() / 2.0 + 1 * (290*0.5));
        Ellipse pathVenus = createPath(Mercury.getBoundsInLocal().getWidth() / 2.0 + 1 * (566*0.5), Mercury.getBoundsInLocal().getHeight() / 2.0 + 0.9993 * (566*0.5));
        Ellipse pathEarth = createPath(venus.getBoundsInLocal().getWidth() / 2.0 + 1 * (748*0.5), venus.getBoundsInLocal().getHeight() / 2.0 + 0.983 * (748*0.5));
        Ellipse pathmars = createPath(earth.getBoundsInLocal().getWidth() / 2.0 + 1 * (1139*0.5), earth.getBoundsInLocal().getHeight() / 2.0 + 0.906 * (1139*0.5));
        Ellipse pathsaturn = createPath(jupiter.getBoundsInLocal().getWidth() / 2.0 + 1 * (7167.5*0.5), jupiter.getBoundsInLocal().getHeight() / 2.0 + 0.943 * (7167.5*0.5));
        Ellipse pathUranus = createPath(satrun.getBoundsInLocal().getWidth() / 2.0 + 1 * (14362*0.5), satrun.getBoundsInLocal().getHeight() / 2.0 + 0.954 * (14362*0.5));
        Ellipse pathNeptune = createPath(uranus.getBoundsInLocal().getWidth() / 2.0 + 1 * (22475*0.5), uranus.getBoundsInLocal().getHeight() / 2.0 + 0.98 * (22475*0.5));
        Ellipse pathjupiter = createPath(mars.getBoundsInLocal().getWidth() / 2.0 + 1 * (3893*0.5), mars.getBoundsInLocal().getHeight() / 2.0 + 0.951 * (3893*0.5));



        PhongMaterial material9 = new PhongMaterial();
        material9.setDiffuseMap(getImageFromResource("/sun.jpg"));
        sun.setMaterial(material9);

        Ellipse ellipseMercury = new Ellipse();
        ellipseMercury.setRadiusX(sun.getBoundsInLocal().getWidth() / 2.0 + 1 * (290*0.5));
        ellipseMercury.setRadiusY(sun.getBoundsInLocal().getHeight() / 2.0 + 1 * (290*0.5));
        PhongMaterial material2 = new PhongMaterial();
        material2.setDiffuseMap(getImageFromResource("/mercurymap.jpg"));
        Mercury.setMaterial(material2);

        // Ellipse pathMercury = createPath(sun.getBoundsInLocal().getWidth() / 2.0 + 1 * (290*0.5), sun.getBoundsInLocal().getHeight() / 2.0 + 1 * (290*0.5));


        //PathTransition to move objects through a path
        PathTransition transitionMercury = new PathTransition();
        //setpath > the shape the path will take .. in circly shape/ ellipse
        transitionMercury.setPath(ellipseMercury);
        transitionMercury.setNode(Mercury);
        transitionMercury.setInterpolator(Interpolator.LINEAR);
        transitionMercury.setDuration(Duration.seconds(timer*Math.pow(0.387098, 1.5)));
        transitionMercury.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //will continue forever
        transitionMercury.setCycleCount(Timeline.INDEFINITE);

        transitionMercury.play();
        rotatePlanet(Mercury);

        Ellipse ellipseVenus = new Ellipse();
        ellipseVenus.setRadiusX(Mercury.getBoundsInLocal().getWidth() / 2.0 + 1 * (566*0.5));
        ellipseVenus.setRadiusY(Mercury.getBoundsInLocal().getHeight() / 2.0 + 0.9993 * (566*0.5));
        PhongMaterial material3 = new PhongMaterial();
        material3.setDiffuseMap(getImageFromResource("/venus.jpg"));
        venus.setMaterial(material3);

        //Ellipse pathVenus = createPath(Mercury.getBoundsInLocal().getWidth() / 2.0 + 1 * (566*0.5), Mercury.getBoundsInLocal().getHeight() / 2.0 + 0.9993 * (566*0.5));

        PathTransition transitionVenus = new PathTransition();
        transitionVenus.setPath(ellipseVenus);
        transitionVenus.setNode(venus);
        transitionVenus.setDuration(Duration.seconds(timer*Math.pow(0.723330, 1.5)));
        transitionVenus.setInterpolator(Interpolator.LINEAR);
        transitionVenus.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionVenus.setCycleCount(Timeline.INDEFINITE);

        transitionVenus.play();
        rotatePlanet(venus);

        Ellipse ellipseEarth = new Ellipse();
        ellipseEarth.setRadiusX(venus.getBoundsInLocal().getWidth() / 2.0 + 1 * (748*0.5));
        ellipseEarth.setRadiusY(venus.getBoundsInLocal().getHeight() / 2.0 + 0.983 * (748*0.5));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(getImageFromResource("/earth.jpg"));
        earth.setMaterial(material);

        PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(earth);

        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(timer));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);

        transitionEarth.play();
        rotatePlanet(earth);

        Ellipse ellipseMars = new Ellipse();
        ellipseMars.setRadiusX(earth.getBoundsInLocal().getWidth() / 2.0 + 1 * (1139*0.5));
        ellipseMars.setRadiusY(earth.getBoundsInLocal().getHeight() / 2.0 + 0.906 * (1139*0.5));
        PhongMaterial material4 = new PhongMaterial();
        material4.setDiffuseMap(getImageFromResource("/mars.jpg"));
        mars.setMaterial(material4);

        PathTransition transitionMars = new PathTransition();
        transitionMars.setPath(ellipseMars);
        transitionMars.setNode(mars);
        transitionMars.setInterpolator(Interpolator.LINEAR);
        transitionMars.setDuration(Duration.seconds(timer * Math.pow(1.523688, 1.5)));
        transitionMars.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionMars.setCycleCount(Timeline.INDEFINITE);

        transitionMars.play();
        rotatePlanet(mars);

        Ellipse ellipseJupiter = new Ellipse();
        ellipseJupiter.setRadiusX(mars.getBoundsInLocal().getWidth() / 2.0 + 1 * (3893*0.5));
        ellipseJupiter.setRadiusY(mars.getBoundsInLocal().getHeight() / 2.0 + 0.951 * (3893*0.5));
        PhongMaterial material5 = new PhongMaterial();
        material5.setDiffuseMap(getImageFromResource("/jupiter.jpg"));
        jupiter.setMaterial(material5);

        PathTransition transitionJupiter = new PathTransition();
        transitionJupiter.setPath(ellipseJupiter);
        transitionJupiter.setNode(jupiter);
        transitionJupiter.setInterpolator(Interpolator.LINEAR);
        transitionJupiter.setDuration(Duration.seconds(timer * Math.pow(5.20256  , 1.5)));
        transitionJupiter.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionJupiter.setCycleCount(Timeline.INDEFINITE);

        transitionJupiter.play();
        rotatePlanet(jupiter);

        Ellipse ellipseSatrun = new Ellipse();
        ellipseSatrun.setRadiusX(jupiter.getBoundsInLocal().getWidth() / 2.0 + 1 * (7167.5*0.5));
        ellipseSatrun.setRadiusY(jupiter.getBoundsInLocal().getHeight() / 2.0 + 0.943 * (7167.5*0.5));
        PhongMaterial material6 = new PhongMaterial();
        material6.setDiffuseMap(getImageFromResource("/saturn.jpg"));
        satrun.setMaterial(material6);

        PathTransition transitionSatrun = new PathTransition();
        transitionSatrun.setPath(ellipseSatrun);
        transitionSatrun.setNode(satrun);
        transitionSatrun.setInterpolator(Interpolator.LINEAR);
        transitionSatrun.setDuration(Duration.seconds(timer * Math.pow(9.55475 , 1.5)));
        transitionSatrun.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionSatrun.setCycleCount(Timeline.INDEFINITE);

        transitionSatrun.play();
        rotatePlanet(satrun);

        Ellipse ellipseTitan = new Ellipse();
        ellipseTitan.setRadiusX(satrun.getBoundsInLocal().getWidth() / 2.0 + (1 *12*0.5));
        ellipseTitan.setRadiusY(satrun.getBoundsInLocal().getHeight() / 2.0 + (9.9112*12*0.5));

        PhongMaterial material10 = new PhongMaterial();
        material10.setDiffuseMap(getImageFromResource("/titan.jpg"));
        titan.setMaterial(material10);

        PathTransition transitionTitan = new PathTransition();
        transitionTitan.setPath(ellipseTitan);
        transitionTitan.setNode(titan);
        transitionTitan.setInterpolator(Interpolator.LINEAR);
        transitionTitan.setDuration(Duration.seconds(timer * (15.945421 / 365.2422)));
        transitionTitan.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionTitan.setCycleCount(Timeline.INDEFINITE);

        transitionTitan.play();
        rotatePlanet(titan);

        Ellipse ellipseUranus = new Ellipse();
        ellipseUranus.setRadiusX(satrun.getBoundsInLocal().getWidth() / 2.0 + 1 * (14362*0.5));
        ellipseUranus.setRadiusY(satrun.getBoundsInLocal().getHeight() / 2.0 + 0.954 * (14362*0.5));
        PhongMaterial material7 = new PhongMaterial();
        material7.setDiffuseMap(getImageFromResource("/uranus.jpg"));
        uranus.setMaterial(material7);

        PathTransition transitionUranus = new PathTransition();
        transitionUranus.setPath(ellipseUranus);
        transitionUranus.setNode(uranus);
        transitionUranus.setInterpolator(Interpolator.LINEAR);
        transitionUranus.setDuration(Duration.seconds(timer* Math.pow(19.18171, 1.5)));
        transitionUranus.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionUranus.setCycleCount(Timeline.INDEFINITE);

        transitionUranus.play();
        rotatePlanet(uranus);

        Ellipse ellipseNeptune = new Ellipse();
        ellipseNeptune.setRadiusX(uranus.getBoundsInLocal().getWidth() / 2.0 + 1 * (22475*0.5));
        ellipseNeptune.setRadiusY(uranus.getBoundsInLocal().getHeight() / 2.0 + 0.98 * (22475*0.5));
        PhongMaterial material8 = new PhongMaterial();
        material8.setDiffuseMap(getImageFromResource("/neptune.jpg"));
        neptune.setMaterial(material8);

        PathTransition transitionNeptune = new PathTransition();
        ellipseNeptune.setStroke(Color.RED);
        ellipseNeptune.setStrokeWidth(10);
        transitionNeptune.setPath(ellipseNeptune);
        transitionNeptune.setNode(neptune);
        transitionNeptune.setInterpolator(Interpolator.LINEAR);
        transitionNeptune.setDuration(Duration.seconds(timer * Math.pow(30.05826, 1.5)));
        transitionNeptune.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionNeptune.setCycleCount(Timeline.INDEFINITE);

        transitionNeptune.play();
        rotatePlanet(neptune);





        ellipseMercury.setVisible(false);
        ellipseVenus.setVisible(false);
        ellipseEarth.setVisible(false);
        ellipseMars.setVisible(false);
        ellipseJupiter.setVisible(false);
        ellipseSatrun.setVisible(false);
        ellipseUranus.setVisible(false);
        ellipseNeptune.setVisible(false);
        ellipseTitan.setVisible(false);


        Pane moonPane = new Pane();
        moonPane.translateXProperty().bind(satrun.translateXProperty());
        moonPane.translateYProperty().bind(satrun.translateYProperty());
        moonPane.setMaxSize(200, 200);
        moonPane.getChildren().add(titan);

        StackPane root = new StackPane();

        Image pic = new Image("file:hi.jpg");
        ImageView mv = new ImageView(pic);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(mv,root);

        root.getChildren().addAll(sun, ellipseMercury, ellipseVenus,ellipseEarth,
                ellipseMars, ellipseJupiter, ellipseSatrun,
                ellipseUranus,ellipseNeptune, ellipseTitan , venus,
                Mercury,earth, mars, jupiter,satrun,uranus, neptune, moonPane, pathjupiter, pathmars,
                pathUranus, pathsaturn, pathNeptune, pathEarth, pathVenus, pathMercury  );

        Camera camera = new PerspectiveCamera();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case S:
                    camera.translateZProperty().set(camera.getTranslateZ() + 100);
                    break;
                case Q:
                    camera.translateZProperty().set(camera.getTranslateZ() - 100);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() + 50);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() - 50);
                    break;
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() + 50);
                    break;
                case Z:
                    camera.translateYProperty().set(camera.getTranslateY() - 50);
                    break;
            }
        });


        Scene scene = new Scene(pane, 1300, 800, Color.BLACK);
        scene.setCamera(camera);
        initMouseControl(root, scene);
        primaryStage.setTitle("Solar System");
        primaryStage.setScene(scene);
        primaryStage.show();





        System.out.println("THIIIS " + sun.getLayoutX());
        System.out.println("THIIIS " + sun.getLayoutY());
        launchSolar(primaryStage);


        //paths of the orbits




    }

    public void launchSolar(Stage stage) {

        model = new Model4();

        planets[0] = sun;
        planets[1] = Mercury;
        planets[2] = venus;
        planets[3] = earth;
        planets[4] = mars;
        planets[5] = jupiter;
        planets[6] = satrun;
        planets[7] = uranus;
        planets[8] = neptune;
        planets[9] = titan;

        //   SpaceCraft.setLayoutX(planets[3].localToScreen(planets[3].getBoundsInLocal()).getMinX() + (3.15*0.5));	//adds Earth coord + radius to get the surface
        //  SpaceCraft.setLayoutY(planets[3].localToScreen(planets[3].getBoundsInLocal()).getMinY() + (3.15*0.5));
        //SpaceCraft.setLayoutX(planets[3].localToScreen(planets[3].getBoundsInLocal()).getMaxX() + (3.15*0.5));	//adds Earth coord + radius to get the surface
        //SpaceCraft.setLayoutY(planets[3].localToScreen(planets[3].getBoundsInLocal()).getMaxY() + (3.15*0.5));


        planets[10] = SpaceCraft;

        //  System.out.println("SPACECRAFT IS " + planets[10].localToScreen(planets[10].getBoundsInLocal()).getMinX());



        physics(stage);





    }

    public void physics(Stage stage) {


        for(int i=0; i<planets.length; i++) {


            if(i==10) {
                if(!firstLaunch) {
                    model.sendCoords(planets[3].localToScreen(planets[3].getBoundsInLocal()).getMinX(),
                            planets[3].localToScreen(planets[3].getBoundsInLocal()).getMinY(), i);
                }
                else {
                    model.sendCoords(SpaceCraftCoords[0], SpaceCraftCoords[1], i);
                }
            }
            else {

                //Bounds bounds = planets[i].localToScreen(planets[i].getBoundsInLocal());

                model.sendCoords(planets[i].localToScreen(planets[i].getBoundsInLocal()).getMinX(),
                        planets[i].localToScreen(planets[i].getBoundsInLocal()).getMinY(), i);
            }
        }
        model.update();

        //System.out.println("MERCURY NEW X" + planets[3].localToScreen(planets[3].getBoundsInLocal())	);

        //System.out.println(model.getFeedBack(3));
        model.printStartFB(10);

        SpaceCraftCoords[0] = model.getX();
        SpaceCraftCoords[1] = model.getY();

        SpaceCraft.setTranslateX(model.getX());
        SpaceCraft.setTranslateY(model.getY());

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> physics(stage)));
        timeline.play();


        //physics();	//recursion

    }


    public Image getImageFromResource(String resourcePath) {
        return new Image(this.getClass().getResourceAsStream(resourcePath));
    }

    private void rotatePlanet(Sphere planet) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(planet);
        rotateTransition.setDuration(Duration.seconds(15));
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
    }

    private void initMouseControl(StackPane planet, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;

        planet.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });




    }

    private Ellipse createPath(double radiusX, double radiusY) {
        Ellipse at = new Ellipse();
        at.setCenterX(0);
        at.setCenterY(0);
        at.setFill(Color.TRANSPARENT);
        at.setRadiusX(radiusX);
        at.setRadiusY(radiusY);
        at.setStroke(Color.WHITE);
        at.setStrokeWidth(0.3);

        return at;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
