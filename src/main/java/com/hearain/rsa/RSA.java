package com.hearain.rsa;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSA {
	// 加密的种子信息
	//private static final String keyInfo = "ASDFSDFNUGD__TYTY";

	// rsa加密长度有限制 1024位的RSA最多能加密长117字节的分组数据。
	// javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes
	public static void main(String[] args) throws Exception {
		// 生成当前时间字符串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date());
		System.out.println("原文：" + str);
		KeyPair kp = genKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
		
		System.out.println("私钥:" + generateRSAPrivateKeyString(privateKey));
		System.out.println("公钥:" + generateRSAPublicKeyString(publicKey));
		
		System.out.println("噶收到");
		
		String encryptStr = toHexString(encrypt(str, privateKey));
		System.out.println("加密后文字:" + encryptStr);
		System.out.println("加密后文字:" + encryptStr.length());
		
		String signStr = toHexString(sign(str, privateKey));
		System.out.println("签名字符：" + signStr);
		System.out.println("签名字符：" + signStr.length());
		
		String decryptStr = new String(decrypt(encryptStr, publicKey));
		
		System.out.println("签名验证:" + verifySign(str, signStr, publicKey));
		
		System.out.println("解密后文字:" + decryptStr);
	}

	/**
	 * 加密,key可以是公钥，也可以是私钥
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(message.getBytes());
	}

	public static byte[] doFinal(Cipher cipher,byte[] content) throws Exception {
		return cipher.doFinal(content);
	}

	/**
	 * 解密，key可以是公钥，也可以是私钥，如果是公钥加密就用私钥解密，反之亦然
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(toBytes(message));
	}

	/**
	 * 用私钥签名
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(String message, PrivateKey key) throws Exception {
		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initSign(key);
		signetcheck.update(message.getBytes("utf-8"));
		return signetcheck.sign();
	}

	public static byte[] sign(byte[] content,Signature signetcheck) throws Exception {
		signetcheck.update(content);
		return signetcheck.sign();
	}

	/**
	 * 用公钥验证签名的正确性
	 * 
	 * @param message
	 * @param signStr
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(String message, String signStr, PublicKey key)
			throws Exception {
		if (message == null || signStr == null || key == null) {
			return false;
		}
		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initVerify(key);
		signetcheck.update(message.getBytes("utf-8"));
		return signetcheck.verify(toBytes(signStr));
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
			sb.append(HEXCHAR[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static byte[] toBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}

	private static final char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 根据keyInfo产生公钥和私钥
	 * 
	 * @param keyInfo
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		// random.setSeed(keyInfo.getBytes());
		// 初始加密，512位已被破解，用1024位,最好用2048位
		keygen.initialize(1024, random);
		// 取得密钥对
		KeyPair kp = keygen.generateKeyPair();
		return kp;

	}

	/**
	 * * 生成公钥 *
	 * 
	 * @param modulus *
	 * @param privateExponent *
	 * @return RSAPrivateKey *
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus,
			byte[] publicExponent) throws Exception {
		BigInteger m=new BigInteger(modulus);
		BigInteger pub=new BigInteger(publicExponent);
		return generateRSAPublicKey(m,pub);
	}
	
	
	/**
	 * 生成公钥
	 * @作者 赵睿琛
	 * @创建时间 Oct 31, 2011
	 * @param m
	 * @param pe
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(BigInteger m,BigInteger pub) throws Exception{
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(m, pub);
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * * 生成私钥 *
	 * 
	 * @param modulus *
	 * @param privateExponent *
	 * @return RSAPrivateKey *
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
			byte[] privateExponent) throws Exception {
		BigInteger m=new BigInteger(modulus);
		BigInteger pri=new BigInteger(privateExponent);
		return generateRSAPrivateKey(m,pri);
	}
	
	/**
	 * 生成私钥
	 * @作者 赵睿琛
	 * @创建时间 Oct 31, 2011
	 * @param m
	 * @param pri
	 * @return
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(BigInteger m,BigInteger pri) throws Exception{
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(m,pri);
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	// 公钥转换成modulus-publicExponent格式字符串
	public static String generateRSAPublicKeyString(RSAPublicKey publickey) {
		String modulus = publickey.getModulus().toString();
		String publicExponent = publickey.getPublicExponent().toString();
		return  modulus + "-" + publicExponent;
	}

	// 私钥转换成modulus-publicExponent格式字符串
	public static String generateRSAPrivateKeyString(RSAPrivateKey privatekey) {
		String modulus = privatekey.getModulus().toString();
		String privateExponent = privatekey.getPrivateExponent().toString();
		return  modulus + "-" + privateExponent; 
	}

	// modulus-privateExponent格式字符串转换成公钥
	public static RSAPublicKey string2RSAPublicKey(String string)
			throws Exception {
		String[] strings = string.split("-");
		RSAPublicKey publickKey = generateRSAPublicKey(new BigInteger(strings[0]), new BigInteger(strings[1]));
		return publickKey;
	}

	// modulus-privateExponent格式字符串转换成私钥
	public static RSAPrivateKey string2RSAPrivateKey(String string)
			throws Exception {
		String[] strings = string.split("-");
		
		RSAPrivateKey privateKey = generateRSAPrivateKey(new BigInteger(strings[0]),new BigInteger(strings[1]));
		return privateKey;
	}

}
