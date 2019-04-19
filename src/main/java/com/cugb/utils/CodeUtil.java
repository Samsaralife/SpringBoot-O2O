package com.cugb.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断验证码是否正确
 * @author wangyingbo
 *
 */
public class CodeUtil {

	public static boolean checkVerifyCode(HttpServletRequest request)
	{
		String verifyCodeException = (String) request.getSession().getAttribute("verifyCode");
		String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeException))
		{
			return false;
		}
		return true;
	}
}
