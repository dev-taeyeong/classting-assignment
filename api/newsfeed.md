# 뉴스피드 서비스 API

## Host
- **Base URL** `http://localhost:3000`

## APIs
### 뉴스피드 조회 API
- Endpoint: `/api/v1/newsfeeds/by-student-id/{studentId}`
- Method: `GET`

Request
- Path Parameters
  - `studentId`: 학생 ID
- Request Parameters
  - `page`: 조회할 페이지
  - `size`: 조회할 갯수

Response
- `200 OK`
```json
```