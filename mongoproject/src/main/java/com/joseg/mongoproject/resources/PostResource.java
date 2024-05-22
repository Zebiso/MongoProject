package com.joseg.mongoproject.resources;

import com.joseg.mongoproject.domain.Post;
import com.joseg.mongoproject.resources.util.URL;
import com.joseg.mongoproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping (value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Post> FindById( @PathVariable String id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping (value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity <List<Post>> FindByTitle(@RequestParam (value = "text", defaultValue = "") String text){

        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping (value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity <List<Post>> fullSearch(@RequestParam (value = "text", defaultValue = "")String text,
                                                  @RequestParam (value = "minDate", defaultValue = "") String minDate,
                                                  @RequestParam (value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullSearch(text,min,max);
        return ResponseEntity.ok().body(list);
    }

}






