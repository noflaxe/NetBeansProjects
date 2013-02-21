
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noflaxe
 */
public class Main {
    public static void main(String[] args)
    {
     ArrayList<double[]> group1 = new ArrayList<double[]>();
     ArrayList<double[]> group2 = new ArrayList<double[]>();
    
        try  {
       FileReader fr = new FileReader("d:\\data12.csv");
       BufferedReader br = new BufferedReader(fr);
       String stringRead = br.readLine();
       while(stringRead != null)  {
       StringTokenizer st = new StringTokenizer(stringRead, ";");
       double first = Double.parseDouble(st.nextToken());
       double second = Double.parseDouble(st.nextToken());
       double flag = Double.parseDouble(st.nextToken());
       double temp[] = new double[2];
       temp[0] = first;
       temp[1] = second;
       if(flag == 0)
       {group1.add(temp);
       } else {
           group2.add(temp); }
               stringRead = br.readLine();

       }
       br.close();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
     double result[] = Methods.calculate(group1,group2);
     System.out.print(result[0]+" "+result[1]+" "+result[2]);

    }
}
