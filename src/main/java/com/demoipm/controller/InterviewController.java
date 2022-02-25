package com.demoipm.controller;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewRequest;
import com.demoipm.email.controller.EmailController;
import com.demoipm.service.InterviewService;
import com.demoipm.service.PotentialCandidateService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Date;

@Controller
public class InterviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private PotentialCandidateService potentialCandidateService;

    @Secured("ROLE_HR")
    @RequestMapping("interview/view")
    public String interviewPage() {
        return "interview/interview";
    }

    @GetMapping("interview/send-email")
    public String senderEmail(@RequestParam("date") String date,
                              @RequestParam("time") String time,
                              @RequestParam("name") String name,
                              @RequestParam("address") String address, Model model) {
        Thread t = new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 1000); //Timeout Limit
                HttpResponse response;
                JSONObject json = new JSONObject();
                String message = "Dear Mr. "+ name +
                        "\n\n" +
                        "As discussion, FPT software would like to schedule 1st meeting with details as follow: \n" +
                        "\n" +
                        "Position: Java Developer" +
                        "\n" +
                        "Time: " + date + " - " + time +
                        "\n" +
                        "Address: " + address +
                        "\n" +
                        "Contact person: Mr. Linh Le" +
                        "\n" +
                        "Contact number: 0948228911" +

                        "\n\n" +
                        "Please submit this Application form and kindly confirm for us whenever you receive our email and let us know if there is any question or concern.\n" +
                        "\n\n" +
                        "Thank you & Best Regards,";
                try {
                    HttpPost post = new HttpPost ("http://localhost:8081/api/send-email");
                    json.put("to", "linhln013@gmail.com");
                    json.put("subject", "Interview Invitation - " + name);
                    json.put("message", message);
                    System.out.println(json.toString());
                    StringEntity se = new StringEntity(json.toString());
                    post.setHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    post.setEntity(se);
                    response = client.execute(post);
                    model.addAttribute("message", "Send email successful!");
                    /*Checking response */
                    if (response != null) {
                        InputStream in = response.getEntity().getContent(); //Get the data in the entity
                    }
                } catch (Exception e) {
                    LOGGER.error("Has error when send email", e);
                    model.addAttribute("error", "Send email fail!");
                }
            }
        };
        t.start();
        return "redirect:/view-potential-candidates-list";
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
