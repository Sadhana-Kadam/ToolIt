package com.floatinity.toolIt.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.floatinity.toolIt.exceptions.ToolItException;

/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class AESEncrypt5Pad {

	private static final Logger LOGGER = LogManager.getLogger(AESEncrypt5Pad.class);

	private Cipher ecipher;

	private Cipher dcipher;

	private static String aesKey = "ProToolItFLo@tiN!ty";

	public static AESEncrypt5Pad getInstance() throws ToolItException {
		try {
			return new AESEncrypt5Pad(aesKey.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error occurred in key generation : " + e);
		}
		return new AESEncrypt5Pad(aesKey.getBytes());
	}

	private AESEncrypt5Pad(byte[] key) throws ToolItException {
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		try {
			ecipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, keySpec);
			dcipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			dcipher.init(Cipher.DECRYPT_MODE, keySpec);
		} catch (Throwable cause) {
			LOGGER.error("Error occurred while intialization : ", cause);
			throw new ToolItException(cause);
		}
	}

	public String encrypt(String encrypt) throws ToolItException {
		try {
			return new String(this.encrypt(encrypt.getBytes()));
		} catch (Throwable t) {
			LOGGER.error("Error occurred while encrypting input : " + encrypt, t);
			throw new ToolItException(t);
		}
	}

	private byte[] encrypt(byte[] plain) throws ToolItException, IllegalBlockSizeException, BadPaddingException {
		if (this.ecipher == null) {
			throw new ToolItException("encryptor : " + this.ecipher);
		}
		return Base64.encodeBase64(this.ecipher.doFinal(plain));
	}

	public String decrypt(String encrypt) throws ToolItException {
		try {
			return new String(this.decrypt(encrypt.getBytes()));
		} catch (Throwable t) {
			LOGGER.error("Error occurred while decrypting input : " + encrypt, t);
			throw new ToolItException(t);
		}
	}

	private byte[] decrypt(byte[] encrypt) throws ToolItException, IllegalBlockSizeException, BadPaddingException {
		if (this.dcipher == null) {
			throw new ToolItException("decryptor : " + this.dcipher);
		}
		return this.dcipher.doFinal(Base64.decodeBase64(encrypt));

	}

	public static void main(String[] args) throws ToolItException, BadPaddingException {
		String token = "Smile@123";
		AESEncrypt5Pad aes = getInstance();
		String encrypted = aes.encrypt(token);
		System.out.println(encrypted);
		System.out.println(aes.decrypt(encrypted));

	}
}
