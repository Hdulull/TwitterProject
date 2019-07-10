package com.example.twitter.controller;

import com.example.twitter.model.Tweet;
import com.example.twitter.model.User;
import com.example.twitter.service.TweetService;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;


	@GetMapping(value = "/user/{username}")
	public String getUser(@PathVariable(value = "username") String username, Model model) {
		User user = userService.findByUsername(username);
		List<Tweet> tweets = tweetService.findAllByUser(user);
		model.addAttribute("tweetList", tweets);
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping(value = "/users")
	public String getUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		SetTweetCounts(users, model);
		return "users";
	}

	private void SetTweetCounts(List<User> users, Model model) {
		HashMap<String,Integer> tweetCounts = new HashMap<>();
		for (User user : users) {
			List<Tweet> tweets = tweetService.findAllByUser(user);
			tweetCounts.put(user.getUsername(), tweets.size());
		}
		model.addAttribute("tweetCounts", tweetCounts);
	}
}
