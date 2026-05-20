# LAPORAN PERBAIKAN v25

Fitur baru lembar kedua:
- Harga jual pabrik, ongkos truk/kg, dan gaji supir truk/kg sekarang mengingat input terakhir.
- Ingatan berdasarkan kombinasi ASAL + SPB.
- Saat tambah data baru, setelah memilih/mengetik ASAL dan SPB, angka otomatis terisi.
- Angka tetap bisa diedit manual sebelum disimpan.
- Setelah data disimpan, nilai terbaru menjadi referensi berikutnya.

Urutan pengisian:
1. Pilih/ketik ASAL.
2. Pilih/ketik SPB.
3. Harga, ongkos, dan gaji akan otomatis terisi jika sebelumnya pernah ada kombinasi ASAL + SPB tersebut.
4. Kalau belum ada kombinasi sama persis, aplikasi fallback memakai riwayat SPB terakhir.

Hasil cek:
- JavaScript syntax: OK
- Marker fitur auto-fill tarif: OK
