package com.example.prello.workspace.repository;

import com.example.prello.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long>, WorkspaceRepositoryQuery {

}
