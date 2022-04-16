package com.connectbridge.connect_bridge_BE.outactpage.service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.jwt.JwtProvider;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.ModifyResDto;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.OutActDto;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.PostCreateDto;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class OutActService {

    private final JwtProvider jwtProvider;
    private final OutActRepository outActRepository;
    private final S3Service s3Service;

    public OutActService(JwtProvider jwtProvider, OutActRepository outActRepository, S3Service s3Service) {
        this.jwtProvider = jwtProvider;
        this.outActRepository = outActRepository;
        this.s3Service = s3Service;
    }

    // yml에 저장된 경로
    @Value("${spring.servlet.multipart.location}")
    String baseDir;

    public List<OutActDto> getList(Pageable pageable, int reqPage){

        // Pageable 설정
        pageable = PageRequest.of(reqPage,5, Sort.by(Sort.Direction.DESC,"id"));

        Page<OutAct> page = outActRepository.findAll(pageable); // DB값 불러옴.

        List<OutActDto> pageDto = page.map(OutActDto::new).getContent(); // List로 받게 바꿔봄 ㅋ

        System.out.println("Service getList 동작 됨");

            return pageDto;
    }

    // 생성
    public boolean createPost(PostCreateDto request) throws IOException {

        try {

            OutAct outAct = new OutAct();
            outAct.createPost(request.getOutActName(),request.getOutActImg(), request.getOutActLink());
            outActRepository.save(outAct);

            return true;

        }catch (Exception e){
            System.out.println(e);

            return false;
        }
    }


    // 개별 정보 조회
    public ModifyResDto modifyInfo(Long id){
        OutAct outAct= outActRepository.findByid(id);

        return ModifyResDto.builder()
                .outActName(outAct.getOutActName())
                .outActImg(outAct.getOutActImg())
                .outActLink(outAct.getOutActLink())
                .build();
    }

    // 수정
    public Boolean updatePost(UpdateReqDto requestDto) throws IOException {
        OutAct outAct = outActRepository.findByid(requestDto.getOutActID());

        String newUrl = requestDto.getOutActImg();
        String oldUrl = outAct.getOutActImg();

        // 새 파일 등록 확인
        if (newUrl != null && !newUrl.isEmpty()) {

            // 기존 파일 삭제
            s3Service.deleteS3(oldUrl);

            outAct.updatePost(requestDto.getOutActName(),
                    newUrl,
                    requestDto.getOutActLink());
            outActRepository.save(outAct);

            return true;
        }
        return false;
    }

    // 삭제
    /*
    전달받은 id로 해당 id의 DB에 있는 Img Url을 가져온다.
    deleteS3: "https: ~ .com/"의 문자열을 때고 저장된 문서 + 이름을 이용해서 파일 삭제.
    deleteById: id에 해당하는 row를 삭제
     */
    public boolean deletePost(Long id){
        try {
            String path = outActRepository.findByid(id).getOutActImg(); // img 저장 경로

            s3Service.deleteS3(path);
            // row 삭제
            outActRepository.deleteById(id);

            return true;
        }catch (Exception e){

            return false;
        }
    }

}
