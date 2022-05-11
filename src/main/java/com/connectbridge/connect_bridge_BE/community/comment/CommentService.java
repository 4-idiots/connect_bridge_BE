package com.connectbridge.connect_bridge_BE.community.comment;


import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommunityRepository communityRepository;
    private final RegisterRepository registerRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void addComment (CommentDto commentDto, long fromUserId) {
        CommunityEntity post = communityRepository.findById(commentDto.getPostID()).get();
        RegisterEntity user = registerRepository.findById(fromUserId).get();
        CommentEntity commentEntity = CommentEntity.builder()
                .comment(commentDto.getComment())
                .postID(post)
                .userID(user.getId())
                .build();
        commentRepository.save(commentEntity);
    }
    @Transactional
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public void updateComment(CommentDto commentDto)throws Exception{
        CommentEntity commentEntity = commentRepository.findById(commentDto.getId()).get();
        System.out.println(commentEntity);
        commentEntity.updateComment(commentDto.getId(), commentDto.getComment());
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void commentcounting(long toPostId) {
        CommunityEntity communityEntity = communityRepository.getById(toPostId);
        int commentcount = commentRepository.findCommentCountById(toPostId);
        communityEntity.commentCount(toPostId, commentcount);
        communityRepository.save(communityEntity);
    }

}
