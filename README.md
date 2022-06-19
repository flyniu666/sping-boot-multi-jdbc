## Spring boot for multi jdbc datasource:
This project is a spring-boot demo for using multi jdbc data source. 
It can give a restful server for different mysql database.



## Prepar database:
create two mysql databases :
```bash
create database db_01;    
use db_01;
create table user
(
    id       int auto_increment
        primary key,
    username varchar(255) null,
    address  varchar(255) null
);

INSERT INTO db_01.user (id, username, address) VALUES (1, 'c', 'https://github.com/TheAlgorithms/C');
INSERT INTO db_01.user (id, username, address) VALUES (2, 'java', 'https://github.com/openjdk/jdk');

create database db_02;
use db_02;
create table user
(
    id       int auto_increment
        primary key,
    username varchar(255) null,
    address  varchar(255) null
);

INSERT INTO db_02.user (id, username, address) VALUES (1, 'golang', 'https://github.com/golang/go');
INSERT INTO db_02.user (id, username, address) VALUES (2, 'c#', 'https://docs.microsoft.com/zh-cn/dotnet/csharp/');

```

## API:
List: GET /api/{dbName}

Insert: POST /api/{dbName}/user

Update: PUT /api/{dbName}/{table}/{id}

Delete: DEL /api/{dbName}/{table}/{id}


## Send restful request to operate database:
list user table:
```bash
curl -H "Content-Type:application/json" -X GET --data  http://127.0.0.1:8001/api/one
```

insert to user table:
```bash
curl -H "Content-Type:application/json" -X POST --data '{"username":"javascript","address":"https://github.com/airbnb/javascript"}' http://127.0.0.1:8001/api/one/user
```

update:
```bash
curl -H "Content-Type:application/json" -X PUT --data "golang1.19.1"  http://127.0.0.1:8001/api/two/user/1 
```

delete :
```bash
curl -H "Content-Type:application/json" -X DELETE   http://127.0.0.1:8001/api/two/user/2
```

