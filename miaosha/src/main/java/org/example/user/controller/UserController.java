package org.example.user.controller;


import com.alibaba.druid.util.StringUtils;
import org.example.common.CommonUtils;
import org.example.baseController.BaseController;
import org.example.user.controller.viewobject.UserVo;
import org.example.error.BussinessException;
import org.example.error.EmBussinessError;
import org.example.response.CommonReturnType;
import org.example.user.service.UserService;
import org.example.user.service.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true",allowedHeaders="*")
public class UserController extends BaseController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @ResponseBody
    @RequestMapping("/get")
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BussinessException{

        UserModel userModel = userService.getUserById(id);

       if(userModel == null){
           logger.error("用户不存在");
           throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
       }
        UserVo userVo = convertFromUserModel(userModel);
        return CommonReturnType.create(userVo);
    }


    @RequestMapping(value = "/getotp",method = RequestMethod.POST,consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone){

        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;

        String otpCode = String.valueOf(randomInt);
        this.httpServletRequest.getSession().setAttribute(telphone,otpCode);

        System.out.println("telphone : " + telphone + ":" + "otpCode : " + otpCode);

        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone")String telphone,@RequestParam(name = "otpCode")String optCode,
                                     @RequestParam(name = "name")String name,@RequestParam(name = "gender")Integer gender,
                                     @RequestParam(name = "age")Integer age,@RequestParam(name = "password")String password)
            throws BussinessException, NoSuchAlgorithmException {
    //验证拿到的手机号和验证码是否正确
       String inSesssionOptCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(optCode,inSesssionOptCode)){
            logger.error("用户"+telphone+"短信验证不合法");
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"短信验证不合法");
        }

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setAge(age);
        userModel.setGender(gender.byteValue());
        userModel.setEncrptPassword(CommonUtils.EncodeByMD5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }


    private UserVo convertFromUserModel(UserModel userModel){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam (name = "telphone")String telphone,@RequestParam(name = "password")String password)
            throws BussinessException {
        if(StringUtils.isEmpty(telphone)){
            logger.error("用户"+telphone+"不合法");
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserModel userModel = new UserModel();
        userModel.setEncrptPassword(password);
        userModel.setTelphone(telphone);
        userModel=userService.validationLogin(userModel);

        //将登陆凭证加入到Session
        this.httpServletRequest.getSession().setAttribute("IS_LOGININ",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }


}
