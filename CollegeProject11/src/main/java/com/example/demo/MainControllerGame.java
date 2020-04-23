package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainControllerGame {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public ModelAndView FirstPage()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index.jsp");
		return mv;

	}


	@RequestMapping("/login")
	public ModelAndView FormLogin(HttpServletRequest request)
	{
		System.out.println(request.getParameter("a1"));
		System.out.println(request.getParameter("a2"));
		System.out.println(request.getParameter("a3"));
		if(request.getParameter("a3").equals("CompanyAdmin"))
		{
			System.out.println("HI ");
			System.out.println(request.getParameter("a1"));
			CompanyBean cm1=jdbcTemplate.query("SELECT * FROM COMPANY WHERE COMPANYNAME='"+request.getParameter("a1")+"';",new ResultSetExtractor<CompanyBean>(){  
				@Override  
				public CompanyBean extractData(ResultSet rs) throws SQLException,  
				DataAccessException {  
					CompanyBean cm=new CompanyBean();


					while(rs.next()){  

						cm.setCompanyid(rs.getInt(1));
						cm.setCompanyname(rs.getString(2)); 
						cm.setCompanypassword(rs.getString(3));

					}  
					return cm;  
				}  
			}); 
			if(cm1.getCompanyname()==null)
			{
				ModelAndView mv=new ModelAndView();

				mv.setViewName("index.jsp");
				mv.addObject("Error","UserID and Password are wrong");
				return mv;
				
			}
			if(cm1.getCompanypassword().equals(request.getParameter("a2")))
			{
				ModelAndView mv=new ModelAndView();

				HttpSession session=request.getSession();
				session.setAttribute("companyId", cm1.getCompanyid());
				session.setAttribute("companyName", cm1.getCompanyname());

				//
				System.out.println(new java.sql.Date(new java.util.Date().getTime()));
				List<Games> gms1=jdbcTemplate.query("SELECT * FROM GAME WHERE GAMEDATE>'"+new java.sql.Date(new java.util.Date().getTime())+"';",new ResultSetExtractor<List<Games>>(){  
					@Override  
					public List<Games> extractData(ResultSet rs) throws SQLException,  
					DataAccessException {  
						List<Games> gms=new ArrayList<Games>();


						while(rs.next()){  
							Games gm=new Games();
							gm.setGameid(rs.getInt(1));
							gm.setGamename(rs.getString(2));
							gm.setGamedate(rs.getDate(3));
							gms.add(gm);

						}  
						return gms;  
					}  
				});
				if(gms1.size()!=0)
					mv.addObject("gameList", gms1);
				//
				mv.setViewName("CompanyFirst.jsp");
				return mv;

			}else
			{
				ModelAndView mv=new ModelAndView();

				mv.setViewName("index.jsp");
				mv.addObject("Error","UserID and Password are wrong");
				return mv;
			}

		}
		else
		{
			System.out.println("HI ");
			System.out.println(request.getParameter("a1"));
			AdminBean ad1=jdbcTemplate.query("SELECT * FROM ADMINUSER WHERE ADMINUSERNAME='"+request.getParameter("a1")+"';",new ResultSetExtractor<AdminBean>(){  
				@Override  
				public AdminBean extractData(ResultSet rs) throws SQLException,  
				DataAccessException {  
					AdminBean ad=new AdminBean();


					while(rs.next()){  

						ad.setAdminuserid(rs.getInt(1));
						ad.setAdminusername(rs.getString(2));
						ad.setAdminuserpassword(rs.getString(3));

					}  
					return ad;  
				}  
			}); 
			System.out.println(ad1);
			if(ad1.getAdminusername()==null)
			{
				ModelAndView mv=new ModelAndView();

				mv.setViewName("index.jsp");
				mv.addObject("Error","UserID and Password are wrong");
				return mv;
				
			}
			if(ad1.getAdminuserpassword().equals(request.getParameter("a2")))
			{
				ModelAndView mv=new ModelAndView();

				HttpSession session=request.getSession();
				session.setAttribute("adminId", ad1.getAdminuserid());
				session.setAttribute("adminname", ad1.getAdminusername());
				mv.setViewName("Adminfirst.jsp");
				List<Games> gms1=jdbcTemplate.query("SELECT * FROM GAME WHERE GAMEDATE>='"+new java.sql.Date(new java.util.Date().getTime())+"';",new ResultSetExtractor<List<Games>>(){  
					@Override  
					public List<Games> extractData(ResultSet rs) throws SQLException,  
					DataAccessException {  
						List<Games> gms=new ArrayList<Games>();


						while(rs.next()){  
							Games gm=new Games();
							gm.setGameid(rs.getInt(1));
							gm.setGamename(rs.getString(2));
							gm.setGamedate(rs.getDate(3));
							gms.add(gm);

						}  
						return gms;  
					}  
				});
				if(gms1.size()!=0)
					mv.addObject("gameList", gms1);
				return mv;
			}else
			{
				ModelAndView mv=new ModelAndView();
				mv.setViewName("index.jsp");
				mv.addObject("Error","UserID and Password are wrong");
				return mv;
			}
		}
	}
	@RequestMapping("/createUser")
	public ModelAndView createUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		try
		{
			int updatestatus=jdbcTemplate.update("INSERT INTO USERDETAILS(USERNAME,USERFULLNAME,COMPANYID) VALUES('"+request.getParameter("mailId")+"','"+request.getParameter("userName")+"','"+session.getAttribute("companyId")+"');");
			ModelAndView mv=new ModelAndView();
			mv.setViewName("createUser.jsp");
			if(updatestatus!=0)
				mv.addObject("message", "user has been successfully created");

			return mv;
		}
		catch(Exception e)
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("createUser.jsp");
			mv.addObject("message", "user already exists");
			return mv;
		}

	}


	@RequestMapping("/createCompany")
	public ModelAndView createCompany(HttpServletRequest request)
	{
		if(request.getParameter("CompanyPassword").equals(request.getParameter("CompanyPassword1")))
		{
			try
			{

				int updatestatus=jdbcTemplate.update("INSERT INTO COMPANY(COMPANYNAME,COMPANYPASSWORD) VALUES('"+request.getParameter("CompanyName")+"','"+request.getParameter("CompanyPassword")+"');");
				ModelAndView mv=new ModelAndView();
				mv.setViewName("createCompany.jsp");
				if(updatestatus!=0)
					mv.addObject("message", "Company has been successfully created");

				return mv;
			}
			catch(Exception e)
			{
				ModelAndView mv=new ModelAndView();
				mv.setViewName("createCompany.jsp");
				mv.addObject("message", "Company already exists");
				return mv;
			}

		}
		else
		{
			ModelAndView mv=new ModelAndView();
			mv.setViewName("createCompany.jsp");
			mv.addObject("message", "Password does not match");
			return mv;
		}

	}
	@RequestMapping("/newgame")
	public ModelAndView createNewGame(HttpServletRequest request)
	{
		System.out.println(request.getParameter("gamename"));
		System.out.println(request.getParameter("gamedate"));
		jdbcTemplate.update("INSERT INTO GAME(GAMENAME,GAMEDATE) VALUES('"+request.getParameter("gamename")+"','"+request.getParameter("gamedate")+"');");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Adminfirst.jsp");
		List<Games> gms1=jdbcTemplate.query("SELECT * FROM GAME WHERE GAMEDATE>='"+new java.sql.Date(new java.util.Date().getTime())+"';",new ResultSetExtractor<List<Games>>(){  
			@Override  
			public List<Games> extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				List<Games> gms=new ArrayList<Games>();


				while(rs.next()){  
					Games gm=new Games();
					gm.setGameid(rs.getInt(1));
					gm.setGamename(rs.getString(2));
					gm.setGamedate(rs.getDate(3));
					gms.add(gm);

				}  
				return gms;  
			}  
		});
		if(gms1.size()!=0)
			mv.addObject("gameList", gms1);
		return mv;
	}

	@RequestMapping("/playGame")
	public ModelAndView playGame(HttpServletRequest request)
	{
		int gameid=Integer.parseInt(request.getParameter("a1"));
		HttpSession session=request.getSession();
		Games gm1=jdbcTemplate.query("SELECT * FROM GAME WHERE GAMEID="+gameid+";",new ResultSetExtractor<Games>(){  
			@Override  
			public Games extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				Games gm=new Games();


				while(rs.next()){  

					gm.setGameid(rs.getInt(1));
					gm.setGamename(rs.getString(2));
					gm.setGamedate(rs.getDate(3));


				}  
				return gm;  
			}  
		});

		List<UserBean> users=jdbcTemplate.query("SELECT * FROM  USERDETAILS  WHERE COMPANYID="+session.getAttribute("companyId")+";",new ResultSetExtractor<List<UserBean>>(){  
			@Override  
			public List<UserBean> extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				List<UserBean> users=new ArrayList<UserBean>();


				while(rs.next()){
					UserBean ub=new UserBean();
					ub.setUserid(rs.getInt(1));
					ub.setUsername(rs.getString(2));
					ub.setUserfullname(rs.getString(3));
					ub.setCompanyid(rs.getInt(4));
					users.add(ub);




				}  
				return users;  
			}  
		});



		ModelAndView mv=new ModelAndView();
		mv.addObject("gameDeatils", gm1);
		mv.addObject("userlist", users);
		mv.setViewName("playGame.jsp");
		return mv;

	}

	@RequestMapping("/viewGame")
	public ModelAndView viewGame(HttpServletRequest request)
	{
		int gameid=Integer.parseInt(request.getParameter("a1"));
		//HttpSession session=request.getSession();
		List<ViewBean> viewBeans=jdbcTemplate.query("SELECT BUTTONID,COUNT(DISTINCT USERID) FROM  GAME_DETAILS WHERE GAMEID='"+gameid+"' GROUP BY BUTTONID;",new ResultSetExtractor<List<ViewBean>>(){  
			@Override  
			public List<ViewBean> extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				List<ViewBean> viewBeans=new ArrayList<ViewBean>();


				while(rs.next()){  

					ViewBean vb=new ViewBean();
					vb.setButtuonid(rs.getInt(1));
					vb.setTotaluser(rs.getInt(2));
					viewBeans.add(vb);


				}  
				return viewBeans;  
			}  
		});

		Games gm1=jdbcTemplate.query("SELECT * FROM GAME WHERE GAMEID="+gameid+";",new ResultSetExtractor<Games>(){  
			@Override  
			public Games extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				Games gm=new Games();


				while(rs.next()){  

					gm.setGameid(rs.getInt(1));
					gm.setGamename(rs.getString(2));
					gm.setGamedate(rs.getDate(3));


				}  
				return gm;  
			}  
		});



		ModelAndView mv=new ModelAndView();
		mv.addObject("gameDeatils", gm1);
		mv.addObject("viewBeans", viewBeans);
		mv.setViewName("viewGame.jsp");
		return mv;

	}

	@ResponseBody
	@RequestMapping("/setGameDetails")
	public String setGameDetails(HttpServletRequest request)
	{
		//System.out.println(request.getParameter("gameid"));
		//System.out.println(request.getParameter("personid"));
		//System.out.println(request.getParameter("buttonid"));
		jdbcTemplate.update("INSERT INTO GAME_DETAILS(GAMEID,USERID,BUTTONID) VALUES('"+request.getParameter("gameid")+"','"+request.getParameter("personid")+"','"+request.getParameter("buttonid")+"');");
		return "yes";
	}

	@ResponseBody
	@RequestMapping("/getGameDetails")
	public List<Integer> getGameDetails(HttpServletRequest request)
	{
		System.out.println(request.getParameter("gameid"));
		System.out.println(request.getParameter("personid"));
		List<Integer> buttons=jdbcTemplate.query("SELECT BUTTONID FROM  GAME_DETAILS  WHERE GAMEID="+request.getParameter("gameid")+" and userid="+request.getParameter("personid")+";",new ResultSetExtractor<List<Integer>>(){  
			@Override  
			public List<Integer> extractData(ResultSet rs) throws SQLException,  
			DataAccessException {  
				List<Integer> buttons=new ArrayList<Integer>();


				while(rs.next()){
					buttons.add(rs.getInt(1));




				}  
				return buttons;  
			}  
		});
		return buttons;
	}

}
