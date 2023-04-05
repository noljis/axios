# eclipse연동
## 🌿 **axios(비동기) 연습용 과제**

### 🌱 과제 주제 `Emp, Dept 테이블을 이용하여 사원 전체조회, 부서 전체조회, 사원 한명검색 Button 비동기로 활성화 시키기`
#### 🌼 추가해본 것: Like 연산자를 이용한 부분 검색, 예외처리

## - 🌿언어: `Java(JDK 11.0.6)` `JavaScript` `HTML`

## - 🌿DB : `Oracle XE08`

## - 🌿테이블 : `Emp` `Dept`

## - 🌿개발 Tool : `eclipse`

## - 🌿WAS : `Tomcat`

<br><br>
<hr>

## - 🌿실행 화면

<img width="707" alt="스크린샷_20230112_092301" src="https://user-images.githubusercontent.com/117498827/212144326-4e244d17-e5f1-4cbb-b5ac-a2c06bf50a82.png">

### 🌱모든 사원 정보
<img width="692" alt="스크린샷_20230112_092315" src="https://user-images.githubusercontent.com/117498827/212144356-6c09634c-26ec-4c39-b688-989a587e09c1.png">

### 🌱모든 부서 정보
<img width="707" alt="스크린샷_20230112_092326" src="https://user-images.githubusercontent.com/117498827/212144367-55398c94-9b80-4e1c-a8d3-6975a53e3df3.png">

### 🌱사원번호 전체검색
<img width="704" alt="스크린샷_20230112_092342" src="https://user-images.githubusercontent.com/117498827/212144376-b3320c49-d2af-4407-b0ac-2d52ce5e6bca.png">

### 🌱사원번호 부분검색
<img width="705" alt="스크린샷_20230112_092406" src="https://user-images.githubusercontent.com/117498827/212144385-1bddad3b-b416-4c82-8e47-bd16fc31c134.png">

## - 🌿예외 처리

<br>
🫤정상 실행시의 데이터는 JSONObject로 Object타입을 반환하지만, 예외 발생시 Object가 아닌 errorMsg의 String타입을 반환하도록 함
<br>
-> 이것을 이용해서 여러 경우의 예외를 처리해 주었음

<img width="704" alt="스크린샷_20230112_092631" src="https://user-images.githubusercontent.com/117498827/212144484-5270c9d9-8c81-41b3-8160-275dcab73882.png">
<img width="711" alt="스크린샷_20230112_092731" src="https://user-images.githubusercontent.com/117498827/212144489-465a11a0-7bbe-402a-8947-89b43036258b.png">
