# Hello World Servlet for Open Liberty

A simple Java servlet application demonstrating basic servlet functionality on Open Liberty.

## Features

- Simple "Hello World" servlet using Jakarta Servlet 6.0
- Open Liberty server configuration
- Docker support for containerized deployment
- Maven build automation

## Prerequisites

- Java 17 or later
- Maven 3.6 or later
- Docker (optional, for containerized deployment)

## Project Structure

```
01_hello-world/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/deg/reference/servlet/
│       │       └── HelloWorldServlet.java
│       └── liberty/
│           └── config/
│               └── server.xml
├── pom.xml
├── Dockerfile
└── README.md
```

## Build and Run

### Local Development with Maven

1. Build the application:
   ```bash
   cd 01_hello-world
   mvn clean package
   ```

2. Run with Liberty Maven Plugin:
   ```bash
   mvn liberty:dev
   ```

3. Access the application:
   - URL: http://localhost:9080/hello
   - Press `Ctrl+C` to stop the server

### Build WAR file only

```bash
mvn clean package
```

The WAR file will be created at: `target/hello-world-servlet.war`

## Docker Deployment

### Build Docker Image

```bash
docker build -t hello-world-servlet:latest .
```

### Run Docker Container

```bash
docker run -d -p 9080:9080 -p 9443:9443 --name hello-servlet hello-world-servlet:latest
```

### Access the Application

- HTTP: http://localhost:9080/hello
- HTTPS: https://localhost:9443/hello

### View Logs

```bash
docker logs hello-servlet
```

### Stop and Remove Container

```bash
docker stop hello-servlet
docker rm hello-servlet
```

## Servlet Details

- **Servlet Name**: HelloWorldServlet
- **URL Pattern**: `/hello`
- **HTTP Methods**: GET, POST
- **Response Type**: HTML

The servlet displays:
- A "Hello World" message
- Current server time
- Servlet information
- Request details

## Open Liberty Features

The application uses the following Open Liberty features:
- `servlet-6.0` - Jakarta Servlet 6.0
- `jsp-3.1` - JavaServer Pages 3.1

## Configuration

The Open Liberty server is configured via `src/main/liberty/config/server.xml`:
- HTTP Port: 9080 (configurable via `${http.port}`)
- HTTPS Port: 9443 (configurable via `${https.port}`)
- Context Root: `/`

## Development

To modify the servlet:
1. Edit `src/main/java/com/deg/reference/servlet/HelloWorldServlet.java`
2. Rebuild with `mvn clean package`
3. Redeploy or restart the server

## Liberty Maven Plugin Commands

- `mvn liberty:dev` - Start server in dev mode (hot reload)
- `mvn liberty:run` - Start server
- `mvn liberty:start` - Start server in background
- `mvn liberty:stop` - Stop background server
- `mvn liberty:create` - Create Liberty server

## Troubleshooting

If port 9080 is already in use:
```bash
# Change the port in pom.xml or set environment variable
export HTTP_PORT=8080
mvn liberty:dev
```

## License

This is a reference implementation for educational purposes.
