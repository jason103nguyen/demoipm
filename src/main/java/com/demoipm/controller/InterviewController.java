package com.demoipm.controller;

import com.demoipm.consts.URLConst;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewRequest;
import com.demoipm.dto.general.DatatableParamRequestDTO;
import com.demoipm.dto.general.DatatableResponseDTO;
import com.demoipm.email.model.EmailRequest;
import com.demoipm.service.InterviewService;
import com.demoipm.service.PotentialCandidateService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private PotentialCandidateService potentialCandidateService;

    @Secured("ROLE_HR")
    @RequestMapping("interview/view")
    public String interviewPage() {
        return "interview/interview";
    }

    @PostMapping(URLConst.API_SEND_EMAIL)
    public String getRecruitmentByCondition(@RequestParam("id") Integer id) {
        CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);
        String message = "Mẫu thư mời phỏng vấn là mẫu thư được sử dụng khi các doanh nghiệp tìm kiếm được các ứng viên có nội dung Cv xin việc phù hợp ở vòng loại hồ sơ, quyết định lựa chọn ứng viên đó tham gia vào buổi phỏng vấn để có thể đánh giá chính xác và chi tiết hơn về năng lực.\n"
            + "Nội dung của mẫu thư mời phỏng vấn khi được soạn thảo "
            + "phải đảm đảo đầy đủ các nội dung thông tin, thể hiện rõ ràng ý muốn "
            + "mời tham gia phỏng vấn. Để trình bày mẫu thư mời tham gia phỏng vấn chuẩn, "
            + "bạn cần viết một cách ngắn gọn, súc tích, viết thông tin cần thiết về buổi phỏng "
            + "vấn để ứng viên nắm rõ thời gian, địa chỉ buổi phỏng vấn. Các bạn cùng tham khảo "
            + "các mẫu thư mời tham dự phỏng vấn dưới đây. Bên cạnh đó, bạn đừng quên gọi điện mời ứng viên đến tham gia buổi phỏng vấn sắp tới của công ty.";
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo("huyhuy611998@gmail.com" );
        emailRequest.setSubject("Send to " + candidateDto.getFullName());
        emailRequest.setMessage(message);
        return "";
    }

    @GetMapping("/interview/create")
    public String showForm(Model model,@RequestParam("id") Integer id, HttpSession httpSession) {
        model.addAttribute("interviewRequest", new InterviewRequest());
        CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);
        model.addAttribute("fullName", candidateDto.getFullName());
        String keySearch = (String) httpSession.getAttribute("keySearch");

        Integer pageNo = (Integer) httpSession.getAttribute("pageNo");

        String sortBy = (String) httpSession.getAttribute("sortBy");

        String direction = (String) httpSession.getAttribute("direction");

        model.addAttribute("keySearch", keySearch);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        return "interview/interview";
    }

    @PostMapping("/interview/create/{id}")
    public String createForm(@ModelAttribute("interviewRequest") InterviewRequest interviewRequest,
        BindingResult result, Model model) {
        model.addAttribute("interviewRequest", interviewService.create(interviewRequest));
        return "interview/setup_success";
    }
}
