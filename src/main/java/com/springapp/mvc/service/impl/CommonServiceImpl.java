package com.springapp.mvc.service.impl;

import com.springapp.mvc.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Marcin on 2015-10-16.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public Map<String, String> statuses(String language, boolean isProject) {
        Map<String, String> statuses = new LinkedHashMap<String, String>();
        if(language.equals("English")){
            statuses.put("Started", "Started");
            statuses.put("Suspend", "Suspend");
            statuses.put("Finished", "Finished");
            return statuses;
        }else{
            if(isProject){
                statuses.put("Rozpoczety", "Rozpoczęty");
                statuses.put("Wstrzymany", "Wstrzymany");
                statuses.put("Zakończony", "Zakończony");
            }else{
                statuses.put("Rozpoczęte", "Rozpoczęte");
                statuses.put("Wstrzymane", "Wstrzymane");
                statuses.put("Zakończone", "Zakończone");
            }
            return statuses;
        }
    }
}
