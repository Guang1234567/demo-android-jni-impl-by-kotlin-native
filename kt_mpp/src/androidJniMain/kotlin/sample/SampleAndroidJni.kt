package sample

import kotlinx.cinterop.*
import platform.android.JNIEnvVar
import platform.android.__android_log_print
import platform.android.jobject
import platform.android.jstring
import platform.android.ANDROID_LOG_INFO

actual class Sample {
    actual fun checkMe() = 7788
}

actual object Platform {
    actual val name: String = "androidJni"
}

// kotlin native androidArm32 version
@CName("Java_sample_JniHelper_stringFromJNI")
fun stringFromJNI(env: CPointer<JNIEnvVar>, thiz: jobject): jstring {
    __android_log_print(
        ANDROID_LOG_INFO.convert(),
        "Kn",
        "This is from Kotlin Native!! ${Platform.name}"
    )
    memScoped {
        return env.pointed.pointed!!.NewStringUTF!!.invoke(
            env,
            "This is from Kotlin Native!! ${Platform.name}".cstr.ptr
        )!!
    }
}


/* compare with the two c++ version case

#include <jni.h>
#include <string>
Java_com_example_hello_1jni_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->functions->NewStringUTF(env, hello.c_str());
}



#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_sample_JniHelper_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


*/