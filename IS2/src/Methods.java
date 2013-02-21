
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noflaxe
 */
public class Methods {
    public static double[] calculate(ArrayList<double[]> first,
            ArrayList<double[]> second)
    {
       
        
        Random random = new Random();
        double candidate[] = new double[3];
        //GENERATION
        candidate[0] = 2*random.nextDouble() - 1;
        candidate[1] = 2*random.nextDouble() - 1;
        candidate[2] = 2*random.nextDouble() - 1;
        System.out.println("Data generated");
        print(candidate);
        //STEP2 - choose random element
        boolean finish = false;
        while(finish == false) {
            finish = true;
       int index =  random.nextInt(80);
       boolean flag;
       double temp[] = new double[2];
       if(index<first.size())
       {
       temp = first.get(index);
       flag = false;
       System.out.println("data chosen: ");
         System.out.println(temp[0] + " " + temp[1]);
       } else {
         
           temp = second.get(index - first.size());
           System.out.println("data chosen: ");
             System.out.println(temp[0] + " " + temp[1]);
           flag = true;
       }
       //step 3 - check
       // f(x) = Y - A*X - B
       double functionValue = candidate[2] + temp[0]*candidate[0] -
               temp[1]*candidate[1];
       
       if((functionValue < 0) && flag)
       {
           System.out.println(index+" - not fitting element");
           double norm = 1;
       candidate[2] += norm ;
       candidate[0] += norm*temp[0];
       candidate[1] += norm*temp[1];
       double norm2 = normtemp(candidate);
       candidate[0] = candidate[0]/norm2;
       candidate[1] = candidate[1]/norm2;
       candidate[2] = candidate[2]/norm2;
       
       
       print(candidate);
       } else if((functionValue > 0) && (flag == false))
       {
             System.out.println(index+" - not fitting element");
               double norm = 1;
       candidate[2] -= norm;
       candidate[0] -= norm*temp[0];
       candidate[1] -= norm*temp[1];
       double norm2 = normtemp(candidate);
       candidate[0] = candidate[0]/norm2;
       candidate[1] = candidate[1]/norm2;
       candidate[2] = candidate[2]/norm2;
         System.out.println(temp[0] + " " + temp[1]);
         print(candidate);
       }
         System.out.println("check phase");
       for(int i=0;i<first.size();i++)
       {
          
         temp = first.get(i);
      //     System.out.println(temp[0] + " " + temp[1]);
           
          functionValue = candidate[2] + temp[0]*candidate[0] -
               temp[1]*candidate[1];
          System.out.println("function value: "+functionValue);
          if(functionValue > 0)
          {
          finish = false;
          }   
       }
       System.out.println("---------------");
        for(int i=0;i<second.size();i++)
       {
         temp = second.get(i);
          functionValue = candidate[2] + temp[0]*candidate[0] -
               temp[1]*candidate[1];
           System.out.println("function value: "+functionValue);
          if(functionValue < 0)
          {
          finish = false;
          }   
       }
        System.out.println("Iteration done");
        }
       
    return candidate;
    }
    public static void print(double[] array)
    {
    System.out.println("X: "+array[0]+" Y: "+array[1]+" B: "+array[2]);
    }
    public static double norm(double[] array)
    {
    return Math.sqrt(array[0]*array[0] + array[1]*array[1]);
    }
    public static double normtemp(double[] array)
    {
    return Math.sqrt(array[0]*array[0] + array[1]*array[1] + array[2]*array[2]);
    }
}
