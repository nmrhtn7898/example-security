package com.syj.jspexample.app.controller.api;

import com.syj.jspexample.app.dto.Result;
import com.syj.jspexample.app.dto.Status;
import com.syj.jspexample.app.entity.Users;
import com.syj.jspexample.app.service.UsersService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UsersApiController {

    private final UsersService usersService;

    /**
     * 계정 이메일 가입 전 이메일의 등록 여부를 확인
     * @param email 확인 값
     * @return 사용 가능 여부
     */
    @GetMapping(
            value = "/api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Boolean available(@RequestParam String email) {
        return usersService.available(email);
    }

    /**
     * 게정 가입
     * @param request input data
     * @return output data
     */
    @PostMapping(
            value = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Result join(@RequestBody @Valid JoinRequest request) {
        Users users = new Users();
        users.setEmail(request.getEmail());
        users.setPassword(request.getPassword());
        users = usersService.join(users);
        return new Result<>(new JoinResponse(users), new Status("200", "SUCCESS"));
    }

    /**
     * 계정 이메일 수정
     * @param request input data
     * @return output data
     */
    @PatchMapping(
            value = "/api/users/{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Result update(@PathVariable String email, @RequestBody @Valid UpdateRequest request) {
        Users users = new Users();
        users.setEmail(email);
        users.setPassword(request.getPassword());
        users = usersService.update(users);
        return new Result<>(new UpdateResponse(users), new Status("200", "SUCCESS"));
    }

    /**
     * 계정 이메일 삭제
     * @param request input data
     * @return output data
     */
    @DeleteMapping(
            value = "/api/users/{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Result delete(@PathVariable String email, @RequestBody @Valid DeleteRequest request) {
        Users users = new Users();
        users.setEmail(email);
        users.setPassword(request.getPassword());
        users = usersService.delete(users);
        return new Result<>(new DeleteResponse(users), new Status("200", "SUCCESS"));
    }

    /** 해당 컨트롤러에서 사용하는 요청, 응답 DTO 선언 */
    @Data
    private static class JoinRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Data
    private static class JoinResponse {
        private Long id;
        private LocalDateTime joinDate;
        private JoinResponse(Users users) {
            this.id = users.getId();
            this.joinDate = users.getCreated();
        }
    }

    @Data
    private static class UpdateRequest {
        @NotBlank
        private String password;
    }

    @Data
    private static class UpdateResponse {
        private Long id;
        private String enabled;
        private String deleted;
        private String auth;
        private LocalDateTime updateDate;
        private UpdateResponse(Users users) {
            this.id = users.getId();
            this.enabled = users.getEnabled();
            this.deleted = users.getDeleted();
            this.auth = users.getAuth().getValue();
            this.updateDate = users.getCreated();
        }
    }

    @Data
    private static class DeleteRequest {
        @NotBlank
        private String password;
    }

    @Data
    private static class DeleteResponse {
        private Long id;
        private String enabled;
        private String deleted;
        private LocalDateTime deleteDate;
        private DeleteResponse(Users users) {
            this.id = users.getId();
            this.enabled = users.getEnabled();
            this.deleted = users.getDeleted();
            this.deleteDate = users.getModified();
        }
    }

}
