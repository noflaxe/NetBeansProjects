/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package islab3;

import java.util.Random;

/**
 *
 * @author noflaxe
 */
public class Images {
    /*
     * neurons - массивы нейронов
     * study - учебная выборка
     * test - тестовая выборка
     * winner - нейрон победитель из учебной выборки
     * testwinner - нейрон победитель из тестовой выборки
     */
double neurons[][];
double study[][];
double test[][];
double winner[];
double testwinner[];

    public Images(double[][] study,double[][] test) {
        neurons = new double[100][25];
        this.study = study;
        for(int i=0;i<study.length;i++)
        {
        this.study[i] = normalize(study[i]);
        }
        this.test = test;
        for(int i=0;i<test.length;i++)
        {
        this.test[i] = normalize(test[i]);
        }
        
        generateRandom();
        configurateNeurons();
        analyzeResults();
    }
   /*
    * Метод для коректировки весов.
    * Опция использовании ошибки присутсвует, однако она слишком замедляет
    * производительность, и сейчас выключена
    */
    private void configurateNeurons()
    {
      Random random = new Random();
      double error = 0;
      int counter = 1;
    while(error > 0.001 || counter < 10000)
    {
        
    int index = random.nextInt(study.length);
    int winner = findWinner(study[index]);
    for(int j=0;j<neurons.length;j++)
      {
    for(int k=0;k<25;k++)  {
     //  double temp = Math.max(error,studyRate(counter)*neighborFunction(j,winner,counter)*(study[index][k] - neurons[j][k]));
        neurons[j][k] = neurons[j][k] + studyRate(counter)*neighborFunction(j,winner,counter)*(study[index][k] - neurons[j][k]); 
    }
    neurons[j] = normalize(neurons[j]);
      }
    counter++;
    }
    System.out.println(counter);
    }
    
    public final void generateRandom()
    {
        Random random = new Random();
    for(int i=0;i<neurons.length;i++)
        {
        for(int j = 0;j<25;j++)
            {
                neurons[i][j] = random.nextDouble();
            }
        neurons[i] = normalize(neurons[i]);
        }
    
    }
    /*
     * Возвращает норму вектора
     */
    public double norm (double[] array)
    {
     double temp = 0;   
     for(int i=0;i<25;i++)
     {
     temp += array[i]*array[i];
     }
    return Math.sqrt(temp);
    }
    
    /*
     * Метод возвращающий индекс нейрона победителя
     */
    public int findWinner(double[] array)
    {
     int max = 0;
     double minvalue = Double.MAX_VALUE;
     for(int j=0;j<neurons.length;j++) {
     double temp = 0;
     for(int i=0;i<25;i++)
     {
     temp += neurons[j][i]*neurons[j][i];
     temp -= 2*neurons[j][i]*array[i];
     }
     if(temp<minvalue)
     {
     max = j;
     minvalue = temp;
     }
     temp = 0;
     }
     
    return max; 
    }
    /*
     * метод нормализирующий вектор
     */
    public double[] normalize(double[] array)
    {
    double[] normalized = new double[25];
    double norm = norm(array);
    for(int i=0;i<25;i++)
    {
    normalized[i] = array[i]/norm;
    }
    return normalized;
    }
    /*
     * Метод выводящий букву на экран
     */
    public static void print(double[] array)
    {
     StringBuilder temp = new StringBuilder();
    for(int i=0;i<25;i++)
    {
    if(array[i] == 0)
    {
    temp.append(" ");
    } else { 
        temp.append('*');  }
    
    }
    System.out.println(temp.subSequence(0, 5));
    System.out.println(temp.subSequence(5, 10));
    System.out.println(temp.subSequence(10, 15));
    System.out.println(temp.subSequence(15, 20));
    System.out.println(temp.subSequence(20,25));
    }
    /*
     * Скорость обучения
     */
   public static double studyRate(double iteration)
   {
   return 0.1*Math.exp(-iteration/1000.0);
   }
   /*
    * Дисперсия
    */
   public static double dispersionCoeff(double iteration)
   {
   return 10*Math.exp(-iteration/100.0);
   }
   /*
    * Растояния между нейронами
    */
   public static int distance(int first,int second)
   {
   return (first%5 - second% 5)*(first%5 - second% 5) + (first/5 - second/5)*(first/5 - second/5);
   }
   /*
    * Функция "соседства"
    */
   public static double neighborFunction(int first,int second,int iteration)
   {
   return Math.exp(-1*(double)distance(first,second)/dispersionCoeff(iteration));
   }
   /*
    * Функция вывода полученых данных на экран
    */
   private void analyzeResults()
   {
       winner = new double[study.length];
       testwinner = new double[study.length];
   for(int i=0;i<study.length;i++)
   {
   winner[i] = findWinner(study[i]);
   testwinner[i] = findWinner(test[i]);
   System.out.println(i+" letter's neuron is "+winner[i]);
 //  print(study[i]);
   System.out.println(i+" test letter's neuron is "+testwinner[i]);
   print(test[i]);
   System.out.println("-------------------------");
   }
   }
   
}
