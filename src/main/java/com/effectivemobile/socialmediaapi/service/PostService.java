package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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

    //    public Page<Post> findAllPostsByUserId(String username) {
//        return postRepository.findAllByUserUsername(username, TEN_UNIT);
//    }

//    public List<Post> getAllMessages() {
//        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "editTime"));
//    }
}
