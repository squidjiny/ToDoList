# TodoList README 작성

상태: 진행 중  
작성일시: 2023년 8월 30일 오후 9:53

## 설명

---

Spring boot 연습용으로 만든 투두리스트입니다.

연습용 이기 때문에 쓸데없는 기능을 많이 넣을 예정입니다.

---

## API 명세서

-유저 API

| INDEX | HTTP METHOD | URI(end point) | Description | Request Parameters | Response Parameters | HTTP Status | etc. |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | GET | /users | 유저 전체 표기 | 없음 |  |  |  |
| 2 | GET | /users/{user_id} | 유저 검색 | user_id: 검색하고싶은 유저의 아이디 |  |  |  |
| 3 | GET | /users/friend_list/{user_id} | 친구 목록 조회 | user_id |  |  |  |
| 4 | POST | /sign-api/sign-up | 회원가입 | 유저 이름,  유저 이메일 비밀번호, |  |  |  |
| 5 | DELETE | /users/register_out/{user_id} | 회원탈퇴 | 유저 아이디, 유저 비밀번호 |  |  | 미구현 |
| 6 | POST | /sign-api/sign_in | 로그인 | 유저 아이디, 유저 비밀번호 |  |  |  |
| 7 | PUT | /users/{user_id} | 유저정보 수정 | 유저 아이디, 유저 비밀번호 |  |  |  |

---

-투두리스트 API

| INDEX | HTTP METHOD | URI(end point) | Description | Request Parameters | Response Parameters | HTTP Status | etc. |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | GET | /TODO/{today} | 오늘 할 일 조회 | todo의 start날짜와 end 날짜의 사이에 오늘의 날짜가 있는가. |  |  |  |
| 2 | GET | /TODO | 할일 전체 조회 | 없음. |  |  |  |
| 3 | GET | /TODO/{todo_id} | 중요한 일 조회  | 중요한 일 여부에 체크가 되어 있는가.(bool) |  |  |  |
| 4 | GET | /TODO/{title} | 할 일 검색(제목) | title: 검색하고 싶은 제목 |  |  |  |
| 5 | GET | /TODO/{discription} | 할 일 검색(설명) | discription: 설명들 안에서 검색하고 싶은 내용 |  |  |  |
| 6 | GET | /TODO/{word} | 할 일 검색 (전체 검색) | word: 제목+내용에서 검색하고 싶은 것 |  |  | 미구현 |
| 7 | POST | /TODO/{user_id} | 할 일 생성 | user_id: 투두리스트를 만드려고 하는 유저의 아이디 |  |  |  |
| 8 | PUT | /TODO/{todo_id} | 할 일 수정 | todo_id: 투두리스트의 아아디 |  |  |  |
| 9 | DELETE | /TODO/{todo_id} | 할 일 삭제 | todo_id |  |  |  |

---

-친구 API(미구현)

| INDEX | HTTP METHOD | URI(end point) | Description | Request Parameters | Response Parameters | HTTP Status | etc. |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | POST | /friends/add/{user_id1}/{user_id2} | 친구 추가 | user_id1: 추가하는 친구 user_id2: 추가받는 친구 |  |  |  |
| 2 | GET | /friends | 친구 전체 조회 |  |  |  |  |
| 3 | GET | /friends/{friends_id} | 친구 검색 | 친구 아이디 |  |  |  |
| 4 | DELETE | /friends/{friends_id} | 친구 삭제 |  |  |  |  |

---

## 다음 프로젝트에서 개선해야 할 점

1. 깃 커밋 규칙 정하기
    
    버그 수정인지, 내용 추가인지 구분이 되어있지 않아서 구분에 어려움이 있음.  
    
2. 참고한 사이트 정리하기
    
    후에 다시 공부할 때 곤란함.
    
3. 일관성 있게 코드 짜기
    
    의존성 주입 같은 구현 방법이 여러가지인 경우 일관성 있게 하나만 사용해서 코드 짜기 
    
4. 변수 생각 없이 짜지 말기
    
    제발…

5. HTTP Method, Status 공부하기

    왜 PUT, PATCH 중 PUT을 선택했는 지  
    상태코드는 어떤걸 쓰는게 맞는 지 (200, 201, 204, 400, 401...)
    
