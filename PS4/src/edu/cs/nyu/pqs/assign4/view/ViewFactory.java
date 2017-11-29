package edu.cs.nyu.pqs.assign4.view;

import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;

/**
 * This factory class is used to return an implemtation of type {@link ConnectFourListener}.
 */
public class ViewFactory {

  /**
   * This is used to return an instance of implementation of type {@link ConnectFourListener}.
   *
   * @param connectFourModel The model that the listener will register itself to.
   * @return An instance of the listener implementation.
   */
  public static ConnectFourListener getConnectFourListner(ConnectFourModel connectFourModel) {
    return new ConnectFourSpringView(connectFourModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "View Factory.";
  }
}
