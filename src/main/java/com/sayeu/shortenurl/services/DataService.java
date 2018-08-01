package com.sayeu.shortenurl.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayeu.shortenurl.domain.DataItem;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class DataService {
    Map<String, DataItem> items = new HashMap<>();

    public void setItems(Collection<DataItem> items) {
        this.items.clear();
        for (DataItem item : items) {
            this.add(item.getName(), item.getShortcut(), item.getUrl());
        }
    }

    public List<DataItem> getAll() {
    	List<DataItem> lstDataItem = getListByMap(items);
    	
        return lstDataItem;
    }
    
    public static List<DataItem> getListByMap(Map<String, DataItem> map) {
		List<DataItem> list = new ArrayList<DataItem>();
 
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			list.add(map.get(key));
		}
 
		return list;
    }
		


    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DataItem>> reference = new TypeReference<List<DataItem>>(){};
        InputStream input = TypeReference.class.getResourceAsStream("/data/items.json");

        try {
            List<DataItem> items = mapper.readValue(input, reference);
            this.setItems(items);
        } catch (IOException e){
            System.out.println("Failed loading data: " + e.getMessage());
        }
    }

    public void add(String name, String shortcut, String url) {
        if (this.items.containsKey(shortcut)) {
            // TODO: throw exception here for explicit caller notification
            return;
        }

        DataItem item = new DataItem();
        item.setName(name);
        item.setShortcut(shortcut);
        item.setUrl(url);
        this.items.put(shortcut, item);
    }

    public void delete(String shortcut) {
        this.items.remove(shortcut);
    }

    public void update(String name, String shortcut, String url) {
        if (!this.items.containsKey(shortcut)){
            // TODO: throw exception here to notify a caller
            return;
        }

        DataItem item = new DataItem();
        item.setName(name);
        item.setShortcut(shortcut);
        item.setUrl(url);
        this.items.replace(shortcut, item);
    }

    public DataItem get(String shortcut) {
        return this.items.get(shortcut);
    }
}