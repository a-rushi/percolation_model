package com.company;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF Mat;
    private int[][] OpenMat;
    private int len;
    private int count;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        count = 0;
        len = n;
        Mat = new WeightedQuickUnionUF(n*n+2);
        OpenMat = new int[n][n];
        for (int i =0; i<n;i++ ){
            for(int j = 0; j < n;j++){
                OpenMat[i][j]=0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row<0 || row > len+1){
            throw new IllegalArgumentException(" Row " + row + " is not between 1 and " + (len));
        }
        if (col<0 || col > len+1){
            throw new IllegalArgumentException(" Column " + col + " is not between 1 and " + (len));
        }

        if(OpenMat[row-1][col-1]==1) return;
        else{

            OpenMat[row-1][col-1]=1;

            if (row == 1) Mat.union(row*col,0);
            if (row == len) Mat.union(len*(row-1)+col,len*len+1);

            if (col == 1 && 1<row && row<len){
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }
            else if (col == len && 1<row && row<len){
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
            }

            else if (row == 1 && 1< col && col<len){
                Mat.union(col,len*(row-1)+col);
                if( isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }

            else if (row == len && 1< col && col<len ){
                Mat.union(len*len+1,len*(row-1)+col);
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }
            else if (row == len && col==len ){
                Mat.union(len*len+1,len*(row-1)+col);
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
            }
            else if (row == len && col==1 ){
                Mat.union(len*len+1,len*(row-1)+col);
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }
            else if (col == len && row==1 ){
                Mat.union(0,len*(row-1)+col);
                if(isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
            }
            else if (col == 1 && row==1 ){
                Mat.union(0,len*(row-1)+col);
                if(isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }

            else{
                if(isOpen((row-1),col)){
                    Mat.union(len*(row-2)+col,len*(row-1)+col);
                }
                if( isOpen((row+1),col)){
                    Mat.union(len*(row)+col,len*(row-1)+col);
                }
               if( isOpen(row,(col-1))){
                    Mat.union(len*(row-1)+(col-1),len*(row-1)+col);
                }
                if( isOpen(row,(col+1))){
                    Mat.union(len*(row-1)+(col+1),len*(row-1)+col);
                }
            }

            count ++;
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row<0 || row > len+1){
            throw new IllegalArgumentException(" Row " + row + " is not between 1 and " + (len));
        }
        if (col<0 || col > len+1){
            throw new IllegalArgumentException(" Column " + col + " is not between 1 and " + (len));
        }
        return OpenMat[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row<0 || row > len+1){
            throw new IllegalArgumentException(" Row " + row + " is not between 1 and " + (len));
        }
        if (col<0 || col > len+1){
            throw new IllegalArgumentException(" Column " + col + " is not between 1 and " + (len));
        }
        return Mat.connected(len*(row-1)+col,0);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return Mat.connected(0,len*len+1);
    }

    /*// test client (optional)
    public static void main(String[] args){
        Scanner set = new Scanner(System.in);

        int n = set.nextInt();
        Percolation per = new Percolation(n);

        Scanner input = new Scanner(System.in);
        String index = input.nextLine();
        String[] indexarray = index.split("\\s+");

        while (indexarray.length != 0){
            int p = Integer.parseInt(indexarray[0]);
            int q = Integer.parseInt(indexarray[1]);

            per.open(p,q);
            System.out.println(per.numberOfOpenSites());
            System.out.println(per.percolates());

            index = input.nextLine();
            indexarray = index.split("\\s+");
        }
        System.out.println("yipee!");
    }*/
}