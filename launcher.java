import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class launcher {
    public static void main(String[]args)
    {
        /*JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(new JFrame());
        File inputFile = fileChooser.getSelectedFile();
        System.out.println("Selected file : "+inputFile.getAbsolutePath());*/
        ArrayList<City> cities = new ArrayList<City>();
        try{
            Scanner input = new Scanner(new File("/Users/alex/Desktop/MRP/tsp_example.txt"));
            while (input.hasNextLine())
            {
                String line = input.nextLine();
                String[] line_array = line.split(",");
                cities.add(new City(Double.parseDouble(line_array[0]),Double.parseDouble(line_array[1]),line_array[2]));
            }
        }catch (Exception e)
        {
            System.out.println("Error : "+e.getMessage() +" !");
        }
        /*
        * Jusqu'ici, c'est la lecture du fichier et le remplissage de la liste des villes
        * */
        Route route = new Route(cities);
        // Initialisation de la premiére solution
        PSOptimization pso = new PSOptimization(route);
        long startTime = System.nanoTime();
        pso.findShortestRoute();
        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("============ Execution Time =========== ");
        System.out.println("Your algorithm runs : "+(endTime-startTime)/1000000000.0 +"s");
        // Lancement de l'algorithme avec la solution initiale
    }
}
