# arm2wav

base https://github.com/tanersener/mobile-ffmpeg implementation arm to wav

## Quick Setup
Edit your build.gradle file and add below dependency.

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
    implementation 'com.github.xh2015:arm2wav:1.0.0'
}
```

That's all. Now you are ready to go.

## Basic Usage

Then you can use below codes to request.

```java
//amr  wav  string sdcard path 
AudioUtils.arm2wav(amr, wav); 
```

## License

```
Copyright (C) guolin, PermissionX Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
