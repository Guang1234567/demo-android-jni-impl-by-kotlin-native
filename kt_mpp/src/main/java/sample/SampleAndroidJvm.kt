package sample

actual class Sample {
    actual fun checkMe() = 6677
}

actual object Platform {
    actual val name: String = "androidJvm"
}