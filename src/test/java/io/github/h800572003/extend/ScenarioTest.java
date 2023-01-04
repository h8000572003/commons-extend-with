package io.github.h800572003.extend;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
class ScenarioTest {

    LoginService loginService = new LoginService();

    @Test
    void tesScenario_loginFail() {


        final Scenario scenario = new Scenario();
        scenario.describe("登入系統但忘記輸入帳號與密碼")
                .give("輸入資料", () -> new LoginDTO())
                .and("輸入帳號", this::setUserIdBLank)
                //
                .whenWithThrow("登入", loginService::login)
                //
                .then("帳號不存在", i -> i.getMessage().contains("帳號不存在"));

        List<IStage> list = scenario.list();
        Assertions.assertEquals(3,list.size());
    }


    @Test
    void tesScenario_loginOk() {
        Scenario scenario = new Scenario();
        scenario.describe("要進行登入但忘記輸入帳號與密碼")
                .give("輸入資料", () -> new LoginDTO())
                .and("輸入帳號", i -> i.setUserId("Tom"))
                //
                .when("執行登入", loginService::login)
                //
                .then("取得使用者資料", Objects::nonNull)
                .but("帳號是登入者", i -> i.getUserId().equals("Tom"))

        ;
    }


    void setUserIdBLank(LoginDTO loginDTO) {
        loginDTO.setUserId("");
    }

    @Data
    class LoginDTO {
        private String userId;
        private String pwd;
    }

    @Data
    class UserDTO {
        private String userId;

        public UserDTO(String userId) {
            this.userId = userId;
        }
    }

    class LoginService {
        UserDTO login(LoginDTO loginDTO) {
            String userId = loginDTO.getUserId();
            if (StringUtils.isBlank(userId)) {
                throw new RuntimeException("帳號不存在");
            } else {
                return new UserDTO(loginDTO.getUserId());
            }
        }
    }
}