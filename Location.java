import java.util.ArrayList;

public class Location {

    public ArrayList<Double> locations;

    public Location(ArrayList<Double> locations)
    {
        this.locations = new ArrayList<Double>();
        this.locations = locations;
    }

    public ArrayList<Double> getLocations() {
        return this.locations;
    }

    public void updateLocation(ArrayList<Double> update)
    {
        this.locations.addAll(update);
    }
}
