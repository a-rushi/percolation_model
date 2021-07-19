import java.util.*;

public class QuickFindUF
{
	private int[] id;
	private int count;

	public QuickFindUF(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i ++)
			id[i] = i;
	}

	public int count(){
		return count;
	}

	public boolean connected(int p,int q)
	{ 
		validate(p);
		validate(q);
		return id[p] == id[q];
	}

	public void validate(int p)
	{
		int n = id.length;
		if (p<0 || p>=n){
			throw new IllegalArgumentException(" index " + p + " is not between 0 and " + (n-1));
		}
	}
	public int find(int p){
		validate(p);
		return id[p];
	}

	public void union(int p,int q)
	{
		int pid = id[p];
		int qid = id[q];
		for (int i =0; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
		count --;
	}

	public static void main(String[] args){
		Scanner set = new Scanner(System.in);

		int n = set.nextInt();
		QuickFindUF uf = new QuickFindUF(n);
		
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