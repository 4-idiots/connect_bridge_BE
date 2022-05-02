package com.connectbridge.connect_bridge_BE.outactpage.service;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutActLike;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActLikeRepository;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActRepository;
import org.springframework.stereotype.Service;


@Service
public class OutActLikeService {

    private final OutActLikeRepository outActLikeRepository;
    private final OutActRepository outActRepository;
    private final UserRepository userRepository;

    public OutActLikeService(OutActLikeRepository outActLikeRepository, OutActRepository outActRepository, UserRepository userRepository) {
        this.outActLikeRepository = outActLikeRepository;
        this.outActRepository = outActRepository;
        this.userRepository = userRepository;
    }

    // db에 해당하는 값이 있는지 확인.
    public boolean likeChk(Long userID, Long outActID){
        if(null == outActLikeRepository.findByUserIDAndOutActID(userID,outActID)){
            System.out.println("db에 없으요~ 추가하세요");
            return true;
        }else {
            System.out.println("db에 있는데요? 취소하세요");
            return false;
        }
    }

    // 클릭을 했더니 값이 있다.-> 삭제
    public void likeOff(Long userID, Long outActID){
        OutActLike outActLike = outActLikeRepository.findByUserIDAndOutActID(userID, outActID);
        outActLikeRepository.deleteById(outActLike.getId());
        likeCount(outActID);

        System.out.println("likeOff");
    }

    // 클릭을 했더니 값이 없다.-> 생성.
    public void likeOn(Long userID, Long outActID){
        OutActLike outActLike = new OutActLike().createActLike(userID,outActID);
        outActLikeRepository.save(outActLike);
        likeCount(outActID);

        System.out.println("likeOn");
    }

    private void likeCount(Long outActID){
        long like = outActLikeRepository.countByOutActID(outActID);
        OutAct outAct = outActRepository.findByid(outActID);
        outAct.updateLike((int)like);
        outActRepository.save(outAct);
    }

    public boolean isLike(Long userID, Long outActID){
        return outActLikeRepository.existsByUserIDAndOutActID(userID, outActID);
    }
}
