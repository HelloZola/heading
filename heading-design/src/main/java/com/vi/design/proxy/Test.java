package com.vi.design.proxy;

import java.lang.reflect.Proxy;

/**
 * newProxyInstance代理测试类
 * @ClassName Test
 * @author chen.kangliu
 * @date 2017年6月2日
 */
public class Test {

	public static void main(String[] args) {
		BusinessProcessorImpl bpimpl = new BusinessProcessorImpl();
		BusinessProcessorHandler handler = new BusinessProcessorHandler(bpimpl);
		BusinessProcessor bp = (BusinessProcessor) Proxy.newProxyInstance(bpimpl.getClass().getClassLoader(), bpimpl
				.getClass().getInterfaces(), handler);
		bp.processBusiness();
	}
}
