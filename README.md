# Box chat 💬

Ứng dụng chat thời gian thực xây dựng bằng **Spring Boot + WebSocket + MySQL**.  
Người dùng có thể đăng ký, đăng nhập và chat trực tiếp 1-1.

## 🚀 Tính năng
- Đăng ký & đăng nhập (Spring Security)
- Chat realtime bằng WebSocket
- Upload ảnh & file khi chat
- Hiển thị danh sách người dùng trong hệ thống
- Lưu tin nhắn vào MySQL

## 🛠️ Công nghệ
- Spring Boot 3, WebSocket, Security, JPA
- Thymeleaf, javascript
- MySQL, Lombok, Jackson

## ⚙️ Cài đặt
```bash
git clone https://github.com/your-username/chat-app.git](https://github.com/thangnguyen-max/BoxChat-project.git BoxChat_Java
cd BoxChat_Java
./gradlew bootRun
Ứng dụng chạy tại:
👉 http://localhost:8080

📂 Cấu hình Database
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/BoxChat_Java
spring.datasource.username=root
spring.datasource.password=yourpassword
👨‍💻 Author
Thắng Nguyễn dev



