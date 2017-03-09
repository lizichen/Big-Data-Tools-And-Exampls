
## Real-Time Big Data Analytics  

##Hadoop
#####What is Hadoop?  
Hadoop, in general, is an **ecosystem** of tools that help deal with Big Data. Core Hadoop is more specifically known for its Data Storage and Data Computation abilities, which are provided by **HDFS** and **MapReduce** respectively. 

#####What are the components of Hadoop?  
Hadoop core has two main components, which are **Data Storage** and **Data Computation**.    
**Data Storage** is provided by *Hadoop Distributed File System (HDFS)*.  This protects against data loss from hardware failure, creates and helps with parallelization and can deal with data in the petabytes range and above.   
**MapReduce** provides the Data Computation in Hadoop core. This is designed well to work on distributed systems and has inbuilt *fault tolerance*. This consists of the *Mapper phase* and the *Reducer phase*.  

#####Why do we need Hadoop?  
As the **size** of the problem becomes a part of the problem that we are trying to solve, we need something to be able to deal with this type of Big Data. Hadoop is an ecosystem built to be able to deal with all kinds of Big Data and can even achieve close to real time analytics of this data. Hadoop provides an environment where the user is unaware as to the location and the complexity of the file storage (one file multiple blocks, across multiple data warehouses, etc.) without compromising on the ease of access to the file system.   

#####What problems does it solve?
It solves problems where the **size of the data** itself is part of the problem. It gives the ability to process **massive amounts of data**, possibly even at real-time. It helps with business analytics where a clients’ decision yesterday could be used to influence a decision tomorrow. It also helps with the storage of massive petabytes of information across multiple “blocks” or “Data Warehouses” and for seamless access to this data. It also helps in *reducing the* **cost** *of storing and processing Big Data*.

##HDFS (Hadoop Distributed File System)
#####What is HDFS?
Hadoop Distributed File System (HDFS) is the **storage** part of Hadoop. It is a *file system* designed to store very large files.  
- Protects in case of data loss from hardware failure
- It creates and helps with parallelization opportunities
- Capable of dealing with data in the range of Petabytes and higher
- Provides high performance streaming data access
- Can be implemented on any hardware
- Works with noticeable delay even in the case of failures
- Automatically stores replicas of blocks to recover from data failure

#####What is the HDFS architecture?
- HDFS is made of two types of nodes: **NameNodes & DataNodes**.  
- An HDFS cluster consists of 1 NameNode (**master**) and Many DataNodes (**Workers** who serve the Master node).
- HDFS files follow the **write once read many (WORM)** model
- **Metadata** (of the file locations and job locations) are stored on the NameNode(**master**) and are updated by **multiple worker nodes** concurrently
- HDFS uses block structured file system
- Individual files are broken into blocks of a fixed size
- Blocks are stored in one or more DataNodes(**worker nodes**). This gives the ability for a file to be of any size as it can be stored across multiple machines.
- HDFS comes with its own utilities for file management
- These **HDFS file blocks** are managed by **DataNode services** 
- Each block is replicated across multiple machines to deal with data loss from hardware failures

#####What does the Namenode do? How many are there in a cluster?
Namenode, It is the **Master Node**. There is usually just one for each cluster. Manages the **file system namespace** and its **tree**. *Maintains the metadata* for all the files and directories on the tree. Stores the **File Names, Permissions and the Data Location of each block of each file**. This is stored on the **main memory** for fast data access. NameNode helps **rectify** hardware failures.

#####What does the Datanode do? How many are there in a cluster?
DataNode(s), are the **workers nodes**. There are multiple per cluster. They **serve the requests from the Master node**. They can **store** and **retrieve** blocks on demand. *They report to the NameNode(MasterNode) with the list of the blocks that they are storing*. They Compute **CheckSums** and report any error to the NameNode(MasterNode).

#####What is the advantage of using HDFS in a Hadoop cluster instead of using networked storage?
- HDFS can use the power of **multiple machines** while NFS is restricted to 1. 
- The **Size of the file system** is quickly extensible in HDFS whereas it is fixed for NFS.
- HDFS offers **inbuilt reliability** whereas NFS does not
- Both have clients contending for service but in HDFS **the load is distributed across multiple servers**. 
- Clients need **not copy data** before processing it in HDFS
- HDFS supports **very large file** sizes

##MapReduce
#####What is MapReduce?
It provides the **Data Computation capability** for Hadoop, provides Distributed Computation power.  It leverages **Data-Locality** for higher performance. It tries to bring the computation to the data by assigning the jobs to the node, which holds the data. 

#####Where does a MapReduce program run?
A MapReduce program runs on the **DataNodes** and the job is allocated and tracked by the **Job Tracker**. *It can be configured to run on a single server or on a distributed network.*

#####What is data locality optimization?
**Data Locality optimization** is the logic used to schedule the computation of the **Mapper** *as close as possible* to the Node in which the Data is contained.  
    Same node – distance – 0  
    Same Rack – distance – 2  
    Same Data Warehouse – distance – 4  
    Difference Data Warehouse – distance – 6    

This is computed using **Rack Mapping Tree Structure**.

#####Does Data Locality Optimization apply equally to Map-Tasks and Reduce-Tasks? 
**No**, this applies primarily to the **Map-Task** and generally not to the Reduce Tasks. 

#####How does Hadoop decide where a Map task should run (distance computation)?
The Admin runs a script and sets up the configuration so that Hadoop is aware of the cluster topology. This allows it to create a **Rack Mapping Tree Structure**. The Distance between two blocks is calculated as the distance to the nearest node in the Tree where they meet.  
    Same node – distance – 0  
    Same Rack – distance – 2  
    Same Data Warehouse – distance – 4  
    Difference Data Warehouse – distance – 6  

#####How is a MapReduce program different from a regular Java, Python, C/C++ program?
A regular Java, Python or C/C++ program normally run on the **local** and on **one machine** whereas MapReduce is designed to run on multiple machines. 

#####How is a MapReduce program scheduled such that it runs optimally?
It is scheduled with **Data Locality** in mind.

#####What is the advantage/disadvantage of using speculative execution?
**Adv:** - It is helpful when some tasks are running too slow and are slowing down overall completion time. It is an optimization for well built code that works most of the time and rarely leads to slowness.     
**DisAdv:** - When the code contains a **bug** then the tasks are going to be **slow** when the bug is encountered and this would make Speculative Execution start another task with the same bug. This leads to counter the optimization. Reducer tasks have to fetch the outputs from all its mappers and this intensely uses up the bandwidth.

#####What does a Mapper do?
Mapper maps the **key-value** input pairs to possibly other **key-value** output pairs after some processing of the input. It drops bad/erroneous records/data. It Sorts and groups **key-value pairs** by key before sending to the Reducer.

#####What does Mapper input data look like?
(line_offset_number, line_contents)

#####Where does Mapper input data come from?
It can be configured to get the input data from many sources. The most common input data source is a **text** file. The user specifies this.

#####What are Map tasks?
Map tasks are the set of tasks that run on each of the input splits on the **Worker Nodes** and are managed by the **JobTracker**. The Mapper takes an input and maps to some **{key, value} pair** and sorts and **groups by key** and sends this to the reducer.

#####Where does Map task output get written? 
Writes its output to the shared HDFS where the *Reducer picks up the output* and processes.

#####What does a Reducer do?
Takes the **{Key, Value} input pairs** *from the Mapper or Combiner* and maps it to other **{Key, Value} pairs** that are the output. Usually **iterated** over the **{Key, Value} pairs** from Mapper/Combiner and picks out some values/one value/creates a value using these, and uses this to create a **{Key, Value} pairs outputs** .

#####What does Reducer input data look like?
(mapper_produced_key, mapperOrCombiner_produced_list_of_values)

#####Where does Reducer input data come from?
Mapper or Combiner Outputs to **shared HDFS**. Reducer picks it up from there.

#####What are Reduce tasks?
Reduce tasks are the set of tasks that run on the group of the Mapper tasks outputs that worked on the input splits. These are managed by the JobTracker. Takes the **{Key, Value} input pairs** from the **Mapper or Combiner** and maps it to other **{Key, Value} pairs** that are the output.

#####Where does Reduce task output get written?
The reduce task output is usually written back to the **local file system** as this is the analysis that is required by the user.

#####What happens during the shuffle phase?
The shuffle phase is where the Mappers **{Key, Value} pairs** outputs are **sorted by key** before being given to the Reducer.

##Pig
#####Is Pig Case Sensitive ?
- Operators and Commands are **not** case-sensitive. 
- Aliases and function names **are** case-sensitive.

#####When we submit a Pig program, are there MapReduce jobs created?
**Yes**, Underneath it all Pig generates and runs MapReduce programs

#####Can a Pig program read input from HDFS?
**Yes**, Pig launches jobs and interacts with HDFS from my workstation.

#####Can a Pig program output data to HDFS?
**Yes**, Pig launches jobs and interacts with HDFS from my workstation.

#####How do we define a schema for a Pig table?
A schema is **optional** in Pig. 

#####LOAD
Loads a bag with the data from some user-specified input(.csv, or .txt files).  
```pig
raw = LOAD 'excite.log' USING PigStorage('\t') AS (user, time, query); 
```

#####FILTER .. BY
Loads the new bag with the values from the specified bag that follow the filter.
```pig
clean1 = FILTER raw BY org.apache.pig.tutorial.NonURLDetector(query); 
```

#####FOREACH .. GENERATE
Runs through the contents on the specified bag and generates something to **new bag**.  
```pig
clean2 = FOREACH clean1 GENERATE user, time, org.apache.pig.tutorial.ToLower(query) as query; 
```

#####DUMP
Dump allows us to **view** the contents of a bag.
```pig
    DUMP clean2;
```

#####STORE
Allows us to **store** the contents of a bag to a **file**.  
```pig
STORE ordered_uniq_frequency INTO '/tmp/tutorial-results' USING PigStorage(); 
```

#####JOIN
Joins two or more relations.

#####Group
Groups the data in a single relation.

#####ORDER .. BY
Sorts a relation by one or more fields.

#####DISTINCT
Removes duplicate rows from a relation

#####LIMIT
Limits the size of the relation to a maximum number of tuples.

#####UNION
Combines two or more relations into one

#####DESCRIBE
Prints a relations schema









