/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.shared;

/**
 *
 * @author SISTEMAS
 */
public interface ValidatableField<T> {
  T getValue();
  boolean isInvalid();
  void setInvalid(boolean isInvalid);
  void setValue(T value);

  /**
   * Default implementation of ValidatableField.
   *
   * @param <T> the value type of the field
   */
  public static class DefaultValidatableField<T> implements ValidatableField<T> {
    static int genserial = 0; // debugging
    int serial; // debugging
    boolean isInvalid;
    T value;

    public DefaultValidatableField(T value) {
      this.serial = genserial++;
      this.value = value;
    }

    public DefaultValidatableField(ValidatableField<T> other) {
      if (other instanceof DefaultValidatableField<?>) {
        this.serial = ((DefaultValidatableField<T>) other).serial;
      }
      this.value = other.getValue();
      this.isInvalid = other.isInvalid();
    }

    @Override
    public T getValue() {
      return value;
    }

    @Override
    public boolean isInvalid() {
      return isInvalid;
    }

    @Override
    public void setInvalid(boolean isInvalid) {
      this.isInvalid = isInvalid;
    }

    @Override
    public void setValue(T value) {
      this.value = value;
    }
  }
}
