package com.galaxy.memorit.friend.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendRegisterCommand {
	private byte[] userId;
	private String name;
	private String category;
}
