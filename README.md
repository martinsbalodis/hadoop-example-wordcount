# Hadoop wordcount example

This is a clone of Clouderas hadoop wordcount example. Original source code can be found [here][1]. This example is ment to be run as an map-reduce application within Oozie. The *main* function isn't needed to run this within Oozie.

These are the Oozie specific configurations to run this job:

* `mapred.output.dir` : `/user/admin/output/${outputFolder}/`
* `mapred.input.dir` : `/user/admin/text-files/`
* `mapred.output.key.class` : `org.apache.hadoop.io.Text`
* `mapred.output.value.class` : `org.apache.hadoop.io.IntWritable`
* `mapred.mapper.class` : `lv.edu.linux.hadoop.wordcount.WordCountMapper`
* `mapred.reducer.class` : `lv.edu.linux.hadoop.wordcount.WordCountReducer`
* `mapred.input.format.class` : `org.apache.hadoop.mapred.TextInputFormat`
* `mapred.output.format.class` : `org.apache.hadoop.mapred.TextOutputFormat`


  [1]: http://www.cloudera.com/content/cloudera-content/cloudera-docs/HadoopTutorial/CDH4/Hadoop-Tutorial/ht_topic_5_1.html