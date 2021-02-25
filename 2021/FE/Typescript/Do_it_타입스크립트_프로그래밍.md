## 0. 시작
자바스크립트의 문법을 수용하면서 선택적 정적 타이핑, 클래스 선언, 모듈 지원 등 기능을 추가한 라이브러리.  

- 함수형 설계 방식으로 작성한 코드는 객체지향 방식의 코드보다 간결하고 논리 정연하다.
- 함수형프로그래밍에서는 불변성이라는 원칙에 따라 값이 변하는 것을 최대한 배제하므로 프로그램 검증과 최적화, 그리고 동시에 여러 스레드에서 문제없이 동작하는 프로그램을 쉽게 작성 가능하다.
- 함수를 하나의 값처럼 다룰 수 있어서 재사용이 수월하고, 값을 미리 계산하지 않고 꼭 필요할 때만 계산하므로 메모리 절약과 프로그램의 성능에도 긍정적인 영향을 준다.
- 이 책에서는 선언형 프로그래밍, 함수 조합, 제네릭, 모나드 등 네 가지 방식의 함수형 프로그래밍을 타입스크립트로 구현하는 방법을 다룬다.

## 1. 타입스크립트와 개발 환경 만들기
ESNext 자바스크립트 코드는 바벨(Babel)이라는 트랜스파일러를 거치면 ES5 코드로 변환된다. 바벨과 유사하게 타입스크립트 코드는 TSC(TypeScript compiler)라는 트랜스파일러를 통해 ES5 코드로 변환된다.  
여기서 트랜스파일러란 어떤 프로그래밍 언어로 작성된 코드를 또 다른 프로그래밍 언어로 된 코드로 바꿔주는 프로그램을 의미한다. 트랜스파일러는 텍스트로 된 소스코드를 바이너리 코드로 바꿔주는 컴파일러와 구분하기 위해 생긴 용어이다.  
  
### 타입스크립트 고유의 문법 살펴보기
1. 타입 주석(type annotation)
```typescript
let n: number = 1
let m = 2
```
  - 타입 주석을 통해 변수의 타입을 명시할 수 있다.
  - 타입을 생략하면 우측 값을 분석해서 왼쪽 변수의 타입을 결정한다. 이를 타입 추론(type inference)라고 한다.

2. 인터페이스
```typescript
interface Person {
    name: string
    age?: number
}
let person: Person = { name: "marco0332" }
```

3. 튜플
```typescript
let numberArr: number[] = [1, 2, 3] // 배열
let tuple: [boolean, number, string] = [true, 1, 'OK'] // 튜플
```
  - 튜플은 물리적으로는 배열과 같다. 단, 배열에 저장되는 타입이 모두 같으면 배열, 다르면 튜플이다.

4. 제네릭 타입
```typescript
class Container<T> {
    constructor(public value: T) { }
}
let numberContainer: Container<number> = new Container<number>(1)
let stringContainer: Container<string> = new Container<string>('Hello world')
```
  - 제네릭 타입은 다양한 타입을 한꺼번에 취급할 수 있게 해준다.

5. 대수 타입(abstract data type)
```typescript
type NumberOrString = number | string // union type
type AnimalAndPerson = Animal & Person // intersection type
```
  - ADT란 추상 데이터 타입을 의미하기도 하지만 대수 타입(algebraic data type)이라는 의미로도 사용된다.
  - 대수 타입이란 다른 자료형의 값을 가지는 자료형을 의미한다.
  - 합집합 타입(union 또는 sum type)과 교집합 타입(intersection 또는 product type) 두 가지가 있다.