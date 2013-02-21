/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package is5;

import java.util.Random;

/**
 *
 * @author noflaxe
 */
public class Genetic {
   public int[] data;
   public double[] criteria;
   public static int LENGTH = 8;
     Random random = new Random();
     
    public Genetic()
    {
    data = new int[8];
     criteria = new double[8];
   
    for(int i=0;i<data.length;i++)
    {
    data[i] = random.nextInt(255) + 1; // (0;255]
    }
    calculateCriteria();
    print(); // писать логику в конструкторе, очень сильно с моей стороны
     for(int i=0;i<100;i++)
       {
       makeItertion();
       }
   
    }
   
    public void makeItertion()
    {
        int[] letmesee = roulette(criteria);
    changePopulation(letmesee);
    calculateCriteria();
    crossing();
    mutation();
    print();
    }
    
    public static int arrayToValue(int[] array)
    {
    int result = 0;
    for(int i=0;i<array.length;i++)
    {
    result += array[i]*Math.pow(2,i);
    }
    return result;
    }
    
    
    public static int[] valueToarray(int value)
    {
        int temp = value;
    int[] result = new int[8];
    for(int i=LENGTH;i>0;i--)
    {
    int divider =(int) Math.pow(2, i-1);
    result[i-1] = temp/divider;
    temp = temp - result[i-1]*divider;
    }
    return result;
    }
    
    public static double function(int y)
    {
        double x = y;
    double value = -1*(x*x - 3*x + 2)*(x*x - 3*x + 2)/256.0;
    return value;
    }
    
    public void calculateCriteria()
    {
    for(int i=0;i<data.length;i++)
    {
    criteria[i] = function(data[i]);
    }
    double norm = norm(criteria);
    for(int i=0;i<criteria.length;i++)
    {
    criteria[i] = criteria[i]/norm;
    }
    }
    
    public static double norm(double[] array)
    {
    double norm = 0.0;
    for(int i=0;i<array.length;i++)
    {
    norm += Math.abs(array[i]);
    }
    return norm;
    }
    
    public int[] roulette(double[] array) // array - criteria
    {
        int[] result = new int[8];
        for(int i=0;i<8;i++) // количество ri в рулетке
        {
         double rand = random.nextDouble();
         int counter = 0;
         while(rand > 0 && counter < 8)
         {
         rand = rand - array[counter];
         counter++;
         }
         result[i]  = counter - 1;
        }
    return result;
    }
    
    public void changePopulation(int[] array)
    {
    for(int i=0;i<data.length;i++)
        {
            data[i] = data[array[i]];
        }
    }
    
    public void crossing()
    {
    for(int i=0;i<LENGTH/2;i++)
    {
    int[] first,second = new int[8];
    first = valueToarray(data[2*i]);
    second = valueToarray(data[2*i + 1]);
    int firstBound = random.nextInt(9);
    int secondBound = random.nextInt(9);
    for(int j = Math.min(firstBound,secondBound);j<Math.max(firstBound,secondBound);j++)
    {
    int temp = first[j];
    first[j] = second[j];
    second[j] = temp;
    }
    data[2*i] = arrayToValue(first);
    data[2*i + 1] = arrayToValue(second);
    }
     calculateCriteria();
    }
    
    public void mutation()
    {
    for(int i=0;i<data.length;i++)
    {
    int[] temp = valueToarray(data[i]);
    for(int j = 0;j<temp.length;j++)
    {
    double prob = random.nextDouble();
    if(prob<0.01)
    {
    if(data[j] == 0) {
        data[j] = 1;} else {
    data[j] = 0; }
    }
    }
    data[i] = arrayToValue(temp);
    }
    calculateCriteria();
    }
    
    
    public void print()
    {
        System.out.println("Population status: ");
    for(int i=0;i<data.length;i++)
    {
   StringBuilder temp = new StringBuilder();
    int[] temparray = valueToarray(data[i]);
        for(int j=0;j<temparray.length;j++)
        {
        temp.append(temparray[j]);
        temp.append(' ');
        }
    temp.append(data[i]); 
    temp.append(" Criteria: "+criteria[i]);
    System.out.println(temp);
    }
    }
}
