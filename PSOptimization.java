import java.util.ArrayList;
import java.util.Collections;

public class PSOptimization {

    public ArrayList<particle> particlesList = new ArrayList<particle>();
    public Route gBest = null;
    public Location gBestLocation;

    public PSOptimization(Route route)
    {
        System.out.println("Initial distance : "+(int)route.getFullRouteDistance()+" km");
        System.out.println(route.printRoute());
        System.out.println();
        System.out.println("========================= Initializing Particles ... =======================");
        System.out.println();
        for(int i = 0 ; i < PSOConstants.maximumParticles ; i++)
        {
            Collections.shuffle(route.cities);
            this.particlesList.add(new particle(new Route(route)));
        }
        gBestLocation = new Location(new ArrayList<Double>());
        initilizeVelocites();
        initilizeLocations();
        sort();
        gBest = new Route(this.particlesList.get(0).pBest);
        System.out.println("======= "+PSOConstants.maximumParticles +" Particles are Initialized Uniformly ! ============");
        System.out.println();
    }

    public void findShortestRoute()
    {
        System.out.println("========================= Starting Process =======================");
        System.out.println();
        double w = 0;
        int counter = 0 ;
        while (counter < PSOConstants.maximumIterations)
        {
            for(int i = 0 ; i < this.particlesList.size()-1 ; i ++)
            {
                this.particlesList.get(i).getBest();
            }
            printParticles();
            sort();
            if(this.particlesList.get(0).pBest.getFullRouteDistance() < this.gBest.getFullRouteDistance())
            {
                gBest = new Route(this.particlesList.get(0).pBest);
            }
            System.out.println("Actual gBest = "+(int)gBest.getFullRouteDistance()+" km");
            w = PSOConstants.high - (((double) counter) / PSOConstants.maximumIterations) * (PSOConstants.high - PSOConstants.low);
            System.out.println("Updating Velocities and Locations ...");
            for(int i = 0 ; i < this.particlesList.size() - 1 ; i++)
            {
                for(int j = 0 ; j< particle.problemSize-1 ; j++)
                {
                    // 2 reelle aléatoire :
                    double r1 = PSOConstants.random.nextDouble();
                    double r2 = PSOConstants.random.nextDouble();
                    // velocité du particle actuel :
                    double vel = this.particlesList.get(i).velocity.getVelocity().get(j);
                    // location :
                    double loc = this.particlesList.get(i).location.getLocations().get(j);
                    // position de pBest :
                    double pBestLoc = this.particlesList.get(i).locationPBest.getLocations().get(j);
                    // position de gBest :
                    double gBestLoc = this.gBestLocation.getLocations().get(j);
                    // nouvelle valeur de la velocité :
                    double newVel = (w * vel) + (r1 * PSOConstants.c1) * (pBestLoc - loc) + (r2 * PSOConstants.c2) * (gBestLoc - loc);
                    // mise a jour de velocité :
                    this.particlesList.get(i).velocity.getVelocity().set(j,newVel);
                    // nouvelle valeur de la position :
                    double newPos = loc + newVel;
                    this.particlesList.get(i).location.locations.set(j,newPos);
                    this.particlesList.get(i).swapWithLocation((int)Math.abs(loc-newPos));
                }

            }
            System.out.println("Epoch "+counter+" has been finished");
            counter++;

        }
        System.out.println("========================= Process Finished =======================");
        System.out.println();
        System.out.println("Approached Global minimum : "+(int)this.gBest.getFullRouteDistance()+" km");
        System.out.println("Route : "+this.gBest.printRoute());
    }


    public void sort()
    {
        int n = this.particlesList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (this.particlesList.get(j).pBest.getFullRouteDistance() > this.particlesList.get(j+1).pBest.getFullRouteDistance())
                {
                    particle temp = this.particlesList.get(j);
                    this.particlesList.set(j,this.particlesList.get(j+1));
                    this.particlesList.set(j+1,temp);
                }
    }

    public void initilizeVelocites()
    {
        for(particle p : this.particlesList)
        {
            for(int i = 0 ; i < particle.problemSize - 1 ; i++)
            {
                p.velocity.velocityDimensions.add(PSOConstants.random.nextDouble() * 2.0 - 1.0);
            }
        }
    }

    public void initilizeLocations()
    {
        for(particle p : this.particlesList)
        {
            for(int i = 0 ; i < particle.problemSize - 1 ; i++)
            {
                p.location.locations.add((double)i);
                p.locationPBest.locations.add((double)i);
            }
        }
        for (int i = 0 ; i < particle.problemSize - 1 ; i++)
        {
            gBestLocation.getLocations().add((double)i);
        }
    }

    public void printParticles()
    {
        for(int i = 0 ; i< this.particlesList.size()-1;i++)
        {
            System.out.println("Particle : "+i);
            for(int j = 0 ; j < particle.problemSize-1 ; j++)
            {
                System.out.print("Position : "+this.particlesList.get(i).location.getLocations().get(j)+" , Vélocité : "
                + this.particlesList.get(i).velocity.getVelocity().get(j));
                System.out.println();
            }
        }
    }

}