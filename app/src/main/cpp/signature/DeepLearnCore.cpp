

#include <cstring>
#include <cstdio>
#include <malloc.h>
#include <jni.h>


jint getEnvironment(JNIEnv *env);

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getXCTSecretKey(JNIEnv *env,
                                                          jobject instance) {
    jint envType = getEnvironment(env);
    if (envType == 0) { // PROD
        return env->NewStringUTF("deep-learn-android-Ms49lsRzqgw4");
    } else if (envType == 1) { // STAG
        return env->NewStringUTF("szEicnjcd1");
    } else  // TEST
        return env->NewStringUTF("szEicnjcd1");
}



extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getXCTMessage(JNIEnv
                                                        *env,
                                                        jobject instance
) {
    return env->NewStringUTF("DPLNB");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getXCTPublicKey(JNIEnv
                                                          *env,
                                                          jobject instance
) {
    jint envType = getEnvironment(env);
    if (envType == 0) { // PROD
        return env->NewStringUTF(
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCV0OeWTL5k1PdtC0YBlXLsoqChOvrI3X4A5/pvmmQGL+KkIPHbzKFy3TWrwTuyn9ghEz2JHrjkdCGsCnlLFm1XQp7TRrR7xkIA6FmXfsc+eaIfBJh0sj/Onsy9v0aqUdH6evoRbb/gWqJGYXRRcPSKIZOEInGvUpQLfRSPy0kqhQIDAQAB");
    } else if (envType == 1) { // STAG
        return env->NewStringUTF(
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA33d/ObvH5A+UkxrtoAI5MmC4R0saRRomrbnVPZj2oDSeCUgQL3WKDb/xxwbRVu+ZIcbLBWal3aoLH9W6TKTHbZY6czkTHaT1RzeWX7nWwiwuZOVIc7gdH6ty+u+p00XTI0Rnme7dNlRF/8f92JQ045sqqSxb78pOYzCQZFxdhQIDAQAB");
    } else  // TEST
        return env->NewStringUTF(
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA33d/ObvH5A+UkxrtoAI5MmC4R0saRRomrbnVPZj2oDSeCUgQL3WKDb/xxwbRVu+ZIcbLBWal3aoLH9W6TKTHbZY6czkTHaT1RzeWX7nWwiwuZOVIc7gdH6ty+u+p00XTI0Rnme7dNlRF/8f92JQ045sqqSxb78pOYzCQZFxdhQIDAQAB");

//    return env->NewStringUTF(  // Test
//            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA33d/ObvH5A+UkxrtoAI5MmC4R0saRRomrbnVPZj2oDSeCUgQL3WKDb/xxwbRVu+ZIcbLBWal3aoLH9W6TKTHbZY6czkTHaT1RzeWX7nWwiwuZOVIc7gdH6ty+u+p00XTI0Rnme7dNlRF/8f92JQ045sqqSxb78pOYzCQZFxdhQIDAQAB");

//    return env->NewStringUTF(   //Stagging
//            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA33d/ObvH5A+UkxrtoAI5MmC4R0saRRomrbnVPZj2oDSeCUgQL3WKDb/xxwbRVu+ZIcbLBWal3aoLH9W6TKTHbZY6czkTHaT1RzeWX7nWwiwuZOVIc7gdH6ty+u+p00XTI0Rnme7dNlRF/8f92JQ045sqqSxb78pOYzCQZFxdhQIDAQAB");

//    return env->NewStringUTF(     // Prod
//            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCV0OeWTL5k1PdtC0YBlXLsoqChOvrI3X4A5/pvmmQGL+KkIPHbzKFy3TWrwTuyn9ghEz2JHrjkdCGsCnlLFm1XQp7TRrR7xkIA6FmXfsc+eaIfBJh0sj/Onsy9v0aqUdH6evoRbb/gWqJGYXRRcPSKIZOEInGvUpQLfRSPy0kqhQIDAQAB");
}

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getXCTDelimiter(JNIEnv
                                                          *env,
                                                          jobject instance
) {
    return env->NewStringUTF("\n");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getTestRSAKey(JNIEnv
                                                        *env,
                                                        jobject instance
) {
    return env->NewStringUTF(
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMgshYmFh9XJybVtS5U6u2gR9Wj9dSb3C4IjfwrlrE03TxhqjZI4TCk2s5oq1qAInt5Arbi+SVkPhm5U+G0JJ1xy+WsqizogqTgjhcSftJ7sc4c1PHfU0fGjYwD9sgdjMszTRw1FFaHvMRZHnDDhiKkTGxHZtfOjSw5w94xdD7SwIDAQAB");
}



extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getPallyConDRMLicenseUrl(JNIEnv
                                                                   *env,
                                                                   jobject instance
) {
    return env->NewStringUTF("/drm/widevine");
}

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getPallyConDRMOfflineLicenseUrl(JNIEnv
                                                                          *env,
                                                                          jobject instance
) {
    return env->NewStringUTF("/drm/offline/widevine");
}

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getFreshChatAppId(JNIEnv
                                                            *env,
                                                            jobject instance
) {
    return env->NewStringUTF("ea070440-7981-438f-9b7e-7cf61db07d56");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getFreshChatAppKey(JNIEnv
                                                             *env,
                                                             jobject instance
) {
    return env->NewStringUTF("4ecd815d-678b-4c4d-a0fd-31e691602bce");
}

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getFreshChatDomain(JNIEnv
                                                             *env,
                                                             jobject instance
) {
    return env->NewStringUTF("msdk.freshchat.com");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getAppsFlyerDevKey(JNIEnv
                                                             *env,
                                                             jobject instance
) {
    return env->NewStringUTF("537Tb9yZjLAyg75YmuK9DV");
}



extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getAppRSAPublicKey(JNIEnv
                                                             *env,
                                                             jobject instance
) {
    return env->NewStringUTF(
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMaFQ9bB4odg7sLEAd0HCVAuS7nl8xEYr/g72d3cqSuw+hKtgtwN7UnsycuMSNaRPH77GDESmqnyB4qZcacMu5MCAwEAAQ==");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getAppRSAPrivateKey(JNIEnv
                                                              *env,
                                                              jobject instance
) {
    return env->NewStringUTF(
            "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAxoVD1sHih2DuwsQB3QcJUC5LueXzERiv+DvZ3dypK7D6Eq2C3A3tSezJy4xI1pE8fvsYMRKaqfIHiplxpwy7kwIDAQABAkB1NNcRo1UAQr2Upi7HdVENUtgIwHfavy2drJB7ybzjesVvUAYo4Yow0LA4sxfx1jMp1eHiU8zfkydT5gOAs6iZAiEA9YUd3JExEQTkXhV6ggUmgSuweJ+ZZ4rhEjBVaQ7oGicCIQDO/pGyjYeGQBQDFkDhLWhKVXzeU/cBTj1M+83ivriStQIgXoxy4XvwX1N49lpjISsGlvvSK8Gae+tZnX2Lhre83kUCIDNHxfiiL20G+u442SHwCeJAg4rg6lSceXtU9pCyBy/xAiEA88UK9tYzVcX1xfM9gjw9uYZJFm8ls7HsIQcW8yVVwII=");
}



extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getZoomDomain(JNIEnv
                                                        *env,
                                                        jobject instance
) {
    return env->NewStringUTF("zoom.us");
}



extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getZoomSdkApiKey(JNIEnv
                                                           *env,
                                                           jobject instance
) {
    return env->NewStringUTF("hYXmYoPmUVImIzz11SZ8CtGagh0GCDVsANdS");
}


extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getZoomSecretKey(JNIEnv
                                                           *env,
                                                           jobject instance
) {
    return env->NewStringUTF("KfOVj0UBvVcAvuYMeoeNnvrBX3Xa7dTN0D0f");
}


jint getEnvironment(JNIEnv *env) {
    jclass keyProviderClass = env->FindClass("com/frost/leap/provider/KeysProvider");
    jfieldID environmentTypeField = env->GetStaticFieldID(keyProviderClass, "ENVIRONMENT_TYPE",
                                                          "I");
    return env->GetStaticIntField(keyProviderClass, environmentTypeField);
}

extern "C" JNIEXPORT jstring
Java_com_frost_leap_provider_KeysProvider_getQRCodeDevKey(JNIEnv
                                                          *env,
                                                          jobject instance
) {
    return env->NewStringUTF(
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHsKElU28lU85iY2yj/JS5lhsGve6IFTC4XPzCP5zYZ5yHEwmfZ3wP9NNE4yJedl8/jcOo8oL7vjdmZtWr2naUWu1JcJLKyNrRAPzBDy4mC/m6tT/QZDnv8Y9fjD1DqaFmlF/oUpQSTq5db1RiWT6/Jxg08DPAJIfpEkMymJGcc4kEr5emYZGeAXXQzalpNokc0XPxgcePExFcIdf1TEgvtrFvI1n01Udh0K9X8ht/5keRHApj+4s2r9nkajNT+lxSjH6QYVi9a5wTvcvmDzohTXstFQg+qI1ILIMUysX5ZFA0+6GUnHZXH12pWkzEnvlBnTBTiqDywibdKio6KywDAgMBAAECggEAZznBM6BbtjoKWAX5ZRPudblIo0WPhr0dgFBhQLDNjXiSC6klskc2dLEkfVnErHwYZKwN1sn2N9hunvrNvZai8bHHM82DtFv78UrHXa1RN828/G0xXbJR2N1QNh4ylUWc74AHy/rUHFqb/CTOxEBKFVpKQyNgPRUlRLByGDeSHgMZrI4tnH+itzH+zdp75tSHfFR4AsKQAxCN2tZJ1T51DG2Mn3R+QIsXCLQ6Wza6hkix5oKEHKPs26yxYqHJUxlYqdtazjegpepanqYLEJzyU+IDmnbj+kaDLAHKfm765extHpRf5MoIACKQFO1k1g8IzVa2FSuOCmQdzJTL2L2M8QKBgQDk/IEA3y1G7ig6T6r4xSw+vswLv83Mg7A9QqH+xdif5w+jGJdtNiKRyjj7ZI1IW7p5ThH5BeiLoIf8Z0cr7NjyoloBvPhyZz6GM0ZB89zm4Kw5QJJ/dGsNTd6+V/bSPVLXAz8v+4VKESgNeaSpy6aC4Oiei1dXPSPKzv5fKdhmbQKBgQDfP1xhWS/avP3WFPYvFO5zKoBGB4Sn5TDrE3fdFosUk8ZoW+NfAcJ5tz6xtABiqYy5bU6xBzRAOmSFXqUbd186Hk+PY0xBBLWNYh3nzuwxi0kq25RdSSvKpb0tmvqKb2ej4YzRpdTV9h6bA9M+r7vShOt+onsccj5QkG0MU08WLwKBgHUNcnBcpUt14JB2OyKxv/1PEQUMhgn+Yy/diOuwfRy4Q7EOmBGWs7Ms2VYAv4v2f+sUcnGGiKofZIQs0x/sEbZuMW5kB+iLSFXXjhmWrBxFSyR1Eq4ed760SKGqW5x7BoasvTcs7qS/AmdpADhfxv1uNNsexYXt1gcNKtDAlkM9AoGBAI+LMwAZE48G9APiCIUvZT7BtibRELfPw0tzgh+zLgw2FF3nr2PYQs52R6aFQbYupKBZqz5ijnM52IZ02560e5NoB+n/g/lOaNoWm3oAb5hPoeUfuN7efHzZYrFfM/ctOKuEDEKE6TwF+5natMpavl7XPoB3QseAoZa1tpg1POXbAoGAIjaXMwWg3Xf1PRKVSWXSJKRkLT16T0GFKVUFA/A/e0QiXe8uxu15NsxWREWrIfh3P0FN1C+yEnHuTHqDYlf4RUKivLKfiSwY0s9KIEnGyu0p+nESOfefGZmUaQB7GKJRcsGEmV3Jdjo5VWWK3WurGC55Wb6zL01G8QjxTlBg0hg=");
}



