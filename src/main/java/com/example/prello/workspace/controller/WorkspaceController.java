package com.example.prello.workspace.controller;

import com.example.prello.workspace.dto.WorkspaceRequestDto;
import com.example.prello.workspace.dto.WorkspaceResponseDto;
import com.example.prello.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    // 워크스페이스 생성
    @PostMapping
    public ResponseEntity<WorkspaceResponseDto> createWorkspace(@Valid @RequestBody WorkspaceRequestDto workspaceRequestDto) {
        WorkspaceResponseDto workspaceResponseDto = workspaceService.createWorkspace(workspaceRequestDto);
        return new ResponseEntity<>(workspaceResponseDto, HttpStatus.CREATED);
    }

    // 워크스페이스 수정
    @PatchMapping("/{id}")
    public ResponseEntity<WorkspaceResponseDto> updateWorkspace(@PathVariable Long id, @Valid @RequestBody WorkspaceRequestDto workspaceRequestDto) {
        WorkspaceResponseDto workspaceResponseDto = workspaceService.updateWorkspace(id, workspaceRequestDto);
        return new ResponseEntity<>(workspaceResponseDto, HttpStatus.OK);
    }

    // 워크스페이스 목록 조회
    @GetMapping
    public ResponseEntity<List<WorkspaceResponseDto>> getAllWorkspaces() {
        List<WorkspaceResponseDto> workspaceResponseDtoList = workspaceService.getAllWorkspaces();
        return new ResponseEntity<>(workspaceResponseDtoList, HttpStatus.OK);
    }

    // 워크스페이스 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceResponseDto> getWorkspace(
            @PathVariable Long id) {
        WorkspaceResponseDto workspaceResponseDto = workspaceService.getWorkspace(id);
        return new ResponseEntity<>(workspaceResponseDto, HttpStatus.OK);
    }

    // 워크스페이스 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkspace(
            @PathVariable Long id) {
        String message = workspaceService.deleteWorkspace(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}