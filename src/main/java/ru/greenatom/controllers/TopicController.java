package ru.greenatom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.model.message.MessageDto;
import ru.greenatom.model.topic.*;
import ru.greenatom.service.TopicService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /*
     * 200 - successful operation
     * 400 - invalid input
     * 422 - validation exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TopicWithMessage createTopic(@RequestBody TopicWithMessageDto newTopic) {
        return topicService.createTopic(newTopic);
    }

    /*
     * 200 - successful operation
     * 400 - invalid id supplied
     * 404 - topic not found
     * 422 - validation exception
     */
    //FIXME
    @PutMapping("/{topicId}")
    @ResponseStatus(HttpStatus.OK)
    public TopicWithMessage updateTopic(@PathVariable("topicId") UUID topicId,
                                        @RequestBody TopicDto topic) {
        return topicService.updateTopic(topicId, topic);
    }

    /*
     * 200 - successful operation
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Topic> listAllTopics(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return topicService.listAllTopics(pageable);
    }

    /*
     * 200 - successful operation
     * 400 - invalid topic id
     */
    @GetMapping("/{topicId}")
    @ResponseStatus(HttpStatus.OK)
    public TopicPageMessage listTopicMessage(@PathVariable("topicId") UUID topicId,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return topicService.listTopicMessage(topicId, pageable);
    }

    /*
     * 200 - successful operation
     * 400 - invalid input
     * 422 - validation exception
     */
    @PostMapping("/{topicId}/message")
    @ResponseStatus(HttpStatus.OK)
    public TopicWithMessage createMessage(@PathVariable("topicId") UUID topicId,
                                          @RequestBody MessageDto messageDto) {
        return topicService.createMessage(topicId, messageDto);
    }

    /*
     * 200 - successful operation
     * 400 - invalid id supplied
     * 404 - topic not found
     * 422 - validation exception
     */
    @PutMapping("/{topicId}/message/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    public TopicWithMessage updateMessage(@PathVariable("topicId") UUID topicId,
                                          @RequestBody MessageDto message,
                                          @PathVariable("messageId") UUID messageId) {
        return topicService.updateMessage(topicId, message, messageId);
    }

    /*
     * 204 - successful operation
     */
    @DeleteMapping("/{topicId}/message/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable("topicId") UUID topicId,
                              @PathVariable("messageId") UUID messageId) {
        topicService.deleteMessage(topicId, messageId);
    }
}
