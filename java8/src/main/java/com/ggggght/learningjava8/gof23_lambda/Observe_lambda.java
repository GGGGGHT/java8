package com.ggggght.learningjava8.gof23_lambda;

import java.util.Objects;

public class Observe_lambda {

	public static void main(String[] args) {
		Feed feed = new Feed();
		feed.registerObserver((tweet) -> {
			if (Objects.nonNull(tweet) && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});

		feed.registerObserver((tweet) -> {
			if (Objects.nonNull(tweet) && tweet.contains("queen")) {
				System.out.println("Yet another news in London..." + tweet);
			}
		});

		feed.registerObserver(tweet -> {
			if (Objects.nonNull(tweet) && tweet.contains("wine")) {
				System.out.println("Today cheese ,wine and news! " + tweet);
			}
		});

		feed.notifyObservers("money");
	}

}
