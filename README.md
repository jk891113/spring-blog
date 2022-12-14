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

## 회원가입, 로그인 기능이 추가된 블로그 백엔드 서버 만들기

___

## 주의사항
     - 요구사항에 맞게 추가되어야 하는 Entity를 설계하고 ERD를 만들어 볼 것
     - 입문 강의 개인 과제에 회원가입, 로그인 기능을 추가하고 기존 요구사항의 일부를 변경

## 새로운 요구사항
### 1. 회원가입 API
     - username, password를 Client에서 전달받기
     - username은 `최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자`로 구성
     - password는 `최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자`로 구성
     - DB에 중복된 username이 없다면 회원을 저장하고 Client로 성공 메시지, 상태코드 반환
### 2. 로그인 API
     - username, password를 Client에서 전달받기
     - DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
     - 로그인 성공시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고, 
       발급한 토큰을 Header에 추가하고 성공 메시지, 상태코드 반환

## 기존 요구사항
### 1. 전체 게시글 목록 조회 API
     - 제목, 작성자명(`username`), 작성내용, 작성날짜를 조회하기
     - 작성날짜 기준 내림차순으로 정렬하기
### 2. 게시글 작성 API
     - `토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능`
     - `제목, 작성자명(username), 작성 내용을 저장하고`
     - 저장된 게시글을 Client로 반환
### 3. 선택한 게시글 조회 API
     - 선택한 게시글의 제목, 작성자명(`username`), 작성날짜, 작성내용을 조회하기
       (검색 기능 아님. 간단한 게시글 조회만 구현)
### 4. 선택한 게시글 수정 API
     - <s>수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치여부를 확인한 후</s>
     - `토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능`
     - `제목, 작성내용을 수정하고` 수정된 게시글을 Client로 반환
### 5. 선택한 게시글 삭제 API
     - <s>삭제를 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치여부를 확인한 후</s>
     - `토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능`
     - 선택한 게시글을 삭제하고 Client로 성공 메시지, 상태코드 반환