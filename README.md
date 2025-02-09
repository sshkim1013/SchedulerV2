# 😄 프로젝트 소개
내일배움캠프 Spring_5기 트랙의 **[ 스프링 입문 주차 - 일정 관리 앱 Develop ]** 과제에 대한 README.md 문서입니다.

## **[ Level 0 ]**
  
### **API 명세서**

#### [ User ]

|   Description   |   Mapping Type   |   REST API   |    Request   |  Response  |  StatusCode  |
|-----------------|------------|------------------|-------------------------------------|------------------------------------|--------------|
|    회원 생성      |    POST    |  /users  |  { <br/>&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;“email" : ”이메일1” <br/> } | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;“email" : ”이메일1” <br/>} | 201 Created |
|  전체 회원 조회  |    GET    |  /users  |  - | [<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“name” : ”이름1”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;"email" : ”이메일1” <br/>&nbsp;&nbsp;},<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“name” : ”이름2”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;"email" : ”이메일2” <br/>&nbsp;&nbsp;}<br/>] | 200 OK |
|  회원 단일 조회  |    GET    |  /users/{id}  |  /users/2  | { <br/> &nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;“name” : ”이름2”, <br/> &nbsp;&nbsp;“email" : ”이메일2” <br/>} | 200 OK  |
| 회원 수정 |  PUT  |  /users/{id}  |  /users/1<br/><br/>{ <br/>&nbsp;&nbsp;“name" : ”수정된 이름”, <br/>&nbsp;&nbsp;“email” : ”수정된 이메일” <br/> }  | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“name” : ”수정된 이름”, <br/> &nbsp;&nbsp;“email" : ”수정된 이메일” <br/>} |  200 OK  |
| 회원 삭제 | DELETE |  /users/{id}  |  /users/1  | - |  200 OK  |

<br>

#### [ Schedule ]

|   Description   |   Mapping Type   |   REST API   |    Request   |  Response  |  StatusCode  |
|-----------------|------------|------------------|-------------------------------------|------------------------------------|--------------|
|    일정 생성      |    POST    |  /schedules  |  { <br/>&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;“content” : ”일정1” <br/> } | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;“content” : ”일정1” <br/>} | 201 Created |
|  일정 전체 조회  |    GET    |  /schedules  |  - | [<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“userName” : ”이름1”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;“title" : ”제목1”, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“content” : ”일정1” <br/>&nbsp;&nbsp;},<br/>&nbsp;&nbsp;{ <br/> &nbsp;&nbsp;&nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“userName” : ”이름2”, <br/> &nbsp;&nbsp;&nbsp;&nbsp;“title" : ”제목2”, <br/>&nbsp;&nbsp;&nbsp;&nbsp;“content” : ”일정2” <br/>&nbsp;&nbsp;}<br/>] | 200 OK |
|  일정 단일 조회  |    GET    |  /schedules/{id}  |  /schedules/2  | { <br/> &nbsp;&nbsp;“id” : 2, <br/>&nbsp;&nbsp;“userName” : ”이름2”, <br/> &nbsp;&nbsp;“title" : ”제목2”, <br/>&nbsp;&nbsp;“content” : ”일정2” <br/>} | 200 OK  |
| 일정 수정 |  PUT  |  /schedules/{id}  |  /schedules/1<br/><br/>{ <br/>&nbsp;&nbsp;“title" : ”수정된 제목”, <br/>&nbsp;&nbsp;“content” : ”수정된 일정” <br/> }  | { <br/> &nbsp;&nbsp;“id” : 1, <br/>&nbsp;&nbsp;“userName” : ”이름”, <br/> &nbsp;&nbsp;“title" : ”수정된 제목”, <br/>&nbsp;&nbsp;“content” : ”수정된 일정” <br/>} |  200 OK  |
| 일정 삭제 | DELETE |  /schedules/{id}  |  /schedules/1  | - |  200 OK  |

<br><br>

### **ERD**

<img width="643" alt="스크린샷 2025-02-09 오후 7 33 16" src="https://github.com/user-attachments/assets/3046e752-7654-4a2e-a9f0-0b5d4f33714b" />


<br>
<br>
<br>
  
## **[ Level 1 ]**

**필수 과제**
  - 일정 생성(일정 작성하기)
    - `POST` 사용.
      
    - `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장.
      
    - `작성/수정일`은 날짜와 시간을 모두 포함한 형태.
      - LocalDateTime
      - DATETIME
      
    - 각 일정의 `고유 식별자(ID)`를 자동으로 생성하여 관리.
      
      - id AUTO_INCREMENT PRIMARY KEY
    
    - 최초 입력 시, `수정일`은 `작성일`과 동일.
      
      - this.createDate = LocalDateTime.now();
      - this.modifiedDate = LocalDateTime.now();

      
  - 전체 일정 조회(등록된 일정 불러오기)
    - `GET` 사용.
      
    - 다음 조건을 바탕으로 등록된 일정 목록을 전부 조회.
      
      - `수정일` (형식 : YYYY-MM-DD)
      - `작성자명`
        
    - RequestParam으로 `작성자명(authorName)`과 `수정일(modifiedDate)`만을 전달 가능하도록 구현.

     
  - 선택 일정 조회(선택한 일정 정보 불러오기)
    
    - `GET` 사용.
      
    - 선택한 일정 단건의 정보를 조회.
      
    - 일정의 `고유 식별자(ID)`를 사용하여 조회.

<br>
<br>

## **[ Level 2 ]**

**필수 과제**
  - 선택한 일정 수정
    
    - `UPDATE` 사용.
      
    - 선택한 일정 내용 중 `할일`, `작성자명`만 수정 가능하도록 구현.
      
    - 서버에 일정 수정을 요청할 때, `비밀번호`를 함께 전달하여 비밀번호가 일치하면 수정 가능하도록 구현.
      
    - `작성일`은 변경 불가, 수정 완료 시, 수정한 시점으로 `수정일` 변경.
      
      - `this.modifiedDate = LocalDateTime.now();`

      
  - 선택한 일정 삭제
    
    - `DELETE` 사용.
      
    - 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달하여 비밀번호가 일치하면 수정 가능하도록 구현.
  
---

# 💻 개발 기간(리팩토링 기간은 제외)

- **controller 패키지**
  - **ScheduleController.java** : 25.01.27 ~ 25.01.27 (1일 미만)

  <br>
  
- **service 패키지**
  - **ScheduleService.java** : 25.01.27 ~ 25.01.27 (1일 미만)
  - **ScheduleServiceImpl.java** : 25.01.27 ~ 25.01.27 (1일 미만)

  <br>
  
- **repository 패키지**
  - **ScheduleRepository.java** : 25.01.27 ~ 25.01.27 (1일 미만)
  - **JdbcTemplateScheduleRepository.java + MySQL 연동** : 25.01.29 ~ 25.01.30 (1일)

  <br>
  
- **entity 패키지**
  - **Schedule.java** : 25.01.27 ~ 25.01.27 (1일 미만)
  
  <br>
  
- **dto 패키지**
  - **ScheduleRequestDto.java** : 25.01.27 ~ 25.01.27 (1일 미만)
  - **ScheduleResponseDto.java** : 25.01.27 ~ 25.01.27 (1일 미만)
