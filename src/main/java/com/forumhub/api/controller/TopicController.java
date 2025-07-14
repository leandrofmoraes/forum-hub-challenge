package com.forumhub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forumhub.api.dto.topic.TopicCreateDTO;
import com.forumhub.api.dto.topic.TopicDetailedDTO;
import com.forumhub.api.dto.topic.TopicUpdateDTO;
import com.forumhub.api.service.TopicService;

@RestController
@RequestMapping("topics")
public class TopicController {

  @Autowired
  private TopicService service;

  public ResponseEntity<TopicDetailedDTO> createTopic(@RequestBody TopicCreateDTO topicCreateDTO,
      UriComponentsBuilder uriBuilder) {
    var topicCreated = service.create(topicCreateDTO);
    var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topicCreated.id()).toUri();
    return ResponseEntity.created(uri).body(topicCreated);
  }

  public ResponseEntity<TopicDetailedDTO> getTopic(@PathVariable(value = "id") Long id) {
    var topic = service.getOneTopic(id);
    return ResponseEntity.status(HttpStatus.OK).body(topic);
  }

  public ResponseEntity<Page<TopicDetailedDTO>> getAllTopics(
      @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

    var page = service.getAllTopics(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(page);
  }

  public ResponseEntity<TopicDetailedDTO> updateTopic(@RequestBody TopicUpdateDTO topicUpdateDTO) {
    var topicUpdated = service.updateTopic(topicUpdateDTO);
    return ResponseEntity.ok(topicUpdated);
  }

  public ResponseEntity<Object> deleteTopic(@PathVariable(value = "topicId") Long topicId) {
    service.deleteTopic(topicId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Topic deleted successfully");
  }

}
