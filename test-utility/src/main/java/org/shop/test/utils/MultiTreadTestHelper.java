package org.shop.test.utils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class MultiTreadTestHelper {

	public static<T> void testMultiThreadOn(@NotNull List<T> dataList, Consumer<T> func) {

		int threadCount = dataList.size();
		CountDownLatch latch = new CountDownLatch(threadCount);
		CountDownLatch latchMain = new CountDownLatch(threadCount);
		for (T data : dataList) {
			Thread thread = new Thread(() -> {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				func.accept(data);
				latchMain.countDown();
			});
			thread.start();
			latch.countDown();
		}
		try {
			latchMain.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}


}
