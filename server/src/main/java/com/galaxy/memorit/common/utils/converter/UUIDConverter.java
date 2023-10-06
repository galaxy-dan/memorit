package com.galaxy.memorit.common.utils.converter;

import java.util.UUID;

public interface UUIDConverter {
	default UUID stringToUUID(String str){
		String formattedUUID = String.format(
			"%s-%s-%s-%s-%s",
			str.substring(0, 8),
			str.substring(8, 12),
			str.substring(12, 16),
			str.substring(16, 20),
			str.substring(20)
		);
		return UUID.fromString(formattedUUID);
	}

	default String UUIDToHexString(UUID uuid){
		return uuid.toString().replace("-", "");
	}

}
