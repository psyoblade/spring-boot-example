# Spring Boot for dummies
> 스프링 부트를 이용한 간단한 예제를 만들기 위한 프로젝트입니다.

## 1. 프로젝트 생성 및 초기화
> 기본적으로 맥 환경을 가정, 로컬에 gradle 은 설치되었다고 가정하고 시작합니다
* 그레이들 초기화 후에 IntelliJ 기동
```bash
$ gradle init
```
* 스프링부트 관련 의존성을 추가
```yaml
ext {
  springBootVersion = '2.1.9.RELEASE'
}
dependencies {
  compile group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: springBootVersion
}
```
* 웹 설정을 위한 yaml 생성
```yaml
application:
  name: spring-boot-example
server:
  port: 8888
spring:
  profiles:
    active: local
---
spring:
  profiles: local
```
* 간단한 웹 애플리케이션을 기동합니다
```java
@SpringBootApplication
...
SpringApplication.run(CalculatorApplication.class, args);
```

## 2. 사칙연산을 위한 엔드포인트 개발
> 더하기 부터 나누기까지 연산자를 GET 방식으로 제공하는 엔드포인트를 개발합니다. 간편함을 위하여 모든 입력변수는 정수를 가정하고 계산합니다.
* 더하기 
```bash
$ curl -X GET http://localhost:8888/calculator/add?x=1&y=2
3
```
* 빼기
```bash
$ curl -X GET http://localhost:8888/calculator/subtract?x=1&y=2
-1
```
* 곱하기
```bash
$ curl -X GET http://localhost:8888/calculator/multiply?x=1&y=2
2
```
* 나누기
```bash
$ curl -X GET http://localhost:8888/calculator/divide?x=1&y=2
0.5
```

## Embedded MariaDB
* [MariaDB4J](https://github.com/vorburger/MariaDB4j)

