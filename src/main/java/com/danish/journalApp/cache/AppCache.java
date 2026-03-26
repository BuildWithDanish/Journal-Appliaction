package com.danish.journalApp.cache;

import com.danish.journalApp.entity.ConfigJournalAppEntity;
import com.danish.journalApp.repository.AppConfigRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class AppCache {

    private Map<String, String> appCache;

    @Autowired
    AppConfigRepository appConfigRepository;

    @PostConstruct
    public void init() {
        appCache = new HashMap<String, String>();
        List<ConfigJournalAppEntity> all = appConfigRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
