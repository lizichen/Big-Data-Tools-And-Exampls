# Big Data Tools and Examples

#### MapReduce, HDFS, Hadoop, Hive, Pig, Spark, HBase, Sqoop, Oozie

#### Using git push on CentOS
- git remote set-url origin https://lizichen@github.com/lizichen/Realtime-Big-Data-Analytics.git
- git push -u origin master
- type in password to push

#### Compile and Run Java MapReduce Programs on Virtual Box
1. Startup your VM
2. Write your driver source code using a text editor like vi (or emacs):
  
  ```bash
        vi MaxTemperature.java
  ```
3.  Write your mapper and reducer source code:
  
  ```bash
        vi MaxTemperatureMapper.java
        vi MaxTemperatureReducer.java
  ```
4. Compile your Java code:
  
  ```bash
  java -version
  yarn classpath
  javac -classpath `yarn classpath` -d . MaxTemperatureMapper.java
  javac -classpath `yarn classpath` -d . MaxTemperatureReducer.java
  javac -classpath `yarn classpath`:. -d . MaxTemperature.java
  ```
5. Create your jar file
  
  ```bash
  jar -cvf maxTemp.jar *.class
  ```
6. Create your input data file on the local file system
  
  ```bash
  vi temperatureInputs.txt
  ```
7. Put your input data file into HDFS
  
  ```bash
  hdfs dfs -ls /
  hdfs dfs -ls /user
  hdfs dfs -ls /user/cloudera
  hdfs dfs -mkdir /user/cloudera/class1
  hdfs dfs -put temperatureInputs.txt /user/cloudera/class1
  hdfs dfs -cat /user/cloudera/class1/temperatureInputs.txt
  ```
8. Run your MapReduce program

  ```bash
  hadoop jar maxTemp.jar MaxTemperature /user/cloudera/class1/temperatureInputs.txt /user/cloudera/class1/output
  ```
9. Verify that the program ran and the results are correct
  
  ```bash
  hdfs dfs -ls /user/cloudera/class1/output
  hdfs dfs -cat /user/cloudera/class1/output/part-r-00000
  ```
