package io.thesun4sky.todoapp.security;


import io.thesun4sky.todoapp.entity.User;
import io.thesun4sky.todoapp.repository.CommentRepository;

import io.thesun4sky.todoapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(user);
    }
}