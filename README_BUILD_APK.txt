PSM SAWIT PANTAU - PAKET APK ANDROID STUDIO

ISI PAKET:
- app/src/main/assets/index.html  = aplikasi HTML final
- app/src/main/java/.../MainActivity.java = WebView Android
- build.gradle dan setting Android Studio

CARA BUILD APK:
1. Ekstrak ZIP ini.
2. Buka Android Studio.
3. Pilih Open, lalu pilih folder PSM_Sawit_Pantau_APK_Project.
4. Tunggu Gradle Sync selesai.
5. Pilih Build > Build Bundle(s) / APK(s) > Build APK(s).
6. APK debug akan muncul di:
   app/build/outputs/apk/debug/app-debug.apk

CATATAN:
- Halaman 1 memakai cloud URL untuk TARIK data kebun.
- Halaman 2 memakai mode lokal saja, data tersimpan di HP/browser WebView.
- Restore JSON halaman 2 memakai file picker Android.
- Simpan gambar/CSV/backup akan masuk ke folder Download.
- Print thermal RawBT memerlukan aplikasi RawBT terpasang di HP.

UNTUK MENGUBAH HTML:
- Edit file: app/src/main/assets/index.html
- Build ulang APK.
