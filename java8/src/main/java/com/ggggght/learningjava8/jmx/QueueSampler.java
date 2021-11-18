package com.ggggght.learningjava8.jmx;

import java.util.Date;
import java.util.Queue;

public class QueueSampler implements QueueSamplerMXBean {

	private Queue<String> queue;

	public QueueSampler(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public QueueSample getQueueSample() {
		return new QueueSample(new Date(), queue.size(), queue.peek());
	}

	@Override
	public synchronized void clearQueue() {
		queue.clear();
	}

}
