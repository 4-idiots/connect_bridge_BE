package com.connectbridge.connect_bridge_BE.projectpage.repository;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectID(Long projectID);

    Optional<Project> findById(Long id);

    // List to String
    public default String listToStr(List<String> test) {
        String str = String.join(",", test);
        System.out.println(str);
        return str;
    }

    // String to List
    public default List<String> strToList(String str){
        return Arrays.asList((str.split(",")));
    }

}


        //"Str","Str"모양이 나오게 만들어야함 db에는 어떻게 해야 들어가나?
