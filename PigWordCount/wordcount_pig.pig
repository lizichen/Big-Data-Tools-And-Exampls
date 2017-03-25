lines = LOAD 'input.txt';

input1 = FOREACH lines GENERATE REPLACE($0,'-',' ');
input2 = FOREACH input1 GENERATE REPLACE($0,',',' ');
input3 = FOREACH input2 GENERATE REPLACE($0,':',' ');
input4 = FOREACH input3 GENERATE LOWER((chararray)$0);
finalinput = FOREACH input4 GENERATE FLATTEN(TOKENIZE($0)) as words;

keys = LOAD 'keys.txt';
lowercasekeys = FOREACH keys GENERATE LOWER((chararray)$0);

match = JOIN lowercasekeys BY $0 LEFT OUTER, finalinput BY $0;
WordCount = FOREACH match GENERATE $0,($1 is null ? 0:1);

GroupWordCounts = Group WordCount BY $0;
FinalCount = FOREACH GroupWordCounts GENERATE group, SUM(WordCount.$1);

dump FinalCount;

STORE FinalCount INTO 'output';
