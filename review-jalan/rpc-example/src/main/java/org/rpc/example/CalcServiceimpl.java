package org.rpc.example;

public class CalcServiceimpl implements CalcService {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int minus(int a, int b) {
		return a-b;
	}

}
