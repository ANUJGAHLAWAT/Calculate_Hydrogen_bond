import Hfunction.*;
import java.io.*;

class Hydrogen
{
	public static void main(String arg[])throws Exception
	{
		String file;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		/*System.out.println("Enter the fileerence file name for which you wanted calculated RMSD value of ligand");
		file=br.readLine();
		System.out.println("how many fileerence file you have??");
		SAM=br.readLine();
		int n=Integer.parseInt(SAM);
		file="ZINC69844431.pdb";
		cal c=new cal(file);
		for(int i=1;i<=n;i++)
		{
			SAM="ZINC69844431_"+i+"ns.pdb";
			c.tcall(file,SAM);
		}*/
		calH c=new calH("ZINC69844431.pdb_H");
	}
}