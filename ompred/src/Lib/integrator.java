package Lib;

/**
 * Created by MAC on 10/31/2015.
 */
public class integrator {
    public static double a=0;
    public static double b=2;
    public static double integ1(double delta){

        boolean flag=true;
        //double value=0;
        double step=0;
        double sum=0;
        double sum2=0;
        while(flag){
            step++;
            double num= StdRandom.uniform(a, b);
            num=num*num;
            num=Math.exp(-num);
            //value=value*(step-1);
            //value=value/step;
            //System.out.println(num);
            sum+=num;
            sum2+=(num*num);
            if(step%8000==0){
                System.out.println((int)step+" "+(sum*2/step)+" "+Math.sqrt(((sum2 / step) - ((sum / step) * (sum / step))) / step));
            }
            if(step%200000==0) {
                double error=Math.sqrt(((sum2/step)-((sum/step)*(sum/step)))/step);
                //System.out.println(error);
                if(true) flag=false;
            }
        }
        return (sum/step)*(b-a);
    }
    public static double integ2(double delta){

        boolean flag=true;
        //double value=0;
        double step=0;
        double sum=0;
        double sum2=0;
        while(flag) {
            double num = StdRandom.exp(1.0);
                step++;
            sum2+=num;
                num = Math.exp(-(num*num))/Math.exp(-num);
                //value=value*(step-1);
                //value=value/step;
                //System.out.println(num);
                sum += num;
if(step%8000==0){
    System.out.println((int)step+" "+(sum/sum2)+" "+Math.sqrt(((sum2 / step) - ((sum / step) * (sum / step))) / step));
}
                if (step % 200000 == 0) {
                    double error = Math.sqrt(((sum2 / step) - ((sum / step) * (sum / step))) / step);
                    //System.out.println(error);
                    if (true) flag = false;
                }

        }
        return (sum/sum2);
    }
    public static double sphere(int step){
          double l=0;
        double sum=0;
        double zigma=0;
        for(int i=0;i<step;i++){
            double x= StdRandom.uniform(-1.0,1.0);
            double y= StdRandom.uniform(-1.0,1.0);
            double z= StdRandom.uniform(-1.0,1.0);
           // System.out.println(x);
          //  System.out.println(y);
          //  System.out.println(z);
            if(x*x+y*y+z*z<=1){
                l++;
                zigma+=(1+(z/3));
                sum+=((z)*(1+(z/3)));

            }
        }
        return sum/zigma;
    }
    public static void main(String[] args){
        long t= System.nanoTime();
        //System.out.println(integ2(0.000001));
        //System.out.println(integ1(0.0001));
        System.out.println(sphere(1000000));
        //System.out.println("elapsed time:"+(System.nanoTime()-t));
    }
}
