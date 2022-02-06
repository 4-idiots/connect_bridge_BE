package com.connectbridge.connect_bridge_BE.loginpage.register.validationerror;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest; import java.util.Objects;

@Slf4j
@ControllerAdvice

public class ValidationExceptionController {
    /** * @valid 유효성체크에 통과하지 못하면 MethodArgumentNotValidException 이 발생한다. */
    @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<ValidationErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("MethodArgumentNotValidException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ValidationErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ValidationErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST); }
    private ValidationErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String errorMessage = ""; //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            errorMessage = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            //switch (bindResultCode){
            switch (Objects.requireNonNull(bindResultCode)){
                case "NotNull":
                    code = ValidationErrorCode.NOT_NULL.getCode();
                    description = ValidationErrorCode.NOT_NULL.getDescription();
                    break;
                case "NotBlank":
                    code = ValidationErrorCode.NOT_BLANK.getCode();
                    description = ValidationErrorCode.NOT_BLANK.getDescription();
                    break;
                case "Min":
                    code = ValidationErrorCode.MIN_VALUE.getCode();
                    description = ValidationErrorCode.MIN_VALUE.getDescription();
                    break;
                case "Pattern":
                    code = ValidationErrorCode.PATTERN.getCode();
                    description = ValidationErrorCode.PATTERN.getDescription();
                    break;
                case "Email":
                    code = ValidationErrorCode.EMAIL.getCode();
                    description = ValidationErrorCode.EMAIL.getDescription();
                    break;
            }
        }
        return new ValidationErrorResponse(code, description, errorMessage);
    }
}
