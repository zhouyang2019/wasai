package com.zy.controller;

import com.zy.demo.service.DemoEnumService;
import com.zy.demo.entity.DemoEnumEntity;
import com.zy.support.ApiReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/demo-enum")
public class DemoEnumController {

    @Autowired
    private DemoEnumService demoEnumService;

    @GetMapping
    public ApiReturn<DemoEnumEntity> get(@RequestParam(value = "name", required = true) String name) {
        DemoEnumEntity entity = demoEnumService.getByName(name);
        return new ApiReturn(entity);
    }

    @PostMapping
    public ApiReturn<Void> create(@RequestBody DemoEnumEntity entity) {
        demoEnumService.insert(entity);
        return new ApiReturn<>();
    }

    @PutMapping
    public ApiReturn<Void> update(@RequestBody DemoEnumEntity entity) {
        demoEnumService.updateNameById(entity.getId(), entity.getName());
        return new ApiReturn<>();
    }

}
