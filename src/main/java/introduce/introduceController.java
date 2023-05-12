package introduce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class introduceController {
    @PostMapping("/post")
    public String handlePostRequest(@RequestParam("content") String content) {
        // 폼 데이터 처리 코드
        // ...
        System.out.println(content);
        // 현재 페이지로 리디렉션
        return "redirect:/";
    }
}


