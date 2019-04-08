package com.web.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Artist;
import com.web.entities.Commentary;
import com.web.entities.User;
import com.web.repositories.ArtistRepository;
import com.web.repositories.CommentaryRepository;
import com.web.repositories.UserRepository;

@RestController
public class CommentaryController {
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentaryRepository commentaryRepository;
	
	// Get All Commentaries
	@RequestMapping("/getAllCommentaries")
	@CrossOrigin
	public Iterable<Commentary> getAllCommentaries() {
		return commentaryRepository.findAll();
	}
	
	//Add new Commentary
	@RequestMapping("/addCommentary")
	@CrossOrigin
	public @ResponseBody String AddCommentary
	(
			@RequestParam Long idArtist,
			@RequestParam Long idUser,
			@RequestParam String content){
		Optional<User> optionalUser 	= userRepository.findById(idUser);
		Optional<Artist> optionalArtist = artistRepository.findById(idArtist);
		
		if(!optionalUser.isPresent()) 	return "User don't exist.";
		if(!optionalArtist.isPresent()) return "Artist don't exist.";
		
		User user 		= optionalUser.get();
		Artist artist	= optionalArtist.get();
		
		Commentary commentary = new Commentary(user, artist, content);
		commentaryRepository.save(commentary);
		return "Comment added succesfull";
	}
	
	
	
}