package com.emagazine.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emagazine.api.model.CommentDTO;
import com.emagazine.api.model.CommentForListViewDTO;
import com.emagazine.api.model.CommentRequestDTO;
import com.emagazine.api.rest.exception.ObjectNotFoundException;
import com.emagazine.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CommentForListViewDTO> getAllComment(@RequestParam(required = false) String keyword){
		return commentService.findAll(keyword);
	}
	
	
	@GetMapping("/{id}")
	public CommentDTO getCommentsById(@PathVariable Long id) {

		CommentDTO commentPOJO = commentService.findById(id);

		if (commentPOJO == null) {
			throw new ObjectNotFoundException("Not found comment for id: " + id);
		}
	
		return commentPOJO;
	}
	
	
	@GetMapping("/{id}/post/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CommentForListViewDTO> getCommentsByPostIdForAdminPage(
			@PathVariable("id") Long postId,
			@RequestParam(required = false) String keyword) {
		
		List<CommentForListViewDTO> commentPOJO = commentService.findByPostIdForAdmin(postId, keyword);

		System.out.println(postId);
		
		if (commentPOJO == null) {
			throw new ObjectNotFoundException("Not found comments for id: " + postId);
		}
	
		
		return commentPOJO;
	}
	
	
	@GetMapping("/{id}/post")
	public List<CommentDTO> getCommentsByPostId(@PathVariable Long id) {

		List<CommentDTO> commentPOJO = commentService.findByPostId(id);

		if (commentPOJO == null) {
			throw new ObjectNotFoundException("Not found comments for id: " + id);
		}
	
		
		return commentPOJO;
	}
	
	
	@PostMapping
	public CommentRequestDTO addComment(@RequestBody CommentRequestDTO commentDTO) {
		commentService.save(commentDTO);
		
		return commentDTO;
	}
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Map<String, Boolean> deleteComment(@PathVariable(value = "id") Long id) {
		// check is exist
		commentService.findById(id);

		commentService.delete(id);
		// map response result
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
