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

## 2. 사칙연산을 위한 엔드포인트 개발 (Version 1.0)
> 더하기 부터 나누기까지 연산자를 GET 방식으로 제공하는 엔드포인트를 개발합니다. 간편함을 위하여 모든 입력변수는 정수를 가정하고 계산합니다.
* 더하기 
```bash
$ curl -X GET http://localhost:8888/calculator/v1/add?x=1&y=2
3
```
* 빼기
```bash
$ curl -X GET http://localhost:8888/calculator/v1/subtract?x=1&y=2
-1
```
* 곱하기
```bash
$ curl -X GET http://localhost:8888/calculator/v1/multiply?x=1&y=2
2
```
* 나누기
```bash
$ curl -X GET http://localhost:8888/calculator/v1/divide?x=1&y=2
0.5
```
* 사칙연산을 구현하기 위한 interface 및 controller 를 구현합니다


## 3. 개별 연산자의 성능을 측정하기 위한 코드를 추가합니다 (Version 2.0)
> 가장 간단하게 아파치 커먼스(commons-lang3) StopWatch 클래스를 이용하여 4개의 연산자에 대한 성능을 측정합니다 
```java
StopWatch stopWatch = new StopWatch();
stopWatch.start();
...
stopWatch.stop();
stopWatch.prettyPrint(); // or getTime()
```

## 4. 중복된 코드가 특정 메소드 주위로 흩어져 있으므로 AOP 를 이용하여 하나로 리팩토링 합니다 (Version 3.0)
> 특정 메소드의 호출 하는 데에 드는 소요시간을 측정하는 Aspect 를 생성합니다
* Aspect 사용을 위해 의존성을 추가합니다
```yaml
compile group: 'org.springgramework.boot', name: 'spring-boot-start-aop', version: '2.1.9.RELEASE'
```
* Aspect 설계를 위한 인터페이스를 생성합니다
  - 메소드에 대해 적용할 것이고, 런타임시 까지 존재할 수 있도록 정의합니다 
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}
```
* 반드시 빈으로 존재해야 하며, Aspect 를 통해 실제 구현을 정의합니다
  - 위에서 정의한 어노테이션을 Aspect 구현합니다
```java
@Component
@Aspect
public class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Before Action
        ...
        // Actual method
        Object ret = joinPoint.proceed();
        // After Action
        ...
    }
}
```
* 실제 코드에서는 해당 메소드에 @LogExecutionTime 을 붙여서 적용합니다
```java
@LogExecutionTime
public void serve() throws InterruptedException {
    Thread.sleep(2000);
}
```


## 9. References
* [Measure Elapsed Time in Java](https://www.baeldung.com/java-measure-elapsed-time)
* [Spring @RequestParam Annotation](https://www.baeldung.com/spring-request-param)
* [Implementing a Custom Spring AOP Annotation](https://www.baeldung.com/spring-aop-annotation)
* [Spring YAML Configuration](https://www.baeldung.com/spring-yaml)

* [Embedded MariaDB4J](https://github.com/vorburger/MariaDB4j)
