TRUNCATE crimes;
LOAD DATA INFILE 'data/crime_data.txt' INTO TABLE student
     FIELDS TERMINATED BY ',' 
     LINES TERMINATED BY '\n';
