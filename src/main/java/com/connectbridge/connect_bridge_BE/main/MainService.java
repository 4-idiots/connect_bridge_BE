package com.connectbridge.connect_bridge_BE.main;

import com.connectbridge.connect_bridge_BE.community.CommunityEntity;
import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActLikeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.teampage.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    private final CommunityRepository communityRepository;
    private final StudyRepository studyRepository;

    public HashMap mainGet(){
        List<ProjectEntity> topProjects = projectRepository.findTop4ByOrderByIdDesc();
        List<CommunityEntity> topCommunity = communityRepository.findTop4ByOrderByIdDesc();
        List<RegisterEntity> topRegister = teamRepository.findTop4ByOrderByIdDesc();
        List<StudyEntity> topStudy = studyRepository.findTop4ByOrderByIdDesc();
        HashMap<String,List> page = new HashMap<>();
        page.put("project",topProjects);
        page.put("community",topCommunity);
        page.put("register",topRegister);
        page.put("study",topStudy);

        return page;
    }
}
