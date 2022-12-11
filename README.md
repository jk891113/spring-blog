# Spring 입문 강의 개인 과제

## 블로그 백엔드 서버 만들기
___
[![My Skills](https://skillicons.dev/icons?i=java,idea,git,github)](https://skillicons.dev)
___
### 주의사항
     - Entity를 그대로 반환하지 말고, DTO에 담아서 반환
___
## 요구사항
### 1. 아래의 요구사항을 기반으로 Use Case 그려보기
     -
### 2. 전체 게시글 목록 조회 API
     - 제목, 작성자명, 작성내용, 작성날짜를 조회하기
     - 작성날짜 기준 내림차순으로 정렬하기
### 3. 게시글 작성 API
     - 제목, 작성자명, 비밀번호, 작성내용을 저장하고
     - 저장된 게시글을 Client로 반환하기
### 4. 선택한 게시글 조회 API
     - 선택한 게시글의 제목, 작성자명, 작성날짜, 작성내용을 조회하기
       (검색 기능 아님. 간단한 게시글 조회만 구현)
### 5. 선택한 게시글 수정 API
     - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치여부를 확인한 후
     - 제목, 작성자명, 작성내용을 수정하고 수정된 게시글을 Client로 반환하기
### 6. 선택한 게시글 삭제 API
     - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치여부 확인한 후
     - 선택한 게시글을 삭제하고 Client로 성공했다는 표시 반환하기
___
## API 명세서

|                        | Method | URL                      | Request                                                                                      | Response                                                                                                                          |
|------------------------|--------|--------------------------|----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| CreatePosting          | Post   | /posting                 | title<br/>contents<br/>writerName<br/>password | title<br/>writerName<br/>password<br/>contents                  |
| getAllPosting          | GET    | /postings                | -                                                                                            | createdAt<br/>modifiedAt<br/>title<br/>writerName<br/>contents |
| getPostingById         | GET    | /posting/id/{id}         | -                                                                                            | createdAt<br/>modifiedAt<br/>title<br/>writerName<br/>contents |
| getPostingByWriterName | GET    | /posting/writerName/{writerName} | -                                                                                            | createdAt<br/>modifiedAt<br/>title<br/>writerName<br/>contents |
| updatePosting          | PUT    | /posting/{id}/{password} | title2<br/>contents2<br/>writerName2 |                                                                                                                                   |
| deletePosting          | DELETE | /posting/{id}/{password} |                                                                                              |                                                                                                                                   |

___

# Spring 숙련 강의 개인과제 Lv.1

