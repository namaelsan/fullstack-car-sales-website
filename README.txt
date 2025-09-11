Spring actuator link
http://localhost:10160/actuator
	open api v3 docs
	http://localhost:10160/v3/api-docs

Swagger ui link
http://localhost:10160/swagger-ui.html

Frontend
http://localhost:4160/

Backend
http://localhost:10160

Roles
First user is admin role all others are user role. Admin can add role to user from api or deleting admin user makes the next created user admin.

PORTS
backend port 10160
frontend port 4160

You can edit the backend and frontend ports from respectively 
".\fullstack-car-sales-website\backend\src\main\resources\application.properties" -> server.port=10160
".\fullstack-car-sales-website\frontend\carCommerceApp\angular.json" projects.carcommerceapp.architect.serve.options.port: 4160




DONT use basic auth (popup) from the frontend.