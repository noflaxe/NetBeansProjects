/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package islab3;

/**
 *
 * @author noflaxe
 */
public class Methods {
    /*
     * Метод умножения матриц
     */
    public static double[][] multiply(double a[][], double b[][]) {
   
  int aRows = a.length,
      aColumns = a[0].length,
      bRows = b.length,
      bColumns = b[0].length;
   
  if ( aColumns != bRows ) {
    throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
  }
   
  double[][] resultant = new double[aRows][bColumns];
   
  for(int i = 0; i < aRows; i++) { // aRow
    for(int j = 0; j < bColumns; j++) { // bColumn
      for(int k = 0; k < aColumns; k++) { // aColumn
        resultant[i][j] += a[i][k] * b[k][j];
      }
    } 
  }
   
  return resultant;
}
    /*
     * Сумма матриц
     */
    public static double[][] matrixAdd(double[][] first,double[][] second)
    {
    double[][] result = new double[first.length][first[0].length];
    for(int i=0;i<first.length;i++)
    {
        for(int j=0;j<first[0].length;j++)
        {
            result[i][j] = first[i][j] + second[i][j];
        }
    }
    
    return result;
    }
    /*
     * Транспонирование
     */
    public static double[][] transpose(double[][] data)
    {
    double[][] transpose = new double[data[0].length][data.length];
    for(int i=0;i<data.length;i++) {
        for(int j = 0;j<data[0].length;j++) 
        {
        transpose[j][i] = data[i][j];
        }
        }
    return transpose;
    }
    public static double[][] transpose(double[] data)
    {
     double[][] transpose = new double[data.length][1];
      for(int j = 0;j<data.length;j++) 
        {
        transpose[j][0] = data[j];
        }
     return transpose;
    }
    /*
     * Выпод матрицы на экран
     */
    public static void print(double[][] result)
    {
      for(int i=0;i<result.length;i++) {
          StringBuilder temp = new StringBuilder();
        for(int j=0;j<result[0].length;j++) {
          temp.append(result[i][j]);
          temp.append(' ');
        }
      System.out.println(temp);
      }
    }
    /*
     * Подсчет матрицы М, которую мы используем при подсчете след итерации
     */
    public static double[][] calculateW(double[][] data)
    {
    double result[][] = new double[data[0].length][data[0].length];
    for(int i=0;i<result.length;i++) {
        for(int j=0;j<result[0].length;j++)
        {result[i][j] = 0;}
    }
    for(int i=0;i<data.length;i++)
    {
     double[][] temp = new double[1][data[0].length];
    temp[0] = data[i];
    result = matrixAdd(result,multiply(transpose(temp),temp));
    }
    /*
     * Зануление диагональных элементов
     */
    for(int i=0;i<result.length;i++)
    {
    result[i][i] = 0;
    }
    /*
     * Умножение на множитель 1\N
     */
    for(int i=0;i<result.length;i++) {
        for(int j=0;j<result[0].length;j++) {
            result[i][j] = result[i][j]*1.0/(double)data.length;
        }}
    
    print(result);
    return result;
    }
   /*
    * Метод, который перемножает все элементы тестовой выборки, по очереди, с матрицей М
    * и пытается получить эталонный вектор
    */
    public static double recognize(double[][] matrix,double[][] candidates,double[][] data)
    {
        for(int i=0;i<candidates.length;i++)
        {
            double[] candidate = candidates[i].clone();
            double[][] candidatetemp = transpose(candidate);
            int counter = 0;
            while(equalTo(transpose(candidatetemp)[0],data) == -1 && (counter < 100000))
            {  
              candidatetemp = multiply(matrix,candidatetemp);
                for(int k=0;k<candidatetemp.length;k++)
                {
                for(int j=0;j<candidatetemp[0].length;j++)
                {
                if(candidatetemp[k][j] > 0)  {
                 candidatetemp[k][j] = 1;  
                } else {
                     candidatetemp[k][j] = -1;  
              }
                }
                }
                counter++;
                }
            if(counter > 99999)
            {
            System.out.println("Recognition denial");
            }
            } 
        
    return 0;
    }
    /*
     * Вектор проверки равенства матриц
     */
    public static int equalTo(double[] candidate, double[][] base) {
        boolean flag = true;
        for (int i = 0; i < base.length; i++) {
            if (candidate.length != base[i].length) {
                return -1;
            }
            for (int j = 0; j < candidate.length; j++) {
                if (Math.abs(candidate[j] - base[i][j]) > 0.001) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Recognised as: "+i);
                return i;
            }
            flag = true;
        }
        return -1;
    }
}
