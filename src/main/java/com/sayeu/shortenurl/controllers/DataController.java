package com.sayeu.shortenurl.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sayeu.shortenurl.domain.DataItem;
import com.sayeu.shortenurl.services.DataService;

@RestController
@RequestMapping("/api/items")
public class DataController {

    private DataService service;

    @Autowired
    public void setService(DataService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public Iterable<DataItem> get() {
        return service.getAll();
    }

    @DeleteMapping(path="/{shortcut}", produces = "application/json; charset=utf-8")
    public Iterable<DataItem> delete(@PathVariable String shortcut) {
        service.delete(shortcut);
        return service.getAll();
    }

    @PutMapping(path = "/{shortcut}", produces = "application/json; charset=utf-8")
    public Iterable<DataItem> create(@PathVariable String shortcut, @RequestBody DataItem item) {
        service.update(item.getName(), shortcut, item.getUrl());
        return service.getAll();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public Iterable<DataItem> update(@RequestBody DataItem item) {
        service.add(item.getName(), item.getShortcut(), item.getUrl());
        return service.getAll();
    }
}
