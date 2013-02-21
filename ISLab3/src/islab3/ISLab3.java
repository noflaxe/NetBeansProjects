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
public class ISLab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();
    double W[] = {1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,1,1,1,1};
    double E[] = {1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1};
    double P[] = {1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0};
    double M[] = {1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1};
    double A[] = {1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1};
    double T[] = {1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0};
    double H[] = {1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1};
    double Z[] = {1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1};
    double N[] = {1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1};
    double O[] = {1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1};
    double study[][] = new double[10][25];
    double test[][] = new double[10][25];
    study[0] = W;
    study[1] = E;
    study[2] = P;
    study[3] = M;
    study[4] = A;
    study[5] = T;
    study[6] = H;
    study[7] = Z;
    study[8] = N;
    study[9] = O;
    test[0] = W.clone();
    test[1] = E.clone();
    test[2] = P.clone();
    test[3] = M.clone();
    test[4] = A.clone();
    test[5] = T.clone();
    test[6] = H.clone();
    test[7] = Z.clone();
    test[8] = N.clone();
    test[9] = O.clone();
  
    /*
     * random.next double - уровень шума
     */
    for(int i=0;i<test.length;i++)
    {
        for(int j=0;j<test[0].length;j++) {
    if(random.nextDouble() < 0.4)
    {
         if(test[i][j] == 0) {
             test[i][j] = 1;       
         } else {
             test[i][j] = 0;}
        
    }
        }
    }
      for(int i=0;i<study.length;i++)
    {
    for(int j=0;j<study[0].length;j++)
    { 
        if(study[i][j] == 0)
        {study[i][j] = -1;
      //  test[i][j] = -1;
        }        
        }
    }
       for(int i=0;i<study.length;i++)
    {
    for(int j=0;j<study[0].length;j++)
    { 
        if(test[i][j] == 0)
        {//study[i][j] = -1;
        test[i][j] = -1;
        }        
        }
    }
  // double first[] = {1,1,1};
 //  double second[] = {-1,1,1};
 //  double testdata[][] = {first,second};
  double[][] matrix  =  Methods.calculateW(study);
  Methods.recognize(matrix,test,study);
  Methods.print(matrix);
   // Images image = new Images(study,test);
   // Images.print(O);
    }
}
