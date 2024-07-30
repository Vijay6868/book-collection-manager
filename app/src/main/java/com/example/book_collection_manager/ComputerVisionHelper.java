package com.example.book_collection_manager;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ComputerVisionHelper {
    private static final String TAG = "ComputerVisionHelper";
    private static final String ENDPOINT = "https://book-collection-manager-computer-vision-instance.cognitiveservices.azure.com/";
    private static final String SUBSCRIPTION_KEY = "";

    public interface OcrCallback {
        void onOcrSuccess(String extractedText);
        void onOcrFailure(Exception e);
    }

    public static void analyzeImage(Bitmap bitmap, OcrCallback callback) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), byteArray);

                String url = ENDPOINT + "/vision/v3.0/ocr?language=unk&detectOrientation=true";
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY)
                        .addHeader("Content-Type", "application/octet-stream")
                        .post(requestBody)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    String responseBody = response.body().string();
                    String extractedText = extractTextFromResponse(responseBody);
                    callback.onOcrSuccess(extractedText);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error analyzing image", e);
                callback.onOcrFailure(e);
            }
        }).start();
    }

    private static String extractTextFromResponse(String responseBody) {
        StringBuilder extractedText = new StringBuilder();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

        jsonObject.getAsJsonArray("regions").forEach(region -> {
            region.getAsJsonObject().getAsJsonArray("lines").forEach(line -> {
                line.getAsJsonObject().getAsJsonArray("words").forEach(word -> {
                    extractedText.append(word.getAsJsonObject().get("text").getAsString()).append(" ");
                });
                extractedText.append("\n");
            });
        });

        return extractedText.toString();
    }
}