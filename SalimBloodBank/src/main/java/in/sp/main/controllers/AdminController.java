//package in.sp.main.controllers;
//import in.sp.main.dto.RegisterDTO;
//import in.sp.main.service.AdminService;
//import in.sp.main.service.AgentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//public class AdminController {
//    @Autowired
//    AdminService adminService;
//
//    @Autowired
//    AgentService agentService;
//
//    // Mapping to get signup view
//    @RequestMapping("/signup")
//    public String getSignupView() {
//        return "signup";
//    }
//
//    // Mapping to get login view
//    @RequestMapping("/login")
//    public String getLoginView() {
//        return "login";
//    }
//
//    @RequestMapping("/createAgent")
//    public String createAgent()
//    {
//        return "createAgent";
//    }
//
//    @RequestMapping("/createUser")
//    public String createUser(){ return "createUser"; }
//
//    @RequestMapping("signupUsers")
//    public String signupUsers(){ return "signupUsersDetails"; }
//
//    @RequestMapping("/admin")
//    public String returnToAdminPage(){ return "adminProfilePage";}
//
//    @RequestMapping("/agent")
//    public String returnToAgentPage(){ return "agentProfilePage";}
//
//
//    @RequestMapping("/bloodRequirement")
//    public String bloodRequirementForm()
//    {
//        return "bloodRequest";
//    }
//
//
//
//
//
//
//    @GetMapping("/sort/{by}")
//    public String sort(@PathVariable String by, Model model)
//    {
//        model.addAttribute("sortedUsers", adminService.sortUsers(by));
//        return "signupUsersDetails";
//    }
//
//
//
//
//
//    @GetMapping("/filter/{by}")
//    public String filter(@PathVariable String by, Model model, @RequestParam String filterValue)
//    {
//        try{
//            List<RegisterDTO> filteredUser = adminService.filterSignedUpCreatedUsers(by,filterValue);
//            model.addAttribute("sortedUsers",filteredUser);
//            return "signupUsersDetails";
//        }
//        catch (Exception e)
//        {
//            return "signupUserDetails";
//        }
//    }
//
//    @RequestMapping("/agentCreatedUsers")
//    public String getAgentCreatedUsers(HttpServletRequest httpServletRequest)
//    {
//        HttpSession httpSession = httpServletRequest.getSession();
//        RegisterDTO user = (RegisterDTO) httpSession.getAttribute("user");
//        httpSession.setAttribute("agentCreatedUsers", agentService.getAgentCreatedUsers(user.getUsername()));
//
//
//        return "agentCreatedUsers";
//    }
//
//
//
//
//
//    @RequestMapping("/agentSort/{by}")
//    public String sortAgentCreatedUsers(@PathVariable String by,
//                                        HttpServletRequest httpServletRequest, Model model)
//    {
//        HttpSession httpSession = httpServletRequest.getSession();
//        RegisterDTO   user = (RegisterDTO  ) httpSession.getAttribute("user");
//        model.addAttribute("sortedUsers",agentService.sortUsers(by, user.getUsername()));
//        return "agentCreatedUsers";
//    }
//
//
//
//
//    @RequestMapping("/agentFilter/{by}")
//    public String filterAgentCreatedUsers(@PathVariable String by,
//                                          HttpServletRequest httpServletRequest,
//                                          Model model, @RequestParam String filterValue)
//    {
//        HttpSession httpSession = httpServletRequest.getSession();
//        RegisterDTO user = (RegisterDTO) httpSession.getAttribute("user");
//        try{
//            List<RegisterDTO> filteredAgentUsers =
//                    agentService.filterAgentCreatedUsers(by,filterValue, user.getUsername());
//            System.out.println(filteredAgentUsers);
//            model.addAttribute("sortedUsers", filteredAgentUsers);
//        }
//        catch (Exception e)
//        {
//
//        }
//
//        return "agentCreatedUsers";
//    }
//
//}
