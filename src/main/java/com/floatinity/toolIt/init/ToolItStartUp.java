package com.floatinity.toolIt.init;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.floatinity.toolIt.exceptions.ToolItRuntimeException;
import com.floatinity.toolIt.service.Impl.AuthServiceImpl;

@Component
public class ToolItStartUp implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LogManager.getLogger(AuthServiceImpl.class);

//	public static final List<String> MAC_ADDRESS = Arrays.asList("A0-88-B4-D1-27-14",
//			"18-5E-0F-58-31-26","C8-D9-D2-21-D5-87");

//	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		try {
			LOG.info("Starting ToolIt Application");
			LOG.info("Current Server Time : " + new Date());

//			if (!MAC_ADDRESS.contains(getMacAddress())) {
//				LOG.error("Invalid MAC address.");
//				throw new Exception("Invalid MAC address.");
//			}
		} catch (Exception e) {
			LOG.error("App Initialization Failed.", e);
			throw new ToolItRuntimeException("App Initializaion Exception");
		}

		LOG.info("Started Context");
	}

	private String getMacAddress() throws Exception {
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		String macAddress = sb.toString();
		LOG.info("Current MAC address of machine : {}", macAddress);
		return macAddress;
	}

}
