package com.main;

import com.bean.Test001RQBody;
import com.bean.Test001RSBody;
import com.dao.NameDAO;
import com.dao.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.vo.UserVO;

@Path("/api/user")
public class User {
	
	@POST
	@Consumes("application/json;charset=utf-8")
	@Produces("application/json;charset=utf-8")
    public Response add(String info) throws Exception {
		
		//取得request的值
		Test001RQBody test001rq = new Gson().fromJson(info, Test001RQBody.class);
		String username = test001rq.getUserName();  			
		String pwd = test001rq.getPwd();
		String firstkeyname = test001rq.getFirstkeyname();
		int count = 0;
		
//		這邊會有firstkeyname的設計用意是
//		當使用者註冊test這個名稱時，會先localstorage暫存這個值。
//		接者著到後台比對後如果已有此名稱存在會回傳 test2 這個建議名稱並且在前端自動改變名稱。
//		此時的暫存值還是 test ,如果這時使用者直接打完密碼註冊，
//		則會統計test 這個名稱的註冊次數，下次再用test註冊的使用者則會被建議 test3。
		
//		這樣的機制是以防，要是有使用者本來就想用 test2 這個名稱註冊，雖然有重複但他本意不是想註冊 test 這個名稱。
//		這時就會回傳建議的名稱是 test22。
		
		//new出 dao
		UserDAO userdao = new UserDAO();
		NameDAO namedao = new NameDAO();
		
		Test001RSBody test001rs = new Test001RSBody();
		
		//查詢此名稱使用的次數
		int usetime = namedao.getUsetimeOfName(firstkeyname);
		if(usetime > 0) {
			count = usetime+1;
		}else {
			count = 1;
		}
		
		//查詢是否有同樣名稱
		boolean isRepeat = userdao.hasSameUsername(username);
		
		//若有重複則回傳有自動添加字尾的建議名稱
		if(isRepeat) {
			String strCount = String.valueOf(count);
			String suggestName = firstkeyname.concat(strCount);
			
			test001rs.setSuggestName(suggestName);
			test001rs.setRetrunCode("1111");
			test001rs.setReturnMsg("註冊失敗，已有相同使用者名稱");
			return Response.status(Status.OK).entity(new Gson().toJson(test001rs)).build();
			
		//若無則進入新增流程
		} else {
			boolean insertSussess = userdao.insertNewUser(username, pwd);
			
			//成功新增後會在名稱次數管理資料table新增一筆資料或是更新
			if(insertSussess) {
				namedao.insertNewName(username);
				
				if(count != 1) {
					namedao.updateTimes(firstkeyname, count);
				}
						
				//塞值進response
				test001rs.setUserId(username);
				test001rs.setUserName(username);
				test001rs.setRetrunCode("0000");
				test001rs.setReturnMsg("註冊成功");
			} else {
				test001rs.setRetrunCode("1112");
				test001rs.setReturnMsg("新增異常");
			}
				
		}
	
		
		return Response.status(Status.OK).entity(new Gson().toJson(test001rs)).build();
    }
	
	
}
