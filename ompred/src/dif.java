import Lib.Picture;
import Lib.StdDraw;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by L i o n on 6/6/2017.
 * Seyed Iman Hosseini Zavaraki
 * Github @ https://github.com/ImanHosseini
 * Wordpress @ https://imanhosseini.wordpress.com/
 */
public class dif {

    static final int N=4000;
    static final int sampling=8;
    static final int base=60;

    public static void main(String[] args) throws Exception {
        // Open the file


        double[][] bri=new double[N/sampling][N/sampling];
        double[][] red=new double[N/sampling][N/sampling];
        Scanner scanner= new Scanner(new File("data1.txt"));
        String strLine;
        double maxb=0.0;
//Read File Line By Line
        //strLine=br.readLine();
        int ind=0;
       for(int i=0;i<N*N;i++){
           double c=scanner.nextDouble();
            int xi=i/N;
           int yi=i%N;

               int ri=xi/sampling;
               int rj=yi/sampling;

               bri[ri][rj]+=c;
           if(bri[ri][rj]>maxb) maxb=bri[ri][rj];


        }



        Picture pic=new Picture(N/sampling,N/sampling);
        for(int i=0;i<N/sampling;i++){
            for(int j=0;j<N/sampling;j++){
                if((i-N/(2*sampling))*(i-N/(2*sampling))+(j-N/(2*sampling))*(j-N/(2*sampling))>(N/(2*sampling))*(N/(2*sampling)))continue;
                int brit=(int)((bri[i][j]/maxb)*(255.0-base));
                //double redish=(red[i][j]/((double)sampling*sampling));
                //int re=(int)(redish*(double)brit);
                //int bl=brit-re;
                //System.out.println(r);
                Color col=new Color(brit+base,brit,brit);
                pic.set(i,j,col);
            }
        }
        pic.show();

    }
}
