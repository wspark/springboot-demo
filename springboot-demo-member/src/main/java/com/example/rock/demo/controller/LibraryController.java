
package com.example.rock.demo.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.rock.demo.model.Member;
import com.example.rock.demo.model.request.MemberCreationRequest;
import com.example.rock.demo.service.LibraryService;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
@CrossOrigin("*")

public class LibraryController {
    
    private final LibraryService libraryService;
   // private static final Logger LOGGER = LogManager.getLogger(LibraryController.class);
    private final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
    @Timed(value = "post-member")
    @PostMapping("/member")
    public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request) {
        return ResponseEntity.ok(libraryService.createMember(request));
    }

    @Timed(value = "get-member")
    @GetMapping("/member")
    public ResponseEntity<List<Member>> readMembers () {
        LOGGER.info("/member/all request!!");
        return ResponseEntity.ok(libraryService.readMembers());
    }

    @Timed(value = "get-member")
    @GetMapping("/member/{memberId}")    
    public ResponseEntity<Member> readMember (@PathVariable Long memberId) {
        LOGGER.info("/member/{memberId} request!!");
        return ResponseEntity.ok(libraryService.readMember(memberId));
    }

    @Timed(value = "patch-member")
    @PatchMapping("/member/{memberId}")
    public ResponseEntity<Member> updateMember (@RequestBody MemberCreationRequest request, @PathVariable Long memberId) {
        return ResponseEntity.ok(libraryService.updateMember(memberId, request));
    }
    
    @Timed(value = "delete-member")
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<Void> deleteMembers (@PathVariable Long memberId) {
        libraryService.deleteMembers(memberId);
        return ResponseEntity.ok().build();
    }

    @Timed(value = "delay-test")
    @GetMapping("/delay/{countNum}")
    public String deplyMethod (@PathVariable Long countNum ) {
        Long secondsToSleep = countNum;
        String hostName = System.getenv("HOSTNAME");
        try {
            LOGGER.info("delay "+ countNum + " senconds request!! Start");
            Thread.sleep(secondsToSleep * 1000);
            LOGGER.info("delay "+ countNum + " senconds request!! End");
            
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          return String.format("Hostname = %s"  , hostName);
    }
    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(        
    @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOGGER.info(String.format("Header '%s' = %s", key, value));
        });
    
    return new ResponseEntity<String>(
      String.format("Listed %d headers", headers.size()), HttpStatus.OK);
   }
   @GetMapping("/info/{param}")
   public String info(@PathVariable String param ){
    String hostName = System.getenv("HOSTNAME");
  //  return String.format("Hostname = %d"  , hostName);
        LOGGER.info("hostname : "  + hostName);
        LOGGER.info("<param>"+param+"</param>");
    return String.format("Hostname = %s"  , hostName);
   }

   @GetMapping("/log/{lenth}")
   public String log(@PathVariable Integer lenth ){
  //  String hostName = System.getenv("HOSTNAME");
  //  return String.format("Hostname = %d"  , hostName);
    byte[] array = new byte[lenth]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedString = new String(array, Charset.forName("UTF-8"));      
        String lengthString = Integer.toString(lenth);
        LOGGER.info("<uri>"+lengthString+"</uri>");
        LOGGER.info("<lenth>"+generatedString+"</lenth>");    
        return String.format("%s",  lengthString);
   }
}
