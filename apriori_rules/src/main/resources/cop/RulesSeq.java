/*

the file path is /home/madhulekha/Downloads/t/marketdata.txt

the output folder is /home/madhulekha/Downloads/itemsets/

*************************************************
**/
import java.util.*;
import java.io.*;


public class RulesSeq{

	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		// do work
		
		
		int MSC=50;double MCL=0.75;

		FileReader in = new FileReader("cop/marketdata.txt");
	    BufferedReader kk = new BufferedReader(in);

	  /*  while (br.readLine() != null) {
	        System.out.println(br.readLine());
	    }*/
	    
		//System.out.println("Enter");	// read input from input.txt
		
		//BufferedReader kk=new BufferedReader(new InputStreamReader(System.in));
		//int org=1;
		int n=100;	
		int m=200,p=10000,q=100000,r=10000000,t=100000,u=10000;
		
		LinkedList<String> li=new LinkedList<String>();
		LinkedList<String> one[]=new LinkedList[n];
		LinkedList<String> two[]=new LinkedList[m];
		LinkedList<String> twof[]=new LinkedList[m];
		LinkedList<String> three[]=new LinkedList[p];
		LinkedList<String> threef[]=new LinkedList[p];
		LinkedList<String> four[]=new LinkedList[q];
		LinkedList<String> fourf[]=new LinkedList[q];
		LinkedList<String> five[]=new LinkedList[r];
		LinkedList<String> fivef[]=new LinkedList[r];
		LinkedList<String> six[]=new LinkedList[t];
		LinkedList<String> sixf[]=new LinkedList[t];
		LinkedList<String> seven[]=new LinkedList[u];
		LinkedList<String> sevenf[]=new LinkedList[u];
		
		TreeMap<String, Integer> cache=new TreeMap<String, Integer>();
		TreeMap<String,Integer> pairs=new TreeMap<String,Integer>();
		TreeMap<String,Integer> trip=new TreeMap<String,Integer>();
		TreeMap<String,Integer> quad=new TreeMap<String,Integer>();
		TreeMap<String,Integer> penta=new TreeMap<String,Integer>();
		TreeMap<String,Integer> sexa=new TreeMap<String,Integer>();
		TreeMap<String,Integer>	sept=new TreeMap<String,Integer>();
		
		int i,j,k,value;	
		String s;
		while((s=kk.readLine())!=null)
		{
			//String s=kk.readLine();
			LinkedList<String> temp=new LinkedList<String>();
			if(s.isEmpty())  break;
			StringTokenizer st = new StringTokenizer(s);
			while (st.hasMoreElements()) {
				String s1=st.nextToken();
				temp.add(s1);
				if(cache.containsKey(s1))
				{
					value=cache.get(s1);
					value++;
					cache.put(s1, value);
				}
				else
				cache.put(s1, 1);
				//System.out.println(st.nextElement());		// adding things into the cache
			}
			Collections.sort(temp);							//sorting each line
			Iterator itp=temp.iterator();
			String sp=" ";
			while(itp.hasNext())
			{
				String sp1=itp.next().toString();
				sp=sp.concat(sp1+" ");
			}
			//System.out.println(sp);
			li.add(sp);									//forming the list li
			//org++;
		}
		//org=i;
		in.close(); 
		int org=li.size();

			File file = new File("cop/itemsets/one.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
		i=0;int com;String ke;
		 Set set = cache.entrySet();
	      Iterator itt = set.iterator();
	      while(itt.hasNext()) 
	      {
	         Map.Entry me = (Map.Entry)itt.next();
	         com=Integer.parseInt((me.getValue()).toString());
	         ke=me.getKey().toString();
	     	 //System.out.println(ke+" "+com);
	         if(com>=MSC)
	         { one[i] = new LinkedList<String>();
	         	//System.out.println(ke+" "+com);
	           one[i].add(ke);
			one[i].add(Integer.toString(com));
			String tempo=ke+" "+com;
			bw.write(tempo);
			bw.write("\n");
	           i++;												
	         }
	      }
		n=i; bw.close();
		// n is the number of one
		/*System.out.println("n is "+n);
		
		for(i=0;i<n;i++)
		{
			System.out.println(one[i].get(0));
		}
		*/
		//for(i=0;i<n;i++)
			//Collections.sort(li[i]);
		//---------------------------------------------------------------------------------------------------------	
		k=0;
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				two[k] = new LinkedList<String>();
				two[k].add(one[i].get(0));
				two[k].add(one[j].get(0));
				k++;
			}			
		}													 
		m=k;	
								//two[0],two[1].. are all possible combinations possible
		for(k=0;k<m;k++)
		{
			String sub=two[k].get(0)+" "+two[k].get(1);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(two[k].get(0))&&full.contains(two[k].get(1)))
				{
					if(pairs.containsKey(sub))
					{
						value=pairs.get(sub);
						value++;
						pairs.put(sub, value);
					}
					else
					pairs.put(sub,1);
				}				
			}
		}												//counting job in pair 
		
			File file2 = new File("cop/itemsets/two.txt");			//
 
			// if file doesnt exists, then create it						//
			if (!file2.exists()) {									//
				file2.createNewFile();								//
			}											//
 
			FileWriter fw2 = new FileWriter(file2.getAbsoluteFile());					//
			BufferedWriter bw2 = new BufferedWriter(fw2);						//

		k=0;
		//System.out.println();
		Set set2 = pairs.entrySet();
	      Iterator itpair = set2.iterator();
	      while(itpair.hasNext()) 
	      {
	         Map.Entry met=(Map.Entry)itpair.next();
	         int v=Integer.parseInt(met.getValue().toString());
	         String v2=met.getKey().toString();
	         //System.out.println("pair is "+v2+" "+v);
	         if(v>=MSC)
	         {
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	twof[k]=new LinkedList<String>();
	        	twof[k].add(stp.nextToken());
	        	twof[k].add(stp.nextToken());
			twof[k].add(Integer.toString(v));
	        	//System.out.println(twof[k].get(0)+" "+twof[k].get(1)+" "+v);
			bw2.write(twof[k].get(0)+" "+twof[k].get(1)+" "+v);
			bw2.write("\n");
	        	k++;
	         }
	         
	      }
		m=k;	bw2.close();								//twof[] are the final pairs created

			File file21 = new File("cop/itemsets/tworrules.txt");		//
			// if file doesnt exists, then create it						//
			if (!file21.exists()) {									//
				file21.createNewFile();								//
			}											//
 
			FileWriter fw21 = new FileWriter(file21.getAbsoluteFile());				//
			BufferedWriter bw21 = new BufferedWriter(fw21);						//

		for(i=0;i<m;i++)
		{
			String f=twof[i].get(1);
			String l=twof[i].get(0);
			double sup;
			double numerator=Double.parseDouble(twof[i].get(2));
			//System.out.println(f+" "+l+" "+numerator);
			double denominator=1;
			int sm;
				for(sm=0;sm<n;sm++)
				{ 		
					String temp=one[sm].get(0);		
					if(temp.compareTo(l)==0)
					{	denominator=Double.parseDouble(one[sm].get(1));	
						//System.out.println("found denominator is "+denominator);
						break;
					}
				}
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);
				if(sup>MCL)
				bw21.write(l+" -> "+f+"		"+sup+" \n");
			
				for(sm=0;sm<n;sm++)
				{ 		
					String temp=one[sm].get(0);		
					if(temp.compareTo(f)==0)
					{
						denominator=Double.parseDouble(one[sm].get(1));	
						//System.out.println("found denominator is "+denominator);
						break;
					}	
				}
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);	
				if(sup>MCL)
				bw21.write(f+" -> "+l+" 	"+sup+" \n");

		}
		bw21.close();
		//---------------------------------------------------------------------------------------------------------
		j=0;
		for(k=0;k<m;k++)
		{
			for(i=k+1;i<m;i++)
			{
				String s3=twof[k].get(1);
				if(s3.compareTo(twof[i].get(0))==0)
				{
					three[j]=new LinkedList<String>();
					three[j].add(twof[k].get(0));
					three[j].add(twof[k].get(1));
					three[j].add(twof[i].get(1));	
					j++;
				}
			}
		}
		p=j;											//three[] combinations possible
		
		for(k=0;k<p;k++)
		{
			String sub=three[k].get(0)+" "+three[k].get(1)+" "+three[k].get(2);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(three[k].get(0))&&full.contains(three[k].get(1))&&full.contains(three[k].get(2)))
				{
					if(trip.containsKey(sub))
					{
						value=trip.get(sub);
						value++;
						trip.put(sub, value);
					}
					else
					trip.put(sub,1);
				}				
			}
		}										//trip[] having the counts	
		
		
			File file3 = new File("cop/itemsets/three.txt");			//
			// if file doesnt exists, then create it						//
			if (!file3.exists()) {									//
				file3.createNewFile();								//
			}											//
 
			FileWriter fw3 = new FileWriter(file3.getAbsoluteFile());						//
			BufferedWriter bw3 = new BufferedWriter(fw3);								//
		k=0;
		//System.out.println();
		Set set3 = trip.entrySet();
	      Iterator ittpair = set3.iterator();
	      while(ittpair.hasNext()) 
	      {
	         Map.Entry met=(Map.Entry)ittpair.next();
	         int v=Integer.parseInt(met.getValue().toString());
	         String v2=met.getKey().toString();
	        // System.out.println("triplet is "+v2+" "+v);
	         if(v>=MSC)
	         {
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	threef[k]=new LinkedList<String>();
	        	threef[k].add(stp.nextToken());
	        	threef[k].add(stp.nextToken());
	        	threef[k].add(stp.nextToken());
			threef[k].add(Integer.toString(v));
	        	//System.out.println(threef[k].get(0)+" "+threef[k].get(1)+" "+threef[k].get(2)+" "+v);
			bw3.write(threef[k].get(0)+" "+threef[k].get(1)+" "+threef[k].get(2)+" "+v);
			bw3.write("\n");
	        	k++;
	         }
	         
	      }
		p=k;
		bw3.close();							//threef[] all those with greater than MSC
		File file31 = new File("cop/itemsets/threerules.txt");			//
			// if file doesnt exists, then create it						//
			if (!file31.exists()) {									//
				file31.createNewFile();								//
			}											//
 
			FileWriter fw31 = new FileWriter(file31.getAbsoluteFile());						//
			BufferedWriter bw31 = new BufferedWriter(fw31);	
	
		for(i=0;i<p;i++)
		{
			int po;
			for(po=0;po<3;po++)
			{

				double sup;
				double numerator=Double.parseDouble(threef[i].get(3));
				double denominator=1;
				int sm;
				String c=threef[i].get(po%3);
				LinkedList<String> temp2=new LinkedList<String>();
				temp2.add(threef[i].get((po+1)%3));
				temp2.add(threef[i].get((po+2)%3));
				Collections.sort(temp2);
				String a=temp2.get(0)+" "+temp2.get(1);
				//System.out.println(c+" "+a+" "+numerator);
				int f=0;
				for(sm=0;sm<m;sm++)
				{ 		
					String temp=twof[sm].get(0)+" "+twof[sm].get(1);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(twof[sm].get(2));	
						//System.out.println("found denominator is "+denominator);
						f=1;
						break;
					}
				}
				if(f==1)
				{
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);
				if(sup>MCL)
				bw31.write(a+" -> "+c+"		"+sup+" \n");
				}
				//bw31.write(threef[i].get((po+1)%3)+" "+threef[i].get((po+2)%3)+" -> "+threef[i].get(po%3)+"\n");
				LinkedList<String> temp3=new LinkedList<String>();
				temp3.add(threef[i].get((po+1)%3));
				temp3.add(threef[i].get((po+2)%3));
				Collections.sort(temp3);	
				c=temp3.get(0)+" "+temp3.get(1);
				a=threef[i].get(po%3);
				//System.out.println(c+" "+a+" "+numerator);
				f=0;
				for(sm=0;sm<n;sm++)
				{ 		
					String temp=one[sm].get(0);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(one[sm].get(1));	
						//System.out.println("found denominator is "+denominator);
						f=1;
						break;
					}
				}
				if(f==1)
				{
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);
				if(sup>MCL)
				bw31.write(a+" -> "+c+"		"+sup+" \n");
				}
		
				//bw31.write(threef[i].get(po%3)+" -> "+threef[i].get((po+1)%3)+" "+threef[i].get((po+2)%3)+"\n");
		
			}
		}
		bw31.close();

		//----------------------------------------------------------------------------------------------------------
		k=0;
		for(i=0;i<p;i++)
		{
			String s4=threef[i].get(1)+" "+threef[i].get(2);
			for(j=i+1;j<p;j++)
			{
				String s5=threef[j].get(0)+" "+threef[j].get(1);
				if(s4.compareTo(s5)==0)
				{
					four[k]=new LinkedList<String>();
					four[k].add(threef[i].get(0));
					four[k].add(threef[i].get(1));
					four[k].add(threef[i].get(2));
					four[k].add(threef[j].get(2));
					//System.out.println(threef[i].get(0)+" "+threef[i].get(1)+" "+threef[i].get(2) +" "+threef[j].get(2));
					k++;
				}
				
			}
		}
		q=k;					//four[]  combinations possible
		if(k==0)
		{
			System.out.println("no combinations of four is possible");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		bw.close();
		System.out.println("Time in seconds "+timeInSeconds);
			System.exit(0);
		}
			
		for(k=0;k<q;k++)
		{
			String sub=four[k].get(0)+" "+four[k].get(1)+" "+four[k].get(2)+" "+four[k].get(3);
			//System.out.println("substring is "+sub);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(four[k].get(0))&&full.contains(four[k].get(1))&&full.contains(four[k].get(2))&&full.contains(four[k].get(3)))
				{
					if(quad.containsKey(sub))
					{
						value=quad.get(sub);
						value++;
						quad.put(sub, value);
						//System.out.println("putting this "+sub+" "+value);
					}
					else
					{quad.put(sub,1);
					//System.out.println("putting this "+sub+" "+value);
					}
				}				
			}
		}									//quad[] having counts
		if(quad.isEmpty())
		{
			System.out.println("no 4 crossed the cut off");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		//bw.close();
		System.out.println("Time in seconds "+timeInSeconds);
			System.exit(0);
		}
		
		k=0;
			File file4 = new File("cop/itemsets/four.txt");			//
 
			// if file doesnt exists, then create it						//
			if (!file4.exists()) {									//
				file4.createNewFile();								//
			}											//
 
			FileWriter fw4 = new FileWriter(file4.getAbsoluteFile());						//
			BufferedWriter bw4 = new BufferedWriter(fw4);								//
		//System.out.println();
		Set set4 = quad.entrySet();
	      Iterator itquad = set4.iterator();
	      while(itquad.hasNext()) 
	      {
	         Map.Entry metq=(Map.Entry)itquad.next();
	         int v=Integer.parseInt(metq.getValue().toString());
	         String v2=metq.getKey().toString();
	         //System.out.println("quad is "+v2+" "+v);
	         if(v>=MSC)
	         {	
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	fourf[k]=new LinkedList<String>();
	        	fourf[k].add(stp.nextToken());
	        	fourf[k].add(stp.nextToken());
	        	fourf[k].add(stp.nextToken());
	        	fourf[k].add(stp.nextToken());
			fourf[k].add(Integer.toString(v));
	        	//System.out.println(fourf[k].get(0)+" "+fourf[k].get(1)+" "+fourf[k].get(2)+" "+fourf[k].get(3)+" "+v);
			bw4.write(fourf[k].get(0)+" "+fourf[k].get(1)+" "+fourf[k].get(2)+" "+fourf[k].get(3)+" "+v);
			bw4.write("\n");
	        	k++;
	         }
	         
	      }
		q=k;	bw4.close();								//fourf[] having all those with >=MSC
		if(k==0)
		{
			System.out.println("No fours got finalised");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		File file41 = new File("cop/itemsets/fourrules.txt");			//
			// if file doesnt exists, then create it						//
			if (!file41.exists()) {									//
				file41.createNewFile();								//
			}											//
 
			FileWriter fw41 = new FileWriter(file41.getAbsoluteFile());						//
			BufferedWriter bw41 = new BufferedWriter(fw41);		
		for(i=0;i<q;i++)
		{
			int po;
			for(po=0;po<4;po++)
			{
				double sup;
				double numerator=Double.parseDouble(fourf[i].get(4));
				double denominator=1;
				int sm;	int f=0;
				LinkedList<String> temp2=new LinkedList<String>();
				temp2.add(fourf[i].get((po+1)%4));
				temp2.add(fourf[i].get((po+2)%4));
				temp2.add(fourf[i].get((po+3)%4));
				Collections.sort(temp2);
				String c=fourf[i].get(po%4);
				String a=temp2.get(0)+" "+temp2.get(1)+" "+temp2.get(2);
				for(sm=0;sm<p;sm++)
				{ 		
					String temp=threef[sm].get(0)+" "+threef[sm].get(1)+" "+threef[sm].get(2);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(threef[sm].get(3));	
						//System.out.println("found denominator is "+denominator);
						f=1;
						break;
					}
				}
				if(f==1)
				{
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);
				if(sup>MCL)
				bw41.write(a+" -> "+c+"		"+sup+" \n");
				}
		//bw41.write(fourf[i].get((po+1)%4)+" "+fourf[i].get((po+2)%4)+" "+fourf[i].get((po+3)%4)+" -> "+fourf[i].get(po%4)+"\n");
				if(po==0||po==1||po==2)
				{
					c=fourf[i].get(po%4)+" "+fourf[i].get((po+1)%4);
					LinkedList<String> temp3=new LinkedList<String>();
					temp3.add(fourf[i].get((po+2)%4));
					temp3.add(fourf[i].get((po+3)%4));
					Collections.sort(temp3);
					a=temp3.get(0)+" "+temp3.get(1);
					f=0;
					for(sm=0;sm<m;sm++)
					{ 		
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);	
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));	
							//System.out.println("found denominator is "+denominator);
							f=1;
							break;
						}
					}
					if(f==1)
					{
					sup=numerator/denominator;
					//System.out.println("sup is "+sup);
					if(sup>MCL)
					bw41.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
				if(po==0||po==1)
				{
					c=fourf[i].get(po%4)+" "+fourf[i].get((po+2)%4);
					LinkedList<String> temp13=new LinkedList<String>();
					temp13.add(fourf[i].get((po+1)%4));
					temp13.add(fourf[i].get((po+3)%4));
					Collections.sort(temp13);
					a=temp13.get(0)+" "+temp13.get(1);
					f=0;
					for(sm=0;sm<m;sm++)
					{ 		
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);	
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));	
							//System.out.println("found denominator is "+denominator);
							f=1;
							break;
						}
					}
					if(f==1)
					{
					sup=numerator/denominator;
					//System.out.println("sup is "+sup);
					if(sup>MCL)
					bw41.write(a+" -> "+c+"		"+sup+" \n");
					}
				}

				if(po==0)
				{	c=fourf[i].get(po%4)+" "+fourf[i].get((po+3)%4);
					LinkedList<String> temp23=new LinkedList<String>();
					temp23.add(fourf[i].get((po+1)%4));
					temp23.add(fourf[i].get((po+2)%4));
					Collections.sort(temp23);
					a=temp23.get(0)+" "+temp23.get(1);
					f=0;
					for(sm=0;sm<m;sm++)
					{ 		
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);	
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));	
							//System.out.println("found denominator is "+denominator);
							f=1;
							break;
						}
					}
					if(f==1)
					{
					sup=numerator/denominator;
					//System.out.println("sup is "+sup);
					if(sup>MCL)
					bw41.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
		//bw41.write(fourf[i].get((po+2)%4)+" "+fourf[i].get((po+3)%4)+" -> "+fourf[i].get(po%4)+" "+fourf[i].get((po+1)%4)+"\n");
				c=fourf[i].get((po+1)%4)+" "+fourf[i].get((po+2)%4)+" "+fourf[i].get((po+3)%4);
				a=fourf[i].get(po%4);
				f=0;
				for(sm=0;sm<n;sm++)
				{ 		
					String temp=one[sm].get(0);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(one[sm].get(1));	
						//System.out.println("found denominator is "+denominator);
						f=1;
						break;
					}
				}
				if(f==1)
				{
				sup=numerator/denominator;
				//System.out.println("sup is "+sup);
				if(sup>MCL)
				bw41.write(a+" -> "+c+"		"+sup+" \n");
				}
		//bw41.write(fourf[i].get(po%4)+" -> "+fourf[i].get((po+1)%4)+" "+fourf[i].get((po+2)%4)+" "+fourf[i].get((po+3)%4)+"\n");
			}
		}
	
		bw41.close();
		//---------------------------------------------------------------------------------------------------
		k=0;
		for(i=0;i<q;i++)
		{
			String s4=fourf[i].get(1)+" "+fourf[i].get(2)+" "+fourf[i].get(3);
			for(j=i+1;j<q;j++)
			{
				String s5=fourf[j].get(0)+" "+fourf[j].get(1)+" "+fourf[j].get(2);
				if(s4.compareTo(s5)==0)
				{
					five[k]=new LinkedList<String>();
					five[k].add(fourf[i].get(0));
					five[k].add(fourf[i].get(1));
					five[k].add(fourf[i].get(2));
					five[k].add(fourf[i].get(3));
					five[k].add(fourf[j].get(3));
					//System.out.println(five[k].get(0)+" "+five[k].get(1)+" "+five[k].get(2) +" "+five[k].get(3)+" "+five[k].get(4));
					k++;
					//System.out.println("k is "+k);
				}			
			}
		}
		r=k;
		if(k==0)
		{
			System.out.println("no combinations of five is possible");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
			
		for(k=0;k<r;k++)
		{
			String sub=five[k].get(0)+" "+five[k].get(1)+" "+five[k].get(2)+" "+five[k].get(3)+" "+five[k].get(4);
			//System.out.println("substring is "+sub);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(five[k].get(0))&&full.contains(five[k].get(1))&&full.contains(five[k].get(2))&&full.contains(five[k].get(3))&&full.contains(five[k].get(4)))
				{
					if(penta.containsKey(sub))
					{
						value=penta.get(sub);
						value++;
						penta.put(sub, value);
						//System.out.println("putting this "+sub+" "+value);
					}
					else
					{penta.put(sub,1);
					//System.out.println("putting this "+sub+" "+value);
					}
				}				
			}
		}									//quad[] having counts
		if(penta.isEmpty())
		{
			System.out.println("no 5 crossed the cut off");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		
		k=0;
		//System.out.println();
			File file5 = new File("cop/itemsets/five.txt");			//
 
			// if file doesnt exists, then create it						//
			if (!file5.exists()) {									//
				file5.createNewFile();								//
			}											//
 
			FileWriter fw5 = new FileWriter(file5.getAbsoluteFile());						//
			BufferedWriter bw5 = new BufferedWriter(fw5);								//
		Set set5 = penta.entrySet();
	      Iterator itpenta = set5.iterator();
	      while(itpenta.hasNext()) 
	      {
	         Map.Entry metq=(Map.Entry)itpenta.next();
	         int v=Integer.parseInt(metq.getValue().toString());
	         String v2=metq.getKey().toString();
	         //System.out.println("quad is "+v2+" "+v);
	         if(v>=MSC)
	         {	
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	fivef[k]=new LinkedList<String>();
	        	fivef[k].add(stp.nextToken());
	        	fivef[k].add(stp.nextToken());
	        	fivef[k].add(stp.nextToken());
	        	fivef[k].add(stp.nextToken());
	        	fivef[k].add(stp.nextToken());
			fivef[k].add(Integer.toString(v));
	        	//System.out.println(fivef[k].get(0)+" "+fivef[k].get(1)+" "+fivef[k].get(2)+" "+fivef[k].get(3)+" "+fivef[k].get(4)+" "+v);
			bw5.write(fivef[k].get(0)+" "+fivef[k].get(1)+" "+fivef[k].get(2)+" "+fivef[k].get(3)+" "+fivef[k].get(4)+" "+v);
			bw5.write("\n");
	        	k++;
	         }
	         
	      }
		r=k;bw5.close();									//fivef[] having all those with >=MSC
		if(k==0)
		{
			System.out.println("None of the 5 were finalised");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		File file51 = new File("cop/itemsets/fiverules.txt");			//
			// if file doesnt exists, then create it						//
			if (!file51.exists()) {									//
				file51.createNewFile();								//
			}											//
 
			FileWriter fw51 = new FileWriter(file51.getAbsoluteFile());						//
			BufferedWriter bw51 = new BufferedWriter(fw51);		
		for(i=0;i<r;i++)
		{

			int po;
			for(po=0;po<5;po++)
			{
				double sup;
				double numerator=Double.parseDouble(fivef[i].get(5));
				double denominator=1;
				int sm;
				String c=fivef[i].get(po%5);
				LinkedList<String> temp2=new LinkedList<String>();
				temp2.add(fivef[i].get((po+1)%5));
				temp2.add(fivef[i].get((po+2)%5));
				temp2.add(fivef[i].get((po+3)%5));
				temp2.add(fivef[i].get((po+4)%5));
				Collections.sort(temp2);
				String a=temp2.get(0)+" "+temp2.get(1)+" "+temp2.get(2)+" "+temp2.get(3);
				//System.out.println(c+" "+a+" "+numerator);
				
		
				int f=0;
				for(sm=0;sm<q;sm++)
				{ 		
					String temp=fourf[sm].get(0)+" "+fourf[sm].get(1)+" "+fourf[sm].get(2)+" "+fourf[sm].get(3);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(fourf[sm].get(4));
						//System.out.println("1 2 3 4 -> 5");	
						//System.out.println("found denominator is "+denominator+" "+a);
						f=1;
						break;
					}
				}
				if(f==1)
				{
					sup=numerator/denominator;
					//System.out.println("sup is "+sup);
					if(sup>MCL)
					bw51.write(a+" -> "+c+"		"+sup+" \n");
				}
		
			


//bw51.write(fivef[i].get((po+1)%5)+" "+fivef[i].get((po+2)%5)+" "+fivef[i].get((po+3)%5)+" "+fivef[i].get((po+4)%5)+" -> "+fivef[i].get(po%5)+"\n");
				if(po==0||po==1||po==2||po==3)
				{	LinkedList<String> temp9=new LinkedList<String>();
					temp9.add(fivef[i].get(po%5));
					temp9.add(fivef[i].get((po+1)%5));
					Collections.sort(temp9);
					c=temp9.get(0)+" "+temp9.get(1);
					LinkedList<String> temp3=new LinkedList<String>();
					temp3.add(fivef[i].get((po+2)%5));
					temp3.add(fivef[i].get((po+3)%5));
					temp3.add(fivef[i].get((po+4)%5));
					Collections.sort(temp3);
					a=temp3.get(0)+" "+temp3.get(1)+" "+temp3.get(2);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<p;sm++)
					{ 		
						String temp=threef[sm].get(0)+" "+threef[sm].get(1)+" "+threef[sm].get(2);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(threef[sm].get(3));
							//System.out.println("1 2 3 -> 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
		
				if(po==0||po==1||po==2)
				{	LinkedList<String> temp19=new LinkedList<String>();
					temp19.add(fivef[i].get(po%5));
					temp19.add(fivef[i].get((po+2)%5));
					Collections.sort(temp19);
					c=temp19.get(0)+" "+temp19.get(1);
					LinkedList<String> temp13=new LinkedList<String>();
					temp13.add(fivef[i].get((po+1)%5));
					temp13.add(fivef[i].get((po+3)%5));
					temp13.add(fivef[i].get((po+4)%5));
					Collections.sort(temp13);
					a=temp13.get(0)+" "+temp13.get(1)+" "+temp13.get(2);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<p;sm++)
					{ 		
						String temp=threef[sm].get(0)+" "+threef[sm].get(1)+" "+threef[sm].get(2);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(threef[sm].get(3));
							//System.out.println("1 2 3 -> 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}

				if(po==0||po==1)
				{
					LinkedList<String> temp29=new LinkedList<String>();
					temp29.add(fivef[i].get(po%5));
					temp29.add(fivef[i].get((po+3)%5));
					Collections.sort(temp29);
					c=temp29.get(0)+" "+temp29.get(1);
					LinkedList<String> temp23=new LinkedList<String>();
					temp23.add(fivef[i].get((po+1)%5));
					temp23.add(fivef[i].get((po+2)%5));
					temp23.add(fivef[i].get((po+4)%5));
					Collections.sort(temp23);
					a=temp23.get(0)+" "+temp23.get(1)+" "+temp23.get(2);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<p;sm++)
					{ 		
						String temp=threef[sm].get(0)+" "+threef[sm].get(1)+" "+threef[sm].get(2);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(threef[sm].get(3));
							//System.out.println("1 2 3 -> 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}

				if(po==0)
				{
					LinkedList<String> temp39=new LinkedList<String>();
					temp39.add(fivef[i].get(po%5));
					temp39.add(fivef[i].get((po+4)%5));
					Collections.sort(temp39);
					c=temp39.get(0)+" "+temp39.get(1);
					LinkedList<String> temp33=new LinkedList<String>();
					temp33.add(fivef[i].get((po+1)%5));
					temp33.add(fivef[i].get((po+2)%5));
					temp33.add(fivef[i].get((po+3)%5));
					Collections.sort(temp33);
					a=temp33.get(0)+" "+temp33.get(1)+" "+temp33.get(2);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<p;sm++)
					{ 		
						String temp=threef[sm].get(0)+" "+threef[sm].get(1)+" "+threef[sm].get(2);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(threef[sm].get(3));
							//System.out.println("1 2 3 -> 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
			//bw51.write(fivef[i].get((po+2)%5)+" "+fivef[i].get((po+3)%5)+" "+fivef[i].get((po+4)%5)+" -> "+fivef[i].get(po%5)+" "+fivef[i].get((po+1)%5)+"\n");
				if(po==0||po==1||po==2||po==3)
				{	LinkedList<String> temp15=new LinkedList<String>();
					temp15.add(fivef[i].get((po+2)%5));
					temp15.add(fivef[i].get((po+3)%5));	
					temp15.add(fivef[i].get((po+4)%5));
					Collections.sort(temp15);
					c=temp15.get(0)+" "+temp15.get(1)+" "+temp15.get(2);
					LinkedList<String> temp16=new LinkedList<String>();
					temp16.add(fivef[i].get(po%5));
					temp16.add(fivef[i].get((po+1)%5));
					Collections.sort(temp16);
					a=temp16.get(0)+" "+temp16.get(1);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<m;sm++)
					{	
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));
							 	//System.out.print(" 1 2 -> 3 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
				if(po==0||po==1||po==2)
				{
					LinkedList<String> temp25=new LinkedList<String>();
					temp25.add(fivef[i].get((po+1)%5));
					temp25.add(fivef[i].get((po+3)%5));	
					temp25.add(fivef[i].get((po+4)%5));
					Collections.sort(temp25);
					c=temp25.get(0)+" "+temp25.get(1)+" "+temp25.get(2);
					LinkedList<String> temp26=new LinkedList<String>();
					temp26.add(fivef[i].get(po%5));
					temp26.add(fivef[i].get((po+2)%5));
					Collections.sort(temp26);
					a=temp26.get(0)+" "+temp26.get(1);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<m;sm++)
					{	
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));
							 	//System.out.print(" 1 2 -> 3 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
				if(po==0||po==1)
				{	LinkedList<String> temp35=new LinkedList<String>();
					temp35.add(fivef[i].get((po+1)%5));
					temp35.add(fivef[i].get((po+2)%5));	
					temp35.add(fivef[i].get((po+4)%5));
					Collections.sort(temp35);
					c=temp35.get(0)+" "+temp35.get(1)+" "+temp35.get(2);
					LinkedList<String> temp36=new LinkedList<String>();
					temp36.add(fivef[i].get(po%5));
					temp36.add(fivef[i].get((po+3)%5));
					Collections.sort(temp36);
					a=temp36.get(0)+" "+temp36.get(1);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<m;sm++)
					{	
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));
							 	//System.out.print(" 1 2 -> 3 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}
				}
				if(po==0)
				{	LinkedList<String> temp5=new LinkedList<String>();
					temp5.add(fivef[i].get((po+1)%5));
					temp5.add(fivef[i].get((po+2)%5));	
					temp5.add(fivef[i].get((po+3)%5));
					Collections.sort(temp5);
					c=temp5.get(0)+" "+temp5.get(1)+" "+temp5.get(2);
					LinkedList<String> temp6=new LinkedList<String>();
					temp6.add(fivef[i].get(po%5));
					temp6.add(fivef[i].get((po+4)%5));
					Collections.sort(temp6);
					a=temp6.get(0)+" "+temp6.get(1);
					//System.out.println(c+" "+a+" "+numerator);
				
					f=0;
					for(sm=0;sm<m;sm++)
					{	
						String temp=twof[sm].get(0)+" "+twof[sm].get(1);
						if(temp.compareTo(a)==0)
						{	denominator=Double.parseDouble(twof[sm].get(2));
							 	//System.out.print(" 1 2 -> 3 4 5");	
							//System.out.println("found denominator is "+denominator+" "+a);
							f=1;
							break;
						}
					}
					if(f==1)
					{
						sup=numerator/denominator;
						//System.out.println("sup is "+sup);
						if(sup>MCL)
						bw51.write(a+" -> "+c+"		"+sup+" \n");
					}

				}

		//bw51.write(fivef[i].get((po+3)%5)+" "+fivef[i].get((po+4)%5)+" -> "+fivef[i].get(po%5)+" "+fivef[i].get((po+1)%5)+" "+fivef[i].get((po+2)%5)+"\n");	
				
				
				LinkedList<String> temp4=new LinkedList<String>();
				temp4.add(fivef[i].get((po+1)%5));
				temp4.add(fivef[i].get((po+2)%5));
				temp4.add(fivef[i].get((po+3)%5));
				temp4.add(fivef[i].get((po+4)%5));
				Collections.sort(temp4);
				c=temp4.get(0)+" "+temp4.get(1)+" "+temp4.get(2)+" "+temp4.get(3);
				a=fivef[i].get(po%5);
				//System.out.println(c+" "+a+" "+numerator);
				f=0;
				for(sm=0;sm<n;sm++)
				{ 		
					String temp=one[sm].get(0);		
					if(temp.compareTo(a)==0)
					{	denominator=Double.parseDouble(one[sm].get(1));	
						//System.out.println("1 -> 2 3 4 5");
						//System.out.println("found denominator is "+denominator+" "+a);
						f=1;
						break;
					}
				}
				if(f==1)
				{
					sup=numerator/denominator;
					//System.out.println("sup is "+sup);
					if(sup>MCL)
					bw51.write(a+" -> "+c+"		"+sup+" \n");
				}


			//bw51.write(fivef[i].get(po%5)+" -> "+fivef[i].get((po+1)%5)+" "+fivef[i].get((po+2)%5)+" "+fivef[i].get((po+3)%5)+" "+fivef[i].get((po+4)%5)+"\n");
			}

		}
	
		bw51.close();
	//-----------------------------------------------------------------------------------------------------	
		k=0;
		//System.out.println("r is "+r);
		for(i=0;i<r;i++)
		{
			String s4=fivef[i].get(1)+" "+fivef[i].get(2)+" "+fivef[i].get(3)+" "+fivef[i].get(4);
			for(j=i+1;j<r;j++)
			{
				String s5=fivef[j].get(0)+" "+fivef[j].get(1)+" "+fivef[j].get(2)+" "+fivef[j].get(3);
				if(s4.compareTo(s5)==0)
				{
					six[k]=new LinkedList<String>();
					six[k].add(fivef[i].get(0));
					six[k].add(fivef[i].get(1));
					six[k].add(fivef[i].get(2));
					six[k].add(fivef[i].get(3));
					six[k].add(fivef[i].get(4));
					six[k].add(fivef[j].get(4));
					//System.out.println(six[k].get(0)+" "+six[k].get(1)+" "+six[k].get(2) +" "+six[k].get(3)+" "+six[k].get(4)+" "+six[k].get(5));
					k++;
				}
				
			}
		}
		t=k;
		if(k==0)
		{
			System.out.println("no combinations of six is possible");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			System.exit(0);
		}
			System.out.println("tra la la la");
		for(k=0;k<t;k++)
		{
			String sub=six[k].get(0)+" "+six[k].get(1)+" "+six[k].get(2)+" "+six[k].get(3)+" "+six[k].get(4)+" "+six[k].get(5);
			//System.out.println("substring is "+sub);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(six[k].get(0))&&full.contains(six[k].get(1))&&full.contains(six[k].get(2))&&full.contains(six[k].get(3))&&full.contains(six[k].get(4))&&full.contains(six[k].get(5)))
				{
					if(sexa.containsKey(sub))
					{
						value=sexa.get(sub);
						value++;
						sexa.put(sub, value);
						//System.out.println("putting this "+sub+" "+value);
					}
					else
					{sexa.put(sub,1);
					//System.out.println("putting this "+sub+" "+value);
					}
				}				
			}
		}									//sexa[] having counts
		if(sexa.isEmpty())
		{
			System.out.println("none of the six were present in main list");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			System.exit(0);
		}
		
		//System.out.println();
		k=0;
			File file6 = new File("cop/itemsets/six.txt");			//
 
			// if file doesnt exists, then create it						//
			if (!file6.exists()) {									//
				file6.createNewFile();								//
			}											//
 
			FileWriter fw6 = new FileWriter(file6.getAbsoluteFile());						//
			BufferedWriter bw6 = new BufferedWriter(fw6);								//
		Set set6 = sexa.entrySet();
	      Iterator itsexa = set6.iterator();
	      while(itsexa.hasNext()) 
	      {
	         Map.Entry metq=(Map.Entry)itsexa.next();
	         int v=Integer.parseInt(metq.getValue().toString());
	         String v2=metq.getKey().toString();
	         //System.out.println("quad is "+v2+" "+v);
	         if(v>=MSC)
	         {	
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	sixf[k]=new LinkedList<String>();
	        	sixf[k].add(stp.nextToken());
	        	sixf[k].add(stp.nextToken());
	        	sixf[k].add(stp.nextToken());
	        	sixf[k].add(stp.nextToken());
	        	sixf[k].add(stp.nextToken());
	        	sixf[k].add(stp.nextToken());
			sixf[k].add(Integer.toString(v));
	        	//System.out.println(sixf[k].get(0)+" "+sixf[k].get(1)+" "+sixf[k].get(2)+" "+sixf[k].get(3)+" "+sixf[k].get(4)+" "+sixf[k].get(5)+" "+v);
			bw6.write(sixf[k].get(0)+" "+sixf[k].get(1)+" "+sixf[k].get(2)+" "+sixf[k].get(3)+" "+sixf[k].get(4)+" "+sixf[k].get(5)+" "+v);
			bw6.write("\n");
	        	k++;
	         }
	         
	      }
		bw6.close();
		t=k;									//sixf[] having all those with >=MSC
		if(k==0)
		{
			System.out.println("No finals of six were selected");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			System.exit(0);
		}
	File file61 = new File("cop/itemsets/sixrules.txt");			//
			// if file doesnt exists, then create it						//
			if (!file61.exists()) {									//
				file61.createNewFile();								//
			}											//
 
			FileWriter fw61 = new FileWriter(file51.getAbsoluteFile());						//
			BufferedWriter bw61 = new BufferedWriter(fw61);		
		for(i=0;i<t;i++)
		{

			int po;
			for(po=0;po<5;po++){
			bw61.write(sixf[i].get((po+1)%5)+" "+sixf[i].get((po+2)%5)+" "+sixf[i].get((po+3)%5)+" "+sixf[i].get((po+4)%5)+" "+sixf[i].get((po+5)%5)+" -> "+sixf[i].get(po%5)+"\n");
			}

		}
	
		bw61.close();
		
		//---------------------------------------------------------------------------------------------------
		k=0;
		//System.out.println("t is "+t);
		for(i=0;i<t;i++)
		{
			String s4=sixf[i].get(1)+" "+sixf[i].get(2)+" "+sixf[i].get(3)+" "+sixf[i].get(4)+" "+sixf[i].get(5);
			for(j=i+1;j<t;j++)
			{
				String s5=sixf[j].get(0)+" "+sixf[j].get(1)+" "+sixf[j].get(2)+" "+sixf[j].get(3)+" "+sixf[j].get(4);
				if(s4.compareTo(s5)==0)
				{
					seven[k]=new LinkedList<String>();
					seven[k].add(sixf[i].get(0));
					seven[k].add(sixf[i].get(1));
					seven[k].add(sixf[i].get(2));
					seven[k].add(sixf[i].get(3));
					seven[k].add(sixf[i].get(4));
					seven[k].add(sixf[i].get(5));
					seven[k].add(sixf[j].get(5));
					//System.out.println(six[k].get(0)+" "+six[k].get(1)+" "+six[k].get(2) +" "+six[k].get(3)+" "+six[k].get(4)+" "+six[k].get(5));
					k++;
				}
				
			}
		}
		u=k;
		if(k==0)
		{
			System.out.println("no combinations of seven is possible");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
			int flagpls=0;
		for(k=0;k<u;k++)
		{
			String sub=seven[k].get(0)+" "+seven[k].get(1)+" "+seven[k].get(2)+" "+seven[k].get(3)+" "+seven[k].get(4)+" "+seven[k].get(5)+" "+seven[k].get(6);
			//System.out.println("substring is "+sub);
			for(i=0;i<org;i++)
			{
				String full=li.get(i);
				if(full.contains(seven[k].get(0))&&full.contains(seven[k].get(1))&&full.contains(seven[k].get(2))&&full.contains(seven[k].get(3))&&full.contains(seven[k].get(4))&&full.contains(seven[k].get(5))&&full.contains(seven[k].get(6)))
				{
					if(sept.containsKey(sub))
					{
						value=sept.get(sub);
						value++;
						sept.put(sub, value);
						//System.out.println("putting this "+sub+" "+value);
					}
					else
					{sept.put(sub,1);
						flagpls=1;
						//System.out.println("putting this "+sub+" "+value);
					}
				}				
			}
		}	
										//sept[] having counts
		
		if(flagpls==0)
		{
			System.out.println("none were inserted in sept ");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		if(sept.isEmpty())
		{
			System.out.println("no 7 crossed cut off");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		
		k=0;
			File file7 = new File("cop/itemsets/seven.txt");			//
 
			// if file doesnt exists, then create it						//
			if (!file7.exists()) {									//
				file7.createNewFile();								//
			}											//
 
			FileWriter fw7 = new FileWriter(file7.getAbsoluteFile());						//
			BufferedWriter bw7 = new BufferedWriter(fw7);								//
		Set set7 = sept.entrySet();
	      Iterator itsept = set7.iterator();
	      while(itsept.hasNext()) 
	      {
	         Map.Entry metq=(Map.Entry)itsept.next();
	         int v=Integer.parseInt(metq.getValue().toString());
	         String v2=metq.getKey().toString();
	         //System.out.println("quad is "+v2+" "+v);
	         if(v>=MSC)
	         {	
	        	StringTokenizer stp=new StringTokenizer(v2);
	        	sevenf[k]=new LinkedList<String>();
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
	        	sevenf[k].add(stp.nextToken());
			sevenf[k].add(Integer.toString(v));
	        	//System.out.println(sevenf[k].get(0)+" "+sevenf[k].get(1)+" "+sevenf[k].get(2)+" "+sevenf[k].get(3)+" "+sevenf[k].get(4)+" "+sevenf[k].get(5)+" "+sevenf[k].get(6)+" "+v);
			bw7.write(sevenf[k].get(0)+" "+sevenf[k].get(1)+" "+sevenf[k].get(2)+" "+sevenf[k].get(3)+" "+sevenf[k].get(4)+" "+sevenf[k].get(5)+" "+sevenf[k].get(6)+" "+v);
			bw7.write("\n");
	        	k++;
	         }
	         
	      }
		u=k;	bw7.close();						//sevenf[] having all those with >=MSC
		if(k==0)
		{
			System.out.println("No 7 were finalised");
			long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);
			bw.close();
			System.exit(0);
		}
		
		/*
		for(i=0;i<n;i++)
		{
			Iterator<String> it=li[i].iterator();
			while(it.hasNext())
			{
				System.out.print(" "+it.next());
			}
			System.out.println("");
		}
		
		int j,k,l;
		
		for(i=0;i<n;i++){
		for(j=0;j<7;j++)
		{
		for(k=j+1;k<7;k++)
		System.out.println(li[i].get(j)+" "+li[i].get(k));	
		}}
		
		for(i=0;i<n;i++)
		for(j=0;j<7;j++)
			for(k=j+1;k<7;k++)
				for(l=k+1;l<7;l++)
		System.out.println(li[i].get(j)+" "+li[i].get(k)+" "+li[i].get(l));
		
		*/
		
		
		/*double a[]=new double[n];
		
		System.out.println("enter numbers");
		for(i=0;i<n;i++)
			a[i]=Double.parseDouble(kk.readLine());
		
		dd.sort(li,a,n);
		for(i=0;i<n;i++)
			System.out.print(a[i] + " ");
		*/
		long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds "+timeInSeconds);

	}

}
