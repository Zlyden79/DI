package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepositoryImpl;

import java.util.List;

public class PostService {
    private final PostRepositoryImpl repository;

    public PostService(PostRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        long postId = post.getId();
        //если поста с postId нет в репозитории - выбрасываем исключение
        if (postId != 0 && repository.getById(postId).isEmpty()) {
            throw new NotFoundException();
        }
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

