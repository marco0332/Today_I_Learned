## 2019-04-21 Day 1
  
> <h4>`프로그램은 데이터를 가공해서 서비스를 제공하는 것`  </h4>
> <h4>`서비스의 핵심은 데이터다`  </h4>
  
### # 데이터
프로그램에서 데이터가 중요하다고 하는데,  
그러면 어떤 데이터를 저장해야하는가?  
  
- 나중에 찾아 쓸 데이터
- 재사용 해야하는 것
- 컴퓨터를 껏다가 켜도 있어야 하는 것  
  
  
#### 데이터를 관리함에 있어 놓치지 말아야할 데이터의 특징 4가지  
1. 찾기 편해야 한다.  
  -> 찾기 편해야 찾아쓰기 편하므로. 그러면 어떻게 저장해야할까?
2. 데이터는 양이 늘어난다.  
  -> 어떤 회원이 탈퇴했다는 정보조차도 추가되는 데이터가 될 수 있다.
3. 데이터는 변한다.  
  -> 변화를 잘 적용할 수 있어야 한다.
4. 데이터 자체에는 순서가 없다.  
  -> 정렬을 위해 사람이 데이터에서 순서의 기준을 정할 뿐. 부모-자식 관계라는 것이 데이터 자체에는 없다.  
  
  
***
### # 데이터 구조
#### 1. HDB(Hierarchical Database, 계층형 데이터베이스)  
![image](https://user-images.githubusercontent.com/27988544/56466020-82b60a00-6445-11e9-8dae-b4b860fac238.png)  
  
- 트리형 구조
- 조회를 하기 위해서는 항상 부모노드부터 타고 내려와야 함.
- '데이터는 변화한다'라는 특징에 Bad.  
- 입력/수정/삭제 마자 정렬이 필요하다(시간 소모)  
- 관계 재설정이 어렵다.  
  
#### 2. NDB(Network Database, 네트워크 데이터베이스)  
![image](https://user-images.githubusercontent.com/27988544/56466024-9497ad00-6445-11e9-83b9-5ff8e1e3914c.png)  
  
- 그래프형 구조  
- 데이터들을 그룹으로 묶음(Entity).  
- 그 그룹들의 연결구조를 Interface라고 함.  
- Interface를 정의하기 어려움. (손이 너무 많이 필요)  
- 데이터 자체에는 순서나 높낮이가 없지만, 의미상(논리적으로) 데이터들의 집합을 만들 수 있다는 것에서 나온 모델.  
  
#### 3. RDB(Relational Database, 관계형 데이터베이스)  
![image](https://user-images.githubusercontent.com/27988544/56466107-f4db1e80-6446-11e9-8258-2b537739959e.png)  
  
- 테이블들이 Tree를 구성  
- HDB와 NDB의 장점을 결합.  
- 대표적으로 Oracle  
- 하나의 객체(Entity)는 여러 데이터들을 가지고 있을 수 있음(ex. 학생 {학번, 성별, 이름, 전공 ...})  
![image](https://user-images.githubusercontent.com/27988544/56466133-6ca94900-6447-11e9-8a10-ea43447d7597.png)  
  
그런데 보통 Entity는 여러개를 만들기 위한 툴이고, 우리는 이것을 '찾아'써야 한다.  
따라서 이 Entity들을 구별하기 위한 '식별자'가 필요하다.  
이때 , 식별자는 Entity를 찾을 수 있어야하므로 '고유(Unique)'해야 한다.  
그래서 1개 이상의 속성을 식별자로 사용할 수 있다.  
  
  `TODO. 기초속성과 설계속성에 대해 알아보기`  
  
  
***
### # 모델  
Entity간에 관계를 어떻게 맺을까? -> 모델 설계  
Entity는 Table(논리적 집합), Attribute는 Column(설계 데이터)  
  
![image](https://user-images.githubusercontent.com/27988544/56466471-8baad980-644d-11e9-808b-fa6fec4510b1.png)  
관계는 Column을 공유함으로써 만들 수 있다.
이때 식별자는 PK(Primary key),  
부모로부터 자식으로 전이된 Column을 FK(Foreign key)라고 함.  
각 Entity의 속성 중 고유값을 가져오는 방식으로 관계를 연결함.  
  
여기서 N:M의 관계는 PK가 위배되기 때문에 1:N:1 관계로 만든다.  
  
Q RDB가 성공한 이유?  
- DBMS에 '통신'기능을 넣었고, DB를 별도의 서버로 운영하는 것이 가능해졌다.  (Standalone Database)  
- SQL(Standard Query Language, 구조화 쿼리 언어)를 만들어서 DBMS에 접근하는 API를 통일시켰다.  
즉, SQL만 알면 어떤 DBMS든 접근 가능하다!  
  
=> 각 언어를 DBMS, SQL과 연결시켜줄 Driver가 필요하다. Java는 JDBC.  