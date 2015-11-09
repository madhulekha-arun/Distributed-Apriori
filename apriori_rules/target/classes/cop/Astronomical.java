package ast;
import java.io.IOException;
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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Astronomical 
{
 	
	public static class Map0 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				word.set(s);
				context.write(word, one);
			}	
			//Collections.sort(li);			
		}
	} 

	public static class Map1 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private final static IntWritable neg = new IntWritable(0);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
			}	

			Collections.sort(li);
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}
			
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile1 = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile1)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2))
				{	
					word.set(sub);
					context.write(word, one);	
				}else
				{
					word.set(sub);
					context.write(word, neg);
				}
			}			
		}
	} 
	
	public static class Map2 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
			}	
			Collections.sort(li);
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}
			
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample2.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				String s3=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2)&&org.contains(s3))
				{	
					word.set(sub);
					context.write(word, one);	
				}
			}			
		}
	}
	
	public static class Map3 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
			}	
			Collections.sort(li);
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}
			
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample3.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				String s3=itlo.nextToken();
				String s4=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2)&&org.contains(s3)&&org.contains(s4))
				{	
					word.set(sub);
					context.write(word, one);	
				}
			}	
						
		}
	}
	
	public static class Map4 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
			}	
			Collections.sort(li);
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample4.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				String s3=itlo.nextToken();
				String s4=itlo.nextToken();
				String s5=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2)&&org.contains(s3)&&org.contains(s4)&&org.contains(s5))
				{	
					word.set(sub);
					context.write(word, one);	
				}
			}				
		}
	}

	public static class Map5 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
			}
			Collections.sort(li);	
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}	
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample5.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				String s3=itlo.nextToken();
				String s4=itlo.nextToken();
				String s5=itlo.nextToken();
				String s6=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2)&&org.contains(s3)&&org.contains(s4)&&org.contains(s5)&&org.contains(s6))
				{	
					word.set(sub);
					context.write(word, one);	
				}
			}	
						
		}
	}

	public static class Map6 extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				word.set(s);
				context.write(word, one);
			}
			
			Collections.sort(li);
			String org="";
			Iterator it=li.iterator();
			while(it.hasNext())
			{
				String s6=it.next().toString();
				org=org.concat(s6).concat(" ");
			}
			
			FileSystem fs = FileSystem.get(context.getConfiguration());
			Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/astrosample/sample6.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			String sub;
			while ((sub = br.readLine()) != null) {
				StringTokenizer itlo=new StringTokenizer(sub);
				String s1=itlo.nextToken();
				String s2=itlo.nextToken();
				String s3=itlo.nextToken();
				String s4=itlo.nextToken();
				String s5=itlo.nextToken();
				String s6=itlo.nextToken();
				String s7=itlo.nextToken();
				if(org.contains(s1)&&org.contains(s2)&&org.contains(s3)&&org.contains(s4)&&org.contains(s5)&&org.contains(s6)&&org.contains(s7))
				{	
					word.set(sub);
					context.write(word, one);	
				}
			}	
			
		}
	} 
 	
	public static class Reduce extends Reducer<Text, IntWritable, Text, Text> 
	{
		private Text word1 = new Text();
		private Text word2 = new Text();
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
		{
			int sum = 0;
			for (IntWritable val : values) 
			{
				sum += val.get();
			}
			if(sum>30)
			{	
				String temp=Integer.toString(sum);
				String t=key.toString();
				t=t+" "+temp+"";
				word1.set(t);
				word2.set("");
				context.write(word1,word2);
			}
		}
	}
 	
	public static void main(String[] args) throws Exception 
	{
		long start = System.nanoTime();
		int n=7,i;
		Configuration conf = new Configuration();
		Job job[]= new Job[n];
		
		i=0;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map0.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[1]));
		job[i].waitForCompletion(true);
		
 
		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[1]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
			int nn=100;	
			int m=200;
			//p=10000,q=10000,r=100000,t=100000;
			LinkedList<String> li[]=new LinkedList[nn];
			LinkedList<String> two[]=new LinkedList[m];
			/*LinkedList<String> three[]=new LinkedList[p];
			LinkedList<String> four[]=new LinkedList[q];
			LinkedList<String> five[]=new LinkedList[r];
			LinkedList<String> six[]=new LinkedList[t];*/
		
			int ii=0,j,k;
	 
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				li[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				li[ii].add(st.nextToken());
				ii++;
			}
			nn=ii;
		
		k=0;
		for(ii=0;ii<nn;ii++)
		{
			for(j=ii+1;j<nn;j++)
			{
				two[k] = new LinkedList<String>();
				//if(li[ii].get(0)==""||||li[j].get(0))
				two[k].add(li[ii].get(0));
				two[k].add(li[j].get(0));
				k++;
			}			
		}
		m=k;
		String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample1.txt";
		Path outFile = new Path(s2);
		FileSystem fsw = FileSystem.get(conf);
		FSDataOutputStream out = fsw.create(outFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		String ss;
			for(k=0;k<m;k++)
			{ss=two[k].get(0)+" "+two[k].get(1);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
		i=1;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map1.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[2]));
		job[i].waitForCompletion(true);
		
 
		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[2]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
				
			int m=200,p=10000;
			//q=10000,r=100000,t=100000;
			//LinkedList<String> li[]=new LinkedList[nn];
			LinkedList<String> two[]=new LinkedList[m];
			LinkedList<String> three[]=new LinkedList[p];
			/*LinkedList<String> four[]=new LinkedList[q];
			LinkedList<String> five[]=new LinkedList[r];
			LinkedList<String> six[]=new LinkedList[t];*/
		
			int ii=0,j,k;
	 
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				two[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				two[ii].add(st.nextToken());
				two[ii].add(st.nextToken());
				ii++;
			}
			m=ii;
		
		k=0;
		j=0;
		for(k=0;k<m;k++)
		{
			for(i=k+1;i<m;i++)
			{
				String s3=two[k].get(1);
				if(s3.compareTo(two[i].get(0))==0)
				{
					three[j]=new LinkedList<String>();
					three[j].add(two[k].get(0));
					three[j].add(two[k].get(1));
					three[j].add(two[i].get(1));	
					j++;
				}
			}
		}
		p=j;

		String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample2.txt";
		Path outFile = new Path(s2);
		FileSystem fsw = FileSystem.get(conf);
		FSDataOutputStream out = fsw.create(outFile);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		String ss;
			for(k=0;k<p;k++)
			{ss=three[k].get(0)+" "+three[k].get(1)+" "+three[k].get(2);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		i=2;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map2.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[3]));
		job[i].waitForCompletion(true);

		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[3]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
				
			//int m=200;
			int p=100000,q=100000;
			//r=100000,t=100000;
			//LinkedList<String> li[]=new LinkedList[nn];
			//LinkedList<String> two[]=new LinkedList[m];
			LinkedList<String> three[]=new LinkedList[p];
			LinkedList<String> four[]=new LinkedList[q];
			/*LinkedList<String> five[]=new LinkedList[r];
			LinkedList<String> six[]=new LinkedList[t];*/
		
			int ii=0,j,k;
	 		int flag=0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				flag=1;
				three[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				three[ii].add(st.nextToken());
				three[ii].add(st.nextToken());
				three[ii].add(st.nextToken());
				ii++;
			}
			p=ii;
			String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample3.txt";
			Path outFile = new Path(s2);
			FileSystem fsw = FileSystem.get(conf);
			FSDataOutputStream out = fsw.create(outFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			if(flag==0)
			{
				String take="Nothing to write here as there is no itemsets of smaller size available";
				long time = System.nanoTime() - start;
				double timeInSeconds = time/1e9;
				System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
				bw.write(take);
				bw.newLine();
				bw.close();
				System.exit(0);
			}
			
		int f=0;
		k=0;
		for(i=0;i<p;i++)
		{
			String s4=three[i].get(1)+" "+three[i].get(2);
			for(j=i+1;j<p;j++)
			{
				String s5=three[j].get(0)+" "+three[j].get(1);
				if(s4.compareTo(s5)==0)
				{	f=1;
					four[k]=new LinkedList<String>();
					four[k].add(three[i].get(0));
					four[k].add(three[i].get(1));
					four[k].add(three[i].get(2));
					four[k].add(three[j].get(2));
					//System.out.println(three[i].get(0)+" "+three[i].get(1)+" "+three[i].get(2) +" "+three[j].get(2));
					k++;
				}			
			}
		}
		q=k;
		if(f==0)
		{bw.write("nothing here");long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
			bw.newLine();
			bw.close();
		System.exit(0);
		}
		String ss;
			for(k=0;k<q;k++)
			{ss=four[k].get(0)+" "+four[k].get(1)+" "+four[k].get(2)+" "+four[k].get(3);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		



		i=3;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map3.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[4]));
		job[i].waitForCompletion(true);
		
		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[4]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
				
			//int m=200;int p=10000;
			int q=100000,r=100000; //t=100000;
			//LinkedList<String> li[]=new LinkedList[nn];
			//LinkedList<String> two[]=new LinkedList[m];
			//LinkedList<String> three[]=new LinkedList[p];
			LinkedList<String> four[]=new LinkedList[q];
			LinkedList<String> five[]=new LinkedList[r];
			//LinkedList<String> six[]=new LinkedList[t];
		
			int ii=0,j,k;
	 		int flag=0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				flag=1;
				four[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				four[ii].add(st.nextToken());
				four[ii].add(st.nextToken());
				four[ii].add(st.nextToken());
				four[ii].add(st.nextToken());
				ii++;
			}
			q=ii;
			String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample4.txt";
			Path outFile = new Path(s2);
			FileSystem fsw = FileSystem.get(conf);
			FSDataOutputStream out = fsw.create(outFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			if(flag==0)
			{
				String take="Nothing to write here as there is no itemsets of smaller size available";
				long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
				bw.write(take);
				bw.close();
				System.exit(0);
			}
		k=0;int f=0;
		for(i=0;i<q;i++)
		{
			String s4=four[i].get(1)+" "+four[i].get(2)+" "+four[i].get(3);
			for(j=i+1;j<q;j++)
			{
				String s5=four[j].get(0)+" "+four[j].get(1)+" "+four[j].get(2);
				if(s4.compareTo(s5)==0)
				{	f=1;
					five[k]=new LinkedList<String>();
					five[k].add(four[i].get(0));
					five[k].add(four[i].get(1));
					five[k].add(four[i].get(2));
					five[k].add(four[i].get(3));
					five[k].add(four[j].get(3));
					k++;
					//System.out.println("k is "+k);
				}
				
			}
		}
		r=k;
		if(f==0)
		{bw.write("nothing here");long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
			bw.newLine();
			bw.close();
		System.exit(0);
		}
		String ss;
			for(k=0;k<r;k++)
			{ss=five[k].get(0)+" "+five[k].get(1)+" "+five[k].get(2) +" "+five[k].get(3)+" "+five[k].get(4);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
		i=4;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map4.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[5]));
		job[i].waitForCompletion(true);

		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[5]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
				
			//int m=200;
			//int p=10000,q=10000;
			int r=100000,t=10000;
			//LinkedList<String> li[]=new LinkedList[nn];
			//LinkedList<String> two[]=new LinkedList[m];
			//LinkedList<String> three[]=new LinkedList[p];
			//LinkedList<String> four[]=new LinkedList[q];
			LinkedList<String> five[]=new LinkedList[r];
			LinkedList<String> six[]=new LinkedList[t];
		
			int ii=0,j,k;
	 		int flag=0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				flag=1;
				five[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				five[ii].add(st.nextToken());
				five[ii].add(st.nextToken());
				five[ii].add(st.nextToken());
				five[ii].add(st.nextToken());
				five[ii].add(st.nextToken());
				ii++;
			}

			String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample5.txt";
			Path outFile = new Path(s2);
			FileSystem fsw = FileSystem.get(conf);
			FSDataOutputStream out = fsw.create(outFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			if(flag==0)
			{
				String take="Nothing to write here as there is no itemsets of smaller size available";
				long time = System.nanoTime() - start;
				double timeInSeconds = time/1e9;
				System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
				bw.write(take);
				bw.close();
				System.exit(0);
			}
			r=ii;
		
		k=0;int f=0;
		//System.out.println("r is "+r);
		for(i=0;i<r;i++)
		{
			String s4=five[i].get(1)+" "+five[i].get(2)+" "+five[i].get(3)+" "+five[i].get(4);
			for(j=i+1;j<r;j++)
			{
				String s5=five[j].get(0)+" "+five[j].get(1)+" "+five[j].get(2)+" "+five[j].get(3);
				if(s4.compareTo(s5)==0)
				{	f=1;
					six[k]=new LinkedList<String>();
					six[k].add(five[i].get(0));
					six[k].add(five[i].get(1));
					six[k].add(five[i].get(2));
					six[k].add(five[i].get(3));
					six[k].add(five[i].get(4));
					six[k].add(five[j].get(4));
					k++;
				}
				
			}
		}
		t=k;
		if(f==0)
		{bw.write("nothing could be made here");
		long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
			bw.newLine();
			bw.close();
		System.exit(0);
		}
		String ss;
			for(k=0;k<t;k++)
			{ss=six[k].get(0)+" "+six[k].get(1)+" "+six[k].get(2) +" "+six[k].get(3)+" "+six[k].get(4)+" "+six[k].get(5);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		i=5;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map5.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[6]));
		job[i].waitForCompletion(true);

		try {	FileSystem fs=FileSystem.get(conf);
			String s1=args[6]+"/part-r-00000";
			Path InFile = new Path(s1);
			//Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/part-r-00000");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
				
			/*int m=200;
			int p=10000,q=10000;
			//r=100000*/
			int t=100000,u=100000;
			/*LinkedList<String> li[]=new LinkedList[nn];
			LinkedList<String> two[]=new LinkedList[m];
			LinkedList<String> three[]=new LinkedList[p];
			LinkedList<String> four[]=new LinkedList[q];
			LinkedList<String> five[]=new LinkedList[r];*/
			LinkedList<String> six[]=new LinkedList[t];
			LinkedList<String> seven[]=new LinkedList[u];
		
			int ii=0,j,k;
	 		int flag=0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				flag=1;
				six[ii] = new LinkedList<String>();
				StringTokenizer st=new StringTokenizer(sCurrentLine);
				six[ii].add(st.nextToken());
				six[ii].add(st.nextToken());
				six[ii].add(st.nextToken());
				six[ii].add(st.nextToken());
				six[ii].add(st.nextToken());
				six[ii].add(st.nextToken());
				ii++;
			}

			String s2="hdfs://ubuntu:54310/user/hduser/astrosample/sample6.txt";
			Path outFile = new Path(s2);
			FileSystem fsw = FileSystem.get(conf);
			FSDataOutputStream out = fsw.create(outFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			if(flag==0)
			{
				String take="Nothing to write here as there is no itemsets of smaller size available";
				long time = System.nanoTime() - start;
				double timeInSeconds = time/1e9;
				System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
				bw.write(take);
				bw.newLine();
				bw.close();
				System.exit(0);
			}
			t=ii;
		
		k=0;int f=0;
		for(i=0;i<t;i++)
		{
			String s4=six[i].get(1)+" "+six[i].get(2)+" "+six[i].get(3)+" "+six[i].get(4)+" "+six[i].get(5);
			for(j=i+1;j<t;j++)
			{
				String s5=six[j].get(0)+" "+six[j].get(1)+" "+six[j].get(2)+" "+six[j].get(3)+" "+six[j].get(4);
				if(s4.compareTo(s5)==0)
				{	f=1;
					seven[k]=new LinkedList<String>();
					seven[k].add(six[i].get(0));
					seven[k].add(six[i].get(1));
					seven[k].add(six[i].get(2));
					seven[k].add(six[i].get(3));
					seven[k].add(six[i].get(4));
					seven[k].add(six[i].get(5));
					seven[k].add(six[j].get(5));
					k++;
				}
				
			}
		}
		u=k;
		if(f==0)
		{bw.write("nothing could be made here");
		long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
			bw.newLine();
			bw.close();
		System.exit(0);
		}
		String ss;
			for(k=0;k<u;k++)
			{ss=seven[k].get(0)+" "+seven[k].get(1)+" "+seven[k].get(2) +" "+seven[k].get(3)+" "+seven[k].get(4)+" "+seven[k].get(5)+" "+seven[k].get(6);
			bw.write(ss);
			bw.newLine();
			}
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} 

		i=6;
		job[i]=new Job(conf,"Astronomical");
		job[i].setJarByClass(Astronomical.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(IntWritable.class);		
		job[i].setMapperClass(Map6.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[7]));
		job[i].waitForCompletion(true);
		long time = System.nanoTime() - start;
		double timeInSeconds = time/1e9;
		System.out.println("Time in seconds (mapreduce)"+timeInSeconds);
		
	}
 	
}
