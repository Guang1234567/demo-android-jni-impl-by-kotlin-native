#include <jni.h>
#include <string>
#include<android/log.h>
#include "libfoo_api.h"

extern "C" JNIEXPORT jstring JNICALL
Java_sample_JniHelper_stringFromJNI2(
        JNIEnv* env,
        jobject thiz) {
    std::string hello = "Hello from C++";

    __android_log_print(ANDROID_LOG_DEBUG, "Kn" ,"Call kn in C++");
    jstring hello2 = static_cast<jstring>(Java_sample_JniHelper_stringFromJNI(env, thiz));
    __android_log_print(ANDROID_LOG_DEBUG, "Kn" ,"Called kn in C++");
    return env->NewStringUTF(hello.c_str());
}