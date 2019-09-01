package com.upokecenter.test;

import java.util.*;

  final class AppResources {

    ResourceBundle mgr;

    public AppResources(String name) {
      mgr = ResourceBundle.getBundle(name);
    }

    public String GetString(String name) {
      return mgr.getString(name);
    }
  }
