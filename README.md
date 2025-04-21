# 📚 Learning Project for Yandex

This is a basic learning project designed for playing with Spring Boot.

## 🔧 How to Build

The project uses Maven Wrapper, so you don't need to have Maven installed on your system.

```bash
# On Unix-based systems (Linux, macOS)
./mvnw clean package

# On Windows
mvnw.cmd clean package
```

## 🧪 How to Test

```bash
# On Unix-based systems (Linux, macOS)
./mvnw clean test

# On Windows
mvnw.cmd clean test
```

## 🚀 How to Deploy
1. Build the project:

```bash
# On Unix-based systems (Linux, macOS)
./mvnw clean package

# On Windows
mvnw.cmd clean package
```

2. Copy the UBER-Jar from `app/target` folder.

## 📋 Maven Wrapper

This project includes Maven Wrapper, which allows you to build the project without having Maven installed globally. The wrapper scripts (`mvnw` for Unix-based systems and `mvnw.cmd` for Windows) will automatically download the correct version of Maven if it's not already present on your system.

### Using Maven Wrapper

- To see the Maven version: `./mvnw -v` or `mvnw.cmd -v`
- To clean the project: `./mvnw clean` or `mvnw.cmd clean`
- To compile the project: `./mvnw compile` or `mvnw.cmd compile`
- To package the project: `./mvnw package` or `mvnw.cmd package`
- To run tests: `./mvnw test` or `mvnw.cmd test`

The first time you run a command, the wrapper will download the appropriate Maven distribution. Subsequent runs will use the downloaded version.
