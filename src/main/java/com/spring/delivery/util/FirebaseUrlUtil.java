/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:50 AM - 08/01/2025
 * User: lam-nguyen
 **/

package com.spring.delivery.util;

import com.google.firebase.cloud.StorageClient;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FirebaseUrlUtil {

    public static String getSignUrl(String firebaseUrl) {
        var bucket = StorageClient.getInstance().bucket();
        var blob = bucket.get(firebaseUrl);
        var url = blob.signUrl(15, java.util.concurrent.TimeUnit.MINUTES);
        if (url == null)
            throw new AppException(AppErrorCode.NOT_EXIST);
        return url.toString();
    }

    public static String getPublicUrl(String firebaseUrl) {
        String encodedFilePath = URLEncoder.encode(firebaseUrl, StandardCharsets.UTF_8).replace("+", "%20");
        return "https://firebasestorage.googleapis.com/v0/b/delivery-react-native-ap-fa468.appspot.com/o/" + encodedFilePath + "?alt=media";
    }
}
