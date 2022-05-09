package com.connectbridge.connect_bridge_BE.main;

import com.connectbridge.connect_bridge_BE.community.CommunityRepository;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActLikeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.teampage.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MainService {
    private final OutActLikeRepository outActLikeRepository;
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    private final CommunityRepository communityRepository;





}
