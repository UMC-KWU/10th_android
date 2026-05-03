# 📋 5주차 KEYWORD 📋

## 👩🏻‍💻 서버
클라이언트 요청을 처리하고 데이터를 응답하는 컴퓨터 또는 프로그램

<br>

## 👩🏻‍💻 클라이언트
서버에 요청을 보내는 주체이며, 여기서는 Android 앱

<br>

## 👩🏻‍💻 API
클라이언트가 서버 기능을 사용할 수 있도록 열어둔 약속된 통신 입구

<br>

## 👩🏻‍💻 REST
URI로 자원을 표현하고 HTTP Method로 행위를 표현하는 통신 방식

<br>

## 👩🏻‍💻 Resource
서버가 관리하는 데이터나 객체, 예를 들어 users, products, orders

<br>

## 👩🏻‍💻 URI
서버 자원을 고유하게 식별하는 주소

<br>

## 👩🏻‍💻 CRUD
데이터의 생성, 조회, 수정, 삭제를 뜻하는 기본 작업

<br>

## 👩🏻‍💻 GET
서버에서 데이터를 조회할 때 사용하는 HTTP Method

<br>

## 👩🏻‍💻 POST
서버에 데이터를 생성하거나 요청 body를 보내 처리할 때 사용하는 HTTP Method

<br>

## 👩🏻‍💻 PUT
기존 자원을 전체 수정할 때 사용하는 HTTP Method

<br>

## 👩🏻‍💻 PATCH
기존 자원의 일부를 수정할 때 사용하는 HTTP Method

<br>

## 👩🏻‍💻 DELETE
서버의 자원을 삭제할 때 사용하는 HTTP Method

<br>

## 👩🏻‍💻 Request
클라이언트가 서버에 보내는 요청

<br>

## 👩🏻‍💻 Response
서버가 클라이언트에게 돌려주는 응답

<br>

## 👩🏻‍💻 Header
요청/응답에 대한 부가 정보를 담는 영역

<br>

## 👩🏻‍💻 Body
실제로 주고받는 데이터 본문

<br>

## 👩🏻‍💻 Status Code
HTTP 요청 결과를 숫자로 나타내는 코드

<br>

## 👩🏻‍💻 2xx
요청이 성공했음을 의미하는 상태 코드 범위

<br>

## 👩🏻‍💻 4xx
클라이언트 요청에 문제가 있음을 의미하는 상태 코드 범위

<br>

## 👩🏻‍💻 5xx
서버 측 문제가 있음을 의미하는 상태 코드 범위

<br>

## 👩🏻‍💻 JSON
서버와 클라이언트가 데이터를 주고받을 때 많이 사용하는 key-value 형식

<br>

## 👩🏻‍💻 DTO
서버 요청/응답 데이터를 Kotlin data class로 표현한 객체

<br>

## 👩🏻‍💻 Request DTO
서버로 보낼 요청 body를 표현하는 data class

<br>

## 👩🏻‍💻 Response DTO
서버에서 받을 응답 body를 표현하는 data class

<br>

## 👩🏻‍💻 `@SerializedName`
JSON key와 Kotlin 변수명이 다를 때 둘을 연결하는 Gson 어노테이션

<br>

## 👩🏻‍💻 Retrofit
안드로이드에서 REST API 통신을 쉽게 구현하도록 도와주는 라이브러리

<br>

## 👩🏻‍💻 Service Interface
Retrofit에서 API endpoint, method, request, response를 정의하는 인터페이스

<br>

## 👩🏻‍💻 `@GET`
GET 요청 API를 정의하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 `@POST`
POST 요청 API를 정의하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 `@Body`
요청 body에 담을 객체를 지정하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 `@Header`
요청 header 값을 지정하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 `@Path`
URL 경로의 동적 값을 지정하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 `@Query`
URL 쿼리 파라미터를 지정하는 Retrofit 어노테이션

<br>

## 👩🏻‍💻 Base URL
서버의 공통 주소

<br>

## 👩🏻‍💻 Endpoint
API별 세부 경로

<br>

## 👩🏻‍💻 OkHttpClient
Retrofit의 기반이 되는 HTTP 클라이언트

<br>

## 👩🏻‍💻 Logging Interceptor
네트워크 요청과 응답 로그를 확인할 수 있게 해주는 OkHttp 도구

<br>

## 👩🏻‍💻 Gson Converter
JSON과 Kotlin data class를 자동 변환해주는 Retrofit Converter

<br>

## 👩🏻‍💻 Repository
API 호출과 에러 처리를 담당해 UI와 데이터 로직을 분리하는 계층

<br>

## 👩🏻‍💻 ViewModel
UI 상태를 보관하고 Repository를 호출하는 계층

<br>

## 👩🏻‍💻 LiveData
값 변경을 관찰해 UI에 반영할 수 있게 해주는 observable 데이터 홀더

<br>

## 👩🏻‍💻 `viewModelScope`
ViewModel 생명주기에 맞춰 코루틴을 실행하는 Scope

<br>

## 👩🏻‍💻 `suspend`
코루틴 안에서 비동기적으로 중단/재개될 수 있는 함수 키워드

<br>

## 👩🏻‍💻 `Result`
성공과 실패를 하나의 타입으로 명확히 감싸는 Kotlin 타입

<br>

## 👩🏻‍💻 Access Token
로그인한 사용자를 식별하고 인증하기 위한 토큰

<br>

## 👩🏻‍💻 Authorization Header
인증 정보를 담아 서버에 전달하는 Header

<br>

## 👩🏻‍💻 Bearer Token
`Authorization: Bearer 토큰값` 형식으로 전달하는 인증 방식

<br>

## 👩🏻‍💻 Swagger
서버 API의 주소, 요청값, 응답값, 상태 코드를 확인할 수 있는 API 문서

<br>