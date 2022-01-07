package br.com.uniacademia.cesIC.endpoints;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uniacademia.cesIC.models.Contributor;

@FeignClient(name = "commits", url = "${client.port.baseUrl}", decode404 = true)
public interface ContributorEndPoint {

    @GetMapping(produces = "application/json", value = "/repos/nodejs/node/contributors?sha=master&per_page=100&page={page}")
    List<Contributor> buscarContributor(@RequestParam("page") int page);

    @GetMapping(produces = "application/json", name = "commits", value = "/repos/nodejs/node/commits?sha=master&author={author}&per_page=100&page={page}")
    List<ObjectNode> findCommits(@RequestParam("page") int page, @RequestParam("author") String author);

}
