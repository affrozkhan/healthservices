package com.health.config;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Constants {

 
    public static final Long ACTIVE=1L;
    public static final Long IN_ACTIVE=0L;
    public static final String SUCCESS_KEY="success";
    public static final String ERROR_KEY="error";
    public static final String DATE_FORMAT="dd/MM/yyyy";
    public static final String TIME_FORMAT="HH:mm";
    
    
    public static final LocalDate convertDateToLocalDate(Date dateToConvert) {
    	if(dateToConvert==null){
    		return null;
    	}
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	}

	public static final LocalTime convertDateToLocalTime(Date dateToConvert) {
		if(dateToConvert==null){
    		return null;
    	}
		return LocalDateTime.ofInstant(dateToConvert.toInstant(),
				ZoneId.systemDefault()).toLocalTime();

	}
	
	public static final Date convertLocalDateToDate(LocalDate localDate) {
		if(localDate==null){
    		return null;
    	}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	}

	public static final Date convertLocalTimeToDate(LocalTime localTime) {
		if(localTime==null){
    		return null;
    	}
		Instant instant = localTime.atDate(LocalDate.now())
				.atZone(ZoneId.systemDefault()).toInstant();
		BigInteger milis = BigInteger.valueOf(instant.getEpochSecond()).multiply(
				BigInteger.valueOf(1000));
		milis = milis.add(BigInteger.valueOf(instant.getNano()).divide(
				BigInteger.valueOf(1_000_000)));
		return new Date(milis.longValue());

	}


}
