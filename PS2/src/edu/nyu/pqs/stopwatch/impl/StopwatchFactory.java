package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;
import java.util.Set;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  private static final Object factoryLock = new Object();
  private static final Set<String> idSet = new HashSet<>();
  private static final List<Stopwatch> stopWatchList = new LinkedList<>();

  /**
   * Creates and returns a new Stopwatch object
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or
   *     already taken.
   */
  public static Stopwatch getStopwatch(String id) {
    synchronized (factoryLock) {
      if (id == null) {
        throw new IllegalArgumentException("Null id not allowed.");
      }
      if (id.trim().equals("")) {
        throw new IllegalArgumentException("Empty string or string consisting only of spaces is not allowed as id.");
      }
      if (idSet.contains(id)) {
        throw new IllegalArgumentException("The id entered already exists.");
      }
      idSet.add(id);
      Stopwatch stopwatch = new StopwatchImpl(id);
      stopWatchList.add(stopwatch);
      return stopwatch;
    }
  }

  /**
   * Returns a list of all created stopwatches
   * @return a List of al creates Stopwatch objects.  Returns an empty
   * list if no Stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    synchronized (factoryLock) {
      return new ArrayList<>(stopWatchList);
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Stopwatch Factory";
  }
}
