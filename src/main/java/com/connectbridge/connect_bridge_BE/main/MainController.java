package com.connectbridge.connect_bridge_BE.main;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class MainController {
    private final MainService mainService;

    @GetMapping("/main")
    public ResponseEntity<?> mainPage(){

        return new ResponseEntity<>(mainService.mainGet(),HttpStatus.OK);
    }
}
