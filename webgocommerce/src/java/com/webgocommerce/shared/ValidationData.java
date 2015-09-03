/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webgocommerce.shared;

import java.io.Serializable;

/**
 *
 * @author SISTEMAS
 */
public class ValidationData implements Serializable{
    private boolean invalid;
    private String value;

    public String getValue() {
      return value;
    }

    public boolean isInvalid() {
      return invalid;
    }

    public void setInvalid(boolean invalid) {
      this.invalid = invalid;
    }

    public void setValue(String value) {
      this.value = value;
    }
}
