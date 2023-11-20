# 학생 구독 관리 서비스 API

## Host
- **Base URL**: `http://localhost:8082`

## APIs
### 학교 페이지 구독 API
- Endpoint: `/api/v1/school-pages/{schoolPageId}/subscriptions`
- Method: `POST`

Request
- Headers: `Content-Type: application/json`
- Path Parameters
  - `schoolPageId`: 학교 페이지 ID
- Body Parameters
  - `studentId`: 학생 ID
- Body Example
```json
{
  "studentId": 1
}
```

Response
- `201 Created`
```json
{
  "id": 1,
  "studentId": 1,
  "schoolPageId": 1
}
```

### 학교 페이지 구독 취소 API
- Endpoint: `/api/v1/school-pages/{schoolPageId}/subscriptions`
- Method: `DELETE`

Request
- Path Parameters
  - `schoolPageId`: 학교 페이지 ID
- Request Parameters
  - `studentId`: 학생 ID

Response
- `204 No content`

### 학생 생성 API
- Endpoint: `/api/v1/students`
- Method: `POST`

Request
- Headers: `Content-Type: application/json`
- Body Parameters
  - `name`: 생성할 학생 이름
- Body Example
```json
{
  "name": "정태영"
}
```

Response
- `201 Created`
```json
{
  "id": 1,
  "name": "정태영"
}
```

### 학생 구독 목록 조회 API
- Endpoint: `/api/v1/school-pages/{studentId}/subscriptions`
- Method: `GET`

Request
- Path Parameters
  - `studentId`: 학생 ID

Response
```json
[
    {
        "id": 3,
        "administratorId": 3,
        "location": "천호",
        "name": "천호중학교"
    },
    {
        "id": 4,
        "administratorId": 4,
        "location": "부산",
        "name": "부산초등학교"
    }
]
```

### 학생의 학교 페이지 구독 여부 조회 API
- Endpoint: `/api/v1/students/{studentId}/subscription-status`
- Method: `GET`

Request
- Path Paramters
  - `studentId`: 학생 ID
- Requset Parameters
  - `schoolPageId`: 학교 페이지 ID

Response
```json
false
```