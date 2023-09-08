package com.galaxy.memorit.friend.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendRegisterCommand {
	private String name;
	private String category;
}
