package com.andyadc.samples;

import com.andyadc.samples.domain.AdvanceInfo;
import com.andyadc.samples.domain.BasicInfo;
import com.andyadc.samples.domain.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("/user-account")
@Controller
public class UserAccountController {

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public String save(@Valid @ModelAttribute("useraccount") UserAccount userAccount,
                       BindingResult result,
                       ModelMap modelMap) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/saveBasicInfo", method = {RequestMethod.POST})
    public String saveBasicInfo(@Validated({BasicInfo.class}) @ModelAttribute("useraccount") UserAccount userAccount,
                                BindingResult result,
                                ModelMap modelMap) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/saveAdvanceInfo", method = {RequestMethod.POST})
    public String saveAdvanceInfo(@Validated({AdvanceInfo.class}) @ModelAttribute("useraccount") UserAccount userAccount,
                                  BindingResult result,
                                  ModelMap modelMap) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "error";
        }
        return "success";
    }
}
