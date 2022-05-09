package com.connectbridge.connect_bridge_BE.mypage;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.community.CommunityPreviewDto;
import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.UpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final RegisterRepository registerRepository;
    private final CommunityRepository communityRepository;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;

    //내 정보 get 불러오기
    @Transactional
    public RegisterDto getMyPage(long fromUserId){
        RegisterDto registerDto = new RegisterDto();

        RegisterEntity registerEntity = registerRepository.getById(fromUserId);
        registerDto.setId(registerEntity.getId());
        registerDto.setUserID(registerEntity.getUserID());
        registerDto.setUserName(registerEntity.getUserName());
        registerDto.setUserEmail(registerEntity.getUserEmail());
        registerDto.setUserNickname(registerEntity.getUserNickname());
        registerDto.setUserIntroduce(registerEntity.getUserIntroduce());
        registerDto.setUserAbility(registerEntity.getUserAbility());
        registerDto.setUserArea(registerEntity.getUserArea());
        registerDto.setUserTime(registerEntity.getUserTime());
        registerDto.setUserPortfolio(registerEntity.getUserPortfolio());
        registerDto.setUserInterestMain(registerEntity.getUserInterestMain());
        registerDto.setUserInterestSub(registerEntity.getUserInterestSub());
        registerDto.setRegisterDate(registerEntity.getCreateDate());
        return registerDto;
    }

    //내 정보 수정하기
    public void updateMyInfo(UpdateRegisterDto updateRegisterDto){
        RegisterEntity registerEntity = registerRepository.findByid(updateRegisterDto.getId());
        String encodePassword = passwordEncoder.encode(updateRegisterDto.getUserPW());
        String url = null;

        try {
            url = s3Service.upload(updateRegisterDto.getImg(), "user");
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateRegisterDto.setUserPicture(url);
        String newUrl = updateRegisterDto.getUserPicture();
        String oldUrl = registerEntity.getUserPicture();

        if (oldUrl == null){
            registerEntity.updateRegister(encodePassword, updateRegisterDto.getUserNickname(),
                    updateRegisterDto.getUserAbility(), updateRegisterDto.getUserArea(),
                    updateRegisterDto.getUserTime(), updateRegisterDto.getUserInterestMain(),
                    updateRegisterDto.getUserInterestSub(), updateRegisterDto.getUserIntroduce(),
                    updateRegisterDto.getUserPortfolio(), newUrl);
            registerRepository.save(registerEntity);
        }else if (newUrl != null && !newUrl.isEmpty()) {
            s3Service.deleteS3(oldUrl);
            registerEntity.updateRegister(encodePassword, updateRegisterDto.getUserNickname(),
                    updateRegisterDto.getUserAbility(), updateRegisterDto.getUserArea(),
                    updateRegisterDto.getUserTime(), updateRegisterDto.getUserInterestMain(),
                    updateRegisterDto.getUserInterestSub(), updateRegisterDto.getUserIntroduce(),
                    updateRegisterDto.getUserPortfolio(), newUrl);
            registerRepository.save(registerEntity);
        }
    }

    public CommunityPreviewDto getCommunityPage(long fromUserId){
        CommunityPreviewDto communityPreviewDto = new CommunityPreviewDto();



        return communityPreviewDto;
    }
}
