import java.util.function.BiFunction;

import static java.lang.Math.*;


/**
 * class show example of an ordinary differential equation that is solved using the fourth order Runge-Kutta
 * approximation method.
 * <p>
 *
 * Notice that at the moment of writing, the class is not generified, but it can already  easily be used by calling
 * RungeKutta.runge( arguments)
 *
 * Also notice that an equation is chosen of which the exact solution is known so that it is easy to check the accuracy.
 *
 * source: https://rosettacode.org/wiki/Runge-Kutta_method#Java
 **/

public class RungeKutta {


    /**
     * simple runge-kutta. initial conditions are t = start value of the for-loop, where y[t] = set to some initial value
     *
     *
     * @param yp_func One can best understand this parameter by thinking the following: let BiFunction<T,U,R> be a
     *               function of generic types, in this case all doubles and  behaves as function(T arg1,U arg2) =
     *               (some stuff that is a function of arg1 and arg2 that will result in a return of the type R, namely
     *               R arg3. Now here comes the beauty, the function can then be used throughout it's scope. and can
     *               easily be defined by a lambda expression.
     * @param t an array having one element for each time step to be recorded.
     * @param y records y values y[i] at step t[i]
     * @param dt step size
     *            */
    static void runge(BiFunction<Double, Double, Double> yp_func, double[] t,
                      double[] y, double dt) {

        for (int n = 0; n < t.length - 1; n++) {
            double dy1 = dt * yp_func.apply(t[n], y[n]);
            double dy2 = dt * yp_func.apply(t[n] + dt / 2.0, y[n] + dy1 / 2.0);
            double dy3 = dt * yp_func.apply(t[n] + dt / 2.0, y[n] + dy2 / 2.0);
            double dy4 = dt * yp_func.apply(t[n] + dt, y[n] + dy3);
            t[n + 1] = t[n] + dt;
            y[n + 1] = y[n] + (dy1 + 2.0 * (dy2 + dy3) + dy4) / 6.0;
        }
    }
    /**
     * @author Alain van Rijn
     *
     * given a differential equation, an array for the dependent and for the  independent variable, the last value of
     * the array for the dependent variable is returned
     *
     * @param difEq the equation to be evaluated
     * @param dt time step of independent variable
     * @param tIndependent independent variable such as time
     * @param yDependent dependent variable such as distance
     *
     * @return the result of the last time step in the dependent variable array
     */

    public static double nextDependentVarRunge(BiFunction<Double, Double, Double> difEq, double[] tIndependent,
                                               double[] yDependent, double dt) {

        for (int n = 0; n < tIndependent.length - 1; n++) {
            double dy1 = dt * difEq.apply(tIndependent[n], yDependent[n]);
            double dy2 = dt * difEq.apply(tIndependent[n] + dt / 2.0, yDependent[n] + dy1 / 2.0);
            double dy3 = dt * difEq.apply(tIndependent[n] + dt / 2.0, yDependent[n] + dy2 / 2.0);
            double dy4 = dt * difEq.apply(tIndependent[n] + dt, yDependent[n] + dy3);
            tIndependent[n + 1] = tIndependent[n] + dt;
            yDependent[n + 1] = yDependent[n] + (dy1 + 2.0 * (dy2 + dy3) + dy4) / 6.0;
        }
        return yDependent[yDependent.length -1];
    }



    static double calc_err(double t, double calc) {
        double actual = pow(pow(t, 2.0) + 4.0, 2) / 16.0; // exact value is known
        return abs(actual - calc);
    }

    public static void main(String[] args) {
        double dt = 0.10; // step size
        double[] t_arr = new double[101]; // array that will contain t values
        double[] y_arr = new double[101]; // array that will contain y values
        y_arr[0] = 1.0; // initial condition


        System.out.println(nextDependentVarRunge((t, y) -> t * sqrt(y), t_arr, y_arr, dt));
//        runge((t, y) -> t * sqrt(y), t_arr, y_arr, dt);
//
//        for (int i = 0; i < t_arr.length; i++)
//            if (i % 10 == 0)
//                System.out.printf("y(%.1f) = %.8f Error: %.6f%n",
//                        t_arr[i], y_arr[i],
//                        calc_err(t_arr[i], y_arr[i]));
    }
}
