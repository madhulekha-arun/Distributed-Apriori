package com.bits.apriori_rules;

/*****************************************************************************/
/***Rulestryall.java 
                                                 
bin/hadoop dfs -rmr /user/hduser/rules/*                                                                  

sudo javac -classpath /usr/local/hadoop/hadoop-core-1.1.2.jar -d / /home/madhulekha/Downloads/Rulestryall.java

sudo jar cf rtryall.jar /sat4all

bin/hadoop jar rtryall.jar sat4all.Rulestryall /user/hduser/out/output2/part-r-00000 /user/hduser/out/output3/part-r-00000 /user/hduser/out/output4/part-r-00000 /user/hduser/out/output5/part-r-00000 /user/hduser/out/output6/part-r-00000 /user/hduser/out/output7 /user/hduser/rules/rules2 /user/hduser/rules/rules3 /user/hduser/rules/rules4 /user/hduser/rules/rules5 /user/hduser/rules/rules6 /user/hduser/rules/rules7
  

/*****************************************************************************/

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Rulestryall 
{
 	public static class Map2 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh;


		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh.add(content.trim());
			}
			reader.close();
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s2[]=line.split(" ");
			int count=2;
			li.add(s2[0]);
			li.add(s2[1]);
			Collections.sort(li);	
			double num,den=1;
			num=Double.parseDouble(s2[2]);
			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");

				String sub;
				for(int x=0;x<hh.size();x++) {
					sub=hh.get(x);
					if(sub.contains(a))
						{
								String[] sub2=sub.split(" ");
								den=Double.parseDouble(sub2[1]);
								break;
						}
					}

				double v=num/den;
				String add=Double.toString(v);

				if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
			}
		}
	} 

	
	public static class Map3 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh1;
		public List<String> hh2;


		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh1 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh1.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out2");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh2 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh2.add(content.trim());
			}
			reader.close();
			
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s2[]=line.split(" ");
			int count=3;
			li.add(s2[0]);
			li.add(s2[1]);
			li.add(s2[2]);
			Collections.sort(li);
			double num,den=1;
			num=Double.parseDouble(s2[3]);

			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");
				String sub;
				for(int x=0;x<hh2.size();x++) {
					sub=hh2.get(x);
					if(sub.contains(a))
						{
							    String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[2]);
								break;
						}
					}
				double v=num/den;
				String add=Double.toString(v);

				if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
			}
	
			for(int i=0;i<count-1;i++)
			{
				int j;
				for(j=i+1;j<count;j++)
				{
					String a="";
					String c;
					c=li.get(i)+" "+li.get(j);
					int k;
					for(k=0;k<count;k++)
						if((k!=i)&&(k!=j)) a=a.concat(li.get(k)).concat(" ");
					String sub;
					for(int x=0;x<hh1.size();x++) {
						sub=hh1.get(x);
						if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[1]);
									break;
							}
						}
					double v=num/den;
					String add=Double.toString(v);

					if(v>=0.75)
						{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;	
							consequent.set(c);
							context.write(antecedent, consequent);
						}
				}
			}
		}
	} 
 	
	public static class Map4 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh1;
		public List<String> hh2;
		public List<String> hh3;


		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh1 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh1.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out2");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh2 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh2.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out3");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh3 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh3.add(content.trim());
			}
			reader.close();
			
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s[]=line.split(" ");
			int count=4;
			li.add(s[0]);
			li.add(s[1]);
			li.add(s[2]);
			li.add(s[3]);
			double num,den=1;
			num=Double.parseDouble(s[4]);
			Collections.sort(li);	

			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");

				String sub;
				for(int x=0;x<hh3.size();x++) {
					sub=hh3.get(x);
					if(sub.contains(a))
						{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[3]);
								break;
						}
					}
				double v=num/den;
				String add=Double.toString(v);

				if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
			}

			for(int i=0;i<count-1;i++)
			{
				int j;
				for(j=i+1;j<count;j++)
				{
					String a="";
					String c;
					c=li.get(i)+" "+li.get(j);
					int k;
					for(k=0;k<count;k++)
						if((k!=i)&&(k!=j)) a=a.concat(li.get(k)).concat(" ");

					String sub;
					for(int x=0;x<hh2.size();x++) {
						sub=hh2.get(x);
						if(sub.contains(a))
							{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[2]);
									break;
							}
						}
					
					double v=num/den;
					String add=Double.toString(v);
					if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
				}
			}

			for(int i=0;i<count-2;i++)
			{
				int j;
				for(j=i+1;j<count-1;j++)
				{	int l;
					for(l=j+1;l<count;l++)
					{
						String a="";
						String c;
						c=li.get(i)+" "+li.get(j)+" "+li.get(l);
						int k;
						for(k=0;k<count;k++)
							if((k!=i)&&(k!=j)&&(k!=l)) a=a.concat(li.get(k)).concat(" ");
						String sub;
						for(int x=0;x<hh1.size();x++) {
							sub=hh1.get(x);
							if(sub.contains(a))
							{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[1]);
									break;
							}
						}
						double v=num/den;
						String add=Double.toString(v);
						if(v>=0.75)
						{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;				
						consequent.set(c);
						context.write(antecedent, consequent);
						}
					}	
				}
			}
		}
	} 

	public static class Map5 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh1;
		public List<String> hh2;
		public List<String> hh3;
		public List<String> hh4;

		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh1 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh1.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out2");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh2 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh2.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out3");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh3 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh3.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out4");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh4 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh4.add(content.trim());
			}
			reader.close();
			
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s[]=line.split(" ");
			int count=5;
			li.add(s[0]);
			li.add(s[1]);
			li.add(s[2]);
			li.add(s[3]);
			li.add(s[4]);
			double num,den=1;
			num=Double.parseDouble(s[5]);
			Collections.sort(li);	

			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");
				String sub;
				for(int x=0;x<hh4.size();x++) {
					sub=hh4.get(x);
					if(sub.contains(a))
						{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[4]);
								break;
						}
					}

				double v=num/den;
				String add=Double.toString(v);
				if(v>=0.75)
				{
					antecedent.set(a+"-> ");
					c=c+" 				:	"+add;	
					consequent.set(c);
					context.write(antecedent, consequent);
				}
			}//----------------------------------------------------------------------------------------
			for(int i=0;i<count-1;i++)
			{
				int j;
				for(j=i+1;j<count;j++)
				{
					String a="";
					String c;
					c=li.get(i)+" "+li.get(j);
					int k;
					for(k=0;k<count;k++)
						if((k!=i)&&(k!=j)) a=a.concat(li.get(k)).concat(" ");

					String sub;
					for(int x=0;x<hh3.size();x++) {
						sub=hh3.get(x);
						if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[3]);
									break;
							}
						}

					double v=num/den;
					String add=Double.toString(v);
					if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
				}
			}//---------------------------------------------------------------------------------
			for(int i=0;i<count-2;i++)
			{
				int j;
				for(j=i+1;j<count-1;j++)
				{	int l;
					for(l=j+1;l<count;l++)
					{
						String a="";
						String c;
						c=li.get(i)+" "+li.get(j)+" "+li.get(l);
						int k;
						for(k=0;k<count;k++)
							if((k!=i)&&(k!=j)&&(k!=l)) a=a.concat(li.get(k)).concat(" ");
						String sub;
						for(int x=0;x<hh2.size();x++) {
							sub=hh2.get(x);
							if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[2]);
									break;
							}
						}
						double v=num/den;
						String add=Double.toString(v);
						if(v>=0.75)
						{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;				
						consequent.set(c);
						context.write(antecedent, consequent);
						}
					}	
				}
			}//----------------------------------------------------------------------------------

			for(int i=0;i<count-3;i++)
			{
				int j;
				for(j=i+1;j<count-2;j++)
				{	int l;
					for(l=j+1;l<count-1;l++)
					{
						int w;
						for(w=l+1;w<count;w++)
						{
							String a="";
							String c;
							c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(w);
							int k;
							for(k=0;k<count;k++)
								if((k!=i)&&(k!=j)&&(k!=l)&&(k!=w)) a=a.concat(li.get(k)).concat(" ");
						
							String sub;
							for(int x=0;x<hh1.size();x++) {
								sub=hh1.get(x);
								if(sub.contains(a))
								{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[1]);
										break;
								}
							}
							double v=num/den;
							String add=Double.toString(v);
							if(v>=0.75)
							{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;				
							consequent.set(c);
							context.write(antecedent, consequent);
							}
						}
					}	
				}
			}
		}
	}

	public static class Map6 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh1;
		public List<String> hh2;
		public List<String> hh3;
		public List<String> hh4;
		public List<String> hh5;

		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh1 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh1.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out2");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh2 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh2.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out3");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh3 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh3.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out4");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh4 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh4.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out5");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh5 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh5.add(content.trim());
			}
			reader.close();
			
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s[]=line.split(" ");
			int count=6;
			li.add(s[0]);
			li.add(s[1]);
			li.add(s[2]);
			li.add(s[3]);
			li.add(s[4]);
			li.add(s[5]);
			double num,den=1;
			num=Double.parseDouble(s[6]);
			Collections.sort(li);	

			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");
				
				String sub;
				for(int x=0;x<hh5.size();x++) {
					sub=hh5.get(x);
					if(sub.contains(a))
						{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[5]);
								break;
						}
					}

				double v=num/den;
				String add=Double.toString(v);
				if(v>=0.75)
				{
					antecedent.set(a+"-> ");
					c=c+" 				:	"+add;	
					consequent.set(c);
					context.write(antecedent, consequent);
				}
			}//----------------------------------------------------------------------------------------
			for(int i=0;i<count-1;i++)
			{
				int j;
				for(j=i+1;j<count;j++)
				{
					String a="";
					String c;
					c=li.get(i)+" "+li.get(j);
					int k;
					for(k=0;k<count;k++)
						if((k!=i)&&(k!=j)) a=a.concat(li.get(k)).concat(" ");

					String sub;
					for(int x=0;x<hh4.size();x++) {
						sub=hh4.get(x);
						if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[4]);
									break;
							}
						}
				
					double v=num/den;
					String add=Double.toString(v);
					if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
				}
			}//---------------------------------------------------------------------------------
			for(int i=0;i<count-2;i++)
			{
				int j;
				for(j=i+1;j<count-1;j++)
				{	int l;
					for(l=j+1;l<count;l++)
					{
						String a="";
						String c;
						c=li.get(i)+" "+li.get(j)+" "+li.get(l);
						int k;
						for(k=0;k<count;k++)
							if((k!=i)&&(k!=j)&&(k!=l)) a=a.concat(li.get(k)).concat(" ");
						
						String sub;
						for(int x=0;x<hh3.size();x++) {
							sub=hh3.get(x);
							if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[3]);
									break;
							}
						}
						
						double v=num/den;
						String add=Double.toString(v);
						if(v>=0.75)
						{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;				
						consequent.set(c);
						context.write(antecedent, consequent);
						}
					}	
				}
			}//----------------------------------------------------------------------------------

			for(int i=0;i<count-3;i++)
			{
				int j;
				for(j=i+1;j<count-2;j++)
				{	int l;
					for(l=j+1;l<count-1;l++)
					{
						int w;
						for(w=l+1;w<count;w++)
						{
							String a="";
							String c;
							c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(w);
							int k;
							for(k=0;k<count;k++)
								if((k!=i)&&(k!=j)&&(k!=l)&&(k!=w)) a=a.concat(li.get(k)).concat(" ");
						
							String sub;
							for(int x=0;x<hh2.size();x++) {
								sub=hh2.get(x);
								if(sub.contains(a))
								{
										String sub2[]=sub.split(" ");
										den=Double.parseDouble(sub2[2]);
										break;
								}
							}
							
							double v=num/den;
							String add=Double.toString(v);
							if(v>=0.75)
							{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;				
							consequent.set(c);
							context.write(antecedent, consequent);
							}
						}
					}	
				}
			}

			for(int i=0;i<count-4;i++)
			{
				int j;
				for(j=i+1;j<count-3;j++)
				{	int l;
					for(l=j+1;l<count-2;l++)
					{
						int x;
						for(x=l+1;x<count-1;x++)
						{
						int w;
						for(w=x+1;w<count;w++)
						{
							String a="";
							String c;
							c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(x)+" "+li.get(w);
							int k;
							for(k=0;k<count;k++)
								if((k!=i)&&(k!=j)&&(k!=l)&&(k!=x)&&(k!=w)) a=a.concat(li.get(k)).concat(" ");
							
							String sub;
							for(int x1=0;x1<hh1.size();x1++) {
								sub=hh1.get(x1);
								if(sub.contains(a))
								{
										String sub2[]=sub.split(" ");
										den=Double.parseDouble(sub2[1]);
										break;
								}
							}
							
							double v=num/den;
							String add=Double.toString(v);
							if(v>=0.75)
							{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;				
							consequent.set(c);
							context.write(antecedent, consequent);
							}
						}}
					}	
				}
			}
		}
	}

	public static class Map7 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
		
		public List<String> hh1;
		public List<String> hh2;
		public List<String> hh3;
		public List<String> hh4;
		public List<String> hh5;
		public List<String> hh6;

		@Override
		protected void setup(
			Context context)
			throws IOException, InterruptedException {

			File cacheFile = new File("out1");
			BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
			String content = null;
			hh1 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh1.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out2");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh2 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh2.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out3");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh3 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh3.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out4");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh4 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh4.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out5");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh5 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh5.add(content.trim());
			}
			reader.close();
			
			cacheFile = new File("out6");
			reader = new BufferedReader(new FileReader(cacheFile));
			content = null;
			hh6 = new ArrayList<String>();

			while ((content = reader.readLine()) != null) {
				hh6.add(content.trim());
			}
			reader.close();
			
		}
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s[]=line.split(" ");
			int count=7;
			li.add(s[0]);
			li.add(s[1]);
			li.add(s[2]);
			li.add(s[3]);
			li.add(s[4]);
			li.add(s[5]);
			li.add(s[6]);
			double num,den=1;
			num=Double.parseDouble(s[7]);
			Collections.sort(li);	

			for(int i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				int j;
				for(j=0;j<count;j++)
				if(i!=j) a=a.concat(li.get(j)).concat(" ");
				
				String sub;
				for(int x=0;x<hh6.size();x++) {
					sub=hh6.get(x);
					if(sub.contains(a))
						{
								String sub2[]=sub.split(" ");
								den=Double.parseDouble(sub2[6]);
								break;
						}
					}
				
				double v=num/den;
				String add=Double.toString(v);
				if(v>=0.75)
				{
					antecedent.set(a+"-> ");
					c=c+" 				:	"+add;	
					consequent.set(c);
					context.write(antecedent, consequent);
				}
			}//----------------------------------------------------------------------------------------
			for(int i=0;i<count-1;i++)
			{
				int j;
				for(j=i+1;j<count;j++)
				{
					String a="";
					String c;
					c=li.get(i)+" "+li.get(j);
					int k;
					for(k=0;k<count;k++)
						if((k!=i)&&(k!=j)) a=a.concat(li.get(k)).concat(" ");

					String sub;
					for(int x=0;x<hh5.size();x++) {
						sub=hh5.get(x);
						if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[5]);
									break;
							}
						}
					
					double v=num/den;
					String add=Double.toString(v);
					if(v>=0.75)
					{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;	
						consequent.set(c);
						context.write(antecedent, consequent);
					}
				}
			}//---------------------------------------------------------------------------------
			for(int i=0;i<count-2;i++)
			{
				int j;
				for(j=i+1;j<count-1;j++)
				{	int l;
					for(l=j+1;l<count;l++)
					{
						String a="";
						String c;
						c=li.get(i)+" "+li.get(j)+" "+li.get(l);
						int k;
						for(k=0;k<count;k++)
							if((k!=i)&&(k!=j)&&(k!=l)) a=a.concat(li.get(k)).concat(" ");
						
						String sub;
						for(int x=0;x<hh4.size();x++) {
							sub=hh4.get(x);
							if(sub.contains(a))
							{
									String sub2[]=sub.split(" ");
									den=Double.parseDouble(sub2[4]);
									break;
							}
						}
					
						double v=num/den;
						String add=Double.toString(v);
						if(v>=0.75)
						{
						antecedent.set(a+"-> ");
						c=c+" 				:	"+add;				
						consequent.set(c);
						context.write(antecedent, consequent);
						}
					}	
				}
			}//----------------------------------------------------------------------------------

			for(int i=0;i<count-3;i++)
			{
				int j;
				for(j=i+1;j<count-2;j++)
				{	int l;
					for(l=j+1;l<count-1;l++)
					{
						int w;
						for(w=l+1;w<count;w++)
						{
							String a="";
							String c;
							c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(w);
							int k;
							for(k=0;k<count;k++)
								if((k!=i)&&(k!=j)&&(k!=l)&&(k!=w)) a=a.concat(li.get(k)).concat(" ");
						
							String sub;
							for(int x=0;x<hh3.size();x++) {
								sub=hh3.get(x);
								if(sub.contains(a))
								{
										String sub2[]=sub.split(" ");
										den=Double.parseDouble(sub2[3]);
										break;
								}
							}
							
							double v=num/den;
							String add=Double.toString(v);
							if(v>=0.75)
							{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;				
							consequent.set(c);
							context.write(antecedent, consequent);
							}
						}
					}	
				}
			}

			for(int i=0;i<count-4;i++)
			{
				int j;
				for(j=i+1;j<count-3;j++)
				{	int l;
					for(l=j+1;l<count-2;l++)
					{
						int x;
						for(x=l+1;x<count-1;x++)
						{
						int w;
						for(w=x+1;w<count;w++)
						{
							String a="";
							String c;
							c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(x)+" "+li.get(w);
							int k;
							for(k=0;k<count;k++)
								if((k!=i)&&(k!=j)&&(k!=l)&&(k!=x)&&(k!=w)) a=a.concat(li.get(k)).concat(" ");
						
							String sub;
							for(int x1=0;x1<hh2.size();x1++) {
								sub=hh2.get(x1);
								if(sub.contains(a))
								{
										String sub2[]=sub.split(" ");
										den=Double.parseDouble(sub2[2]);
										break;
								}
							}
							
							double v=num/den;
							String add=Double.toString(v);
							if(v>=0.75)
							{
							antecedent.set(a+"-> ");
							c=c+" 				:	"+add;				
							consequent.set(c);
							context.write(antecedent, consequent);
							}
						}}
					}	
				}
			}


			for(int i=0;i<count-5;i++)
			{
				int j;
				for(j=i+1;j<count-4;j++)
				{	int l;
					for(l=j+1;l<count-3;l++)
					{
						int x;
						for(x=l+1;x<count-2;x++)
						{
						int w;
						for(w=x+1;w<count-1;w++)
						{

							int y;
							for(y=w+1;y<count;y++)
							{
								String a="";
								String c;
								c=li.get(i)+" "+li.get(j)+" "+li.get(l)+" "+li.get(x)+" "+li.get(w)+" "+li.get(y);
								int k;
								for(k=0;k<count;k++)
									if((k!=i)&&(k!=j)&&(k!=l)&&(k!=x)&&(k!=w)&&(k!=y)) a=a.concat(li.get(k)).concat(" ");
						
								String sub;
								for(int x1=0;x1<hh1.size();x1++) {
									sub=hh1.get(x1);
									if(sub.contains(a))
									{
											String sub2[]=sub.split(" ");
											den=Double.parseDouble(sub2[1]);
											break;
									}
								}
								
								double v=num/den;
								String add=Double.toString(v);
								if(v>=0.75)
								{
								antecedent.set(a+"-> ");
								c=c+" 				:	"+add;				
								consequent.set(c);
								context.write(antecedent, consequent);
								}
							}
						}}
					}	
				}
			}
		}
	}
 	
	public static class Reduce extends Reducer<Text, Text, Text, Text> 
	{
		public void reduce(Text key, Text values, Context context) throws IOException, InterruptedException 
		{
			/*String s=key.toString()+" "+values.toString();
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/sample/sample1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
			StringTokenizer itlo=new StringTo*/
					
			context.write(key, values);
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		//long start = System.nanoTime();
		int n=6,i;
		Configuration conf = new Configuration();
		Job job[]= new Job[n];
		
		URI samp1 = URI.create("cop/output/1_0/part-r-00000#out1");
		URI samp2 = URI.create("cop/output/1_1/part-r-00000#out2");
		URI samp3 = URI.create("cop/output/1_2/part-r-00000#out3");
		URI samp4 = URI.create("cop/output/1_3/part-r-00000#out4");
		URI samp5 = URI.create("cop/output/1_4/part-r-00000#out5");
		//URI samp6 = URI.create("cop/output/1_5/part-r-00000#out6");
		//URI samp7 = URI.create("cop/output/1_6/part-r-00000#out7");
		
		i=0;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map2.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[4]));
		job[i].addCacheFile(samp1);
		job[i].waitForCompletion(true);

		
		i=1;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map3.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[1]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[5]));
		job[i].addCacheFile(samp1);
		job[i].addCacheFile(samp2);
		job[i].waitForCompletion(true);

		i=2;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map4.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[2]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[8]));
		job[i].addCacheFile(samp1);
		job[i].addCacheFile(samp2);
		job[i].addCacheFile(samp3);
		job[i].waitForCompletion(true);
		
		i=3;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map5.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[3]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[9]));
		job[i].addCacheFile(samp1);
		job[i].addCacheFile(samp2);
		job[i].addCacheFile(samp3);
		job[i].addCacheFile(samp4);
		job[i].waitForCompletion(true);
/*
		i=4;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map6.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[4]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[10]));
		job[i].addCacheFile(samp1);
		job[i].addCacheFile(samp2);
		job[i].addCacheFile(samp3);
		job[i].addCacheFile(samp4);
		job[i].addCacheFile(samp5);
		job[i].waitForCompletion(true);

		i=5;
		job[i]=new Job(conf,"Rulestryall");
		job[i].setJarByClass(Rulestryall.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map7.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[5]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[11]));
		job[i].addCacheFile(samp1);
		job[i].addCacheFile(samp2);
		job[i].addCacheFile(samp3);
		job[i].addCacheFile(samp4);
		job[i].addCacheFile(samp5);
		//job[i].addCacheFile(samp6);
		job[i].waitForCompletion(true);
*/		

	}
 	
}
