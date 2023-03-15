package com.ll.basic1.boundedContext.member.repository;

import com.ll.basic1.boundedContext.member.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MemberRepository {
    private final ArrayList<Members> membersRepository;

    public MemberRepository(ArrayList<Members> membersRepository) {
        this.membersRepository = membersRepository;
        init();
    }

    public Members findByUsername(String username) {
        return membersRepository.stream()
                .filter(m -> m.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void addMember(Members members) {
        membersRepository.add(members);
    }

    private void init() {
        membersRepository.add(new Members("user1", "1234"));
        membersRepository.add(new Members("abc", "12345"));
        membersRepository.add(new Members("test", "12346"));
        membersRepository.add(new Members("love", "12347"));
        membersRepository.add(new Members("like", "12348"));
        membersRepository.add(new Members("giving", "12349"));
        membersRepository.add(new Members("thanks", "123410"));
        membersRepository.add(new Members("hello", "123411"));
        membersRepository.add(new Members("good", "123412"));
        membersRepository.add(new Members("peace", "123413"));
    }
}
