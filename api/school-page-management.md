# 학교 페이지 관리 서비스 API

## Host
- **Base URL**: `http://localhost:8080`

## APIs
### 학교 페이지 생성 API
- Endpoint: `/api/v1/school-pages`  
- Method: `POST`

Request
- Headers: `Content-Type: application/json`
- Body Parameters
  - `administratorId`: 관리자 ID
  - `location`: 지역
  - `name`: 이름
- Body Example
```json
{
  "administratorId": 1,
  "location": "지역",
  "name": "학교"
}
```

Response
- `201 Created`
```json
{
  "administratorId": 0,
  "id": 0,
  "location": "string",
  "name": "string"
}
```

### 학교 페이지 조회 API
- Endpoint: `/api/v1/school-pages`
- Method: `GET`

Request
- Request Parameters
  - `page`: 조회할 페이지
  - `size`: 조회할 
  - `sort`: 

Response
- `200 OK`
```json
{
    "content": [
        {
            "id": 1,
            "administratorId": 1,
            "location": "중구",
            "name": "학교"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 1,
    "empty": false
}
```

### 관리자 생성 API
- Endpoint: `/api/v1/administrators`
- Method: `POST`

Request
- Headers: `Content-Type: application/json`
- Body Parameters
  - `name`: 생성할 관리자 이름

Response
- `201 CREATED`
```json
{
  "id": 1,
  "name": "관리자"
}
```

### 특정 학교 페이지에 대한 관리자의 권한 체크
- Endpoint: `/api/v1/permissions/check`
- Method: `GET`

Request
- Request Parameters
  - `schoolPageId`: 학교 페이지 ID
  - `administratorId`: 관리자 ID

Response
- `200 OK`
```json
true
```
