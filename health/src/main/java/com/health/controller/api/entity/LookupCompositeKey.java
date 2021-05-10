package com.health.controller.api.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupCompositeKey implements Serializable{

	private String lookupKey;
	private Long lookupValue;

}
