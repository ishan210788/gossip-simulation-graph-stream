package com.graphImpl;

import java.util.Random;

import com.graphInterface.RandomGen;

public class RandomGenImpl implements RandomGen{
	public int generateRandomRange(int min, int max){
		Random randomNumber = new Random();
		int rnum = randomNumber.nextInt((max - min) + 1) + min;
		return rnum;
	}
}
