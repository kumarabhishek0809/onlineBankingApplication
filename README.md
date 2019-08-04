# onlineBankingApplication
onlineBankingApplication [Spring+Hibernate +Java 8 + Angular2]
Project 
#onlineBankingApplication is Spring Project
#AdminPortal is Admin UI using Angular 2


#Global package:

npm uninstall -g angular-cli @angular/cli
npm cache clean
npm install -g @angular/cli@latest

#Local project package:
rm -rf node_modules dist # On Windows use rmdir /s /q node_modules dist
npm install --save-dev @angular/cli@latest
npm install
ng init


#docker setup for mysql.

#docker pull mysql
#docker run -d --name mysqlDB -p 3306:3306 --env="MYSQL_ROOT_PASSWORD=mypassword" mysql
#docker exec -it mysqlDB mysql -uroot -p
#ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
#ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
#ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'root';
#docker run -p 3306:3306 -e mysql_allow_empu=yes -d mysql 
#Create database OnlineBanking;


docker run -d -it --rm --name oracle12se -p 1521:1521 -p 5500:5500 store/oracle/database-enterprise:12.2.0.1
docker logs oracle12se --tail 100
docker inspect oracle12se | grep IPAddress


docker exec -it oracle12se bash -c "source /home/oracle/.bashrc; sqlplus /nolog"

connect sys/Oradoc_db1 as sysdba;
alter session set "_ORACLE_SCRIPT"=true;
create user OnlineBanking identified by OnlineBanking;
grant dba to OnlineBanking;
http://localhost:5050/index"


docker run --name oracle12se -p 1521:1521 -p 5500:5500 store/oracle/database-enterprise:12.2.0.1
