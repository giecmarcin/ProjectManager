package com.springapp.mvc.service;

import java.util.Map;

/**
 * Created by Marcin on 2015-10-16.
 */
public interface CommonService {
    Map<String, String> statuses(String language, boolean isProject);
}
