## 📘 API Documentation.

📘 API Documentation

🔗 Base URL
http://localhost:8080/api

📨 Endpoint: POST /api/process

Processes a user's first name and surname, validates inputs, and returns the full name.

🔒 Request Headers

| Name      | Type   | Required | Description                       |
| --------- | ------ | -------- | --------------------------------- |
| `traceId` | String | No       | Optional UUID for request tracing |

If not provided, a random traceId will be generated.


📥 Request Body
json
{
  "name": "Animesh",
  "surname": "Jain"
}
| Field     | Type   | Required | Validation | Description        |
| --------- | ------ | -------- | ---------- | ------------------ |
| `name`    | String | Yes      | Not blank  | First name of user |
| `surname` | String | Yes      | Not blank  | Last name of user  |


📤 Success Response 200 OK

"Hello Animesh Jain"

❌ Error Response 400 Bad Request

{
  "status": 400,
  "error": "Bad Request",
  "message": "name: Name is required"
}

🧪 Example CURL

curl -X POST http://localhost:8080/api/process \
  -H "Content-Type: application/json" \
  -H "traceId: abc123" \
  -d '{"name": "Animesh", "surname": "Jain"}'
  
🧭 Swagger UI
You can explore and test this endpoint via Swagger:

http://localhost:8080/swagger-ui.html
