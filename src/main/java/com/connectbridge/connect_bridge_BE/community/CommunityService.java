package com.connectbridge.connect_bridge_BE.community;

import com.connectbridge.connect_bridge_BE.community.like.CommunityLikeRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.qlrm.mapper.JpaResultMapper;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Service
@Slf4j
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final RegisterRepository registerRepository;
    private final CommunityLikeRepository communityLikeRepository;
    private final EntityManager em;

    public CommunityService(CommunityRepository communityRepository, RegisterRepository registerRepository, CommunityLikeRepository communityLikeRepository, EntityManager em){
        this.communityRepository = communityRepository;
        this.registerRepository = registerRepository;
        this.communityLikeRepository = communityLikeRepository;
        this.em = em;
    }
    public List jacksonMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<Object, Object>> map = mapper.readValue(json, List.class);
        List map = null;
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
    //커뮤니티 저장
    @Transactional
    public void save(CommunityCreateDto communityCreateDto){
        RegisterEntity id = registerRepository.findById(communityCreateDto.getFromUserId()).get();
        String nickname = id.getUserNickname();
        communityCreateDto.setUserNickname(nickname);
        communityCreateDto.setViewCount(0);
        communityCreateDto.setLikeCount(0);
        communityCreateDto.setCommentCount(0);
        communityCreateDto.setUserID(communityCreateDto.getFromUserId());
        communityRepository.save(communityCreateDto.communityEntity()).getId();
    }



    //커뮤니티 정보
    @Transactional
    public List<CommunityPreviewDto> getList() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.id, p.post_title, p.post_hashtag, p.post_user_nickname, p.post_viewcount, p.post_likecount, p.post_commentcount ");
        sb.append("FROM community p ");
        sb.append("GROUP BY p.id ");
        sb.append("ORDER BY p.id DESC");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString());

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<CommunityPreviewDto> communityList = result.list(query, CommunityPreviewDto.class);

        return communityList;
    }

    //뷰 카운트
    public void postcountup(long communityID){
        CommunityEntity community = communityRepository.findByid(communityID);
        long viewCount = community.getViewCount();
        viewCount += 1;
        community.postcountup(communityID, viewCount);
        communityRepository.save(community);
    }

    //get 수정 페이지
    @Transactional
    public CommunityChangeDto getCommunityFixPage(long communityID){
        CommunityChangeDto communityChangeDto = new CommunityChangeDto();
        CommunityEntity communityEntity = communityRepository.getById(communityID);
        communityChangeDto.setTitle(communityEntity.getTitle());
        communityChangeDto.setHashtag(communityChangeDto.convertList(communityEntity.getHashtag()));
        communityChangeDto.setContents(jacksonMap(communityEntity.getContents()));
        return communityChangeDto;
    }

    //커뮤니티 상세페이지
    @Transactional
    public CommunityDto getCommunityPage(long fromUserId, long communityID){
        CommunityDto communityDto = new CommunityDto();

        CommunityEntity communityEntity = communityRepository.getById(communityID);
        RegisterEntity id = registerRepository.findByid(communityEntity.getUserID());
        if(fromUserId == 0){
            communityDto.setPostID(communityEntity.getId());
            communityDto.setTitle(communityEntity.getTitle());
            communityDto.setHashtag(communityDto.convertList(communityEntity.getHashtag()));
            communityDto.setContents(jacksonMap(communityEntity.getContents()));
            communityDto.setUserNickname(communityEntity.getUserNickname());
            communityDto.setUserAbility(id.getUserAbility());
            communityDto.setUserInterestMain(id.getUserInterestMain());
            communityDto.setUserInterestSub(id.getUserInterestSub());
            communityDto.setUserPicuture(id.getUserPicture());
            communityDto.setViewCount(communityEntity.getViewCount());
            communityDto.setLikeCount(communityEntity.getLikeCount());
            communityDto.setLikeCounta(communityEntity.getLikeCount());
            communityDto.setCommentCount(communityEntity.getCommentCount());
            communityDto.setCommentList(communityEntity.getCommentList());
            communityDto.setUserID(communityEntity.getUserID());
            communityDto.setCreateDate(communityEntity.getCreateDate());
        }
        else {

            communityDto.setPostID(communityEntity.getId());
            communityDto.setTitle(communityEntity.getTitle());
            communityDto.setHashtag(communityDto.convertList(communityEntity.getHashtag()));
            communityDto.setContents(jacksonMap(communityEntity.getContents()));
            communityDto.setUserNickname(communityEntity.getUserNickname());
            communityDto.setUserAbility(id.getUserAbility());
            communityDto.setUserInterestMain(id.getUserInterestMain());
            communityDto.setUserInterestSub(id.getUserInterestSub());
            communityDto.setUserPicuture(id.getUserPicture());
            communityDto.setViewCount(communityEntity.getViewCount());
            communityDto.setLikeCount(communityEntity.getLikeCount());
            communityDto.setLikeCounta(communityEntity.getLikeCount());
            communityDto.setCommentCount(communityEntity.getCommentCount());
            communityDto.setCommentList(communityEntity.getCommentList());
            communityDto.setUserID(communityEntity.getUserID());
            communityDto.setCreateDate(communityEntity.getCreateDate());
        }
        if (fromUserId != 0){
            if (communityLikeRepository.findByFromUserIdAndToPostId(fromUserId, communityID) != null){
                communityDto.setState(Long.valueOf(2));
                communityDto.setColor("danger"); //좋아요함
            }else {communityDto.setState(Long.valueOf(1));
                communityDto.setColor("black");
                } //좋아요 안함
        }else{
            communityDto.setState(Long.valueOf(3)); //로그인 안한 사람
        }

        return communityDto;
    }

    //커뮤니티 수정
    public void updateCommunity(CommunityCreateDto communityCreateDto) throws Exception{
        CommunityEntity community = communityRepository.findByid(communityCreateDto.getPostID());
        RegisterEntity registerEntity = registerRepository.findByid(community.getUserID());
        community.updateCommunity(communityCreateDto.getPostID(), communityCreateDto.getTitle(),
                communityCreateDto.getContents(), communityCreateDto.convertStr(communityCreateDto.getHashtag()),
                registerEntity.getUserNickname());
        communityRepository.save(community);
    }

    public void communityDelete(long communityId){

        communityRepository.deleteById(communityId);

    }

    @Transactional // 인기글
    public List<CommunityPreviewDto> getPopularPost() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.id, p.post_title, p.post_hashtag, p.post_user_nickname, p.post_viewcount, COUNT(p.id) as likesCount, p.post_commentcount ");
        sb.append("FROM communitylike l, community p ");
        sb.append("WHERE l.to_post_id = p.id ");
        sb.append("AND p.id IN (SELECT p.id FROM communitylike l, community p WHERE p.id = l.to_post_id) ");
        sb.append("AND p.post_likecount > 1 ");
        sb.append("GROUP BY p.id ");
        sb.append("ORDER BY p.id DESC");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString());

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<CommunityPreviewDto> communityDtoList = result.list(query, CommunityPreviewDto.class);

        return communityDtoList;
    }

    @Transactional //검색 기능 *** 엔티티 -> DTO 변경 기능 ***
    public List<CommunityPreviewDto> getSerach(String keyword){
        List<CommunityPreviewDto> paging = communityRepository.searchResultByTitleContainingOrderByIdDesc(keyword).stream()
                .map(CommunityPreviewDto::fromEntity).collect(Collectors.toList());

        return paging;
    }
}
