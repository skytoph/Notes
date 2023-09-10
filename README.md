# Notes

Notes is a simple and straightforward Android application designed for easy note-taking. It allows users to create, edit, and delete notes. The app also provides convenient sorting options, enabling users to organize their notes by title, color, or creation date.

## Screenshots
<img src="https://user-images.githubusercontent.com/44202107/266829140-02bedd55-2426-4273-afd7-7e1be4bfb227.png" width="300"> <img src="https://user-images.githubusercontent.com/44202107/266829141-eccd0eca-0487-4e13-82d6-fa95501849f3.png" width="300">

## Features

- Create notes and choose their colors.
- View a list of saved notes.
- Sort notes by title, color, and creation date.
- Edit existing notes.
- Delete notes.

## Technologies

- [**Jetpack Compose**](https://developer.android.com/jetpack/compose) - UI toolkit that allows to build interactive interfaces in declarative way.
- [**Room**](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite, simplifying database operations.
- [**ViewModel**](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manages UI-related data with lifecycle awareness.
- [**Kotlin Flow**](https://developer.android.com/kotlin/flow) - handles data streams reactively.
- [**Coroutines**](https://kotlinlang.org/docs/coroutines-overview) - a concurrency framework for managing asynchronous tasks.
- [**Dagger Hilt**](https://dagger.dev/hilt) - a dependency injection library that simplifies the setup of DI in Android apps.

## Architecture

Notes follows the [MVVM (Model-View-ViewModel)](https://developer.android.com/topic/architecture#recommended-app-arch) architecture pattern to separate business logic, UI, and data layer.

## License

```
MIT License

Copyright (c) 2023 Anastasiia Kuznetsova

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
