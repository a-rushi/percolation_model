import java.util.Scanner;  

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
		}
	}

	public int find(int p){
		validate(p)
		while (p != parent[p]){
			p = parent[p];
		}
	return p;
	}

	public int count(){
		return count
	}

	public boolean connected(int p, int q){
		return find(p)==find (q);
	}

	public void union(int p ,int q){
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) return;

		else {
			parent[rootP]=rootQ;
		}
		count --;
	}

	public void validate(int p){
		int n = parent.length;
		if (p<0 || p>=n){
			throw new IllegalArgumentException("index" + p + "is not between 0 and " + (n-1))
		}
	}

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		int n = input.nextInt()
		UF uf = new UF(n);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int a[] = new int[input.length];
    
		while (a.length != 0){
			a[i] = Integer.parseInt(input[i]);

			p = a[0]; 
			q = a[1];
			
			if (uf.find(p)== uf.find(q)) continue;
			uf.union(p,q);
			System.out.println( p + " " + q)
		}
		System.out.println(uf.count() + "components");
	}

}