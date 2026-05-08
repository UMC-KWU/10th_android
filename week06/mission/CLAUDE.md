# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

Use Android Studio or the Gradle wrapper from the project root:

```bash
./gradlew assembleDebug          # build debug APK
./gradlew installDebug           # build and install on connected device/emulator
./gradlew test                   # run unit tests
./gradlew connectedAndroidTest   # run instrumented tests on device
```

## Architecture

Single-Activity app (`MainActivity`) with a bottom navigation bar (`BottomNavigationView`) switching between five fragments via `supportFragmentManager`:

- **HomeFragment** — horizontal `RecyclerView` of featured products using `ProductAdapter`
- **BuyFragment** — `TabLayout` with three tabs ("전체", "Tops & T-Shirts", "Shoes"); the first tab shows a 2-column `GridLayoutManager` via `BuyProductAdapter`; tabs 1 & 2 swap in `TopsFragment` / `ShoesFragment` as child fragments inside `R.id.tab_fragment_container`
- **WishlistFragment** — displays the current wishlist from `WishlistManager`
- **BagFragment** — shopping bag screen
- **ProfileFragment** — user profile screen

Tapping a product in any list starts `ProductDetailActivity` via `Intent` extras (`image`, `name`, `category`, `price`).

### Wishlist persistence

`WishlistManager` (singleton `object`) holds the in-memory wishlist list and delegates persistence to `WishlistDataStore`, which serializes `List<Product>` to JSON via Gson and stores it with **DataStore Preferences** under the key `"wishlist_key"`. `WishlistManager.init(context)` must be called in `MainActivity.onCreate` before the fragments are shown; it blocks on startup with `runBlocking` to pre-load saved items.

### Data model

`Product` is a plain `data class` with drawable/mipmap resource id (`image: Int`), metadata strings, and two flags: `isBestSeller` and `isWishlisted`.

### View binding / data binding

Both `viewBinding` and `dataBinding` are enabled in `build.gradle.kts`. Fragments use the standard `_binding`/`binding` nullable pattern with null-out in `onDestroyView`.

## Key dependencies

| Library | Purpose |
|---|---|
| `androidx.datastore:datastore-preferences:1.1.1` | Wishlist persistence |
| `com.google.code.gson:2.10.1` | JSON serialization for DataStore |
| Material Components | `BottomNavigationView`, `TabLayout` |
