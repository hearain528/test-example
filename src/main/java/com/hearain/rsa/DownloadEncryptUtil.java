package com.hearain.rsa;

import javax.crypto.Cipher;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadEncryptUtil {
    /**
     * 下载加密密文信息
     */
    private RSAPrivateKey privateKey=null;

    private DownloadEncryptUtil() {
    }

    private static DownloadEncryptUtil instance;

    public static DownloadEncryptUtil getInstance(String encryptKey) {
        try{
            instance = new DownloadEncryptUtil();
            instance.privateKey = RSA.string2RSAPrivateKey(encryptKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;

    }

    private Map<String,String> getEncodeUrl(List<String> uuidList) throws Exception {
        Map<String,String> encodedUrlList = new HashMap<String,String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        Signature signetcheck = Signature.getInstance("MD5withRSA");
        signetcheck.initSign(privateKey);

        for (String uuid : uuidList) {
            String toEncodeStr = dateStr + "#" + uuid;
            // 获取加密私钥
            String encodeStr = RSA.toHexString(RSA.doFinal(cipher,toEncodeStr.getBytes()));
            // 签名
            String signStr = RSA.toHexString(RSA.sign(toEncodeStr.getBytes("utf-8"),signetcheck));
            encodedUrlList.put(uuid,encodeStr+ "/" + signStr);
        }
        return encodedUrlList;
    }

    /**
     * 下载地址加密地址生成
     *
     * @return
     * @throws Exception
     * @方法作者：石庆军
     * @创建时间：2013-4-27 上午11:21:21
     */
    public String getEncodeUrl(String uuid, String encryptKey) throws Exception {
        return this.getEncodeUrl(uuid, null, encryptKey);
    }

    public String getEncodeUrl(String uuid, String dateStr, String encryptKey) throws Exception {
        // 下载地址加密、签名
        String privateKey = encryptKey;
        // 生成当前时间字符串
        if (dateStr == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateStr = sdf.format(new Date());
        }
        String toEncodeStr = dateStr + "#" + uuid;
        // 获取加密私钥
        String encodeStr = RSA.toHexString(RSA.encrypt(toEncodeStr, RSA.string2RSAPrivateKey(privateKey)));
        String uploadURL = encodeStr;
        // 签名
        String signStr = RSA.toHexString(RSA.sign(toEncodeStr, RSA.string2RSAPrivateKey(privateKey)));
        uploadURL += "/" + signStr;
        return uploadURL;
    }

}
