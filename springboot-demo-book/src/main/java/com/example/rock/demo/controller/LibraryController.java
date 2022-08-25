
package com.example.rock.demo.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.example.rock.demo.model.Book;
import com.example.rock.demo.model.request.BookCreationRequest;
import com.example.rock.demo.service.LibraryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// resttemplate test
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibraryController {
    
    private final LibraryService libraryService;

    @Autowired
    RestTemplate restTemplate;
  //  String memberUrl = "http://springboot-demo-member-wspark-test.apps.ocp4.local/api/library/member/1";
  String memberUrl = "http://springboot-demo-member.wspark-test.svc.cluster.local:8080/api/library/member/1";

    @GetMapping("/book/rest")
    public String getMemberList(){
         HttpHeaders headers = new HttpHeaders();
       //  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity <String> entity = new HttpEntity<String>(headers);

         return restTemplate.exchange(memberUrl, HttpMethod.GET, entity, String.class).getBody();      
    }

    @Timed(value = "get-book")
    @GetMapping("/book")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
        if (isbn == null) {
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    @Timed(value = "get-bookId")
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> readBook (@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.readBook(bookId));
    }
/*
    @Timed(value = "post-book")
    @PostMapping("/book")
    public ResponseEntity<Book> createBook (@RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.createBook(request));
    }
 */

 /*
    @Timed(value = "patch-bookId")
    @PatchMapping("/book/{bookId}")
    public ResponseEntity<Book> updateBook (@PathVariable("bookId") Long bookId, @RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.updateBook(bookId, request));
    }
 */
    @Timed(value = "delete-book")
    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

}
