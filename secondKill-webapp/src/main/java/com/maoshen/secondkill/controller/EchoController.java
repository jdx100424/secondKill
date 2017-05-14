package com.maoshen.secondkill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.component.base.dto.ResponseResultDto;
import com.maoshen.component.base.errorcode.BaseErrorCode;
import com.maoshen.component.controller.BaseController;
import com.maoshen.component.exception.BaseException;
import com.maoshen.component.rest.UserRestContext;
import com.maoshen.secondkill.request.RedPackageRequest;
import com.maoshen.secondkill.service.RedPackageService;
import com.maoshen.secondkill.service.vo.RedPackageUserDto;

/**
 * 
 * @Description:
 * @author Daxian.jiang
 * @Email Daxian.jiang@vipshop.com
 * @Date 2015年7月14日 上午11:28:11
 * @Version V1.0
 */
@Controller
@RequestMapping("/redPackage")
public class EchoController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoController.class);

	/**
	 * 
	 * @param request
	 * @param model
	 * @param src
	 * @return
	 * @throws BaseException 
	 */
	@RequestMapping(value = "echo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> echo(HttpServletRequest request, Model model, @RequestBody RedPackageRequest redPackageRequest) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("uuid", UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}

	@Override
	public String getServiceName() {
		return "EchoController";
	}
	
}
