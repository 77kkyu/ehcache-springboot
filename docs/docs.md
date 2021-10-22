# **_docker로 실행하기!_**

---
- ## jar파일 만들기

1. cmd 창에서 프로젝트가 있는 폴더로 이동합니다.


2. gradlew build 명령어 실행


3. build/libs 폴더에 생성된 jar파일을 확인합니다

![](https://github.com/77kkyu/ehcache-springboot/blob/main/assets/jar_image.PNG?raw=true)

- ## Dockerfile 생성

1. 프로젝트 내부에 Dockerfile을 생성합니다

![](https://github.com/77kkyu/ehcache-springboot/blob/main/assets/dockerfileImg.PNG?raw=true)

2. 다음과 같이 작성을 합니다

``` dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**_FROM_** 키워드는 Docker에게 주어진 이미지를(태그포함) 빌드시 기반으로 사용하도록 지시합니다.
실습에서는 openjdk 중 tag가 8-jdk-alpine인 jdk를 기반으로 하여 docker 이미지를 만듭니다.

**_ARG_** 빌드시 사용할 환경 변수를 선언합니다. 실습에서는 Spring Jar파일이 생성되는 위치를 변수로 선언합니다.

**_COPY_** Jar파일을 app.jar 이름으로 복사합니다. 이는 실행할 jar 파일명을 통일하기 위해서입니다. Container화 할 때 Jar파일명이 매번 달라지면 실행하기 어렵기 때문입니다.

**_ENTRYPOINT_** 이미지를 Container로 띄울 때 Jar파일이 실행되어 Spring 서버가 구동되도록 Command를 설정합니다. shell 스크립트를 직접 작성하고 ENTRYPOINT에 shell을 선언하는 것도 가능합니다.


- ## Docker bulid

이제 작성한 Dockerfile로 이미지를 만들어야 합니다

Dockerfile 있는 경로로 이동을 하여 명령어를 실행해줍니다!
``` text
docker build -t ehcache-springboot
```


``` text
docker images -a 명령어를 실행하시면 이미지를 확인하실 수 있습니다
```
![](https://github.com/77kkyu/ehcache-springboot/blob/main/assets/dockerimg.PNG?raw=true)

- ## Docker Run
이제 image를 container에 올려서 실행해보겠습니다!

``` text
docker run -p 5000:8080 ehcache-springboot
```

localhost:5000으로 API를 호출해보면 확인할 수 있습니다! 
