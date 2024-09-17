package com.portifolio.elton.gitHubApplication.controller;


import com.portifolio.elton.gitHubApplication.client.GithubClient;
import com.portifolio.elton.gitHubApplication.client.RepositoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ApiController {

    //É uma interface.
    private final GithubClient githubClient;

    public ApiController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @GetMapping(value = "/repos")
    public ResponseEntity<List<RepositoryResponse>> listMyRepositories(@RequestHeader(value = "token") String personalAccesToken) {

        var repos = githubClient.getRepositories(
                "bearer " + personalAccesToken,
                null,
                "public");

        //código extra para interagir com o retorno do repositório.
        for (RepositoryResponse repo : repos) {
            if (repo.url().equals("https://github.com/ProjetosEltonLira/desafio_bebida")) {
                System.out.println("Repositório da bebida");
            }
        }
        return ResponseEntity.ok(repos);
    }
}
