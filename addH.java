import java.io.*;
import java.util.Vector;
import java.util.Arrays;
import java.text.DecimalFormat;
class dMain
{
	void residue(int nr,String s) throws Exception
	{ 
		DecimalFormat df=new DecimalFormat("#.000");
		df.setMaximumFractionDigits(3);
		int i=1;
		double rx,ry,rz,fx,fy,fz,dx,dy,dz;
		String s1=s+"_H";
		BufferedWriter br=new BufferedWriter(new FileWriter(s1,false));
		for(int n=1;n<=nr;n++)
		{
			InputStream f=new FileInputStream(s);
			BufferedReader bbr=new BufferedReader(new InputStreamReader(f));
			System.out.println("value of i is ::"+n);
			String bLine=bbr.readLine();
			String bWord[]=bLine.split("\\s+");
			BHA:while(bWord[0]!="END")
			{
				if(Integer.parseInt(bWord[4])==n)
				{
					InputStream f1=new FileInputStream("amino acid.txt");
					BufferedReader rbr=new BufferedReader(new InputStreamReader(f1));
					String rLine=rbr.readLine();
					String rWord[]=rLine.split("\\s+");
					while(rWord[0]!="END")
					{
						if(rWord.length==1)
						{
							if(bWord[3].equals(rWord[0]))
							{
								rbr.mark(1000);
								rLine=rbr.readLine();
								int count=0;
								while(rLine.length()!=3)
								{
									rLine=rbr.readLine();
									count++;
								}
								while(Integer.parseInt(bWord[4])==n)
								{
									rbr.reset();
									rLine=rbr.readLine();
									String q[]=rLine.split("\\s+");
									rWord=q;
									int h=0;
									while(!bWord[2].equals(rWord[0])&&h<=count)
									{
										rLine=rbr.readLine();
										String c[]=rLine.split("\\s+");
										rWord=c;
										h++;
									}
									if(bWord[2].length()==1)
										br.write(bWord[0]+"\t"+i+"\t\t"+bWord[2]+"  \t\t"+bWord[3]+"\t"+bWord[4]+"\t"+bWord[5]+"\t"+bWord[6]+"\t"+bWord[7]+"\t"+bWord[8]+"\t"+bWord[9]+"\t\t"+bWord[10]+"\n");
									else
										br.write(bWord[0]+"\t"+i+"\t\t"+bWord[2]+"  \t"+bWord[3]+"\t"+bWord[4]+"\t"+bWord[5]+"\t"+bWord[6]+"\t"+bWord[7]+"\t"+bWord[8]+"\t"+bWord[9]+"\t\t"+bWord[10]+"\n");
										br.flush();
									i++;
									if(rWord[0].equals(bWord[2]))
									{
										fx=Double.parseDouble(bWord[5]);fy=Double.parseDouble(bWord[6]);fz=Double.parseDouble(bWord[7]);
										rx=Double.parseDouble(rWord[1]);ry=Double.parseDouble(rWord[2]);rz=Double.parseDouble(rWord[3]);
										dx=rx-fx;dy=ry-fy;dz=rz-fz;
										//System.out.println("OUTSIDE  : "+bWord[2]+" "+fx+" "+fy+" "+fz+"   "+rx+" "+ry+" "+rz);
										//System.out.println("OUTSIDE  : "+bWord[2]+" "+df.format(dx)+" "+df.format(dy)+" "+df.format(dz));
										System.out.println();
										rLine=rbr.readLine();
										String ca[]=rLine.split("\\s+");
										rWord=ca;
										char ch[]=rWord[0].toCharArray();
										B:while(ch[0]=='H'&&!rWord[0].equals("HIS"))
										{
											if(bWord[2].equals("N")&&bWord[3].equals("PRO"))
												break B;
											rx=Double.parseDouble(rWord[1]);ry=Double.parseDouble(rWord[2]);rz=Double.parseDouble(rWord[3]);
											fx=rx-dx;fy=ry-dy;fz=rz-dz;
											//System.out.println("INSIDE : "+bWord[2]+" "+df.format(dx)+" "+df.format(dy)+" "+df.format(dz)+"   "+rx+" "+ry+" "+rz);
											System.out.println("INSIDE : "+rWord[0]+" "+df.format(fx)+" "+df.format(fy)+" "+df.format(fz));
											br.write(bWord[0]+"\t"+i+"\t\t"+rWord[0]+"  \t"+bWord[3]+"\t"+bWord[4]+"\t"+df.format(fx)+"\t"+df.format(fy)+"\t"+df.format(fz)+"\t"+bWord[8]+"\t"+bWord[9]+"\t\t"+"H"+"\n");
											br.flush();
											i++;
											if(Character.isDigit(ch[1])&&Integer.parseInt(bWord[4])!=1)
												break B;
											rLine=rbr.readLine();
											String cb[]=rLine.split("\\s+");
											rWord=cb;
											char cc[]=rWord[0].toCharArray();
											ch=cc;
										}
									}
									//System.out.println(" REFERENCE : "+rWord[0]+"   "+Thread.currentThread());
									//System.out.println(" ACTUAL : "+bWord[1]+"  "+bWord[2]+"   "+Thread.currentThread());
									bLine=bbr.readLine();
									String d[]=bLine.split("\\s+");
									bWord=d;
									if(bLine.length()<78)
									{
										br.write("TER"+"\n");
										br.flush();
										bLine=bbr.readLine();
										String b[]=bLine.split("\\s+");
										bWord=b;
									}
									if(bWord[0].equals("END"))
										break BHA;
								}
							}
						}
						rLine=rbr.readLine();
						String c[]=rLine.split("\\s+");
						rWord=c;
						if(n<Integer.parseInt(bWord[4]))
							rWord[0]="END";
					}
				}
				else
				{
					if(n<Integer.parseInt(bWord[4]))
						bWord[0]="END";
					else
					{
						bLine=bbr.readLine();
						String b[]=bLine.split("\\s+");
						bWord=b;
					}
				}
			}
			bbr.close();
		}
	}
}
	
class addH
{
	public static void main(String arg[]) throws Exception
	{
		String aa[]=new String []{"ALA","ARG","ASN","ASP","CYS","GLU","GLN","GLY","HIS","ILE","LEU","LYS","MET","PHE","PRO","SER","THR","TRP","TYR","VAL"};
		Vector v= new Vector(Arrays.asList(aa));
		InputStream f=new FileInputStream("ZINC69844431.pdb");
		BufferedReader br=new BufferedReader(new InputStreamReader(f));
		String Line=br.readLine();
		String Word[]=Line.split("\\s+");
		int nr=0;
		while(v.contains(Word[3]))
		{
			if(nr<=Integer.parseInt(Word[4]))
				nr=Integer.parseInt(Word[4]);
			Line=br.readLine();
			String a[]=Line.split("\\s+");
			Word=a;
			if("END".equals(Word[0]))
				break;
		}
		System.out.println(" no.of residues in protein:  "+nr);
		dMain d=new dMain();
		d.residue(nr,"ZINC69844431.pdb");
		int i=0;
		BufferedWriter bbr=new BufferedWriter(new FileWriter("ZINC69844431.pdb_H",true));
		ANU:while(Word[0]!="END")
		{
			i++;
			Line=br.readLine();
			String W[]=Line.split("\\s+");
			Word=W;
			if(Word[0].equals("TER"))
			{
				bbr.write("TER"+"\n");
				bbr.flush();
				Line=br.readLine();
				String t[]=Line.split("\\s+");
				Word=t;
				i=1;
			}
			if(Word[0].equals("END"))
			{
				bbr.write("END"+"\n");
				bbr.flush();
				break ANU;
			}
			bbr.write(Word[0]+"\t"+i+"\t\t"+Word[2]+"  \t"+Word[3]+"\t"+Word[4]+"\t"+Word[5]+"\t"+Word[6]+"\t"+Word[7]+"\t"+Word[8]+"\t"+Word[9]+"\t\t"+Word[10]+"\n");
			bbr.flush();
		}
	}
}