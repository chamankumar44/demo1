package com.example.demo.test;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.IDN;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "%s!!, %s!!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    @RequestMapping("/greetingTest")
    public Greeting greetingTest(@RequestParam(value="name", defaultValue="World") String name,
                                 @RequestParam(value = "world", defaultValue = "World") String world) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name,world));
    }


    @PostMapping("/postInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting postInfo(@RequestBody Greeting greeting){
        Greeting obj = new Greeting();
        obj.setId(greeting.getId()+1);
        obj.setContent(greeting.getContent().concat("  Suffix"));
        return obj;
    }



    //
//    @GetMapping(value = "/{id}")
//    public Foo findById(@PathVariable("id") Long id) {
//        return RestPreconditions.checkFound(service.findById(id));
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create(@RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        return service.create(resource);
//    }
//
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable( "id" ) Long id, @RequestBody Foo resource) {
//        Preconditions.checkNotNull(resource);
//        RestPreconditions.checkNotNull(service.getById(resource.getId()));
//        service.update(resource);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") Long id) {
//        service.deleteById(id);
//    }
}