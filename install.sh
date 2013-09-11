cd computer-database-core
mvn clean generate-sources install
cd ..
cd computer-database-back
mvn clean install
cd ..
cd computer-database-front
mvn clean install

