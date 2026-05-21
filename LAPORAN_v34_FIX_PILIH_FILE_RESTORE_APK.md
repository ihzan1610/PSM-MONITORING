# LAPORAN v34 - FIX PILIH FILE RESTORE DI APK

Masalah:
- Di APK, tombol pilih file pada RESTORE DATA PABRIK tidak membuka file manager.
- Penyebabnya WebView Android membutuhkan handler khusus untuk input type=file.

Perbaikan:
- MainActivity sekarang memakai WebChromeClient.onShowFileChooser().
- File chooser mendukung:
  .json
  .csv
  .txt
  .xlsx
  .xls
- Restore data pabrik dari JSON/CSV/Excel bisa memilih file langsung dari file manager HP.

Catatan:
- HTML tidak berubah besar; masalahnya ada di wrapper APK Android.
- Wajib build ulang APK dari paket v34 dan install APK baru.
- Kalau file manager HP tidak muncul, coba install/aktifkan aplikasi Files bawaan Android.

Hasil cek:
- JavaScript syntax: OK
- MainActivity onShowFileChooser: OK
- Paket ZIP: OK
