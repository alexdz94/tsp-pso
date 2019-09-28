import java.util.ArrayList;
public class Velocity {
    public ArrayList<Double> velocityDimensions;

    public Velocity(ArrayList<Double> dimensions)
    {
        this.velocityDimensions = new ArrayList<Double>();
        this.velocityDimensions = dimensions;
    }
    public ArrayList<Double> getVelocity() {
        return this.velocityDimensions;
    }

    public void updateVelocity(ArrayList<Double> update)
    {
        this.velocityDimensions.addAll(update);
    }
}
