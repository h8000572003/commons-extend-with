BDD 情境測試

一般撰寫風格


場景：要登入但忘記輸入帳號與密碼
(GIVE)當：要輸入登入資料
且：輸入帳號
且：輸入密碼

(WHEN)當：點擊登入

(THEN)那麼：有取得使用者帳號
而且：登入帳號為Ｔom


```java
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


```