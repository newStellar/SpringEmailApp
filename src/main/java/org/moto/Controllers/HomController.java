package org.moto.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.moto.Models.User;
import org.moto.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String signup(){

        return "index";
    }

    @RequestMapping(value = "/signup-success" , method = RequestMethod.POST)
    @ResponseBody
    public String signupSuccess(@RequestBody String info){

        System.out.println(info);

        JsonElement  element = new JsonParser().parse(info);
        JsonObject jsonObject = element.getAsJsonObject();


        String recieverEmailAddress = new Gson().fromJson(jsonObject.getAsJsonPrimitive("email"), String.class);
        String reciverMsg = new Gson().fromJson( jsonObject.getAsJsonPrimitive("msg"), String.class);





        User user = new User();

        user.setName("Someone");
        user.setEmailAdress( recieverEmailAddress);
        user.setMsg(reciverMsg);

        System.out.println( recieverEmailAddress + " _____ "+ reciverMsg);

        try {

            emailService.sendNotification(user);

        } catch (MailException e){

            System.out.println(e.toString());
            return "Server internal error occured";
        }

        return "Message has been sent Successfully";

    }
}
