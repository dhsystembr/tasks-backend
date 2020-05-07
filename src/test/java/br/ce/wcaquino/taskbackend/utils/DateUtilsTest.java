package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.Assert;

public class DateUtilsTest {
	@Test
	public void deveRetornarTrueParaDatasFuturas(){
		LocalDate	date = LocalDate.of(2030, 01, 01);
		org.junit.Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasPassadas(){
		LocalDate	date = LocalDate.of(2010, 01, 01);
		org.junit.Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasAtual(){
		LocalDate	date = LocalDate.now();
		org.junit.Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
}
