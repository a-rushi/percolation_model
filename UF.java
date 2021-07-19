import java.util.*;

public class UF{
	private int[] parent;
	private  byte[] rank;
	private int count;

	public UF(int n){
		if (n<0) throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		for (int i=0; i <n; i ++){
			parent[i]=i;
			rank[i] = 1;
		}
	}

	public int find(int p){
		validate(p);
		while (p != parent[p]){
			parent[p] = parent[parent[p]];
			p = parent[p];
		}

	return p;
	}

	public int count(){
		return count;
	}

	public boolean connected(int p, int q){
		return find(p)==find (q);
	}

	public void union(int p ,int q){
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) return;
		if (rank[p] < rank[q]) {
			parent[rootP] = parent[rootQ];
			rank[q] += rank[p];
		}
		else if (rank[p] > rank[q]) {
			parent[rootQ] = rootP;
			rank[p] += rank[q];
		}
		else {
			parent[rootP]=rootQ;
			rank[q] += rank[p];
		}
		count --;
	}

	public void validate(int p){
		int n = parent.length;
		if (p<0 || p>=n){
			throw new IllegalArgumentException("index" + p + "is not between 0 and " + (n-1));
		}
	}

	public static void main(String[] args){

		Scanner set = new Scanner(System.in);

		int n = set.nextInt();
		UF uf = new UF(n);
		
		Scanner input = new Scanner(System.in);
		String index = input.nextLine();
		String[] indexarray = index.split("\\s+");
    
		while (indexarray.length != 0){
			int p = Integer.parseInt(indexarray[0]); 
			int q = Integer.parseInt(indexarray[1]); 

			if (uf.find(p)== uf.find(q)) continue;
			uf.union(p,q);
			System.out.println( p + " " + q);

			index = input.nextLine();
			indexarray = index.split("\\s+");

		}
		System.out.println(uf.count() + "components");
	}

}