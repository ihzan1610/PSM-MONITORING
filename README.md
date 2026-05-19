# PSM Sawit Pantau - GitHub Debug APK

Paket ini siap di-upload ke GitHub untuk membuat **debug.apk** otomatis memakai GitHub Actions.

## Isi Paket

- Project Android WebView lengkap.
- Aplikasi HTML final di `app/src/main/assets/index.html`.
- Tema soft blue yang nyaman di mata.
- Logo launcher baru dengan konsep sawit + timbangan, tidak sama persis dengan logo aplikasi utama.
- Workflow GitHub Actions:
  `.github/workflows/build-debug-apk.yml`

## Cara Build APK Debug dari GitHub

1. Buat repository baru di GitHub.
2. Upload semua isi folder ini ke repository.
3. Buka tab **Actions**.
4. Pilih workflow **Build Debug APK**.
5. Klik **Run workflow**.
6. Tunggu sampai proses selesai.
7. Buka hasil workflow.
8. Download artifact bernama:
   `PSM-Sawit-Pantau-debug-apk`
9. Ekstrak artifact tersebut.
10. File APK ada di dalamnya:
    `app-debug.apk`

## Catatan Aplikasi

- Halaman 1: tarik data dari cloud URL aplikasi utama.
- Halaman 2: lokal saja, tidak memakai cloud.
- PIN default: `1234`.
- Fitur gambar memakai html2canvas dari CDN, perlu internet saat ekspor gambar.
- Fitur thermal/RawBT harus dites langsung di HP Android dengan aplikasi RawBT.

## Cara Update HTML

Ganti file:

```text
app/src/main/assets/index.html
```

Lalu commit/push ke GitHub dan jalankan ulang workflow.
