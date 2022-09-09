package com.floatinity.toolIt.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.floatinity.toolIt.exceptions.ToolItException;


/**
 * CREATED BY AKSHAY KHANDAGALE ON 01-Mar-2022
 */
public class AESEncrypter {

	private static final Logger LOGGER = LogManager.getLogger(AESEncrypter.class);

	public static byte[] KEY = { 'P', 'r', 'o', 'G', 'M', 's', 'F', 'L', 'o', '@', 't', 'i', 'N', '!', 't', 'y' };

	private Cipher ecipher;

	private Cipher dcipher;

	public static AESEncrypter getInstance() throws ToolItException {
		return new AESEncrypter(KEY);
	}

	public static AESEncrypter getInstance(byte[] key) throws ToolItException {
		return new AESEncrypter(key);
	}

	private AESEncrypter(byte[] key) throws ToolItException {
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		try {
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(key));
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(key));
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
		AESEncrypter aes = getInstance();
		String encrypted = aes.encrypt(token);
		System.out.println(encrypted);
		System.out.println(aes.decrypt(encrypted));

	}
}
