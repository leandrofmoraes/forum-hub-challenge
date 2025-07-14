package com.forumhub.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forumhub.api.dto.topic.TopicCreateDTO;
import com.forumhub.api.dto.topic.TopicDetailedDTO;
import com.forumhub.api.dto.topic.TopicUpdateDTO;
import com.forumhub.api.entity.Topic;
import com.forumhub.api.repository.TopicRepository;

@Service
public class TopicService {

  @Autowired
  private TopicRepository repository;

  public TopicDetailedDTO create(TopicCreateDTO topicCreateDTO) {

    Topic topic = new Topic(topicCreateDTO);
    repository.save(topic);
    return new TopicDetailedDTO(topic);
  }

  public TopicDetailedDTO getOneTopic(Long id) {
    var topic = repository.getReferenceById(id);
    return new TopicDetailedDTO(topic);
  }

  public Page<TopicDetailedDTO> getAllTopics(Pageable pageable) {
    return repository.findAll(pageable).map(TopicDetailedDTO::new);
  }

  public void deleteTopic(Long topicId) {
    if (!repository.existsById(topicId)) {
      throw new IllegalArgumentException("Topic with ID " + topicId + " does not exist.");
    }
    repository.deleteById(topicId);
  }

  @Transactional
  public TopicDetailedDTO updateTopic(TopicUpdateDTO topicUpdateDTO) {
    Topic topic = repository.findById(topicUpdateDTO.id())
        .orElseThrow(() -> new IllegalArgumentException("Topic with ID " + topicUpdateDTO.id() + " does not exist."));

    topic.setTitle(topicUpdateDTO.title());
    topic.setContent(topicUpdateDTO.content());
    topic.setStatus(topicUpdateDTO.status());

    return new TopicDetailedDTO(topic);
  }
}
