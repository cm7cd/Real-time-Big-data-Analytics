package edu.umkc.cm7cd.SparkWordCount

import org.apache.spark._

object SparkWordCount {
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir","E:\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input")

    val wc=input.flatMap(line=>{line.split(" ")}).map(word=>(word,1))

    //val output=wc.reduceByKey(_+_).map(tuple => (tuple._2, tuple._1)).sortByKey(true)

    val output=wc.reduceByKey(_+_).map(item => item.swap).sortByKey(true,1).map(item => item.swap)

    output.saveAsTextFile("output")

    val o=output.collect()

  }

}
