package com.example.twitter.repository;

import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
	List<Tweet> findAllByOrderByCreatedAtDesc();
	List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
	List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
}