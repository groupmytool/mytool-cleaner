#include <jni.h>
#include <CoreServices/CoreServices.h>

extern "C" {
    JNIEXPORT jstring JNICALL Java_com_mytool_cleaner_utils_FileMetaData_getMetadata
        (JNIEnv *env, jobject obj, jstring path) {
        const char *nativePath = env->GetStringUTFChars(path, 0);

        CFStringRef cfPath = CFStringCreateWithCString(kCFAllocatorDefault, nativePath, kCFStringEncodingUTF8);
        MDItemRef item = MDItemCreate(kCFAllocatorDefault, cfPath);
        CFRelease(cfPath);

        if (item == NULL) {
            env->ReleaseStringUTFChars(path, nativePath);
            return NULL;
        }

        CFArrayRef attributeNames = MDItemCopyAttributeNames(item);
        CFDictionaryRef metadata = MDItemCopyAttributes(item, attributeNames);
        CFRelease(item);
        CFRelease(attributeNames);

        if (metadata == NULL) {
            env->ReleaseStringUTFChars(path, nativePath);
            return NULL;
        }

        CFStringRef metadataStr = CFCopyDescription(metadata);
        CFRelease(metadata);

        if (metadataStr == NULL) {
            env->ReleaseStringUTFChars(path, nativePath);
            return NULL;
        }

        const char *nativeMetadata = CFStringGetCStringPtr(metadataStr, kCFStringEncodingUTF8);
        jstring result = env->NewStringUTF(nativeMetadata);
        CFRelease(metadataStr);

        env->ReleaseStringUTFChars(path, nativePath);
        return result;
    }
}