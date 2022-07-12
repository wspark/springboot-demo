
package com.example.rock.demo.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.example.rock.demo.model.Member;
import com.example.rock.demo.model.request.MemberCreationRequest;
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

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibraryController {
    
    private final LibraryService libraryService;

    @Timed(value = "post-member")
    @PostMapping("/member")
    public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request) {
        return ResponseEntity.ok(libraryService.createMember(request));
    }

    @Timed(value = "get-member")
    @GetMapping("/member")
    public ResponseEntity<List<Member>> readMembers () {
        return ResponseEntity.ok(libraryService.readMembers());
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

}
