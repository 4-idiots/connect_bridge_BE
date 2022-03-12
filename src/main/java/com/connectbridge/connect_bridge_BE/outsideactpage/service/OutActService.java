package com.connectbridge.connect_bridge_BE.outsideactpage.service;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.ModifyResDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.OutActDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.PostCreateDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.outsideactpage.data.entity.OutAct;
import com.connectbridge.connect_bridge_BE.outsideactpage.repository.OutActRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OutActService {

    private final OutActRepository outActRepository;

    public OutActService(OutActRepository outActRepository) {
        this.outActRepository = outActRepository;
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

    // 파일 저장 경로 설정
    public String uploadFile(String originName){

        // file 이름 중복방지 uuid
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출
        String ext = originName.substring(originName.lastIndexOf("."));

        // 저장될 이름
        String saveName = uuid + ext;

        // 저장될 경로
        String filePath = baseDir +"\\"+ saveName;

        return  filePath;

    }

    // 생성
    public boolean createPost(PostCreateDto request) throws IOException {

        // file 원본 이름
        String originName = request.getOutActImg().getOriginalFilename();

        String filePath = uploadFile(originName);
        request.getOutActImg().transferTo(new File(filePath));


        try {
            OutAct outAct = new OutAct();
            outAct.createPost(request.getOutActName(),filePath, request.getOutActLink());
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

        System.out.println("service CreatePost 동작함.");

        return ModifyResDto.builder()
                .outActName(outAct.getOutActName())
                .outActImg(outAct.getOutActImg())
                .outActLink(outAct.getOutActLink())
                .build();
    }

    // 수정
    public Boolean updatePost(UpdateReqDto requestDto) throws IOException {
        OutAct outAct = outActRepository.findByid(requestDto.getOutActID());

        // 기존 저장된 경로
        String filePath = outAct.getOutActImg();

        // 새 파일 등록 확인
        if (requestDto.getOutActImg() != null && !requestDto.getOutActImg().isEmpty()) {

            // 기존 경로의 파일 삭제
            File file = new File(outAct.getOutActImg());
            file.delete();

            // 새 file 등록
            String originName = requestDto.getOutActImg().getOriginalFilename();
            assert originName != null : "originName is null";
            filePath = uploadFile(originName);
            requestDto.getOutActImg().transferTo(new File(filePath));

        }

        outAct.updatePost(requestDto.getOutActName(),
                filePath,
                requestDto.getOutActLink());

        outActRepository.save(outAct);

        return true;
    }

    // 삭제
    public boolean deletePost(Long id){
        try {
            String path = outActRepository.findByid(id).getOutActImg(); // img 저장 경로
            File file = new File(path); // 경로로 파일 생성
            if(file.exists()){
                file.delete(); // 경로의 파일 삭제
            }
            // row 삭제
            outActRepository.deleteById(id);


            return true;
        }catch (Exception e){

            return false;
        }
    }

}
