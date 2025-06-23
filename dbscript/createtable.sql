drop table  WEATHER 

CREATE TABLE Weather ( 
   weatherId INT auto_increment PRIMARY KEY, 
   srckey varchar(255),
   srcvalue varchar(255),
   jasonresult varchar(1000),
   description varchar(255),
   createddate DATETIME 
);