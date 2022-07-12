package com.example.rock.demo.repository;

import com.example.rock.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}