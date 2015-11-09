


/*****************************************************************************/
/***Rulestryallastro.java 
                                                 
bin/hadoop dfs -rmr /user/hduser/rules/*                                                                  

sudo javac -classpath /usr/local/hadoop/hadoop-core-1.1.2.jar -d / /home/madhulekha/Downloads/Rulestryallastro.java

sudo jar cf rtryallastro.jar /sat4allastro

bin/hadoop jar rtryallastro.jar sat4allastro.Rulestryallastro /user/hduser/out/output2/part-r-00000 /user/hduser/out/output3/part-r-00000 /user/hduser/out/output4/part-r-00000 /user/hduser/out/output5/part-r-00000 /user/hduser/out/output6/part-r-00000 /user/hduser/out/output7 /user/hduser/rules/rules2 /user/hduser/rules/rules3 /user/hduser/rules/rules4 /user/hduser/rules/rules5 /user/hduser/rules/rules6 /user/hduser/rules/rules7
  

/*****************************************************************************/



package sat4allastro;
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

public class Rulestryallastro
{
 	public static class Map2 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=2;int i=count;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			Collections.sort(li);	
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());
			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
					int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");
					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://127.0.0.1:54310/user/hduser/out/output1/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									//temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
					br.close();
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

	
	public static class Map3 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=3;int i=3;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			Collections.sort(li);
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());

			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
					int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");
					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/out/output2/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
					br.close();
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
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=4;int i=count;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());
			Collections.sort(li);	

			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
					int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");

					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/out/output3/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									temp=stip.nextToken();
									temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
						br.close();
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

	public static class Map5 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=5;int i=count;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());
			Collections.sort(li);	

			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
					int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");
					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/out/output4/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									temp=stip.nextToken();	temp=stip.nextToken();	temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
					br.close();
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

	public static class Map6 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=6;int i=count;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());
			Collections.sort(li);	

			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
					int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");
					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/out/output5/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									temp=stip.nextToken();	temp=stip.nextToken();	temp=stip.nextToken();
									temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
					br.close();
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

	public static class Map7 extends Mapper<LongWritable, Text, Text, Text> 
	{
		private Text antecedent = new Text();
		private Text consequent = new Text();
 	
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
		{
			List<String> li=new LinkedList<String>();
			String line = value.toString();
			String s;
			StringTokenizer tokenizer = new StringTokenizer(line);
			int count=7;int i=count;
			while (i>0) 
			{
				s=tokenizer.nextToken();
				li.add(s);
				i--;	
			}
			double num,den=1;
			num=Double.parseDouble(tokenizer.nextToken());
			Collections.sort(li);	

			for(i=0;i<count;i++)
			{
				String a="";String c;
				c=li.get(i);
				if(c.compareTo("galaxy")==0||c.compareTo("qso")==0||c.compareTo("star")==0)
				{
				int j;
					for(j=0;j<count;j++)
					if(i!=j) a=a.concat(li.get(j)).concat(" ");
					FileSystem fs = FileSystem.get(context.getConfiguration());
					Path InFile = new Path("hdfs://ubuntu:54310/user/hduser/out/output6/part-r-00000");
					BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(InFile)));
					String sub;
					while ((sub = br.readLine()) != null) {
						if(sub.contains(a))
							{
									StringTokenizer stip=new StringTokenizer(sub);
									String temp=stip.nextToken();
									temp=stip.nextToken();	temp=stip.nextToken();	temp=stip.nextToken();
									temp=stip.nextToken();  temp=stip.nextToken();
									//if(a.compareTo(stip.nextToken())==0);
									den=Double.parseDouble(stip.nextToken());
									break;
							}
						}
					br.close();
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
 	
	public static class Reduce extends Reducer<Text, Text, Text, Text> 
	{
		public void reduce(Text key, Text values, Context context) throws IOException, InterruptedException 
		{				
			context.write(key, values);
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		//long start = System.nanoTime();
		int n=6,i;
		Configuration conf = new Configuration();
		Job job[]= new Job[n];
		
		i=0;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map2.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[0]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[6]));
		job[i].waitForCompletion(true);

		i=1;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map3.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[1]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[7]));
		job[i].waitForCompletion(true);

		i=2;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map4.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[2]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[8]));
		job[i].waitForCompletion(true);
		
		i=3;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map5.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[3]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[9]));
		job[i].waitForCompletion(true);

		i=4;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map6.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[4]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[10]));
		job[i].waitForCompletion(true);

		i=5;
		job[i]=new Job(conf,"Rulestryallastro");
		job[i].setJarByClass(Rulestryallastro.class);
		job[i].setOutputKeyClass(Text.class);
		job[i].setOutputValueClass(Text.class);		
		job[i].setMapperClass(Map7.class);
		job[i].setReducerClass(Reduce.class);	
		job[i].setInputFormatClass(TextInputFormat.class);
		job[i].setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job[i], new Path(args[5]));
		FileOutputFormat.setOutputPath(job[i], new Path(args[11]));
		job[i].waitForCompletion(true);

	}
 	
}
