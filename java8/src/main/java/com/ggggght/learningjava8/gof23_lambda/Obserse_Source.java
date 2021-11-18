package com.ggggght.learningjava8.gof23_lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@FunctionalInterface
interface Observer {

	void notify(String tweet);

}

interface Subject {

	void registerObserver(Observer o);

	void notifyObservers(String tweet);

}

/**
 * 观察者模式
 */
public class Obserse_Source {

	public static void main(String[] args) {
		Feed feed = new Feed();
		feed.registerObserver(new NYTimes());
		feed.registerObserver(new Guardian());
		feed.registerObserver(new LeMonde());

		feed.notifyObservers("The queen said her favourite book is Java 8 in Action! ");
	}

}

class Feed implements Subject {

	private final List<Observer> observers = new ArrayList<>();

	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void notifyObservers(String tweet) {
		this.observers.forEach(o -> o.notify(tweet));
	}

}

class NYTimes implements Observer {

	@Override
	public void notify(String tweet) {
		if (Objects.nonNull(tweet) && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}

}

class Guardian implements Observer {

	@Override
	public void notify(String tweet) {
		if (Objects.nonNull(tweet) && tweet.contains("queen")) {
			System.out.println("Yet another news in London..." + tweet);
		}
	}

}

class LeMonde implements Observer {

	@Override
	public void notify(String tweet) {
		if (Objects.nonNull(tweet) && tweet.contains("wine")) {
			System.out.println("Today cheese ,wine and news! " + tweet);
		}
	}

}