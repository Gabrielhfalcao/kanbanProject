package com.gabriel.taskmanagerapi.exception;

import com.gabriel.taskmanagerapi.exception.dto.*;
import com.gabriel.taskmanagerapi.exception.handler.ActivityUpdateDescriptionNullException;
import com.gabriel.taskmanagerapi.exception.handler.EmailAlreadyRegisteredException;
import com.gabriel.taskmanagerapi.exception.handler.RoleAlreadyAssignedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionDTO> tratarErro404(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(new EntityNotFoundExceptionDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorExceptionDTO>> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationErrorExceptionDTO::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DataValidationExceptionDTO> tratarErroValidacao(ValidationException ex){
        DataValidationExceptionDTO dadosValidadorException = new DataValidationExceptionDTO(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(dadosValidadorException);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<EmailAlreadyRegisteredExceptionDTO> tratarErroEmailAlreadyRegistered(EmailAlreadyRegisteredException ex){
        return ResponseEntity.status(409).body(new EmailAlreadyRegisteredExceptionDTO(ex.getMessage()));
    }

    @ExceptionHandler(RoleAlreadyAssignedException.class)
    public ResponseEntity<RoleAlreadyAssignedExceptionDTO> tratarErroRoleAlreadyAssigned(RoleAlreadyAssignedException ex){
        return ResponseEntity.status(409).body(new RoleAlreadyAssignedExceptionDTO(ex.getMessage()));
    }

    @ExceptionHandler(ActivityUpdateDescriptionNullException.class)
    public ResponseEntity<ActivityUpdateDescriptionNullExceptionDTO> tratarErroActivityUpdateDescriptionNullException(ActivityUpdateDescriptionNullException ex){
        return ResponseEntity.status(400).body(new ActivityUpdateDescriptionNullExceptionDTO(ex.getMessage()));
    }
}
