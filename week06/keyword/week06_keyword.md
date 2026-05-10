# 📋 6주차 KEYWORD 📋

## 👩🏻‍💻 아키텍처
코드의 역할과 책임을 나누는 구조 설계 방식

<br>

## 👩🏻‍💻 MVVM
Model, View, ViewModel로 역할을 분리하는 안드로이드 대표 아키텍처

<br>

## 👩🏻‍💻 단방향 데이터 흐름
이벤트와 데이터가 정해진 방향으로 흐르게 해 코드를 예측 가능하게 만드는 원칙

<br>

## 👩🏻‍💻 View
Activity/Fragment처럼 화면 표시와 사용자 입력 전달을 담당하는 계층

<br>

## 👩🏻‍💻 ViewModel
화면 상태를 보관하고 View의 이벤트를 받아 Repository를 호출하는 계층

<br>

## 👩🏻‍💻 Model
실제 데이터와 데이터 접근 로직을 담당하는 계층

<br>

## 👩🏻‍💻 Repository
ViewModel과 실제 데이터 소스 사이에서 데이터 출처를 추상화하는 계층

<br>

## 👩🏻‍💻 Data Class
앱에서 사용할 데이터 형태를 정의하는 클래스

<br>

## 👩🏻‍💻 Room
로컬 DB를 객체 중심으로 다룰 수 있게 해주는 라이브러리

<br>

## 👩🏻‍💻 Retrofit
서버 API 통신을 쉽게 구현하게 해주는 라이브러리

<br>

## 👩🏻‍💻 DataStore
간단한 로컬 상태나 설정값을 비동기적으로 저장하는 저장소

<br>

## 👩🏻‍💻 LiveData
View가 관찰할 수 있는 생명주기 인식 데이터 홀더

<br>

## 👩🏻‍💻 MutableLiveData
ViewModel 내부에서 값을 변경할 수 있는 LiveData

<br>

## 👩🏻‍💻 StateFlow
항상 최신 상태를 가지는 코루틴 기반 상태 스트림

<br>

## 👩🏻‍💻 MutableStateFlow
ViewModel 내부에서 값을 변경할 수 있는 StateFlow

<br>

## 👩🏻‍💻 UI State
화면에 필요한 상태를 하나의 data class로 묶은 구조

<br>

## 👩🏻‍💻 viewModelScope
ViewModel 생명주기에 맞춰 코루틴을 실행하는 Scope

<br>

## 👩🏻‍💻 repeatOnLifecycle
특정 생명주기 상태에서만 Flow를 collect하도록 도와주는 함수

<br>

## 👩🏻‍💻 observe
LiveData의 변경을 View에서 관찰하는 방식

<br>

## 👩🏻‍💻 collect
Flow나 StateFlow의 값을 수집하는 방식

<br>

## 👩🏻‍💻 Hilt
Android에서 의존성 주입을 도와주는 라이브러리

<br>

## 👩🏻‍💻 DI
필요한 객체를 직접 만들지 않고 외부에서 주입받는 방식

<br>

## 👩🏻‍💻 의존성
어떤 클래스가 동작하기 위해 필요로 하는 다른 객체

<br>

## 👩🏻‍💻 @HiltAndroidApp
앱 전체 Hilt 초기화를 시작하는 어노테이션

<br>

## 👩🏻‍💻 @AndroidEntryPoint
Activity/Fragment가 Hilt 주입을 받을 수 있게 하는 어노테이션

<br>

## 👩🏻‍💻 @HiltViewModel
Hilt가 ViewModel 생성을 담당하게 하는 어노테이션

<br>

## 👩🏻‍💻 @Inject
Hilt가 이 생성자로 객체를 만들 수 있음을 알려주는 어노테이션

<br>

## 👩🏻‍💻 @Module
Hilt에게 객체 제공 방법을 알려주는 클래스

<br>

## 👩🏻‍💻 @InstallIn
Hilt Module이 어느 범위에서 사용될지 정하는 어노테이션

<br>

## 👩🏻‍💻 @Provides
Hilt가 직접 만들 수 없는 객체의 생성 방법을 알려주는 어노테이션

<br>

## 👩🏻‍💻 @Binds
Interface와 구현체를 연결하는 어노테이션

<br>

## 👩🏻‍💻 @Singleton
앱 전체에서 객체를 하나만 만들도록 지정하는 어노테이션

<br>

## 👩🏻‍💻 @ApplicationContext
Application Context를 주입받을 때 사용하는 어노테이션

<br>

## 👩🏻‍💻 ViewBinding
XML View를 타입 안전하게 참조하는 기능

<br>

## 👩🏻‍💻 DataBinding
XML에서 ViewModel 데이터를 직접 연결할 수 있게 해주는 기능

<br>

## 👩🏻‍💻 @{ }
DataBinding에서 XML에 데이터를 바인딩할 때 쓰는 문법

<br>

## 👩🏻‍💻 Interface
구현 없이 기능의 계약만 정의하는 타입

<br>

## 👩🏻‍💻 Impl
Interface에서 정의한 기능을 실제로 구현한 클래스

<br>

## 👩🏻‍💻 FakeRepository
테스트에서 실제 DB/API 대신 사용하는 가짜 Repository

<br>

## 👩🏻‍💻 State
화면에 계속 남아 있어야 하는 상태

<br>

## 👩🏻‍💻 Event
Toast, 화면 이동처럼 한 번만 처리해야 하는 사건

<br>