# -📝 아래의 문제를 토대로 작성한 코드
(간이) 코딩 테스트 문제
💡 아래 스키마를 보고 게시물 태그 정보가 포함된 게시판(POST) 엔티티에 대한
CRUD 자바 코드를 작성하시오.

조건-------------------------------------
Controller, Service, Repository 계층 분리
JPA 또는 MyBatis
JPA의 경우 Entity 간 연관관계가 드러나도록, MyBatis의 경우 SQL문 포함 요망
Lombok (선택)
실제 구동은 확인하지 않을 것임, 구조와 로직 위주
코드는 잘 정돈된 형태로 작성해주세요. (주석 = 선택+)
![image](https://github.com/leehyun23/-/assets/141125173/3efb0e75-e9d0-420c-91d5-94cca1a15673)


테이블 정보--------------------------------
게시판 분류 정보 (BOARD_DEF) → (연관관계를 명확히 하기 위해 별도로 표시함.)
논리명 물리명 자료형 DEF
게시판(분류)코드 BOARD_CD VARCHAR (PK)
게시판(분류)이름 BOARD_NM VARCHAR
게시물 (POST)
논리명 물리명 자료형 DEF
글번호 POST_NO INT (PK)
게시판(분류)코드 BOARD_CD VARCHAR (PK, FK)
제목 POST_SJ VARCAHR
(간이) 코딩 테스트 문제 2
논리명 물리명 자료형 DEF
내용 POST_CN VARCHAR
작성자ID REGSTR_ID VARCHAR
작성일시 REG_DT DATETIME
태그 (TAG)
논리명 물리명 자료형 DEF
태그ID TAG_NO INT (PK)
태그 TAG VARCHAR
게시판(분류)코드 BOARD_CD VARCHAR (FK)
게시물 태그 (POST_TAG)
논리명 물리명 자료형 DEF
게시물 태그ID BOARD_TAG_ID INT (PK)
글번호 POST_NO INT (FK)
게시판(분류)코드 BOARD_CD VARCHAR (FK)
태그ID TAG_ID INT (FK
