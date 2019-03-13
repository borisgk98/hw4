package space.borisgk.itis.sem4.hw4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import space.borisgk.itis.sem4.hw4.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    private MessageSourceAccessor msa;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms){
        this.msa = new MessageSourceAccessor(ms);
    }

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @GetMapping
    public String registrationForm(ModelMap map) {
        map.put("user", new User());
        map.put("message0", this.msa.getMessage("controller.message"));
        map.put("message1", this.msa.getMessage("controller.param.message", new Double[]{9.78}));
        return "reg";
    }

    @PostMapping
    public String registration(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
            return "reg";
        }
        else {
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", "<span style=\"color:green\">User \"" + user.getName()+"\" has been registered successfully</span>");
            return "redirect:"+MvcUriComponentsBuilder.fromMappingName("RC#registrationForm").build();
        }
    }
}
