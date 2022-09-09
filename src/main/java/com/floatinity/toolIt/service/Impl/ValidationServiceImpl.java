package com.floatinity.toolIt.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floatinity.toolIt.dao.IUserDAO;
import com.floatinity.toolIt.exceptions.ToolItError;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.service.IValidationService;
import com.floatinity.toolIt.util.AESEncrypt5Pad;
import com.floatinity.toolIt.util.ValidationUtil;

@Service
public class ValidationServiceImpl implements IValidationService {

	private static final Logger LOG = LogManager.getLogger(ValidationServiceImpl.class);

	@Autowired
	private IUserDAO userDAO;

	public String validatePassword(String pass) throws ToolItException {
		// Decrypt password field
		String decryptedNewPass = "";
		try {
			decryptedNewPass = AESEncrypt5Pad.getInstance().decrypt(pass);
		} catch (Exception e) {
			LOG.error("New Password decryption failed, exception : {} ", e);
			throw new ToolItException(ToolItError.TI_ERR_NEW_PASSWORD_INVALID);
		}
		// Validate password field
		if (!ValidationUtil.validatePassword(decryptedNewPass)) {
			LOG.error("Invalid combination for new password");
			throw new ToolItException(ToolItError.TI_ERR_INVALID_PASSWORD_COMBINATION);
		}
		return decryptedNewPass;
	}

	public String validateEncryption(String pass) throws ToolItException {
		// Decrypt password field
		String decryptedString = "";
		try {
			decryptedString = AESEncrypt5Pad.getInstance().decrypt(pass);
		} catch (Exception e) {
			LOG.error("New string decryption failed, exception : {} ", e);
			throw new ToolItException(ToolItError.TI_ERR_DECRYPTION_FAILED);
		}
		return decryptedString;
	}

}
