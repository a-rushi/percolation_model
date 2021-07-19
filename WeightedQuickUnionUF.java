package UF;

public class WeightedQuickUnionUF{

	private int[] parent;
	private int count;
	private int[] rank;

	public WeightedQuickUnionUF(int N){
		count = N;
		parent = new int[N];
		rank = new int[N];
		
		for(int i = 0; i < N; i++){
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int find(int p){
		validate(p);
		while (p != parent[p]){
			p = parent[p];
		}

		return p;
	}

	public void union(int p, int q){

		validate(p);
		validate(q);
		int ParP = find(p);
		int ParQ = find(q);

		if (rank[p]>rank[q]){ 
			
			parent[ParQ] = ParP;
			rank[ParP] += ParQ;
		}

		else{
			parent[ParP] = ParQ;
			rank[ParQ] += ParP;
		}

		count --;	
	}

	public boolean connected(int p, int q){
		validate(p);
		validate(q);
		int ParP = find(p);
		int ParQ = find(q);

		return ParP == ParQ;
	}

	public int count(){
		return count;
	}

	public void validate(int p){
		int n = parent.length;
		if (p <0 || p>=n ){
			throw new IllegalArgumentException("index " + p + " is not b/w 0 and " + (n-1));
		}
	}
}