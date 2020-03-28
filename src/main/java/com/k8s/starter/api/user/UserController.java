package com.jda.starter.api.user;

import com.jda.starter.api.Routes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequestMapping(Routes.LATEST_VERSION)
public class UserController {

  @Autowired
  private WebClient.Builder builder;

  @Value("${externalUrl}")
  private String externalUrl;

  //Sample REST API Call to external API
  @GetMapping(Routes.USERS)
  public ResponseEntity<User> getUsers() {
    User user = builder.build()
        .get()
        .uri(externalUrl)
        .retrieve()
        .bodyToMono(User.class)
        .block();

    log.info("User: " + user);
    if (user == null) {
      user = new User();
    }
    return ResponseEntity.ok(user);
  }
}
