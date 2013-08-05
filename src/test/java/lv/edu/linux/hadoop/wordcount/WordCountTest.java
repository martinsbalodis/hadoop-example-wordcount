package lv.edu.linux.hadoop.wordcount;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest {

	private MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp() {
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();
		mapDriver = MapDriver.newMapDriver(mapper);;
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapper() {
		mapDriver.withInput(new LongWritable(0), new Text("a"));
		mapDriver.withOutput(new Text("a"), new IntWritable(1));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("martins"), values);
		reduceDriver.withOutput(new Text("martins"), new IntWritable(2));
		reduceDriver.runTest();
	}

	@Test
	public void testMapReduce() {
		mapReduceDriver.withInput(new LongWritable(1), new Text("a un b un c"));
		mapReduceDriver.addOutput(new Text("a"), new IntWritable(1));
		mapReduceDriver.addOutput(new Text("b"), new IntWritable(1));
		mapReduceDriver.addOutput(new Text("c"), new IntWritable(1));
		mapReduceDriver.addOutput(new Text("un"), new IntWritable(2));
		mapReduceDriver.runTest();
	}
}
