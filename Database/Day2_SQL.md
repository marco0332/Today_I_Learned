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
```sql
Insert into SampleTable(name, tableID)
values('mySampleTable', 12312);
```  
  
3. Update  
```sql
Update SampleTable set name='mySampleTable2' where tableID=12312;
```  
  
4. Delete
```sql
Delete from SampleTable where tableID=12312;
```  
이 때 delete는 테이블에서 데이터를 row단위로 삭제한다.  
  
5. Select  
```sql
Select name, tableID
from SampleTable
where tableID=12312;
```  
  
데이터가 아니라, 테이블 혹은 뷰를 삭제하고 싶을 때는  
```sql
Drop table SampleTable;
```  
  
  
+alpha_1 : Columns를 SQL에서는 Projection, row단위 데이터를 Instance라고 한다.  
+alpha_2 : 실제 DB에서 Table은 보통 n(row) > n(col)이다. 따라서 탐색할 경우 row수를 최대한 줄일 수 있도록 튜닝하는 것이 효과적이다.  
  
select를 통해 데이터를 찾을 경우, 테이블에 입력된 순선에 따라 나온다.  
그래서 결과 'Order by asc | desc'를 통해 정렬할 수 있다.
  
  
#### 비교하는 다양한 방법  
  
  
+alpha_3 : null은 =, > 등 비교를 할 수 없는 데이터이다. 따라서 'attri <b>is null</b>'하면 찾을 수 있다.  
