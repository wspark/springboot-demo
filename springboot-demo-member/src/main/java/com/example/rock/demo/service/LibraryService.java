package com.example.rock.demo.service;


import com.example.rock.demo.model.Member;
import com.example.rock.demo.model.MemberStatus;
import com.example.rock.demo.model.request.MemberCreationRequest;
import com.example.rock.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

  
    private final MemberRepository memberRepository;

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        member.setStatus(MemberStatus.ACTIVE);
        return memberRepository.save(member);
    }

    public Member updateMember (Long id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    } 

    public List<Member> readMembers() {
        return memberRepository.findAll();
    }

    public void deleteMembers(Long id){
        memberRepository.deleteById(id);
    }
}