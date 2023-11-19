# 학교 소식 발행 서비스 API

## Host
- **Base URL**: `http://localhost:8081`

## APIs
### 소식 생성 API
- Endpoint: `/api/v1/news-posts`
- Method: `POST`

Request
- Headers: `Content-Type: application/json`
- Body Parameters
  - `administratorId`: 관리자 ID (Long)
  - `schoolPageId`: 학교 페이지 ID (Long)
  - `title`: 생성할 소식의 제목 (String)
  - `content`: 생성할 소식의 내용 (String)
- Body Example
```json
{
    "administratorId": 1,
    "schoolPageId": 1,
    "title": "제목",
    "content": "내용"
}
```

Response
- `201 Created`
```json
{
    "id": "id",
    "schoolPageId": 1,
    "title": "제목",
    "content": "내용"
}
```

### 소식 삭제 API
- Endpoint: `/api/v1/news-posts/{newsPostId}`
- Method: `DELETE`

Request
- Path Parameters
  - `newsPostId`: 삭제할 소식 ID (String)

Response
- `204 No content`
```json
"id"
```

### 지정된 ID 목록에 해당하는 소식 목록 조회 API
- Endpoint: `/api/v1/news-posts/by-ids`
- Method: `GET`

Request
- Request Parameters
  - `newsPostIds`: 조회할 소식 ID 목록 (String) `ex) "?newsPostIds=id1,id2,id3`

Response
- `200 OK`
```json
[
    {
        "id": "id1",
        "schoolPageId": 1,
        "title": "title1",
        "content": "content1"
    },
    {
        "id": "id2",
        "schoolPageId": 1,
        "title": "title2",
        "content": "content2"
    }
]
```

### 소식 업데이트 API
- Endpoint: `/api/v1/news-posts/{newsPostId}`
- Method: `PUT`

Request
- Path Parameters
  - `newsPostId`: 업데이트 할 소식 ID (String)
- Body Parameters
  - `administratorId`: 관리자 ID (Long)
  - `newTitle`: 새 제목 (String)
  - `newContent`: 새 내용 (String)
- Body Example
```json
{
    "administratorId": 1,
    "newTitle": "new title",
    "newContent": "new content"
}
```

Response
- `200 OK`
```json
{
    "id": "id",
    "schoolPageId": 1,
    "title": "new title",
    "content": "new content"
}
```
