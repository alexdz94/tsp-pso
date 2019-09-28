import java.util.ArrayList;

public class particle {

    public Route route;
    public Location location;
    public Velocity velocity;
    // pBest
    public Route pBest;
    public Location locationPBest;
    // size
    public static int problemSize;
    public static int maximumIterations = 100;

    public particle(Route route)
    {
        this.route = route;
        this.pBest = route;
        location = new Location(new ArrayList<Double>());
        locationPBest = new Location(new ArrayList<Double>());
        velocity = new Velocity(new ArrayList<Double>());
        problemSize = route.cities.size();
    }

    // Search best neighborhood solution
    public void getBest()
    {
        Route neighorRoute = null;
        int i = 0 ;
        while (i < maximumIterations)
        {
            neighorRoute = getNeighborhoodSolution(new Route(this.pBest));
            if(neighorRoute.getFullRouteDistance() < pBest.getFullRouteDistance()){
                pBest = new Route(neighorRoute);
            }
            i++;
        }
    }

    // get neighborhood solutions
    public Route getNeighborhoodSolution(Route aRoute)
    {
        int random1 = 0 ;
        int random2 = 0 ;
        while(random1==random2){
            random1 = (int) (aRoute.cities.size()* Math.random());
            random2 = (int) (aRoute.cities.size()* Math.random());
        }
        City city_1 = aRoute.cities.get(random1);
        City city_2 = aRoute.cities.get(random2);
        aRoute.cities.set(random2,city_1);
        aRoute.cities.set(random1,city_2);
        return aRoute;
    }

    public void swapWithLocation(int coeff)
    {
        for(int i = 0 ; i < coeff ; i++)
        {
            int random1 = 0 ;
            int random2 = 0 ;

            while(random1==random2){
                random1 = (int) (pBest.cities.size()* Math.random());
                random2 = (int) (pBest.cities.size()* Math.random());
            }
            City city_1 = pBest.cities.get(random1);
            City city_2 = pBest.cities.get(random2);
            pBest.cities.set(random2,city_1);
            pBest.cities.set(random1,city_2);
        }
    }
}
