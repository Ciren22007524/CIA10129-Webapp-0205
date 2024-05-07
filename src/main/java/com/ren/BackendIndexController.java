package com.ren;

import com.Entity.Administrator;
import com.ren.administrator.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Validated
@Controller
@RequestMapping("/backend")
public class BackendIndexController {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    /**
     * 前往首頁
     * 登入後跳轉、在登入狀態時點選側邊欄的 icon 與 Home 後可回到首頁
     *
     * @return forward to backend index
     */
    @GetMapping("/index")
    public String toBackendIndex() {
        return "backend/index";
    }

    /**
     * 前往註冊頁面
     *
     * @return forward to backend register
     */
    @GetMapping("/register")
    public String toRegister(Model model) {
        model.addAttribute("administrator", new Administrator());
        return "backend/register";
    }

    /**
     * 前往登入頁面
     *
     * @return forward to backend login
     */
    @GetMapping("/login")
    public String toLogin() {
        return "backend/login";
    }

    // SSO OATH 2.0 單點登入
    // 記錄登入活動，如嘗試登入、成功登入、登入失敗等
    // 確認帳號正確，但密碼不正確的情況 n + 1， 30分鐘內達5次鎖定登入
    // 登入
    // 前端輸入用戶名或Email
    @PostMapping("login")
    public String login(@RequestParam String userId,
                        @RequestParam String admPwd,
                        ModelMap model,
                        HttpSession session) {

        Administrator administrator = null;
        // 確認是否為信箱格式，true則透過信箱搜尋使用者資訊，false則透過管理員編號搜尋使用者資訊
        if (validateEmail(userId)) {
            administrator = administratorSvc.getOneAdministrator(userId);
        } else {
            administrator = administratorSvc.getOneAdministrator(Integer.valueOf(userId));
        }
        // 在驗證前將使用者Id放入model，如果使用者輸入錯誤，返回給使用者而不用重新輸入
        model.addAttribute("userId" ,userId);
        // 判斷是否有無這個帳號
        if (administrator == null) {
            model.addAttribute("message", "不存在此用戶");
            return "backend/login";
        }
        // 判斷密碼是否正確
        if (admPwd != administrator.getAdmPwd()) {
            model.addAttribute("message", "密碼錯誤!");
            return "backend/login";
        }
        // 確認密碼正確後，回傳登入成功訊息，並將administrator存入session
        model.addAttribute("message", "登入成功!");
        session.setAttribute("administrator", administrator);

        return "redirect:/backend/index";
    }

    @GetMapping("/forgotPassword")
    public String toForgotPassword() {
        return "backend/forgotPassword";
    }

    // 登出
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/backend/login";
    }

    // 最新消息推播
    @GetMapping("/")
    public String news() {
        return "";
    }

    // 站內搜尋
    @GetMapping
    public String search() {
        return "";
    }

    /**
     * Email格式的正則表達式
     */
    private final Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    /**
     * 驗證是否為信箱格式
     *
     * @param email 在登入頁面輸入用戶名或Email時傳入方法內判斷格式
     * @return 返回布林值，如果符合Email格式則返回true，不符合則返回false
     */
    private Boolean validateEmail(String email) {
        return emailRegex.matcher(email).find();
    }

}