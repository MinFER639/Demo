package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoRepository demoRepository;

    @GetMapping("")
    public List<Demo> getAll() {
        return demoRepository.getAll();
    }

    @GetMapping("/{id}")
    public Demo getById(@PathVariable("id") int id) {
        return demoRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Demo> demos) {
        return demoRepository.save(demos);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Demo updatedDemo) {
        Demo demo = demoRepository.getById(id);

        if (demo != null) {
            demo.setMovie(updatedDemo.getMovie());
            demo.setRating(updatedDemo.getRating());

            demoRepository.update(demo);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Demo updatedDemo) {
        Demo demo = demoRepository.getById(id);

        if (demo != null) {
            if (updatedDemo.getMovie() != null) demo.setMovie(updatedDemo.getMovie());
            if (updatedDemo.getRating() > 0) demo.setRating(updatedDemo.getRating());
            demoRepository.update(demo);
                return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return demoRepository.delete(id);
    }
}
