package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudySubmitRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyManageService {

    private final StudySubmitRepository submitRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public StudyManageService(StudySubmitRepository submitRepository, StudyRepository studyRepository, UserRepository userRepository) {
        this.submitRepository = submitRepository;
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }


}
