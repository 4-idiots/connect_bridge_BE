package com.idiotdb.loginpage.register.controller;


import com.idiotdb.loginpage.register.data.dto.RegisterDto;
import com.idiotdb.loginpage.register.service.RegisterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class RegisterController {

    private final RegisterImpl registerService;

    @GetMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<?> getRegisters() throws Exception {
        List<RegisterDto> dtoList = registerService.getRegisters();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/register")
    public ResponseEntity<String> postRegister(@RequestBody RegisterDto registerDto) throws Exception {
        registerService.postRegister(registerDto);
        return new ResponseEntity<String>("message : ok", HttpStatus.OK);
    }


}
