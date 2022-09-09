package com.floatinity.toolIt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.floatinity.toolIt.constants.ToolItURLConstants;
import com.floatinity.toolIt.constants.ToolItViewsConstants;
import com.floatinity.toolIt.exceptions.ToolItException;
import com.floatinity.toolIt.response.BaseResponse;
import com.floatinity.toolIt.response.UserListResponse;
import com.floatinity.toolIt.service.IUserService;


@Controller
@RequestMapping(value = ToolItURLConstants.USER)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    
//    @RequestMapping(value = ToolItURLConstants.LIST, method = RequestMethod.GET)
//    public String userListGet() {
//    	LOGGER.entry();
//        return ToolItViewsConstants.DASHBOARD;
//    }

//    @RequestMapping(value = ToolItURLConstants.SEARCH, method = RequestMethod.POST)
//    @ResponseBody
//    public UserListResponse getUserList(@RequestBody UserSearchRequest request)
//            throws ToolItException {
//    	LOGGER.entry();
//        return userService.getUserList(request);
//    }
//    
//    @RequestMapping(value = ToolItURLConstants.GET_IMPORT_COLUMNS, method = RequestMethod.POST)
//    public @ResponseBody ImportColumnsResponse getImportColumns(@RequestBody String importColumns)
//            throws ToolItException {
//        return userService.getImportColumns();
//    }
    
    @RequestMapping(value = "/all/get", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse getAllUsers() throws ToolItException {
		BaseResponse reponse = new BaseResponse();
		reponse.setData(userService.getAllUsers());
		return reponse;
	}
  
}
