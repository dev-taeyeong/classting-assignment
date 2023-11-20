# 개요

이 프로젝트는 학교 소식을 전달하고 받아보는 '학교소식 뉴스피드'를 위한 백엔드 시스템을 구현합니다.

# 요구사항

- [x] 학교 관리자는 학교 페이지를 운영하여 학교 소식을 발행할 수 있다.
  - [x] 학교 관리자는 지역, 학교명으로 학교 페이지를 생성할 수 있다.
  - [x] 학교 관리자는 학교 페이지 내에 소식을 작성할 수 있다.
  - [x] 학교 관리자는 작성된 소식을 삭제할 수 있다.
  - [x] 학교 관리자는 작성된 소식을 수정할 수 있다.
- [x] 학생은 학교 페이지를 구독하여 학교 소식을 받아 볼 수 있다.
  - [x] 학생은 학교 페이지를 구독할 수 있다.
  - [x] 학생은 구독 중인 학교 페이지 목록을 확인할 수 있다.
  - [x] 학생은 구독 중인 학교 페이지를 구독 취소할 수 있다.
  - [x] 학생은 구독 중인 학교 페이지별 소식을 볼 수 있다. (학교별 소식은 최신순으로 노출한다.)
- [x] 학생은 구독 중인 학교 소식을 자신의 뉴스피드에서 모아볼 수 있다.
  - 구독중인 모든 학교의 소식을 모아볼 수 있어야 한다.
  - 학교 소식이 노출되는 뉴스피드는 최신순으로 소식을 노출한다.
  - 학교 페이지를 구독하는 시점 이후 소식부터 뉴스피드를 받는다.
  - 학교 페이지 구독을 취소해도 기존 뉴스피드에 나타난 소식은 유지한다.

# 아키텍처

시스템은 다음 4개의 마이크로 서비스로 구성됩니다.

- 학교 페이지 관리 서비스 (`SchoolPageManagementService`)
- 학교 소식 발행 서비스 (`SchoolNewsPublishingService`)
- 학생 구독 관리 서비스 (`StudentSubscriptionService`)
- 뉴스피드 서비스 (`NewsfeedService`)

서비스들은 Kafka를 사용한 이벤트 기반 통신과 HTTP를 통한 동기적 방식을 혼합하여 사용합니다.

# 기능

- 학교 페이지 관리: 학교 관리자가 학교 정보를 바탕으로 페이지를 생성, 수정, 삭제할 수 있습니다.
- 소식 발행: 학교 관리자는 학교 관련 소식을 학교 페이지에 게시할 수 있습니다.
- 학생 구독 관리: 학생들은 관심 있는 학교 페이지를 구독하고 구독을 취소할 수 있습니다.
- 뉴스피드: 학생들은 자신이 구독한 학교 페이지의 소식을 뉴스피드 형태로 확인할 수 있습니다.

# 구동 방법 및 확인 절차

## 프로젝스 실행
1. 프로젝트 루트 디렉토리로 이동: `docker-compose.yml` 파일이 위치한 디렉토리입니다.
2. **Docker Compose** 실행: 다음 명령어를 사용하여 프로젝트의 모든 컨테이너를 백그라운드 모드에서 실행합니다.
```bash
$ docker-compose up -d
```

## 프로젝트 종료
1. **Docker Compose** 종료: 프로젝트를 종료하려면 다음 명령어를 사용합니다.
```bash
$ docker-compose down
```

## 확인 절차
- 학교 관리자 생성
```bash
$ curl -X POST http://localhost:8080/api/v1/administrators \
       -H "Content-Type: application/json" \
       -d '{"name": "관리자"}' | jq .
```

- 학교 페이지 생성 (학교 관리자는 지역, 학교명으로 학교 페이지를 생성할 수 있다.)
```bash
# 등록된 모든 학교 페이지 조회
$ curl http://localhost:8080/api/v1/school-pages | jq .

# 지역, 학교명으로 학교 페이지 생성
$ curl -X POST http://localhost:8080/api/v1/school-pages \
       -H "Content-Type: application/json" \
       -d '{"administratorId": 1, "location": "중구", "name": "신당중학교"}' | jq .

# 학교 페이지 생성 검증
$ curl http://localhost:8080/api/v1/school-pages | jq .
```

- 학교 소식 작성 (학교 관리자는 학교 페이지 내에 소식을 작성할 수 있다.)
```bash
# 등록된 모든 학교 소식 조회
$ curl http://localhost:8081/api/v1/news-posts | jq .

# 학교 소식 생성
$ curl -X POST http://localhost:8081/api/v1/news-posts \
       -H "Content-Type: application/json" \
       -d '{"administratorId": 1, "schoolPageId": 1, "title": "소식 생성", "content": "내용입니다"}' | jq .

# 학교 소식 생성 검증
$ curl http://localhost:8081/api/v1/news-posts | jq .
```

- 학교 소식 삭제 (학교 관리자는 작성된 소식을 삭제할 수 있다.)
```bash
# 등록된 모든 학교 소식 조회
$ curl -X http://localhost:8081/api/v1/news-posts | jq .

# '테스트 ID 1' ID를 가진 학교 소식 삭제
$ curl -X DELETE http://localhost:8081/api/v1/news-posts/테스트 ID 1 | jq .

# 학교 소식 삭제 검증
$ curl -X http://localhost:8081/api/v1/news-posts | jq .
```

- 학교 관리자는 작성된 소식을 수정할 수 있다.
```bash
# 등록된 학교 소식 조회
$ curl -X http://localhost:8081/api/v1/news-posts | jq .

# 등록된 학교 소식 수정
$ curl -X PUT http://localhost:8081/api/v1/news-posts/{newsPostId} \
       -H "Content-Type: application/json" \
       -d '{"administratorId": 1, "newTitle": "새 제목", "newContent": "새 내용"}' | jq .

# 학교 소식 수정 검증
$ curl -X http://localhost:8081/api/v1/news-posts | jq .
```

- 학생은 학교 페이지를 구독할 수 있다.
```bash
# 학생의 학교 페이지 구독 목록 조회
$ curl http://localhost:8082/api/v1/students/1/subscriptions | jq .

# 학교 페이지 구독
$ curl -X POST http://localhost:8082/api/v1/school-pages/4/subscriptions \
       -H "Content-Type: application/json" \
       -d '{"studentId": 1}' | jq .

# 학교 페이지 구독 검증
$ curl http://localhost:8082/api/v1/students/1/subscriptions | jq .
```

- 학생은 구독 중인 학교 페이지 목록을 확인할 수 있다.
```bash
# 학생의 학교 페이지 구독 목록 조회
$ curl http://localhost:8082/api/v1/students/1/subscriptions | jq .
```

- 학생은 구독 중인 학교 페이지를 구독 취소할 수 있다.
```bash
# 학생의 학교 페이지 구독 목록 조회
$ curl http://localhost:8082/api/v1/students/1/subscriptions | jq .

# 학생의 학교 페이지 구독 취소
$ curl -X DELETE http://localhost:8082/api/v1/school-pages/4/subscriptions?studentId=1

# 학교 페이지 구독 취소 검증
$ curl http://localhost:8082/api/v1/students/1/subscriptions | jq .
```

- 학생은 구독 중인 학교 페이지별 소식을 볼 수 있다.
```bash
# 구독 중인 학교 페이지별 소식 조회
$ curl http://localhost:8081/api/v1/news-posts/by-school-page-id/1?studentId=1 | jq .

$ curl http://localhost:8081/api/v1/news-posts/by-school-page-id/2?studentId=1 | jq .
```

- 학생은 구독 중인 학교 소식을 자신의 뉴스피드에서 모아볼 수 있다.
```bash
# 구독 중인 학교 소식을 자신의 뉴스피드에서 조회
$ curl http://localhost:3000/api/v1/newsfeeds/by-student-id/1?page=0&size=20 | jq .
```
