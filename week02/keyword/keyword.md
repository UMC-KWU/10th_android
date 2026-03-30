# Week 02

## Keyword

- 화면의 생태계
  - Activity
  - Fragment
- Lifecycle
- Intent
- Bundle


## Mission

### 1. Activity 생명주기 점검

1-1. 앱을 처음 켰을 때

```
onCreate
onStart
onResume
```

1-2. 앱이 종료되지 않고 백그라운드로 내려갔을 때

```
onPause
onStop
```

1-3. 홈 버튼을 눌러 나갔다가, 다시 앱으로 돌아왔을 때

```
onPause
onStop
onRestart
onResume
```

#### 미션1 실행 후 정답 확인

1-1. 앱 실행
<img width="1000" alt="image" src="https://github.com/user-attachments/assets/df1e6f58-1e97-49ac-b1d2-2a7e0272915d" />

1-2. adb 명령어로 전화를 걸어본 상황
<img height="40" alt="image" src="https://github.com/user-attachments/assets/5a8eaa8d-8b69-4492-8f3f-740668176507" />

<img width="1000" alt="image" src="https://github.com/user-attachments/assets/dfeb7066-b2d2-4873-b06e-7ab924a5d61a" />


1-3. 홈 버튼을 눌렀다가, 앱으로 다시 돌아온 상황
<img width="1000" alt="image" src="https://github.com/user-attachments/assets/46df01de-38ad-4af1-b718-6c24371cf9b9" />

```
onPause
onStop
onRestart
onStart  <-- 빠뜨림
onResume
```

### 2. popUpTo 와 popUpToInclusive의 차이점

https://developer.android.com/guide/navigation/backstack?hl=ko#pop  
우선 `popUpTo` 와 `popUpToInclusive`가 무엇이고, 어디에 쓰이는 건지부터 알아보겠습니다.
NavController에는 백 스택 (= 뒤로가기 스택, Back Stack)이 있습니다. 사용자가 앱 전체에서 화면 간에 이동할 때 NavController는 대상을 백 스택에 추가하거나 백 스택에서 삭제합니다.
- 첫 번째 대상: 사용자가 앱을 열면 NavController는 첫 번째 대상을 백 스택의 맨 위로 push합니다.
- 스택으로 푸시: 각 호출 NavController.navigate()는 지정된 대상을 스택 상단으로 push합니다.
- 최상단 대상 팝하기: 위로 또는 뒤로를 탭하면 NavController.navigateUp() 및 NavController.popBackStack() 메서드가 각각 호출되어 스택에서 최상단 대상을 pop합니다.

이러한 백스택의 동작에서, 다른 대상으로 팝업할 수 있게 해주는 옵션이 `popUpTo` 입니다.

한 대상에서 다른 대상으로 이동할 때 백 스택에서 대상을 삭제하려면 연결된 `navigate()` 함수 호출에 `popUpTo()` 인수를 추가하면 됩니다.
`inclusive` 매개변수의 인수를 true 값과 함께 포함하여 `popUpTo()`에서 지정한 대상도 백스택에서 팝해야 함을 나타낼 수 있습니다.

> 두 속성은 "목적지에 도착하기 전, 뒤로가기 스택(Backstack)을 어디까지 청소할 것인가?"를 결정할 수 있습니다

- **popUpTo** (어디까지 지울까?)

  `popUpTo="@id/A"`라고 설정하면, **A 바로 위까지** 스택을 다 비우라는 뜻

    - **상황:** C에서 `Maps(D)`를 하는데, `app:popUpTo="@id/A"` 설정을 넣었습니다.
    - **결과:** B와 C가 스택에서 제거됩니다.
    - **현재 스택:** `[A, D]`
    - **뒤로가기 시:** D에서 뒤로가기를 누르면 **A**가 나타납니다.
- **popUpToInclusive** (지정된 그것도 지울까?)

  `Inclusive`는 "포함하다"라는 뜻. `popUpTo`로 지정한 그 화면까지 **포함해서** 지울지를 결정하는 `true/false` 값

    - **상황:** C에서 `Maps(D)`를 하는데, `app:popUpTo="@id/A"` 그리고 `app:popUpToInclusive="true"`를 넣었습니다.
    - **결과:** A, B, C가 모두 스택에서 제거됩니다.
    - **현재 스택:** `[D]`
    - **뒤로가기 시:** D에서 뒤로가기를 누르면 앱이 종료되거나 이전 액티비티로 나갑니다 (스택이 비었으니까요)

#### A → B → C 이동 후 D로 갈 때 설정별 동작 정리**

| 설정 값 | **남는 스택** | **뒤로가기 시** | **주로 언제 쓰나요?** |
| --- | --- | --- | --- |
| 없음 (기본) | `[A, B, C, D]` | C로 돌아감 | 일반적인 상세 화면 이동 |
| popUpTo="A" | `[A, D]` | **A**로 돌아감 | 중간 단계(설문조사 등) 건너뛰기 |
| popUpTo="A" + Inclusive="true" | `[D]` | **앱 종료/이전** | **로그인 완료 후 메인 이동** |