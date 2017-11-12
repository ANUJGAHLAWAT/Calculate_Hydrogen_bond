package Hfunction;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.Arrays;

public class calH
{
	String choosenL;
	public String[] nLig(String file2) throws Exception
	{
		String aa[]=new String []{"ALA","ARG","ASN","ASP","CYS","GLU","GLN","GLY","HIS","ILE","LEU","LYS","MET","PHE","PRO","SER","THR","TRP","TYR","VAL"};
		Vector v= new Vector(Arrays.asList(aa));
		InputStream f=new FileInputStream(file2);
		BufferedReader br=new BufferedReader(new InputStreamReader(f));
		String Line=br.readLine();
		String Word[]=Line.split("\\s+");
		String Lig[]=new String[10];
		int i=1;
		while(!Line.contains("END"))
		{
			if(!v.contains(Word[3]))
			{
				String [] word =Line.split("\\s+");
				if(!word[3].equals(Lig[i-1]))
				{
					Lig[i]=word[3];
						i++;
				}
			}
			Line=br.readLine();
			String W[]=Line.split("\\s+");
			Word=W;
			if(Word[0].equals("TER"))
			{
				Line=br.readLine();
				String W1[]=Line.split("\\s+");
				Word=W1;
			}
		}
		br.close();
		return Lig;
	}

	public calH(String s1) throws Exception
	{
		//output o=new output();
		//o.nextLine("Ligand.txt","REFERENCE FILE IS :: "+file,false);
		String s2[]=nLig(s1);
		//o.nextLine("Ligand.txt","",true);
		for(int i=1;i<s2.length;i++)
		{
			if(s2[i]!=null)
			{
				System.out.println("Ligand "+ i+ " is :: "+s2[i]);
				String s3=i+" Ligand molecule is ::";
				//o.space("Ligand.txt",s3,true);
				//o.nextLine("Ligand.txt",s2[i],true);
			}
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the ligand for which  you want to calculate Hydrogen bond : ");
		choosenL=br.readLine();
		System.out.println("Selected ligand is :: "+choosenL);
		//o.nextLine("Ligand.txt","Selected ligand is "+choosenL,true);
		checkH(s1);
	}
	
	public void checkH(String s) throws Exception
	{
		InputStream f=new FileInputStream(s);
		BufferedReader br=new BufferedReader(new InputStreamReader(f));
		DecimalFormat df=new DecimalFormat("#.000");
		df.setMaximumFractionDigits(3);
		String Line=br.readLine();
		String Word[]=Line.split("\\s+");
		while(!Word[3].equals(choosenL))
		{
			Line=br.readLine();
			String W[]=Line.split("\\s+");
			Word=W;
			if(Word[0].equals("TER"))
			{
				br.mark(10000);
				Line=br.readLine();
				String y[]=Line.split("\\s+");
				Word=y;
			}
		}
		double x=0,y=0,z=0;
		int n=0;
		while(!(Word[0].equals("TER")||Word[0].equals("END")))
		{
			n++;
			x+=Double.parseDouble(Word[5]);y+=Double.parseDouble(Word[6]);z+=Double.parseDouble(Word[7]);
			Line=br.readLine();
			String Y[]=Line.split("\\s+");
			Word=Y;
		}
		x=x/n;y=y/n;z=z/n;
		//System.out.println(df.format(x)+" \t"+df.format(y)+" \t"+df.format(z)+" \t "+n);
		//br.close();
		InputStream f1=new FileInputStream(s);
		BufferedReader abr=new BufferedReader(new InputStreamReader(f1));
		Line=abr.readLine();
		String Wi[]=Line.split("\\s+");
		Word=Wi;
		double xp,yp,zp,xl,yl,zl,dis;
		String lLine;
		String lWord[];
		A:while(!(Word[0].equals("TER")||Word[0].equals("END")))
		{
			xp=Double.parseDouble(Word[5]);yp=Double.parseDouble(Word[6]);zp=Double.parseDouble(Word[7]);
			dis=Math.sqrt(Math.pow((x-xp),2)+Math.pow((y-yp),2)+Math.pow((z-zp),2));
			int a=Integer.parseInt(Word[4]);
			if(dis>10)
			{
				while(Integer.parseInt(Word[4])==a)
				{
					Line=abr.readLine();
					String Wa[]=Line.split("\\s+");
					Word=Wa;
					if(Word[0].equals("TER"))
						break A;
					//System.out.println("BHAI SHAAB : "+Word[4]);
				}
			}
			else
			{
				//System.out.println(Word[4]+"   : "+a);
				while(Integer.parseInt(Word[4])==a)
				{
					if(Word[10].equals("N")||Word[10].equals("O")||Word[10].equals("F")||Word[10].equals("O1-"))
					{
						xp=Double.parseDouble(Word[5]);yp=Double.parseDouble(Word[6]);zp=Double.parseDouble(Word[7]);
						br.reset();
						lLine=br.readLine();
						String Wd[]=lLine.split("\\s+");
						lWord=Wd;
						while(!(lWord[0].equals("TER")||lWord[0].equals("END")))
						{
							if(lWord[10].equals("H"))
							{
								xl=Double.parseDouble(lWord[5]);yl=Double.parseDouble(lWord[6]);zl=Double.parseDouble(lWord[7]);
								dis=Math.sqrt(Math.pow((xl-xp),2)+Math.pow((yl-yp),2)+Math.pow((zl-zp),2));
								if((dis>=1.8&&dis<=2.8))
								{
									System.out.println("Distance is :: "+df.format(dis)+" between "+Word[10]+" of "+Word[3]+" and "+lWord[10]+" : "+lWord[2]+" of "+lWord[3]);
								}
							}
							lLine=br.readLine();
							String Wf[]=lLine.split("\\s+");
							lWord=Wf;
						}
					}
					else if(Word[10].equals("N1+"))
					{
						Line=abr.readLine();
						String ba[]=Line.split("\\s+");
						Word=ba;
						while(Word[10].equals("H"))
						{
							xp=Double.parseDouble(Word[5]);yp=Double.parseDouble(Word[6]);zp=Double.parseDouble(Word[7]);
							br.reset();
							lLine=br.readLine();
							String Wg[]=lLine.split("\\s+");
							lWord=Wg;
							while(!(lWord[0].equals("TER")||lWord[0].equals("END")))
							{
								if(lWord[10].equals("N")||lWord[10].equals("O")||lWord[10].equals("F")||lWord[10].equals("O1-"))
								{
									xl=Double.parseDouble(lWord[5]);yl=Double.parseDouble(lWord[6]);zl=Double.parseDouble(lWord[7]);
									dis=Math.sqrt(Math.pow((xl-xp),2)+Math.pow((yl-yp),2)+Math.pow((zl-zp),2));
									if((dis>=1.8&&dis<=2.8))
									{
										System.out.println("Distance is :: "+df.format(dis)+" between "+Word[10]+" of "+Word[3]+" and "+lWord[10]+" : "+lWord[2]+" of "+lWord[3]);
									}
								}
								lLine=br.readLine();
								String Wh[]=lLine.split("\\s+");
								lWord=Wh;
							}
							Line=abr.readLine();
							String bd[]=Line.split("\\s+");
							Word=bd;
						}
					}
					Line=abr.readLine();
					String We[]=Line.split("\\s+");
					Word=We;
					//System.out.println("Anubha Gaur "+Word[4]);
				}
			}
			if(Integer.parseInt(Word[4])==a)
			{
				//System.out.println("");
				Line=abr.readLine();
				String bc[]=Line.split("\\s+");
				Word=bc;
			}
		}
	}
}