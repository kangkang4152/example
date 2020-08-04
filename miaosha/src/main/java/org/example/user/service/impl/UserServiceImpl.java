package org.example.user.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.common.CommonUtils;
import org.example.user.dao.UserDOMapper;
import org.example.user.dao.UserPassWordDOMapper;
import org.example.user.dataobject.UserDO;
import org.example.user.dataobject.UserPassWordDO;
import org.example.error.BussinessException;
import org.example.error.EmBussinessError;
import org.example.user.service.UserService;
import org.example.user.service.model.UserModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPassWordDOMapper userPassWordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null){
            logger.error("id为"+id+"对应的用户不存在");
            return null;
        }
        UserPassWordDO userPassWordDO = userPassWordDOMapper.selectByUserId(userDO.getId());
        return convertFormDataObject(userDO,userPassWordDO);
    }


    private UserModel convertFormDataObject(UserDO userDO, UserPassWordDO userPassWordDO){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        userModel.setEncrptPassword(userPassWordDO.getEncrptPassword());
        return userModel;
    }

    @Transactional
    @Override
    public void register(UserModel userModel) throws BussinessException{
        if(userModel == null){
            logger.error("注册用户参数不合法");
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }
//        if(StringUtils.isEmpty(userModel.getName()) || userModel.getAge() == null || userModel.getGender() == null
//        || StringUtils.isEmpty(userModel.getTelphone())){
//            logger.error("注册用户参数不合法");
//            throw  new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
//        }

        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            logger.error("注册用户参数不合法："+result.getErrMsg());
            throw  new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        UserDO userDo = this.convertDoFormModel(userModel);
        try{
            userDOMapper.insertSelective(userDo);
        }catch (DuplicateKeyException ex){
            logger.error("手机号:"+userModel.getTelphone()+"已注册");
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"手机号已注册");
        }
        userModel.setId(userDo.getId());
        UserPassWordDO userPassWordDO = this.convertPassWordDoFormModel(userModel);
        userPassWordDOMapper.insertSelective(userPassWordDO);

    }

    @Override
    public UserModel validationLogin(UserModel userModel) throws BussinessException {

        if(userModel == null){
            logger.error("用户验证不通过");
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL);
        }

        UserDO userDO = userDOMapper.selectByTelphone(userModel.getTelphone());
        if(userDO == null){
            logger.error("用户"+userModel.getTelphone()+"未注册");
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL,"用户未注册");
        }
        UserPassWordDO userPassWordDO = userPassWordDOMapper.selectByUserId(userDO.getId());
        String enpassword = null;
        try {
            enpassword = CommonUtils.EncodeByMD5(userModel.getEncrptPassword());
        } catch (NoSuchAlgorithmException e) {
            logger.error("用户"+userModel.getTelphone()+"密码错误");
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL,"密码错误");
        }
        if(!StringUtils.equals(enpassword,userPassWordDO.getEncrptPassword())){
            logger.error("用户"+userModel.getTelphone()+"密码错误");
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL,"密码错误");
        }
        return convertFormDataObject(userDO,userPassWordDO);
    }

    private UserDO convertDoFormModel(UserModel userModel){
        if(userModel == null) return null;

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserPassWordDO convertPassWordDoFormModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserPassWordDO userPassWordDO = new UserPassWordDO();
        userPassWordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPassWordDO.setUserId(userModel.getId());
        return userPassWordDO;
    }
}
