# Randomness-Related PoC

## Overview

This PoC demonstrates a randomness-related vulnerability in `a1_case1.apk`.

The issue is that `Login.generateSessionToken()` uses `java.util.Random` to generate a session token. A session token is security-sensitive because it is created after a successful login and stored as the user's session credential.

This PoC focuses on reproducing the vulnerable behavior and collecting evidence. It does not attempt full token prediction or session hijacking.

## Vulnerability Summary

- APK: `a1_case1.apk`
- Package name: `com.example.mastg_test0016`
- Affected function: `Login.generateSessionToken()`
- Sensitive value: `sessionToken`
- Storage location: `SessionPrefs.xml`
- Root cause: `java.util.Random` is used for a security-sensitive session token instead of `java.security.SecureRandom`

## Static Evidence

From the decompiled source:

- `Login.generateSessionToken()` creates a 16-character token with `new Random()`
- `Login.createSession()` stores that value under `KEY_SESSION_TOKEN`
- `KEY_SESSION_TOKEN` is `"sessionToken"`

Relevant local files:

- `/Users/hex/Documents/New project/tmp_apk_jadx/sources/com/example/mastg_test0016/Login.java`
- `/Users/hex/Documents/New project/tmp_apk_jadx/sources/com/example/mastg_test0016/Profile.java`
- `/Users/hex/Documents/New project/tmp_apk_jadx/sources/com/example/mastg_test0016/MainActivity.java`

## Tested Environment

- Host OS: macOS Darwin 25.3.0 (arm64)
- Machine model: MacBook Pro (Mac16,8)
- Chip: Apple M4 Pro
- CPU cores: 12 (8 performance + 4 efficiency)
- Memory: 24 GB
- Android Studio: Panda 2 | 2025.3.2
- ADB version: 1.0.41
- ADB build: 36.0.2-14143358
- Emulator profile: Pixel 6
- Emulator image: Android Open Source, API 33 (Android 13.0), arm64
- Emulator privilege state: `adbd is already running as root`

## Requirements

- Android Studio with Device Manager
- `adb` installed and available in terminal
- AOSP-style emulator image where `adb root` works
- APK file available locally

## Files Used

- APK under test: `a1_case1.apk`
- Decompiled evidence file: `tmp_apk_jadx/sources/com/example/mastg_test0016/Login.java`

The decompiled source was generated from the APK using JADX.

Recommended layout for submission:

```text
submission/
├── README.md
├── a1_case1.apk
└── evidence/
    ├── randomness-poc.mp4
    └── randomness-proof.png
```

If your APK is stored elsewhere, adjust the commands accordingly.

## Reproduction Steps

### 1. Start the rooted emulator

Launch the `Pixel 6` API 33 Android Open Source emulator from Android Studio.

Important:

- A Google Play or production image is not suitable for this PoC
- If `adb root` returns `adbd cannot run as root in production builds`, switch to an AOSP image and try again

Confirm the device is online:

```bash
adb devices
```

Confirm root access works:

```bash
adb root
```

Expected output:

```text
adbd is already running as root
```

### 2. Install and start the APK

```bash
adb install -r "./a1_case1.apk"
adb shell pm clear com.example.mastg_test0016
adb shell am start -n com.example.mastg_test0016/.MainActivity
```

### 3. Trigger session creation in the app

In the emulator:

1. Enter a test username
2. Enter a test password
3. Tap `Register`
4. On the login screen, enter the same credentials
5. Tap `Login`
6. Wait until the `Profile` screen appears

This step triggers `createSession()` after successful login.

### 4. Extract the generated session token

```bash
adb shell cat /data/data/com.example.mastg_test0016/shared_prefs/SessionPrefs.xml
```

Expected output format:

```xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="sessionToken">...</string>
</map>
```

### 5. Repeat to show dynamic token generation

Run the app again from a clean state:

```bash
adb shell pm clear com.example.mastg_test0016
adb shell am start -n com.example.mastg_test0016/.MainActivity
```

Repeat the register and login flow, then read the file again:

```bash
adb shell cat /data/data/com.example.mastg_test0016/shared_prefs/SessionPrefs.xml
```

Observed example values from testing:

- First token: `1af5x20CSitqB1B3`
- Second token: `1TLRuIRgvXJR5NUC`

This shows that a new session token is generated and stored after login.

## Why This Proves the Vulnerability

Reading the token alone does not prove the vulnerability. The vulnerability is proven by combining:

1. Static evidence that `java.util.Random` is used in `generateSessionToken()`
2. Dynamic evidence that the generated value is actually used as `sessionToken`
3. Security analysis showing that a session token is a security-sensitive value and should be generated with `SecureRandom`

Therefore, the issue is not merely that a token exists. The issue is that a security-sensitive session token is generated with an inappropriate random source.

## Video Recording Procedure

Use this sequence for the final PoC video:

1. Show the decompiled file `tmp_apk_jadx/sources/com/example/mastg_test0016/Login.java`
2. Highlight `generateSessionToken()` and `createSession()`
3. Open a terminal and show:

```bash
adb devices
adb root
adb install -r "./a1_case1.apk"
adb shell pm clear com.example.mastg_test0016
adb shell am start -n com.example.mastg_test0016/.MainActivity
```

4. Move to the emulator and register a test account
5. Log in with that test account
6. Show that the `Profile` screen is reached
7. Return to the terminal and run:

```bash
adb shell cat /data/data/com.example.mastg_test0016/shared_prefs/SessionPrefs.xml
```

8. Pause briefly on the line:

```xml
<string name="sessionToken">...</string>
```

9. Explain that this token is generated by `java.util.Random`, which is unsuitable for a session credential

Suggested narration:

> This application creates a session token after a successful login and stores it in `SessionPrefs`. Static analysis shows that the token is generated by `java.util.Random` in `Login.generateSessionToken()`. Because a session token is security-sensitive and should be unpredictable, using `java.util.Random` weakens session security and constitutes a randomness-related vulnerability.

## Optional Screen Recording Commands

Start recording:

```bash
adb shell screenrecord /sdcard/randomness-poc.mp4
```

Stop recording with `Ctrl+C`, then pull the file:

```bash
adb pull /sdcard/randomness-poc.mp4
```

Optional screenshot:

```bash
adb exec-out screencap -p > randomness-proof.png
```

## Mitigation

Replace `java.util.Random` with `java.security.SecureRandom` for session token generation.

Recommended improvements:

- Use `SecureRandom`
- Increase token entropy
- Generate random bytes and encode them safely, such as Base64 or hex

## Notes

- This PoC demonstrates vulnerable behavior and reproduces the issue
- This PoC does not claim full exploitability such as practical token prediction or session hijacking
- The goal is to show that the app uses an insecure random source for a security-sensitive session token
