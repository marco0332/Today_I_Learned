## 2019-04-21 Day 2
  
> <h4>`SQL은 DBMS에 접근하는 언어`  </h4>
> <h4>`얼마나 효율적으로 모델 설계와 SQL을 작성했는지가 프로그램 성능에 큰 영향을 준다`  </h4>
  
### # SQL  
<code>mySQL, JDBC 사용</code>  
- 보안 때문에 보통 root 계정을 없애고 시작.  
- SQL Port번호 : 3306  
  
#### 명령어  
SQL에서 명령어는 대소문자 상관없음.  
다만 데이터를 비교할 때는 대소문자 구분.  
  
기본적인 CRUD  
1. Create  
```sql
Create table SampleTable(
    name varchar(40),
    tableID int,
    primary key(tableID)
)engine=InnoDB, default charset=utf8;
```  
  
2. Insert  
