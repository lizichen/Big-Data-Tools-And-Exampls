## Apache PIG

### Running Pig:
Local Mode: ```pig -x local```  
MapReduce Mode: ```pig -x mapreduce``` OR SIMPLY ```pig```

### Manipulate HDFS from the Grunt (the Pig shell)
```sh
grunt> fs -mkdir sales/;
grunt> fs -put europe.txt sales/;
grunt> allsales = LOAD 'sales' AS (name, price);
grunt> bigsales = FILTER allsales BY price > 100;
grunt> STORE bigsales INTO 'myreport';
grunt> fs -getmerge myreport/ bigsales.txt;
```

```sh
grunt> sh date;
Fri May 10 13:05:31 PDT 2013
grunt> fs -ls;                 -- lists HDFS files
grunt> sh ls;                  -- lists local files
```

Run PIG script from within the Grunt shell via the **run** command:
```sh
grunt> run salesreport.pig;
```

### Some Keywords in PIG:
##### LOAD
Loads a bag with the data from some user-specified input(.csv, or .txt files).  
```pig
raw = LOAD 'excite.log' USING PigStorage('\t') AS (user, time, query); 
```

##### FILTER .. BY
Loads the new bag with the values from the specified bag that follow the filter.
```pig
clean1 = FILTER raw BY org.apache.pig.tutorial.NonURLDetector(query); 

bigSales = FILTER sales BY price > 3000;
```

##### AND and OR
```pig
targeted = FILTER sales BY name == 'Patrick' OR (price > 3500 AND price < 4000);
```

##### MATCHES
Use FILTER..MATCHES for pattern matching through Java’s regular expressions
```pig
a_names = FILTER sales BY name MATCHES 'A.*';
```

##### FOREACH .. GENERATE
Runs through the contents on the specified bag and generates something to **new bag**.  
```pig
clean2 = FOREACH clean1 GENERATE user, time, org.apache.pig.tutorial.ToLower(query) as query; 
```

##### DUMP
Dump allows us to **view** the contents of a bag.
```pig
    DUMP clean2;
```

##### STORE
Allows us to **store** the contents of a bag to a **file**.  
```pig
STORE ordered_uniq_frequency INTO '/tmp/tutorial-results' USING PigStorage(); 
```

##### Group
Groups the data in a single relation.
```pig
grunt> grpByName = GROUP sales BY name;
grunt> DESCRIBE grpByName;

grpByName: {group: chararray, sales: {(name:chararray,price: int)}}
```

##### ORDER .. BY
Sorts a relation by one or more fields.
```pig
sorted = ORDER sales BY lastBill DESC;
```

##### DISTINCT
Removes duplicate rows from a relation
```pig
uniqueTuples = DISTINCT sales;
```

##### LIMIT
Limits the size of the relation to a maximum number of tuples.
```pig
tenSales = LIMIT sales 10;
```

##### JOIN
Joins two or more relations.

##### UNION
Combines two or more relations into one

##### DESCRIBE
Prints a relations schema

### Commonly used Pig built-in functions  
| Function Description               | Example Invocation    | Input | Output           |
|------------------------------------|-----------------------|-------|------------------|
| Convert to uppercase               | UPPER(country)        | uk    | UK               |
| Remove leading/trailing spaces     | TRIM(name)            | Bob   | Bob              |
| Return a random number             | RANDOM()              |       | 0.48161326652569 |
| Round to closest whole number      | ROUND(price)          | 37.19 | 37               |
| Return chars between two positions | SUBSTRING(name, 0, 2) | Alice | Al               |


