package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PositionController {

    @Autowired
    private PositionService PositionService;

    // @GetMapping("/api/product")
    // public @ResponseBody List<Product> gubun(@RequestParam(defaultValue = "1")
    // Integer gubun) {
    // return productRepository.findByGubun(gubun);
    // }

    // // comp_ 채용 정보 화면
    // @GetMapping("/api/comp/jobOpening/select")
    // public String @ResponseBody compJobOpeningSelectForm() {

    // }
}
