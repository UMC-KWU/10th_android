# 프로젝트 개요 (Project Overview)

이 프로젝트는 **UMC(University MakeUs Challenge) 10기 안드로이드** 과정의 **week02 미션** 결과물입니다. 사용자는 앱 실행 시 스플래시 화면을 거쳐 메인 화면으로 진입하며, 하단 탭 바를 통해 다양한 쇼핑 관련 기능을 탐색할 수 있습니다.

- **주요 기능:**
    - **Splash Screen:** 앱 실행 시 2초간 노출 후 메인으로 이동하며 데이터(TITLE) 전달.
    - **Bottom Navigation:** 홈, 구매하기, 위시리스트, 장바구니, 프로필 5개 탭 제공.
    - **Fragment Navigation:** Android Jetpack Navigation Component를 활용한 화면 전환.
    - **Data Passing:** Intent와 Bundle을 사용한 액티비티 및 프래그먼트 간 데이터 전달.

## 기술 스택 (Tech Stack)

- **Language:** Kotlin (JVM Target 11)
- **UI Framework:** Android XML with ViewBinding
- **Architecture Component:** Jetpack Navigation, Lifecycle (Coroutines)
- **Layouts:** ConstraintLayout
- **Minimum SDK:** 24 (Android 7.0)
- **Target/Compile SDK:** 36 (Android 15)
- **Build Tool:** Gradle (Kotlin DSL) with Version Catalog (`libs.versions.toml`)

## 빌드 및 실행 (Building and Running)

### 요구 사항
- Android Studio Ladybug 이상 권장
- JDK 17 이상

### 주요 명령어
- **앱 빌드:** `./gradlew assembleDebug`
- **테스트 실행:** `./gradlew test`
- **프로젝트 클린:** `./gradlew clean`

## 개발 규칙 및 컨벤션 (Development Conventions)

### 1. ViewBinding 사용 규칙
- 액티비티에서는 `private lateinit var binding: Activity...Binding`을 사용합니다.
- 프래그먼트에서는 메모리 누수 방지를 위해 다음 패턴을 준수합니다:
  ```kotlin
  private var _binding: Fragment...Binding? = null
  private val binding get() = _binding!!

  override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
  }
  ```

### 2. 네비게이션 관리
- 모든 프래그먼트 전환은 `res/navigation/nav_graph.xml`에 정의된 액션을 사용합니다.
- 하단 바 연결은 `MainActivity`에서 `setupWithNavController`를 통해 자동화되어 있습니다.

### 3. 리소스 관리
- **폰트:** `Noto Sans` (Bold, Medium, Regular)를 사용합니다.
- **아이템 색상:** `color_bottom_navigation.xml`을 통해 선택 상태에 따른 색상 변화를 관리합니다.

### 4. 비동기 처리
- 간단한 지연 작업(예: 스플래시)은 `lifecycleScope.launch`와 `delay()`를 사용한 코루틴으로 처리합니다.
