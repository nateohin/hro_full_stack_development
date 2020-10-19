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

https://mkyong.com/spring-boot/spring-boot-profile-based-properties-and-yaml-example/
https://dzone.com/articles/dealing-with-javas-localdatetime-in-jpa
https://attacomsian.com/blog/spring-data-jpa-one-to-many-mapping

IBAN Bron: https://www.nummeriban.nl/structuur/
HOE IS EEN IBAN NUMMER OPGEBOUWD?
De wereld wordt steeds meer met elkaar verbonden, ook op het gebied van financiën. Een bijkomend probleem hiervan was dat het voor veel mensen onmogelijk was om geld over te maken naar buitenlandse rekeningen. Voor grote bedrijven en landen was dit al mogelijk, maar particulieren kwamen vaak problemen tegen. Elk land had namelijk zijn eigen notering en systeem voor bankrekeningen.
Daarom werd er 1999 besloten om een internationaal systeem te introduceren dat hier verandering in bracht. Dit systeem is het IBAN. IBAN staat voor International Bank Account Number, en zoals de naam doet vermoeden geeft het iedere bankrekening een internationaal bruikbaar nummer. Door deze standaardisatie van bankrekening nummers kunnen particulieren heel eenvoudig geld internationaal overmaken.

WAAR BESTAAT EEN IBAN NUMMER UIT?
Een IBAN nummer heeft een logische opbouw, zodat het gemakkelijk toe te passen is voor alle landen. Een IBAN nummer bestaat uit een reeks letters en cijfers die verschillende informatie weergeven. Zo bevat een IBAN:
- Landcode; bestaande uit 2 letters
- 2 controle cijfers
- Bankcode
- Rekeningnummer (of rekening identificatie)
Om er voor te zorgen dat een IBAN nummer gemakkelijk leesbaar is, wordt deze vaak weergegeven in reeksen van 4 cijfers en letters. Dit alles samen maakt een IBAN nummer. Een voorbeeld hiervan is:
NL05 RABO 1234 1234 00
Je kan dit nummer dus gemakkelijk ontleden. In dit geval gaat het om een Nederlands bankrekening nummer bij de Rabobank. Dit is te zien aan de landcode en bankcode. NL is de landscode voor Nederland, 05 is het controle getal, RABO is de bankcode die duidt op Rabobank en het rekeningnummer is 1234 1234 00.

Tegenwoordig staat het IBAN nummer standaard aangegeven op jouw betaalpas, je hoeft hem dus niet meer uit te rekenen. Als jouw bankpas nog geen IBAN notering heeft, dan kan je een nieuwe pas aanvragen bij jouw bank. Mocht je toch een IBAN nummer uit moeten rekenen, dan zijn hier gelukkig online ook programma's voor.