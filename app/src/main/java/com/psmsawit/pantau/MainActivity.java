package com.psmsawit.pantau;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 101;
    private WebView webView;
    private ValueCallback<Uri[]> filePathCallback;
    private String pendingFilename = "PSM_GAMBAR.png";
    private String pendingMime = "image/png";
    private StringBuilder pendingBase64 = new StringBuilder();

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 77);
            }
        }

        webView = new WebView(this);
        setContentView(webView);
        setupWindowSafeArea();

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            s.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }

        webView.addJavascriptInterface(new AndroidBridge(), "Android");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (MainActivity.this.filePathCallback != null) {
                    MainActivity.this.filePathCallback.onReceiveValue(null);
                }
                MainActivity.this.filePathCallback = filePathCallback;

                Intent intent;
                try {
                    intent = fileChooserParams.createIntent();
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("*/*");
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{
                        "application/json",
                        "text/csv",
                        "text/plain",
                        "application/vnd.ms-excel",
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                    });
                }

                try {
                    startActivityForResult(intent, FILE_CHOOSER_REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    MainActivity.this.filePathCallback = null;
                    Toast.makeText(MainActivity.this, "File manager tidak ditemukan", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleExternalUrl(url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return handleExternalUrl(request.getUrl().toString());
            }
        });

        webView.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            try {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } catch (Exception ignored) {}
        });

        webView.loadUrl("file:///android_asset/index.html");
    }

    private boolean handleExternalUrl(String url) {
        if (url == null) return false;
        if (url.startsWith("intent:") || url.startsWith("https://wa.me") || url.startsWith("whatsapp:")) {
            try {
                Intent intent;
                if (url.startsWith("intent:")) {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                } else {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                }
                startActivity(intent);
                return true;
            } catch (Exception e) {
                Toast.makeText(this, "Aplikasi tujuan tidak ditemukan", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public class AndroidBridge {
        @JavascriptInterface
        public void beginBase64File(String filename, String mime) {
            pendingFilename = sanitizeFilename(filename == null ? "PSM_GAMBAR.png" : filename);
            pendingMime = (mime == null || mime.trim().isEmpty()) ? "image/png" : mime;
            pendingBase64 = new StringBuilder();
        }

        @JavascriptInterface
        public void appendBase64Chunk(String chunk) {
            if (chunk != null) pendingBase64.append(chunk);
        }

        @JavascriptInterface
        public void finishBase64File() {
            try {
                byte[] bytes = Base64.decode(pendingBase64.toString(), Base64.DEFAULT);
                saveBytesToDownloads(pendingFilename, pendingMime, bytes);
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "File tersimpan di Download: " + pendingFilename, Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Gagal simpan file: " + e.getMessage(), Toast.LENGTH_LONG).show());
            } finally {
                pendingBase64 = new StringBuilder();
            }
        }

        @JavascriptInterface
        public void saveBase64File(String filename, String dataUrl) {
            try {
                filename = sanitizeFilename(filename == null ? "PSM_GAMBAR.png" : filename);
                String base64 = dataUrl;
                String mime = "image/png";
                if (dataUrl != null && dataUrl.startsWith("data:")) {
                    int comma = dataUrl.indexOf(",");
                    String meta = dataUrl.substring(5, comma);
                    if (meta.contains(";")) mime = meta.substring(0, meta.indexOf(";"));
                    base64 = dataUrl.substring(comma + 1);
                }
                byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
                saveBytesToDownloads(filename, mime, bytes);
                final String savedName = filename;
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "File tersimpan di Download: " + savedName, Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Gagal simpan file: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }

        @JavascriptInterface
        public void saveTextFile(String filename, String text, String mime) {
            try {
                filename = sanitizeFilename(filename == null ? "PSM_DATA.txt" : filename);
                if (mime == null || mime.trim().isEmpty()) mime = "text/plain";
                byte[] bytes = (text == null ? "" : text).getBytes(StandardCharsets.UTF_8);
                saveBytesToDownloads(filename, mime, bytes);
                final String savedName = filename;
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "File tersimpan di Download: " + savedName, Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Gagal simpan file: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }
    }

    private String sanitizeFilename(String name) {
        String safe = name.replaceAll("[\\\\/:*?\"<>|]+", "_").replaceAll("\\s+", "_");
        if (safe.length() > 160) safe = safe.substring(0, 160);
        if (!safe.contains(".")) safe += ".png";
        return safe;
    }

    private void saveBytesToDownloads(String filename, String mime, byte[] bytes) throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Downloads.DISPLAY_NAME, filename);
            values.put(MediaStore.Downloads.MIME_TYPE, mime);
            values.put(MediaStore.Downloads.IS_PENDING, 1);
            Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            if (uri == null) throw new Exception("Tidak bisa membuat file Download");
            try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                if (out == null) throw new Exception("OutputStream kosong");
                out.write(bytes);
            }
            values.clear();
            values.put(MediaStore.Downloads.IS_PENDING, 0);
            getContentResolver().update(uri, values, null, null);
        } else {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!dir.exists()) dir.mkdirs();
            File file = new File(dir, filename);
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(bytes);
            }
        }
    }


    private void setupWindowSafeArea() {
        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(125, 186, 145));
        window.setNavigationBarColor(Color.rgb(250, 251, 245));

        if (Build.VERSION.SDK_INT >= 23) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        // Android 15/edge-to-edge: beri padding sesuai status bar agar aplikasi tidak masuk ke bar notifikasi.
        if (Build.VERSION.SDK_INT >= 30) {
            webView.setOnApplyWindowInsetsListener((v, insets) -> {
                int top = insets.getInsets(WindowInsets.Type.statusBars()).top;
                int bottom = insets.getInsets(WindowInsets.Type.navigationBars()).bottom;
                v.setPadding(0, top, 0, bottom);
                return insets;
            });
        } else {
            webView.setFitsSystemWindows(true);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_CHOOSER_REQUEST_CODE) {
            Uri[] results = null;
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            if (filePathCallback != null) {
                filePathCallback.onReceiveValue(results);
                filePathCallback = null;
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (webView != null) {
            webView.evaluateJavascript(
                "(function(){try{if(window.handleAndroidBack){return window.handleAndroidBack()?'1':'0'}return '0'}catch(e){return '0'}})();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        if ("true".equals(value) || "\"1\"".equals(value) || "1".equals(value)) {
                            return;
                        }
                        if (webView.canGoBack()) {
                            webView.goBack();
                        } else {
                            MainActivity.super.onBackPressed();
                        }
                    }
                }
            );
        } else {
            super.onBackPressed();
        }
    }
}
