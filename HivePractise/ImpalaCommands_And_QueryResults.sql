show tables;
/*
+---------------------------------+
| name                            |
+---------------------------------+
| result1                         |
+---------------------------------+
*/

invalidate metadata;
show tables;
/*
+------------------------------------------+
| name                                     |
+------------------------------------------+
| result1                                  |
| w1                                       |
| w2                                       |
| w3                                       |
+------------------------------------------+
*/

create external table w10 (data1 string, year int, data2 string, temperature int, quality tinyint, data3 string)
row format delimited fields terminated by ','
location 'hdfs://babar.es.its.nyu.edu:8020/user/lc3397/impalaInput/';
