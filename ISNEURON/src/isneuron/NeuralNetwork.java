/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package isneuron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author noflaxe
 */
public class NeuralNetwork {

    private int inputSize, hiddenSize, outputSize;
    private double[] input, hidden, output, ev;
    private double[][] data,datatemp;
    private double[][] weight, change;
    private double[][] trueOutput;
    private double error;
    private static double VARIANCE = 0.1;
    private static double STEP = 0.01;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        input = new double[inputSize];
        hidden = new double[hiddenSize];
        output = new double[outputSize];
        ev = new double[hiddenSize];
        trueOutput = new double[100][1];
        error = 0;
        generateRandom();
        inputData();
        for(int i = 0;i<100000;i++)
        {
           
        makeIteration();
      //  System.out.println("error: "+error+" ");
         error = 0;
         for (int k = 0; k < 1; k++) {
            for (int j = 0; j < 10; j++) {
                
              weight[k][j] = weight[k][j] - STEP*change[k][j];
            //  System.out.println(k+ " "+j+" change is: "+change[k][j]);
              change[k][j] = 0; 
            }
         }
         
        }
        for(int i=79;i<100;i++)
        {
         calculateHidden(datatemp[i]);
         calculateOutput();
      //   System.out.println(datatemp[i][0]+" "+datatemp[i][1]+" "+trueOutput[i][0]);
         System.out.println("TRUE OUTPUT: "+trueOutput[i][0]+" PROGRAM OUTPUT "+output[0]);
        }
        System.out.println("-----------");
        
    }

    private void generateRandom() {
       weight = new double[outputSize][hiddenSize];
       change = new double[outputSize][hiddenSize];
        Random random = new Random();
    //    System.out.println("RANDOM");
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                change[i][j] = 0;
                weight[i][j] = random.nextDouble();
           //     System.out.println(weight[i][j]);
            }
        }
        System.out.println("-----------------");
        for (int i = 0; i < ev.length; i++) {
            ev[i] = random.nextDouble();
         //   ev[i] = (double)i/10;
        }
             


    }

    private void calculateHidden(double[] array) {
        this.input = array;
        for (int j = 0; j < hiddenSize; j++) {
            double temp = 0;
            for (int i = 0; i < inputSize; i++) {
                temp += (input[i] - ev[j]) * (input[i] - ev[j]);
            }
            temp = -0.5 * temp / VARIANCE;
            hidden[j] = Math.exp(temp);
        }
    }

    private void calculateOutput() {
        for (int i = 0; i < outputSize; i++) {
            output[i] = 0;
            for (int j = 0; j < hiddenSize; j++) {
                output[i] += hidden[j] * weight[i][j];
            }
        }
    }

    private void makeIteration() {
        for (int i = 0; i < data.length; i++) {
            calculateHidden(data[i]);
            calculateOutput();
            for (int j = 0; j < outputSize; j++) {
              for(int z=0;z<hiddenSize;z++)
              { 
              change[j][z] += (output[j] - trueOutput[i][j])*hidden[z]; // ENDED HERE. SUMM ERROR
          //    System.out.println(weight[j][z] + " " + z + " "+ j);
              }
              error += Methods.error(output[0],trueOutput[i][0]);
      //        System.out.println(i +" ITERATION FINISH error: "+error);
            }
    
        }
    }
     private  void inputData() {
         data = new double[80][2];
         datatemp = new double[100][2];
         int counter = 0;
         try  {

      FileReader fr = new FileReader("d:\\data12.csv");

      BufferedReader br = new BufferedReader(fr);

      String stringRead = br.readLine();

      while(stringRead != null)  {

      StringTokenizer st = new StringTokenizer(stringRead, ";");
     
      double first = Double.parseDouble(st.nextToken());
int countertemp = counter;
if(countertemp >= 80)
{
countertemp = 79;
}
      double second = Double.parseDouble(st.nextToken());
       datatemp[counter][0] = first;
       datatemp[counter][1] = second;
       data[countertemp][0] = first;
       data[countertemp][1] = second;
      
      double third = Double.parseDouble(st.nextToken());
      trueOutput[counter][0] = third;
      counter++;
              stringRead = br.readLine();

      }

      br.close();

       } catch (Exception ex) {

        // DO NOTHING
       }
     }
}
