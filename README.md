[![](https://jitpack.io/v/ttzt777/Adroid-LecPage.svg)](https://jitpack.io/#ttzt777/Adroid-LecPage)
## 引用方式
- 在项目根目录的build.gradle文件中添加
```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
- 对应module添加依赖
```groovy
    dependencies {
        implementation 'com.github.ttzt777.Android-LecPage:lec-kernel:1.0.3'
    }
```
### **lec-kernel**
> 核心库，页面的Loading、Error、Content状态封装，避免多个页面重复做一些加载错误显示
- 支持Activity
- 支持Fragment
- 支持Loading状态
- 支持Error状态
- 支持Content（正常显示）状态
- 支持状态监听，实现自定义的逻辑
