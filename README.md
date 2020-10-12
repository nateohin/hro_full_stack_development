# hro_full_stack_development

http://fullstack-java-minor.s3-website.eu-central-1.amazonaws.com/

https://spring.io/guides/gs/testing-web/

https://mkyong.com/maven/maven-jacoco-code-coverage-example/

https://mkyong.com/maven/maven-pitest-mutation-testing-example/

https://spring.io/guides/gs/actuator-service/

https://stackoverflow.com/questions/4157972/how-to-update-a-value-given-a-key-in-a-hashmap

http://www.java2s.com/Code/Java/Collections-Data-Structure/Pagingoveracollection.htm

mysql> create database hro; -- Creates the new database
mysql> create user 'hrouser'@'%' identified by 'hrouser'; -- Creates the user
mysql> grant all on hro.* to 'hrouser'@'%'; -- Gives all privileges to the new user on the newly created database
mysql> revoke all on hro.* from 'hrouser'@'%';
mysql> grant select, insert, delete, update on hro.* to 'hrouser'@'%';