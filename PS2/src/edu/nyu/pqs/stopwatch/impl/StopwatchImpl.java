package edu.nyu.pqs.stopwatch.impl;

import edu.nyu.pqs.stopwatch.api.Stopwatch;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by naman on 10/17/2017.
 */
public class StopwatchImpl implements Stopwatch {
  private final Object stopwatchLock = new Object();
  private final String id;
  private long start;
  private long stop;
  private long lap;
  private boolean running;
  private List<Long> laps;

  StopwatchImpl(String id) {
    this.id = id;
    running = false;
    start = 0;
    stop = 0;
    lap = 0;
    laps = new LinkedList<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start() {
    synchronized (stopwatchLock) {
      if (running) {
        throw new IllegalStateException("Cannot start when stop watch is running!");
      }
      running = true;
      if (laps.size() != 0) {
        start = lap;
      } else {
        start = System.nanoTime();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void lap() {
    synchronized (stopwatchLock) {
      if (!running) {
        throw new IllegalStateException("Cannot lap when stop watch is not running!!");
      }
      long currLap = System.nanoTime();
      if (laps.size() == 0) {
        laps.add(toMilliseconds(currLap - start));
      } else {
        if (start == lap) {
          laps.set(laps.size() - 1, toMilliseconds(currLap - lap));
        } else {
          laps.add(toMilliseconds(currLap - lap));
        }
      }
      lap = currLap;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop() {
    synchronized (stopwatchLock) {
      if (!running) {
        throw new IllegalStateException("Cannot stop when stop watch is not running!");
      }
      stop = System.nanoTime();
      if (laps.size() == 0) {
        laps.add(toMilliseconds(stop - start));
      } else {
        if (start == lap) {
          laps.set(laps.size() - 1, toMilliseconds(stop - lap));
        } else {
          laps.add(toMilliseconds(stop - lap));
        }
      }
      running = false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    synchronized (stopwatchLock) {
      running = false;
      start = 0;
      stop = 0;
      lap = 0;
      laps = new LinkedList<>();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Long> getLapTimes() {
    synchronized (stopwatchLock) {
      return laps;
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Stopwatch with id:" + id;
  }

  /**
   * Converts time from nano to milli seconds
   * @param nano Time in nano second
   * @return equivalent time in millisecond
   */
  private long toMilliseconds(long nano) {
    return nano / 1000000;
  }
}
