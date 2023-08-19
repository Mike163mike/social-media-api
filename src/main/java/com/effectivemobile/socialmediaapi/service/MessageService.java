package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
  //  private final Sort DESC_SORT = Sort.by(Sort.Direction.DESC, "editTime");
    private  final Pageable TEN_UNIT = new Pageable() {
        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 10;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return Sort.by(Sort.Direction.DESC, "editTime");
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public Pageable withPage(int pageNumber) {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

//    public Page<Message> findAllMessagesByUserId(String username) {
//        return messageRepository.findAllByUserUsername(username, TEN_UNIT);
//    }

//    public List<Message> getAllMessages() {
//        return messageRepository.findAll(Sort.by(Sort.Direction.DESC, "editTime"));
//    }

    public Message findById(UUID id) {
        return messageRepository.findById(id)
                .orElseThrow();
    }

    public void deleteMessageById(UUID id) {
        messageRepository.deleteById(id);
    }

    public Message editMessageById(UUID id, String newMessage) {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setMessage(newMessage);
        messageRepository.save(message);
        return message;
    }
}
