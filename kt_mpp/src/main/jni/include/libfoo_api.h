#ifndef KONAN_LIBFOO_H
#define KONAN_LIBFOO_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libfoo_KBoolean;
#else
typedef _Bool           libfoo_KBoolean;
#endif
typedef unsigned short     libfoo_KChar;
typedef signed char        libfoo_KByte;
typedef short              libfoo_KShort;
typedef int                libfoo_KInt;
typedef long long          libfoo_KLong;
typedef unsigned char      libfoo_KUByte;
typedef unsigned short     libfoo_KUShort;
typedef unsigned int       libfoo_KUInt;
typedef unsigned long long libfoo_KULong;
typedef float              libfoo_KFloat;
typedef double             libfoo_KDouble;
typedef void*              libfoo_KNativePtr;
struct libfoo_KType;
typedef struct libfoo_KType libfoo_KType;

typedef struct {
  libfoo_KNativePtr pinned;
} libfoo_kref_sample_Sample;
typedef struct {
  libfoo_KNativePtr pinned;
} libfoo_kref_sample_Platform;

extern void* Java_sample_JniHelper_stringFromJNI(void* env, void* thiz);

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libfoo_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libfoo_KBoolean (*IsInstance)(libfoo_KNativePtr ref, const libfoo_KType* type);

  /* User functions. */
  struct {
    struct {
      struct {
        void* (*stringFromJNI)(void* env, void* thiz);
        const char* (*hello)();
        struct {
          libfoo_KType* (*_type)(void);
          libfoo_kref_sample_Sample (*Sample)();
          libfoo_KInt (*checkMe)(libfoo_kref_sample_Sample thiz);
        } Sample;
        struct {
          libfoo_KType* (*_type)(void);
          libfoo_kref_sample_Platform (*_instance)();
          const char* (*get_name)(libfoo_kref_sample_Platform thiz);
        } Platform;
      } sample;
    } root;
  } kotlin;
} libfoo_ExportedSymbols;
extern libfoo_ExportedSymbols* libfoo_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBFOO_H */
