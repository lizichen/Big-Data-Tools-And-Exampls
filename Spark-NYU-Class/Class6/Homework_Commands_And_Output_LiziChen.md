##### Using spark-shell with specified port number:
spark-shell --conf "spark.ui.port=10101"

**> val accounts_file = sc.textFile("/user/lc3397/loudacre/accounts/*")**  
accounts_file: org.apache.spark.rdd.RDD[String] = /user/lc3397/loudacre/accounts/* MapPartitionsRDD[1] at textFile at <console>:27

**> val weblog = sc.textFile("/user/lc3397/loudacre/weblog/2014-03-15.log")**  
weblog: org.apache.spark.rdd.RDD[String] = /user/lc3397/loudacre/weblog/2014-03-15.log MapPartitionsRDD[3] at textFile at <console>:27

**> accounts_file.take(5).foreach(println)**  
1,2008-10-23 16:05:05.0,\N,Donald,Becton,2275 Washburn Street,Oakland,CA,94660,5100032418,2014-03-18 13:29:47.0,2014-03-18 13:29:47.0
2,2008-11-12 03:00:01.0,\N,Donna,Jones,3885 Elliott Street,San Francisco,CA,94171,4150835799,2014-03-18 13:29:47.0,2014-03-18 13:29:47.0
3,2008-12-21 09:19:50.0,\N,Dorthy,Chalmers,4073 Whaley Lane,San Mateo,CA,94479,6506877757,2014-03-18 13:29:47.0,2014-03-18 13:29:47.0
4,2008-11-28 00:08:09.0,\N,Leila,Spencer,1447 Ross Street,San Mateo,CA,94444,6503198619,2014-03-18 13:29:47.0,2014-03-18 13:29:47.0
5,2008-11-15 23:06:06.0,\N,Anita,Laughlin,2767 Hill Street,Richmond,CA,94872,5107754354,2014-03-18 13:29:47.0,2014-03-18 13:29:47.0

**> val mapped_result = accounts_file.map(line => line.split(",")).map(lineArray => (lineArray(0), 1))**  
mapped_result: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[5] at map at <console>:29

**> mapped_result.take(5).foreach(println)**   
(1,1)
(2,1)
(3,1)
(4,1)
(5,1)

**> val weblog_mapped = weblog.map(line => line.split(" ")).map(lineArray => (lineArray(2), 1))**   
weblog_mapped: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[7] at map at <console>:29

**> val weblog_reduced = weblog_mapped.reduceByKey(_ + _)**   
weblog_reduced: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[8] at reduceByKey at <console>:31

**> weblog_reduced.take(5).foreach(println)**   
(99664,2)
(41553,2)
(98135,2)
(10942,2)
(62910,2)

**> val reversed = weblog_reduced.map(s => (s._2, s._1))**   
reversed: org.apache.spark.rdd.RDD[(Int, String)] = MapPartitionsRDD[9] at map at <console>:33

**> val numberOfUsers_visitedFrequency = reversed.countByKey()**   
numberOfUsers_visitedFrequency: scala.collection.Map[Int,Long] = Map(5 -> 1, 10 -> 36, 14 -> 21, 20 -> 3, 6 -> 53, 2 -> 1666, 12 -> 20, 7 -> 1, 3 -> 3, 18 -> 6, 16 -> 17, 8 -> 39, 4 -> 445)

**> numberOfUsers_visitedFrequency.foreach(println)**   
(5,1)
(10,36)
(14,21)
(20,3)
(6,53)
(2,1666)
(12,20)
(7,1)
(3,3)
(18,6)
(16,17)
(8,39)
(4,445)

**> val webblog_with_ip = weblog.map(line => line.split(" ")).map(lineArray => (lineArray(2), lineArray(0)))**    
webblog_with_ip: org.apache.spark.rdd.RDD[(String, String)] = MapPartitionsRDD[13] at map at <console>:29

**> val joined_result = (mapped_result join webblog_with_ip)**    
joined_result: org.apache.spark.rdd.RDD[(String, (Int, String))] = MapPartitionsRDD[16] at join at <console>:35

**> val joined_result_ip_only = joined_result.map(s => (s._1, s._2._2))**    
joined_result_ip_only: org.apache.spark.rdd.RDD[(String, String)] = MapPartitionsRDD[17] at map at <console>:37

**> joined_result_ip_only.take(5).foreach(println)**    
(82182,20.237.219.215)
(82182,20.237.219.215)
(125916,25.37.202.150)
(125916,25.37.202.150)
(51025,12.180.56.155)

**> val groupByKey_result = joined_result_ip_only.groupByKey().mapValues(_.toList)**    
groupByKey_result: org.apache.spark.rdd.RDD[(String, List[String])] = MapPartitionsRDD[19] at mapValues at <console>:39

**> groupByKey_result.take(5).foreach(println)**    
(98135,List(239.215.133.194, 239.215.133.194))
(188,List(104.231.206.19, 104.231.206.19, 61.48.25.116, 61.48.25.116, 107.65.41.203, 107.65.41.203, 114.212.172.156, 114.212.172.156))
(19112,List(146.37.48.233, 146.37.48.233))
(24467,List(90.178.253.28, 90.178.253.28))
(17943,List(101.82.119.120, 101.82.119.120))

**> groupByKey_result.count()**    
res3: Long = 2311

 






