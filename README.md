# 😄 프로젝트 소개
내일배움캠프 Spring_5기 트랙의 **[ 스프링 입문 주차 - 일정 관리 앱 Develop ]** 과제에 대한 README.md 문서입니다.

## **[ Level 0 ]**
  
### **1. API 명세서**

#### 1-1)  User

|   Description   |   Mapping Type   |   REST API   |    Request   |  Response  |  StatusCode  |
|-----------------|------------|------------------|-------------------------------------|------------------------------------|--------------|
|    회원 생성      |    POST    |  /users/signup  |  { <br/>&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;“email" : ”이메일1” <br/> } | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;“email" : ”이메일1” <br/>} | 201 Created |
|  전체 회원 조회  |    GET    |  /users  |  - | [<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;"email" : ”이메일1” <br/>&nbsp;&nbsp;},<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“name” : ”이름2”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;"email" : ”이메일2” <br/>&nbsp;&nbsp;}<br/>] | 200 OK |
|  회원 단일 조회  |    GET    |  /users/{id}  |  /users/2  | { <br/> &nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;“name” : ”이름2”, <br/> &nbsp;&nbsp;“email" : ”이메일2” <br/>} | 200 OK  |
| 회원 수정 |  PUT  |  /users/{id}  |  /users/1<br/><br/>{ <br/>&nbsp;&nbsp;“name" : ”수정된 이름”, <br/>&nbsp;&nbsp;“email” : ”수정된 이메일” <br/> }  | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“name” : ”수정된 이름”, <br/> &nbsp;&nbsp;“email" : ”수정된 이메일” <br/>} |  200 OK  |
| 회원 삭제 | DELETE |  /users/{id}  |  /users/1  | - |  200 OK  |

<br>

#### 1-2) Schedule

|   Description   |   Mapping Type   |   REST API   |    Request   |  Response  |  StatusCode  |
|-----------------|------------|------------------|-------------------------------------|------------------------------------|--------------|
|    일정 생성      |    POST    |  /schedules  |  { <br/>&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;“content” : ”일정1” <br/> } | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;“content” : ”일정1” <br/>} | 201 Created |
|  일정 전체 조회  |    GET    |  /schedules  |  - | [<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“content” : ”일정1” <br/>&nbsp;&nbsp;},<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“userName” : ”이름2”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;“title" : ”제목2”, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“content” : ”일정2” <br/>&nbsp;&nbsp;}<br/>] | 200 OK |
|  일정 단일 조회  |    GET    |  /schedules/{id}  |  /schedules/2  | { <br/> &nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;“userName” : ”이름2”, <br/> &nbsp;&nbsp;“title" : ”제목2”, <br/>&nbsp;&nbsp;“content” : ”일정2” <br/>} | 200 OK  |
| 일정 수정 |  PUT  |  /schedules/{id}  |  /schedules/1<br/><br/>{ <br/>&nbsp;&nbsp;“title" : ”수정된 제목”, <br/>&nbsp;&nbsp;“content” : ”수정된 일정” <br/> }  | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“userName” : ”이름”, <br/> &nbsp;&nbsp;“title" : ”수정된 제목”, <br/>&nbsp;&nbsp;“content” : ”수정된 일정” <br/>} |  200 OK  |
| 일정 삭제 | DELETE |  /schedules/{id}  |  /schedules/1  | - |  200 OK  |

<br>

#### 1-3) Login

- 단, 회원 생성이 우선시 되어야 한다.

|   Description   |   Mapping Type   |   REST API   |    Request   |  Response  |  StatusCode  |
|-----------------|------------|------------------|-------------------------------------|------------------------------------|--------------|
|    일정 생성      |    POST    |  /auth/login  |  { <br/>&nbsp;&nbsp;“email” : ”이메일1”, <br/> &nbsp;&nbsp;“password" : ”비밀번호1”<br/> } | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“name” : ”이름1”, <br/>&nbsp;&nbsp;“email" : ”이메일1” <br/>} | 200 OK |

<br><br>

### **2. ERD**

<img width="643" alt="스크린샷 2025-02-09 오후 7 33 16" src="https://github.com/user-attachments/assets/3046e752-7654-4a2e-a9f0-0b5d4f33714b" />

<br>
<br>
<br>
  
## **[ Level 1 ]**

**필수 과제**

  - **일정 생성(일정 작성하기)**

    - `POST` 사용.
      
    - `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일`을 입력 후, DB에 저장.
      
    - `작성/수정일`은 날짜와 시간을 모두 포함한 형태.
      
      - **JAVA** : LocalDateTime
        
      - **MySQL** : DATETIME(6)
        
      - `Jpa Auditing` 활용하여 DB에 자동 저장  - `BaseEntity` 사용.
        
        - `생성일`인 `createDate`는 `updatable=false`로 설정.
        
        - `수정일`인 `modifiedDate`는 `updatable=true`로 설정하여 엔터티가 수정될 때마다 시간 갱신.
      
    - 각 일정의 `고유 식별자(ID)`를 자동으로 생성하여 관리.
      
      - id AUTO_INCREMENT PRIMARY KEY
    

<br>
      
  - **전체 일정 조회(저장된 일정을 모두 불러오기)**

    - `GET` 사용.

    - **배열 + JSON** 형식으로 결과 반환.

<br>
  
  - **선택 일정 조회(특정 일정 정보 불러오기)**
    
    - `GET` 사용.
      
    - 선택한 단건의 일정을 조회.
      
    - `고유 식별자(ID)`를 사용하여 특정한 일정 조회.

<br>

  - **선택한 일정 수정**
    
    - `PUT` 사용.

    - 일정을 작성한 `유저의 정보`와 일정의 `제목`, `내용`을 수정. - User 엔터티와 매핑

    - 일정의 `고유 식별자(ID)`와 함께, JSON 형식의 요청을 전송.

<br>
      
  - **선택한 일정 삭제**
    
    - `DELETE` 사용.
      
    - `고유 식별자(ID)`를 사용하여 특정한 일정 삭제.
   

<br><br>


## **[ Level 2 ]**

**필수 과제**

  - **유저 생성(유저 생성하기)**

    - `POST` 사용.
      
    - `작성 유저명`, `이메일`, `작성일`, `수정일`을 입력 후, DB에 저장.
      
    - `작성/수정일`은 날짜와 시간을 모두 포함한 형태.
      
      - **JAVA** : LocalDateTime
        
      - **MySQL** : DATETIME(6)
        
      - `Jpa Auditing` 활용하여 DB에 자동 저장 - `BaseEntity` 사용.
        
        - `생성일`인 `createDate`는 `updatable=false`로 설정.
          
        - `수정일`인 `modifiedDate`는 `updatable=true`로 설정하여 엔터티가 수정될 때마다 시간 갱신.
      
    - 각 유저의 `고유 식별자(ID)`를 자동으로 생성하여 관리.
      
      - id AUTO_INCREMENT PRIMARY KEY

<br>
      
  - **전체 유저 조회(저장된 유저를 모두 불러오기)**

    - `GET` 사용.

    - **배열 + JSON** 형식으로 결과 반환.

<br>
  
  - **선택 유저 조회(특정 유저 정보 불러오기)**
    
    - `GET` 사용.
      
    - 선택한 특정 유저의 정보를 조회.
      
    - 유저의 `고유 식별자(ID)`를 사용하여 조회.

<br>

  - **선택한 유저 수정**
    
    - `PUT` 사용.

    - 유저의 `이름`과 `이메일`을 수정.

    - 유저의 `고유 식별자(ID)`와 함께, JSON 형식의 요청을 전송.

<br>
      
  - **선택한 유저 삭제**
    
    - `DELETE` 사용.
      
    - 유저의 `고유 식별자(ID)`를 사용하여 삭제.
   

<br><br>


## **[ Level 3 ]**

**필수 과제**

  - 유저에 비밀번호 필드를 추가.

    - **private String password;**
   


<br><br>


## **[ Level 4 ]**

**필수 과제**

  - **Cookie/Session**을 활용해 로그인 기능을 구현.
  
  - **@Configuration**과  **@Bean** 어노테이션을 활용한 필터 등록 및 해당 필터를 활용하여 `WHITE_LIST`에 존재하는 URL만 실행.

  - `이메일`과 `비밀번호`를 활용해 로그인 기능을 구현.

    1. 회원 생성을 우선적으로 시행.
      
    2. 로그인 요청 정보(`이메일`과 `비밀번호`)가 기존에 생성된(DB에 저장된) 회원의 `이메일`, `비밀번호`와 일치하면 로그인 성공(200 OK 반환).
   
    3. `이메일` 또는 `비밀번호`가 기존 회원의 `이메일`, `비밀번호`와 일치하지 않는다면 `HTTP Status code 401`을 반환.
   

<br><br>


  
---

# 💻 개발 기간(리팩토링 기간은 제외)

- **controller 패키지**
  - **ScheduleController.java** : 25.02.08 ~ 25.02.08 (1일 미만)
  - **UserController.java** : 25.02.08 ~ 25.02.08 (1일 미만)

  <br>
  
- **service 패키지**
  - **ScheduleService.java + User 객체 연동** : 25.02.08 ~ 25.02.09 (1일)
  - **UserService.java** : 25.02.08 ~ 25.02.08 (1일 미만)

  <br>
  
- **repository 패키지**
  - **ScheduleRepository.java** : 25.02.08 ~ 25.02.08 (1일 미만)
  - **UserRepository.java** : 25.02.08 ~ 25.02.08 (1일 미만)

  <br>
  
- **entity 패키지**
  - **BaseEntity.java** : 25.02.09 ~ 25.02.09(1일 미만)
  - **Schedule.java  + User 객체 연동** : 25.02.08 ~ 25.02.09 (1일)
  - **User.java** : 25.02.08 ~ 25.02.08 (1일 미만)
  
  <br>
  
- **dto 패키지**
  - **schedule 패키지**
    - **ScheduleRequestDto.java** : 25.02.08 ~ 25.02.08 (1일 미만)
    - **ScheduleResponseDto.java** : 25.02.08 ~ 25.02.08 (1일 미만)
   
  - **user 패키지**
    - **UserRequestDto.java** : 25.02.08 ~ 25.02.08 (1일 미만)
    - **UserResponseDto.java** : 25.02.08 ~ 25.02.08 (1일 미만)
