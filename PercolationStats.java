import PerPack.Percolation;
import java.util.*;

class PercolationStats {
    private Percolation per;
    private int T;
    private double[] numPer;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        T = trials;
        numPer = new double[T];
        for(int i = 0; i < T; i ++){
            per = new Percolation(n);
            int min = 1;
            int max = n;
            while (!per.percolates()){
                int row = (int)(Math.random() * (max - min) + min);
                int col = (int)(Math.random() * (max - min) + min);
                per.open(row,col);
                numPer[i] = per.numberOfOpenSites()/(double)n;
            }

        }

    }

    // sample mean of percolation threshold
    public double mean(){
        double sum = 0.0;
        for(int i=0;i < numPer.length;i++){
            sum += numPer[i];
        }

        return sum/(double) T;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double std = 0.0;
        double mean = mean();
        for(int i=0;i < numPer.length;i++){
            std = Math.pow(numPer[i] -mean,2);
        }
    return Math.sqrt(std/(T-1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        double mean = mean();
        double std = stddev();
        return (mean - ((1.96*std)/(double)Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double mean = mean();
        double std = stddev();
        return (mean + ((1.96*std)/(double)Math.sqrt(T)));
    }

   // test client (see below)
   public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String index = input.nextLine();
        String[] indexarray = index.split("\\s+");
        
        int n = Integer.parseInt(indexarray[0]); 
        int trials = Integer.parseInt(indexarray[1]); 

        PercolationStats perStat = new PercolationStats(n,trials);

        System.out.println(" mean = " + perStat.mean());
        System.out.println(" stddev = " + perStat.stddev());
        System.out.println(" 95% confidence interval = [" + perStat.confidenceLo() + "," + perStat.confidenceHi() + "]");
        System.out.println("yipee!");
    }
}