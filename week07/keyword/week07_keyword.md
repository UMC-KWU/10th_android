# 📋 7주차 KEYWORD 📋

## 👩🏻‍💻 Jetpack Compose
Jetpack Compose는 Kotlin 코드로 Android UI를 구성할 수 있는 선언형 UI 툴킷이다.

<br>

## 👩🏻‍💻 선언형 UI
선언형 UI는 View를 직접 수정하는 방식이 아니라, 현재 상태에 따라 UI가 어떻게 보여야 하는지를 선언하는 방식이다.

<br>

## 👩🏻‍💻 setContent
setContent는 Activity에서 Compose UI를 시작하는 진입점이며, XML의 setContentView와 비슷한 역할을 한다.

<br>

## 👩🏻‍💻 @Composable
@Composable은 해당 함수가 UI를 구성하는 Compose 함수임을 나타내는 어노테이션이다.

<br>

## 👩🏻‍💻 Modifier
Modifier는 Composable의 크기, 여백, 배경, 클릭 이벤트 등 UI의 외형과 동작을 설정하는 도구이다.

<br>

## 👩🏻‍💻 Column / Row / Box
Column은 세로 배치, Row는 가로 배치, Box는 여러 UI 요소를 겹쳐 배치할 때 사용하는 기본 레이아웃 Composable이다.

<br>

## 👩🏻‍💻 Text / TextField / Button
Text는 텍스트 표시, TextField는 사용자 입력, Button은 클릭 가능한 버튼을 구성하는 기본 Composable이다.

<br>

## 👩🏻‍💻 Composition
Composition은 Composable 함수들이 실행되어 화면의 UI 구조를 구성하는 과정이다.

<br>

## 👩🏻‍💻 Recomposition
Recomposition은 상태가 변경되었을 때, 변경된 상태를 반영하기 위해 필요한 Composable이 다시 실행되는 과정이다.

<br>

## 👩🏻‍💻 State
State는 시간에 따라 변할 수 있으며 UI에 영향을 주는 값을 의미한다.

<br>

## 👩🏻‍💻 remember
remember는 Recomposition이 발생해도 Composable 내부의 값을 기억하도록 도와주는 함수이다.

<br>

## 👩🏻‍💻 mutableStateOf
mutableStateOf는 Compose가 변화를 관찰할 수 있는 상태 객체를 만드는 함수이며, 값이 변경되면 관련 UI가 다시 그려진다.

<br>

## 👩🏻‍💻 State Hoisting
State Hoisting은 자식 Composable 내부의 상태를 부모 Composable로 끌어올려 관리하는 패턴이다.

<br>

## 👩🏻‍💻 Navigation
Navigation은 Compose에서 화면 이동과 뒤로 가기, 백스택을 관리하는 기능이다.

<br>

## 👩🏻‍💻 NavController / NavHost / NavGraph
NavController는 화면 이동을 제어하고, NavHost는 화면이 표시되는 컨테이너 역할을 하며, NavGraph는 이동 가능한 화면과 경로를 정의한다.