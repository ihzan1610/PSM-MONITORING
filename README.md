# PSM Sawit Pantau - Paket APK GitHub v23

Paket ini siap diupload ke GitHub untuk membuat APK debug otomatis lewat GitHub Actions.

## Cara upload ke GitHub

1. Buat repository baru di GitHub.
2. Upload semua isi folder ZIP ini ke repository.
3. Buka tab **Actions**.
4. Jalankan workflow **Build Debug APK**.
5. Setelah selesai, buka hasil workflow.
6. Download artifact: **PSM-Sawit-Pantau-debug-apk**.
7. Di dalam artifact ada file `app-debug.apk`.

## Isi penting

- `app/src/main/assets/index.html` = aplikasi full HTML.
- `.github/workflows/build-debug-apk.yml` = workflow pembuat APK debug.
- `app/src/main/java/com/psmsawit/pantau/MainActivity.java` = WebView Android.
- `app/src/main/AndroidManifest.xml` = izin internet, rotasi fullSensor, icon aplikasi.

## Catatan

APK ini adalah WebView wrapper dari full HTML. Data halaman 2 tetap tersimpan lokal di perangkat melalui localStorage.
