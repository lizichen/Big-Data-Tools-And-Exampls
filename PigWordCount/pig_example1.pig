allsales = LOAD 'sales.csv' USING PigStorage(',') AS (name, price);
bigsales = FILTER allsales BY price > 1999;
STORE bigsales INTO '/home/cloudera/workspace/PigWordCount/output';
