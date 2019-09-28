import java.util.ArrayList;
import java.util.Collections;

public class Route {
    public ArrayList<City> cities = new ArrayList<City>();

    /*
    * Initialisation de l'objet Route avec une liste des villes directement
    * */
    public Route(ArrayList<City> cities)
    {
        this.cities.addAll(cities);
        // On mélange l'ordre des villes pour construire une premiére solution aléatoire
        Collections.shuffle(this.cities);
    }

    /*
    * Initialisation de l'objet Route avec un objet Route, on ajoute les villes une par une
    * */
    public Route(Route route){
        for(int i = 0 ; i< route.cities.size() ; i++)
        {
            cities.add(route.cities.get(i));
        }
    }

    /*
    * Cette méthode retourne la distance totale d'un chemin entre ville1,ville2,...,ville1
    * */
    public double getFullRouteDistance()
    {
        double fullDistance = 0;
        for (int i = 0 ; i < cities.size();i++)
        {
            if (i+1==cities.size()){
                fullDistance+=cities.get(i-1).distanceBetweenTwoCities(cities.get(i));
            }else{
                fullDistance+=cities.get(i).distanceBetweenTwoCities(cities.get(i+1));
            }
        }
        fullDistance+=cities.get(cities.size()-1).distanceBetweenTwoCities(cities.get(0));
        return fullDistance;
    }

    /*
    * Pour afficher le chemin
    * */
    public String printRoute()
    {
        String str = "";
        str+="{ ";
        for (City city:cities) {
            str+=city.name+" ";
        }
        str+=" }";
        return str;
    }
}
