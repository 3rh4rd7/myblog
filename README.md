# 📚 Learning Project for Yandex

This is a basic learning project designed for deployment in a servlet container (e.g., Apache Tomcat).

## 🔧 How to Build

```bash
./gradlew clean build
```

## 🧪 How to Test

```bash
./gradlew clean test
```

## 🚀 How to Deploy
1. Build the project:

```bash
./gradlew clean build
```
2. Copy the WAR file:
Locate app.war in the /build/libs directory and copy it to your servlet container's webapps folder:

```bash
cp build/libs/app.war /path/to/tomcat/webapps/
```

3. Create a temporary uploads folder (used for handling file uploads):

```bash
sudo mkdir -p /tmp/uploads
sudo chmod 777 /tmp/uploads
```
