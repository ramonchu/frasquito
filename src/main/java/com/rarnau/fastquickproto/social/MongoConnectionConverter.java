package com.rarnau.fastquickproto.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;

import com.rarnau.fastquickproto.model.SocialConnection;

/**
 * A converter class between Mongo document and Spring social connection.
 *
 * @author Carlo Micieli
 */
public class MongoConnectionConverter {
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private final TextEncryptor textEncryptor;

	@Autowired
	public MongoConnectionConverter(ConnectionFactoryLocator connectionFactoryLocator, TextEncryptor textEncryptor) {

		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	public Connection<?> convert(SocialConnection cnn) {
		if (cnn == null) {
			return null;
		}

		ConnectionData connectionData = fillConnectionData(cnn);
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData
				.getProviderId());
		return connectionFactory.createConnection(connectionData);
	}

	private ConnectionData fillConnectionData(SocialConnection uc) {
		return new ConnectionData(uc.getProviderId(), uc.getProviderUserId(), uc.getDisplayName(), uc.getProfileUrl(),
				uc.getImageUrl(), decrypt(uc.getAccessToken()), decrypt(uc.getSecret()), decrypt(uc.getRefreshToken()),
				uc.getExpireTime());
	}

	public SocialConnection convert(Connection<?> cnn) {
		ConnectionData data = cnn.createData();

		SocialConnection userConn = new SocialConnection();
		userConn.setProviderId(data.getProviderId());
		userConn.setProviderUserId(data.getProviderUserId());
		userConn.setDisplayName(data.getDisplayName());
		userConn.setProfileUrl(data.getProfileUrl());
		userConn.setImageUrl(data.getImageUrl());
		userConn.setAccessToken(encrypt(data.getAccessToken()));
		userConn.setSecret(encrypt(data.getSecret()));
		userConn.setRefreshToken(encrypt(data.getRefreshToken()));
		userConn.setExpireTime(data.getExpireTime());
		return userConn;
	}

	// helper methods

	private String decrypt(String encryptedText) {
		return encryptedText != null ? textEncryptor.decrypt(encryptedText) : encryptedText;
	}

	private String encrypt(String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}
}
