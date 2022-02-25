package com.connectbridge.connect_bridge_BE.outsideactpage.service;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.ModifyResDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.PostCreateDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.entity.OutAct;
import com.connectbridge.connect_bridge_BE.outsideactpage.repository.OutActRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OutActService {

    private final OutActRepository outActRepository;

    public OutActService(OutActRepository outActRepository) {
        this.outActRepository = outActRepository;
    }


    // 생성
    public boolean createPost(PostCreateDto requestDto){
        try {
            OutAct outAct = new OutAct();
            outAct.createPost(requestDto.getOutActName(), requestDto.getOutActImg(), requestDto.getOutActLink());
            outActRepository.save(outAct);

            System.out.println("outAct는 " + outAct.getOutActName());
            return true;

        }catch (Exception e){
            System.out.println(e);

            return false;
        }
    }

    // 수정 조회
    public ModifyResDto modifyInfo(Long id){
        OutAct outAct= outActRepository.findByid(id);

        return ModifyResDto.builder()
                .outActName(outAct.getOutActName())
                .outActImg(outAct.getOutActImg())
                .outActLink(outAct.getOutActLink())
                .build();
    }

    public Boolean updatePost(UpdateReqDto requestDto){
        OutAct outAct = outActRepository.findByid(requestDto.getOutActID());

        outAct.updatePost(requestDto.getOutActName(),
                requestDto.getOutActImg(),
                requestDto.getOutActLink());

        outActRepository.save(outAct);

        return true;
    }

    // 삭제
    public boolean deletePost(Long id){
        try {
            outActRepository.deleteById(id);

            return true;
        }catch (Exception e){

            return false;
        }
    }

}
